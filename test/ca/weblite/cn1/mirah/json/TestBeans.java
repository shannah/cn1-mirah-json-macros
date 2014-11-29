/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.cn1.mirah.json;

import ca.weblite.codename1.bean.BeanClass;
import ca.weblite.codename1.bean.BeanObject;
import com.codename1.testing.AbstractTest;

/**
 *
 * @author shannah
 */
public class TestBeans extends AbstractTest  {

    public boolean runTest() throws Exception {
        SinkTestBeanClass beanClass = new SinkTestBeanClass();
        BeanClass.register(SinkTestClass.class, beanClass);
        
        SinkTestClass c1 = new SinkTestClass();
        SinkTestClass c2 = new SinkTestClass();
        BeanObject o1 = BeanClass.wrap(c1);
        BeanObject o2 = BeanClass.wrap(c2);
        
        o1.put("intVal", 1);
        o1.put("doubleVal", 1.5);
        o1.put("floatVal", 1.5f);
        o1.put("stringVal", "Foobar");
        o1.put("byteVal", 2);
        o1.put("charVal", 'c');
        //o1.put("booleanVal", true);
        this.assertBool(c1.getIntVal()==1);
        this.assertBool(new Integer(1).equals(o1.get("intVal")));
        this.assertBool(c1.getDoubleVal()==1.5);
        this.assertBool(new Double(1.5f).equals(o1.get("doubleVal")));
        this.assertBool(c1.getFloatVal()==1.5f);
        this.assertBool("Foobar".equals(c1.getStringVal()));
        this.assertBool(c1.getByteVal()==2);
        this.assertBool(c1.getCharVal()== 'c');
        //this.assertBool(c1.getBo==true);
        
        o2.putAll(o1);
        this.assertBool(c2.getIntVal()==1);
        this.assertBool(new Integer(1).equals(o2.get("intVal")));
        this.assertBool(c2.getDoubleVal()==1.5);
        this.assertBool(new Double(1.5f).equals(o2.get("doubleVal")));
        this.assertBool(c2.getFloatVal()==1.5f);
        this.assertBool("Foobar".equals(c2.getStringVal()));
        this.assertBool(c2.getByteVal()==2);
        this.assertBool(c2.getCharVal()== 'c');
        
        return true;
    }
    
}
