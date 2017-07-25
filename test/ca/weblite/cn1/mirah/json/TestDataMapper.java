/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.cn1.mirah.json;

import ca.weblite.codename1.mapper.DataMapper;
import com.codename1.io.Log;
import com.codename1.testing.AbstractTest;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author shannah
 */
public class TestDataMapper extends AbstractTest  {

    public boolean runTest() throws Exception {
        SinkTestDataMapper mapper = new SinkTestDataMapper();
        mapper.setReadKeyConversions(Arrays.asList(DataMapper.CONVERSION_CAMEL_TO_SNAKE));
        SinkTestClass test = mapper.readJSON("{\"name\": \"Steve\", \"public_int\" : 3, \"stringArr\":null}", SinkTestClass.class);
        assertBool(test != null, "readJSON returned a null object");
        assertBool(test.publicInt == 3);
        
        test = mapper.readJSON("{\"childrenList\": [{\"name\":\"Child 1\"}, {\"name\":\"Child 2\"}}", SinkTestClass.class);
        assertBool( test.getChildrenList() != null );
        assertBool( test.getChildrenList().size() == 2);
        
        
        CalendarMapper cmapper = new CalendarMapper();
        CalendarTest ctest = cmapper.readJSON("{\"updated\":\"2012-04-23T18:25:43.511Z\"}", CalendarTest.class);
        Log.p(("updated is "+ctest.getUpdated()));
       
        
        RestaurantMapper rmapper = new RestaurantMapper();
        Restaurant r  = rmapper.readJSON("{\"image\":null, \"types\":null, \"address\":null, \"lng\":-122.096932, \"specials\":null, \"numReviews\":0, \"placeId\":null, \"rating\":0.0, \"description\":\"Steve's House\", \"favourite\":false, \"seating\":\"UNKNOWN\", \"tags\":null, \"numDevices\":0, \"lastUpdated\":null, \"phone\":null, \"name\":\"Foo Bar Foo\", \"weekdayText\":null, \"attributes\":null, \"estimateOnly\":true, \"id\":1, \"waitTime\":\"UNKNOWN\", \"class\":\"ca.weblite.pander.dto.Restaurant\", \"lat\":37.391858}", Restaurant.class);
        Log.p("Restaurant is "+r);
        return true;
    }
    
}
