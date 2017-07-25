package ca.weblite.cn1.mirah.json;


import ca.weblite.codename1.bean.BeanClass;
import ca.weblite.codename1.bean.BeanObject;
import ca.weblite.codename1.mapper.FieldMapper;
import ca.weblite.codename1.mapper.Mapper;
import ca.weblite.codename1.mapper.NumberUtil;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMirahJSON {

    private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
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
        Form hi = new Form("Hi World");
        hi.addComponent(new Label("Hi World"));
        hi.show();
        
        TestJSONClass cls = new TestJSONClass();
        Map m = new HashMap();
        
        m.put("foo", "Hello World");
        m.put("bar", 756);
        
        Map m2 = new HashMap();
        m2.put("foo", "GoodBye World");
        m2.put("bar", 100);
        m.put("child", m2);
        
        
        Mapper mapper = new Mapper();
        cls = mapper.readObject(m, TestJSONClass.class);
        
        System.out.println("Foo is "+cls.foo()+" bar is "+cls.bar()+" child is "+cls.childString());
        
        JSONDataMapper jmapper = new JSONDataMapper();
        cls = jmapper.readMap(m2, TestJSONClass.class);
        System.out.println("2nd run Foo is "+cls.foo()+" bar is "+cls.bar()+" child is "+cls.childString());
        
        cls = jmapper.readMap(m, TestJSONClass.class);
        System.out.println("3rd run Foo is "+cls.foo()+" bar is "+cls.bar()+" child is "+cls.childString());
        
        SinkTestDataMapper sinkMapper = new SinkTestDataMapper();
        
        Map sinkMap = new HashMap();
        sinkMap.put("intVal", 1);
        sinkMap.put("shortVal", 2);
        sinkMap.put("floatVal", 3f);
        sinkMap.put("doubleVal", 4.0);
        sinkMap.put("stringVal", "Some string");
        sinkMap.put("byteVal", 1);
        sinkMap.put("charVal", 'c');
        sinkMap.put("boxedIntVal", 1);
        sinkMap.put("boxedShortVal", 2);
        sinkMap.put("boxedFloatVal", 3f);
        sinkMap.put("boxedDoubleVal", 4.0);
        sinkMap.put("intArrayVal", new int[]{1,2,3});
        sinkMap.put("stringList", Arrays.asList("one", "two", "Three"));
        sinkMap.put("pubStringList", Arrays.asList("one", "two", "Three"));
        sinkMap.put("intList", Arrays.asList(1,2,3));
        sinkMap.put("floatList", Arrays.asList(1f, 2f, 3f));
        sinkMap.put("doubleList", Arrays.asList(1.0, 2.0, 3.0));
        sinkMap.put("longList", Arrays.asList(1l, 2l, 3l));
        sinkMap.put("publicInt", 2);
        
        Map pubStringMap = new HashMap<String,String>();
        pubStringMap.put("one", "oneval");
        pubStringMap.put("two", "twoval");
        sinkMap.put("pubStringMap", pubStringMap);
        
        Map<String,Integer> intMap = new HashMap<String,Integer>();
        intMap.put("One", 1);
        intMap.put("Two", 2);
        intMap.put("Three", 3);
        
        sinkMap.put("integerMap", intMap);
        
        
        //sinkMapper.setListValueType("pubStringList", String.class);
        sinkMapper.setListValueType("pubStringMap", String.class);
        
        SinkTestClass sc = sinkMapper.readMap(sinkMap, SinkTestClass.class);
        System.out.println(sc.toString());
        
        Map scOut = sinkMapper.writeMap(sc);
        System.out.println("ScOut is "+scOut);
        
        Integer[] test1 = new Integer[]{1,2,3};
        int[] results = NumberUtil.toIntArray(test1);
        
        System.out.println(Arrays.toString(results));
        
        
        sinkMap.put("intArrayVal", Arrays.asList(1,2,3));
        SinkTestClass sc2 = sinkMapper.readMap(sinkMap, SinkTestClass.class);
        System.out.println("Int array loaded "+Arrays.toString(sc2.getIntArrayVal()));
        System.out.println("String list loaded "+sc2.getStringList());
        System.out.println("Int list loaded "+sc2.getIntList());
        System.out.println("Float list loaded "+sc2.getFloatList());
        System.out.println("Double list loaded "+sc2.getDoubleList());
        System.out.println("Long list loaded "+sc2.getLongList());
        System.out.println("Int Map loaded "+sc2.getIntegerMap());
        System.out.println("Public int laoded "+sc2.publicInt);
        sinkMap.put("stringArrayVal", Arrays.asList("foo", "bar", "fuzz"));
        SinkTestClass sc3 = sinkMapper.readMap(sinkMap, SinkTestClass.class);
        System.out.println("String array loaded from list "+Arrays.toString(sc3.getStringArrayVal()));
        
        sinkMap.put("stringArrayVal", new String[]{"foo", "bar", "fuzz"});
        SinkTestClass sc4 = sinkMapper.readMap(sinkMap, SinkTestClass.class);
        System.out.println("String array loaded from array "+Arrays.toString(sc4.getStringArrayVal()));
        
        
        Map child = new HashMap();
        child.put("intVal", 2);
        child.put("stringArrayVal", new String[]{"child", "strings"});
        sinkMap.put("child", child);
        SinkTestClass sc5 = sinkMapper.readMap(sinkMap, SinkTestClass.class);
        System.out.println("Child strings are "+Arrays.toString(sc5.getChild().getStringArrayVal()));
        
        List children = new ArrayList();
        children.add(child);
        children.add(child);
        children.add(child);
        sinkMap.put("children", children);
        SinkTestClass sc6 = sinkMapper.readMap(sinkMap, SinkTestClass.class);
        System.out.println("Child strings are "+Arrays.toString(sc6.getChildren()));
        
        System.out.println("Converted back to map: "+sinkMapper.writeMap(sc6));
        System.out.println(Result.fromContent(sinkMapper.writeMap(sc6)).toString());
        
        try {
            //Map gcalData = this.getGCalJSON();
            CalendarMapper cmapper = new CalendarMapper();
            EventMapper emapper = new EventMapper();
            cmapper.register(CalendarTest.Event.class, emapper);
            /*
            FieldMapper startEndFieldMapper = new FieldMapper(){

                public Object getValue(Map map, String fieldName) {
                    Object o = map.get(fieldName);
                    if ( o instanceof Map ){
                        return ((Map)o).get("dateTime");
                    }
                    return null;
                }

                public void putValue(Map map, String fieldName, Object value) {
                    Map m = new HashMap();
                    m.put("dateTime", value.toString());
                    map.put(fieldName, m);
                }

                public boolean valueExists(Map map, String fieldName) {
                    return map.containsKey(fieldName) && map.get(fieldName) instanceof Map;
                }
                
            };
            */
            
            emapper.setFieldMapper("start", "/start/dateTime");
            emapper.setFieldMapper("end", "/end/dateTime");
            
            
            
            //CalendarTest ctest = cmapper.readMap(gcalData, CalendarTest.class);
            CalendarTest ctest = cmapper.readJSONFromURL("https://www.googleapis.com/calendar/v3/calendars/weblite.ca_5778lgg76mo76r01osl63o9hbs@group.calendar.google.com/events?key=AIzaSyBzpCgeAgkMDYSZKSfpuosxt5iS0ON353E", CalendarTest.class);
            System.out.println("Calendar name: "+ctest.getSummary());
            
            
            SinkTestBeanClass beanClass = new SinkTestBeanClass();
            BeanClass.register(SinkTestClass.class, beanClass);
            BeanObject obj = beanClass.wrap(sc6);
            
            System.out.println("The keys for sinkTest are "+obj.keySet());
            System.out.println("Public Int is "+sc6.getIntVal());
            obj.put("intVal", 44);
            System.out.println("After setting to 44, the bean object has "+obj.get("intVal")+" and the object has "+sc6.getIntVal());
            
            
            CalendarTest ctest2 = cmapper.readJSON("{\"updated\":\"2012-04-23T18:25:43.511Z\"}", CalendarTest.class);
            System.out.println("Date with millis is "+ctest2.getUpdated());
            System.out.println("Date as millis is "+ctest2.getUpdated().getTime());
            
            Map tmap = cmapper.writeMap(ctest2);
            System.out.println(tmap);
            
            
        } catch (Exception ex){
            Log.e(ex);
        }
        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

    public Map getGCalJSON() throws IOException{
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("https://www.googleapis.com/calendar/v3/calendars/weblite.ca_5778lgg76mo76r01osl63o9hbs@group.calendar.google.com/events?key=AIzaSyBzpCgeAgkMDYSZKSfpuosxt5iS0ON353E");
        req.setPost(false);
        req.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueueAndWait(req);
        JSONParser parser = new JSONParser();
        return parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData())));
    }
    
}
