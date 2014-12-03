/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.models;

import ca.weblite.oscon2014.schedulebuilder.util.HTMLEntityDecoder;
import com.codename1.io.Log;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.xml.XMLParser;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 *
 * @author shannah
 */
public class Schedule {
    private List<Event> events;
    private List<Speaker> speakers;
    private List<Venue> venues;

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
        
    }
    
    public void decodeHtmlEntities(){
        
        if ( events != null ){
            for ( Event e : events ){
                e.setName(HTMLEntityDecoder.unescapeHtml3(e.getName()));
                e.setDescription(HTMLEntityDecoder.unescapeHtml3(e.getDescription()));
                if ( e.getCategories() != null ){
                    int len = e.getCategories().size();
                    for ( int i=0; i<len; i++){
                        e.getCategories().set(i, HTMLEntityDecoder.unescapeHtml3(e.getCategories().get(i)));
                    }
                }
            }
        }
            
    }
    
    
    /**
     * @return the speakers
     */
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    /**
     * @param speakers the speakers to set
     */
    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    /**
     * @return the venues
     */
    public List<Venue> getVenues() {
        return venues;
    }

    /**
     * @param venues the venues to set
     */
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
    
    public Speaker getSpeaker(String serial){
        for ( Speaker speaker : getSpeakers()){
            if ( serial.equals(speaker.getSerial())){
                return speaker;
            }
        }
        return null;
    }
    
    public Venue getVenue(String serial){
        for ( Venue venue : getVenues()){
            if ( serial.equals(venue.getSerial())){
                return venue;
            }
        }
        return null;
    }
    
    public Date[] getDateRange(){
        Set<String> dateSignatures = new HashSet<String>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> out = new ArrayList<Date>();
        for ( Event evt : getEvents()){
            if ( evt.getTimeStart() == null ){
                continue;
            }
            String sig = fmt.format(evt.getTimeStart());
            if ( !dateSignatures.contains(sig)){
                dateSignatures.add(sig);
                out.add(evt.getTimeStart());
            }
        }
        Collections.sort(out, new Comparator<Date>(){

            public int compare(Date o1, Date o2) {
                return (int)(o1.getTime()-o2.getTime());
            }
            
        });
        return out.toArray(new Date[0]);
    }
    
    public List<Event> getEventsOnDate(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateString = fmt.format(date);
        SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date startRange = fmt2.parse(dateString+" 00:00");
            Date endRange = fmt2.parse(dateString+" 23:59");
            long startRangeTime = startRange.getTime();
            long endRangeTime = endRange.getTime();
            
            List<Event> out = new ArrayList<Event>();
            for ( Event evt : getEvents()){
                long timeStart = evt.getTimeStart().getTime();
                if ( timeStart < endRangeTime && timeStart >= startRangeTime){
                    out.add(evt);
                }
            }
            Collections.sort(out, new Comparator<Event>(){

                public int compare(Event o1, Event o2) {
                    return (int)(o1.getTimeStart().getTime() - o2.getTimeStart().getTime());
                }
                
            });
            return out;
        } catch (ParseException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public List<String> getCategories(){
        Set<String> out = new HashSet<String>();
        for ( Event evt : getEvents()){
            if ( evt.getCategories() != null ){
                out.addAll(evt.getCategories());
            }
        }
        return new ArrayList<String>(out);
    }
}
