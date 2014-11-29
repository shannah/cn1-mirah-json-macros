/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.models;

import java.util.List;

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
}
