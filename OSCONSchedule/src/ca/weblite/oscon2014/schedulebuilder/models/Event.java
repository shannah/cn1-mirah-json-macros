/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author shannah
 */
public class Event {
    private String serial;
    private String name;
    private String eventType;
    private String venueSerial;
    private String description;
    private String websiteUrl;
    private Date timeStart;
    private Date timeStop;
    
    private List<String> speakers;
    private List<Speaker> cachedSpeakers;
    private List<String> categories;

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

  
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the websiteUrl
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * @param websiteUrl the websiteUrl to set
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * @return the timeStart
     */
    public Date getTimeStart() {
        return timeStart;
    }

    /**
     * @param timeStart the timeStart to set
     */
    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * @return the timeStop
     */
    public Date getTimeStop() {
        return timeStop;
    }

    /**
     * @param timeStop the timeStop to set
     */
    public void setTimeStop(Date timeStop) {
        this.timeStop = timeStop;
    }

    /**
     * @return the speakers
     */
    public List<String> getSpeakers() {
        return speakers;
    }

    /**
     * @param speakers the speakers to set
     */
    public void setSpeakers(List<String> speakers) {
        this.speakers = speakers;
    }

    /**
     * @return the categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return the venueSerial
     */
    public String getVenueSerial() {
        return venueSerial;
    }

    /**
     * @param venueSerial the venueSerial to set
     */
    public void setVenueSerial(String venueSerial) {
        this.venueSerial = venueSerial;
    }
    
    
    public List<Speaker> getSpeakers(Schedule schedule){
        if ( cachedSpeakers == null ){
            cachedSpeakers = new ArrayList<Speaker>();
            if ( speakers != null ){
                for ( String speakerId : speakers ){
                    Speaker speaker = schedule.getSpeaker(speakerId);
                    if ( speaker != null ){
                        cachedSpeakers.add(speaker);
                    }
                }
            }
        }
        return cachedSpeakers;
        
    }
    
    
}
