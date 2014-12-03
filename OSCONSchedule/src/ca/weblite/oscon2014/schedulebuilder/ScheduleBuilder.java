package ca.weblite.oscon2014.schedulebuilder;


import ca.weblite.codename1.mapper.DataMapper;
import ca.weblite.oscon2014.schedulebuilder.models.EventMapper;
import ca.weblite.oscon2014.schedulebuilder.models.Schedule;
import ca.weblite.oscon2014.schedulebuilder.models.ScheduleMapper;
import ca.weblite.oscon2014.schedulebuilder.models.SpeakerMapper;
import ca.weblite.oscon2014.schedulebuilder.models.VenueMapper;
import ca.weblite.oscon2014.schedulebuilder.views.ScheduleList;
import ca.weblite.oscon2014.schedulebuilder.views.SplashScreen;
import com.codename1.io.Log;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Arrays;



import userclasses.StateMachine;

public class ScheduleBuilder {
   
    Resources theme;
    private Form current;
    private ScheduleList scheduleList;
    private Schedule schedule;
    private Object LOCK = new Object();
    private boolean doneLoading = false;
    public void init(Object context) {
        try {
            theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
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
        
        Image img = theme.getImage("calendar52 (1).png");
        Log.p("Image "+img.getHeight());
        SplashScreen screen = new SplashScreen(img, img);
        screen.show();
        final long time = System.currentTimeMillis();
        doneLoading = false;
        
        
        
        Display.getInstance().scheduleBackgroundTask(new Runnable(){
            
            public void run() {
                try {
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
                    schedule = scheduleMapper.readJSONFromURL("http://www.oreilly.com/pub/sc/osconfeed", Schedule.class, "/Schedule");
                    schedule.decodeHtmlEntities();
                    synchronized(LOCK){
                        doneLoading = true;
                        LOCK.notifyAll();
                    }
                } catch (IOException ex) {
                    Log.e(ex);
                }
            }
            
        });
        
        Display.getInstance().invokeAndBlock(new Runnable(){

            public void run() {
                
                while ( !doneLoading ){
                    synchronized(LOCK){
                        try {
                            LOCK.wait();
                        } catch (InterruptedException ex) {
                            Log.e(ex);
                        }
                    }
                }
            }
            
        });
        scheduleList = new ScheduleList(schedule);
        current = scheduleList.getListView();
        if ( System.currentTimeMillis() - time < 2000){
            Display.getInstance().invokeAndBlock(new Runnable(){

                public void run() {
                    try {
                        Thread.sleep((int)(1000));
                    } catch (InterruptedException ex) {
                        Log.e(ex);
                    }
                }
                
            });
        }
        current.show();               
        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
