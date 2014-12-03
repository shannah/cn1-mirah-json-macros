/**
 * Your application code goes here
 */

package userclasses;

import ca.weblite.codename1.mapper.DataMapper;
import ca.weblite.oscon2014.schedulebuilder.formatters.EventFormatter;
import ca.weblite.oscon2014.schedulebuilder.models.Event;
import ca.weblite.oscon2014.schedulebuilder.models.EventMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Schedule;
import ca.weblite.oscon2014.schedulebuilder.models.ScheduleMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Speaker;
import ca.weblite.oscon2014.schedulebuilder.models.SpeakerMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Venue;
import ca.weblite.oscon2014.schedulebuilder.models.VenueMapper;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import generated.StateMachineBase;
import com.codename1.ui.*; 
import com.codename1.ui.events.*;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {
    Schedule schedule;
    Event selectedEvent;
    int lastSelectedTabIndex = 0;
    int lastSelectedRowIndex = 0;
    int lastScrollY = 0;
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }
    
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    protected void initVars(Resources res) {
        ScheduleMapper scheduleMapper = new ScheduleMapper();
        DataMapper.createContext(Arrays.asList(
                scheduleMapper,
                new EventMapper(),
                new VenueMapper(),
                new SpeakerMapper()
            ), new DataMapper.Decorator() {

            public void decorate(DataMapper mapper) {
                mapper.setReadKeyConversions(Arrays.asList(DataMapper.CONVERSION_CAMEL_TO_SNAKE));
            }
        });
        
        
        
        try {
            schedule = scheduleMapper.readJSONFromURL("http://www.oreilly.com/pub/sc/osconfeed", Schedule.class, "/Schedule");
            
        } catch (IOException ex) {
            Log.e(ex);
        }
    }


    @Override
    protected boolean initListModelMultiList(List cmp) {
        /*
        ArrayList<Map> items = new ArrayList<Map>();
        EventFormatter formatter = new EventFormatter();
        for ( Event event : schedule.getEvents()){
            Map m = new HashMap();
            m.put("Line1", event.getName());
            m.put("Line2", formatter.formatStartAndStop(event));
            m.put("event", event);
            items.add(m);
        }
        cmp.setModel(new com.codename1.ui.list.DefaultListModel(items));
        */
        return true;
    }

    @Override
    protected void onMain_MultiListAction(Component c, ActionEvent event) {
        Map selectedMap = (Map)((List)c).getSelectedItem();
        selectedEvent = (Event)selectedMap.get("event");
        this.showForm("EventDetails", null);
        lastSelectedTabIndex = findDateTabs(c).getSelectedIndex();
        lastSelectedRowIndex = ((MultiList)c).getSelectedIndex();
        lastScrollY = ((MultiList)c).getScrollY();
        
    }

    @Override
    protected void beforeEventDetails(Form f) {
        EventFormatter fmt = new EventFormatter();
        Event e = selectedEvent;
        findEventName(f).setText(e.getName());
        findDateTime(f).setText(fmt.formatDateAndTime(e));
        Speaker speaker = new Speaker();
        speaker.setName("Speaker TBA");
        speaker.setAffiliation("");
        if ( e.getSpeakers() != null && e.getSpeakers().size()>0 ){
            speaker = schedule.getSpeaker(e.getSpeakers().get(0));
            
        } 
        findSpeaker(f).setText(speaker.getName());
        findAffiliation(f).setText(speaker.getAffiliation());
        Venue venue = new Venue();
        venue.setName("Location TBA");
        
        if ( e.getVenueSerial() != null ){
            venue = schedule.getVenue(e.getVenueSerial());
        }
        findRoom(f).setText(venue.getName());
        
        findDescription(f).setPage(e.getDescription(), null);
    }

    @Override
    protected void beforeMain(Form f) {
        
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd");
        for ( Date d : schedule.getDateRange()){
            final MultiList cmp = new MultiList();
            cmp.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent evt) {
                    onMain_MultiListAction(cmp, evt);
                }
                
            });
            ArrayList<Map> items = new ArrayList<Map>();
            EventFormatter formatter = new EventFormatter();
            for ( Event event : schedule.getEventsOnDate(d)){
                Map m = new HashMap();
                m.put("Line1", event.getName());
                m.put("Line2", formatter.formatStartAndStop(event));
                m.put("event", event);
                items.add(m);
            }
            cmp.setModel(new com.codename1.ui.list.DefaultListModel(items));
            
            this.findDateTabs(f).addTab(fmt.format(d), cmp);
        }
        this.findDateTabs(f).removeTabAt(0);
        
       
        
    }

    @Override
    protected void postMain(Form f) {
         MultiList selectedList = (MultiList)this.findDateTabs(f).getTabComponentAt(lastSelectedTabIndex);
        this.findDateTabs(f).setSelectedIndex(lastSelectedTabIndex);
        Log.p("Last scroll Y is "+lastScrollY);
        selectedList.setSelectedIndex(lastSelectedRowIndex, true);
        //selectedList.scrollRectToVisible(new Rectangle(0, lastScrollY, 10,10));
    }
}
