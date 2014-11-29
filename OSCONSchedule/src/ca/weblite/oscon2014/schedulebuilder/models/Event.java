/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.models;

import com.codename1.ui.List;
import java.util.Date;

/**
 *
 * @author shannah
 */
public class Event {
    private String serial;
    private String name;
    private String eventType;
    private Venue venue;
    private String description;
    private String websiteUrl;
    private Date timeStart;
    private Date timeStop;
    
    private List<Speaker> speakers;
    private List<String> categories;
    
}
