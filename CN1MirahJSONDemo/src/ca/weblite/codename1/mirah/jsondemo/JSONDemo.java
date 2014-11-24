package ca.weblite.codename1.mirah.jsondemo;


import com.codename1.io.Log;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

import userclasses.StateMachine;

public class JSONDemo {
   
    private Form current;

    public void init(Object context) {
        // Pro users - uncomment this code to get crash reports sent to you automatically
        /*Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource());
                Log.sendLog();
            }
        });*/
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new StateMachine("/theme");  
        CalendarMapper calendarMapper = new CalendarMapper();
        EventMapper eventMapper = new EventMapper();
        calendarMapper.register(Calendar.Event.class, eventMapper);
        eventMapper.setFieldMapper("start", "/start/dateTime");
        eventMapper.setFieldMapper("end", "/end/dateTime");
        try {
            Calendar calendar = calendarMapper.readJSONFromURL("https://www.googleapis.com/calendar/v3/calendars/weblite.ca_5778lgg76mo76r01osl63o9hbs@group.calendar.google.com/events?key=AIzaSyBzpCgeAgkMDYSZKSfpuosxt5iS0ON353E", Calendar.class);
            Form form = new Form(calendar.getSummary());
            form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            for ( Calendar.Event evt : calendar.getItems()){
                form.addComponent(new Label(evt.getSummary()));
            }
            form.show();
        } catch (IOException ex) {
            Log.e(ex);
        }
        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
