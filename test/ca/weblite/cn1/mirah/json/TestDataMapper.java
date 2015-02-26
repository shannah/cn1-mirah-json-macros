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
        SinkTestClass test = mapper.readJSON("{\"name\": \"Steve\", \"public_int\" : 3}", SinkTestClass.class);
        assertBool(test != null, "readJSON returned a null object");
        assertBool(test.publicInt == 3);
        
        test = mapper.readJSON("{\"childrenList\": [{\"name\":\"Child 1\"}, {\"name\":\"Child 2\"}}", SinkTestClass.class);
        assertBool( test.getChildrenList() != null );
        assertBool( test.getChildrenList().size() == 2);
        
        
        CalendarMapper cmapper = new CalendarMapper();
        CalendarTest ctest = mapper.readJSON("{\"updated\":\"2012-04-23T18:25:43.511Z\"}", CalendarTest.class);
        Log.p(("updated is "+ctest.getUpdated()));
        
        
       
    
        return true;
    }
    
}
