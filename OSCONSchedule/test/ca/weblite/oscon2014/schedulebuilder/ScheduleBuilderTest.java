/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder;

import ca.weblite.codename1.mapper.DataMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Event;
import ca.weblite.oscon2014.schedulebuilder.models.EventMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Schedule;
import ca.weblite.oscon2014.schedulebuilder.models.ScheduleMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Speaker;
import ca.weblite.oscon2014.schedulebuilder.models.SpeakerMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Venue;
import ca.weblite.oscon2014.schedulebuilder.models.VenueMapper;
import com.codename1.testing.AbstractTest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author shannah
 */
public class ScheduleBuilderTest extends AbstractTest{

    public boolean runTest() throws Exception {
        ScheduleMapper scheduleMapper = new ScheduleMapper();
        EventMapper eventMapper = new EventMapper();
        VenueMapper venueMapper = new VenueMapper();
        SpeakerMapper speakerMapper = new SpeakerMapper();
        
        Map<Class,DataMapper> context = new HashMap<Class,DataMapper>();
        
        context.put(Event.class, eventMapper);
        context.put(Venue.class, venueMapper);
        context.put(Speaker.class, speakerMapper);
        context.put(Schedule.class, scheduleMapper);
        for ( DataMapper mapper : new DataMapper[]{eventMapper, venueMapper, speakerMapper, scheduleMapper}){
            mapper.setContext(context);
        }
        
        eventMapper.setReadKeyConversions(Arrays.asList(DataMapper.CONVERSION_CAMEL_TO_SNAKE));
        speakerMapper.setReadKeyConversions(Arrays.asList(DataMapper.CONVERSION_CAMEL_TO_SNAKE));
        venueMapper.setReadKeyConversions(Arrays.asList(DataMapper.CONVERSION_CAMEL_TO_SNAKE));
        scheduleMapper.setReadKeyConversions(Arrays.asList(DataMapper.CONVERSION_CAMEL_TO_SNAKE));
        
        
        Map responseMap = scheduleMapper.readJSONFromURL("http://www.oreilly.com/pub/sc/osconfeed");
        //System.out.println("Response: "+responseMap);
        Schedule schedule = scheduleMapper.readMap((Map)responseMap.get("Schedule"), Schedule.class);
        System.out.println("Schedule is "+schedule);
        System.out.println("Events is "+schedule.getEvents());
        System.out.println("Num Events: "+schedule.getEvents().size());
        return true;
    }
    
    
}
