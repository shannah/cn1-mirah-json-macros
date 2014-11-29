/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.weblite.cn1.mirah.json
import java.util.Arrays
import ca.weblite.codename1.mapper.Mappable
import ca.weblite.cn1.mirah.json.CalendarTest.*


/**
 *
 * @author shannah
 */
mappable class TestJSONClass implements Mappable
	attr_accessor foo:String, bar:int, child:TestJSONClass
  
  def childString:String
    
    ia = int[3]
    ia[0] = 1; ia[1] = 2; ia[2] = 3
    
    da = cast_array(ia, double)
    
    oa = Object[3]
    oa[0] = 'One'; oa[1] = 'Two'; oa[2] = 'Three'
    
    sa = cast_array(oa, String)
    
    il = [1,2,3]
    
    unboxed = unbox_list(il, int)
    unboxed2 = unbox_list([2,3,4], int)
    
    
    puts "Double array is #{Arrays.toString(da)}"
    puts "String array is #{Arrays.toString(sa)}"
    puts "Unboxed int list is #{Arrays.toString(unboxed)}"
    puts "Unboxed2 int list is #{Arrays.toString(unboxed2)}"
    return "chfoo is #{@child.foo} chbar is :#{@child.bar}" if @child
    return 'Childless'
  end
end

data_mapper TestJSONClass:JSONDataMapper
data_mapper SinkTestClass:SinkTestDataMapper
data_mapper CalendarTest:CalendarMapper
data_mapper Event:EventMapper
bean_class SinkTestClass:SinkTestBeanClass
