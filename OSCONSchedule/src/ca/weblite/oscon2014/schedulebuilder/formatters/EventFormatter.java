/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.formatters;

import ca.weblite.oscon2014.schedulebuilder.models.Event;
import ca.weblite.oscon2014.schedulebuilder.models.Schedule;
import ca.weblite.oscon2014.schedulebuilder.models.Speaker;
import ca.weblite.oscon2014.schedulebuilder.models.Venue;
import com.codename1.l10n.SimpleDateFormat;

/**
 *
 * @author shannah
 */
public class EventFormatter {
    private SimpleDateFormat timeFormat = new SimpleDateFormat("h:mmaa");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");
    public String formatStartAndStop(Event evt){
        return timeFormat.format(evt.getTimeStart())+"-"+timeFormat.format(evt.getTimeStop());
    }
    
    public String formatDateAndTime(Event evt){
        return dateFormat.format(evt.getTimeStart())+" "+timeFormat.format(evt.getTimeStart())+"-"+timeFormat.format(evt.getTimeStop());
    }
    
    public String formatSpeakers(Schedule schedule, Event evt){
        StringBuilder sb = new StringBuilder();
        for ( Speaker speaker : evt.getSpeakers(schedule)){
            sb.append("-").append(speaker.getName()).append(", ").append(speaker.getAffiliation());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public String formatVenue(Schedule schedule, Event evt){
        Venue v = new Venue();
        v.setName("Location TBA");
        if ( evt.getVenueSerial() != null ){
            Venue tmp = schedule.getVenue(evt.getVenueSerial());
            if ( tmp != null ){
                v = tmp;
            }
        }
        return v.getName();
    }
}
