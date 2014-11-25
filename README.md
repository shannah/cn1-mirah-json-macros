#CN1 DataMapper Macro

>A JSON parsing utility for Codename One that is similar to Jackson.  Read JSON to POJO without the manual coding.

##Requirements

1. Netbeans 7.4 or higher with Codename One plugin
2. Codename One DataMapper Lib
3. Netbeans Mirah Module 1.0.26 or higher

##Installation

1. Install the CN1DataMapper library into your project.
2. Copy CN1MirahJSON-macros.jar into your Codename One project's `lib/mirah/macros` directory.
3. Right click your project in the project explorer, and select "Refresh Libs"
4. Select "Clean & Build" project.

##Example

A POJO (Plain Old Java Object).  `MyValue.java`:

~~~
package com.myapp;

public class MyValue {
  private String name;
  private int age;
  
  public String getName(){ return name;}
  public void setName(String name){ this.name = name;}
  public int getAge(){ return age;}
  public void setAge(int age){ this.age = age;}
}
~~~


2 Lines of Mirah.  `DataMappers.mirah`:

~~~
package com.myapp;

data_mapper MyValue : MyValueMapper
~~~

Now convert JSON data to POJOs:

~~~
MyValueMapper mapper = new MyValueMapper();
MyValue value = mapper.readJSON("{'name' : 'John', 'age' : 11}", MyValue.class);

Log.p("Name is "+value.getName()); // John
Log.p("Age is "+value.getAge()); // 11
~~~

That's it.

###How does it work?

This library adds a Mirah macro called `data_mapper` that you can use inside your Codename One project.  It automatically generates a `DataMapper` class for the class that you provide.  Using compile-time reflection, the macro is able to create the appropriate read() methods to convert appropriate JSON data into instances of the specified class.

The line:

~~~
data_mapper MyValue : MyValueMapper
~~~

creates a class named `MyValueMapper` which extends `DataMapper`, and knows how to convert objects of type `MyValue` to and from a generic object graph consisting of Maps and Lists (and thus arbitrary JSON data).

The above example can also be accomplished without the `data_mapper` macro.  You would just have to implement the `MyValueMapper` class manually.  The implementation would be something like the following:

~~~
package com.myapp;

public class MyValueMapper extends DataMapper {
    public MyValueMapper(){
        register(MyValue.class, MyValueMapper.class);
    }
    
    @Override
    public void readMap(Map source, Object dest){
        MyValue val = (MyValue)dest;
        if ( exists(source, "name") ){
            val.setName((String)source.get("name"));
        }
        if ( exists(source, "age") ){
            val.setAge(NumberUtil.intValue(source.get("age")));
        }
    }
    
    @Override
    public void writeMap(Map dest, Object source){
        MyValue val = (MyValue)source;
        map.put("name", source.getName());
        map.put("age", source.getAge());
    }
}
~~~

This example only includes a single POJO with two properties, but it should also be apparent that writing this type of binding code is both boring and prone to errors.  Using the `data_mapper` macro, therefore will help you reduce the amount of code you write while simultaneously reducing the number of errors.
