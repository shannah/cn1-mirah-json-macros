#Codename One JSON <-> POJO Converter

> Use the `data_mapper` macro in conjunction with the `DataMapper` class to parse JSON data directly to Java Objects (POJOs). (Similar to [Jackson](https://github.com/FasterXML/jackson))

[Return to README](../README.md)

###Features

1. Can convert JSON data to POJOs.
2. Can convert POJOs to JSON data.
3. Supports all primitive types (`int`,`float`,`double`,`short`,`long`,`byte`,`boolean`,`char`), and boxed types (`Integer`, `Float`, `Double`, `Short`, `Long`, `Byte`, `Character`, and `Boolean`), dates (`Date`), and any custom types that have had a corresponding `DataMapper` class created and registered.
4. Support for custom date formats for both reading and writing dates.
5. Read and write POJOs to/from `java.util.Map`.  Optionally recursively write convert children to Maps/Lists.
6. Optional custom property mapping if the property names in the JSON data are different than the property names in the POJO.
7. Auto boxing/unboxing of primitive values as necessary.
8. Auto conversion from List to arrays as necessary.

##Simple Example

MyClass.java:
~~~
package com.example;

class MyClass {
  public String name;
  public int age;
}
~~~

DataMappers.mirah:
~~~
package com.example
data_mapper MyClass:MyClassMapper
~~~

Main.java:
~~~
MyClassMapper mapper = new MyClassMapper();
MyClass object = mapper.readJSON("{\"name\":\"Steve\", \"age\":77}", MyClass.class);
~~~

The resulting object would, as you might suspect, have `name`="Steve" and `age`=77.

###Read JSON From URL

~~~
MyClass object = mapper.readJSONFromURL("http://example.com/mycontent.json", MyClass.class);
~~~

###Read JSON From Connection Request

~~~
ConnectionRequest req = new ConnectionRequest();
req.setURL("http://example.com/mycontent.json");
req.setPost(false);
MyClass object = mapper.readJSONFromConnection(req, MyClass.class);
~~~

###Field Mapping

When the JSON data fields have a different than the POJO fields:

~~~
MyClassMapper mapper = new MyClassMapper();
mapper.setFieldMapper('name', 'username');
mapper.setFieldMapper('age', 'person_age');
MyClass object = mapper.readJSON("{\"username\":\"Steve\", \"person_age\":77}", MyClass.class);
~~~

When the JSON data have a different heirarchy:

~~~
MyClassMapper mapper = new MyClassMapper();
mapper.setFieldMapper('age', '/details/age');
MyClass object = mapper.readJSON("{\"name\":\"Steve\", \"details\": {\"age\":77}}", MyClass.class);
~~~

###Nested Data Structure

~~~
class MyClass {
  public String name;
  public int age;
  public MyClass child;
}
~~~

~~~
MyClassMapper mapper = new MyClassMapper();
MyClass object = mapper.readJSON("{\"name\":\"Steve\", \"age\":77, \"child\": {\"name\":\"John\", \"age\":33}}", MyClass.class);
~~~

In this case, `object.name` is "Steve" and `object.child.name` is "John".

###Using Setters and Getters

Previous examples used public properties for simplicity of understanding the concepts, but you can also make your properties private, and supply setters and getters instead:

~~~
class MyClass {
  private String name;
  private int age;
  private MyClass child;
  
  public String getName(){ return name;}
  public int getAge(){ return age;}
  public MyClass child(){ return child;}
  
  public void setName(String name){ this.name = name;}
  public void setAge(int age){ this.age = age;}
  public void setChild(MyClass child){ this.child = child;}
}
~~~

And the mapper will work the same way.

~~~
MyClassMapper mapper = new MyClassMapper();
MyClass object = mapper.readJSON("{\"name\":\"Steve\", \"age\":77, \"child\": {\"name\":\"John\", \"age\":33}}", MyClass.class);
~~~


###Multiple Object Types in JSON Data

Previous examples only showed mapping a JSON request to a single data type.  You can, however, register multiple classes to be decoded.  The DataMapper only parses content that it knows how to parse.  E.g. Consider the following class:

~~~
class MyClass {
  public String name;
  public int age;
  public SomeOtherClass other;
}
~~~

Then if we try to parse it using:

~~~
MyClassMapper mapper = new MyClassMapper();
MyClass object = mapper.readJSON("{\"name\":\"Steve\", \"age\":77, \"other\": {\"name\":\"John\", \"age\":33}}", MyClass.class);
~~~

We would have `object.name`="Steve", etc.., but `object.other` would be `null` even though the 'other' key was included in the JSON object data.  This is because the `MyClassMapper` object doesn't know how to parse the `SomeOtherClass` data.

**Solution**:  Create a DataMapper class for the `SomeOtherClass` class using the Mirah `data_mapper` macro, then register an instance of this class with the `MyClassMapper`.  E.g.

DataMappers.mirah:
~~~
package com.example
data_mapper MyClass:MyClassMapper
data_mapper SomeOtherClass:SomeOtherClassMapper  
# Note:  The naming convention doesn't have to be followed
# You can name your mapper classes anything you like!
~~~

Then

~~~
MyClassMapper mapper = new MyClassMapper();
SomeOtherClassMapper socMapper = new SomeOtherClassMapper();
mapper.register(SomeOtherClass.class, socMapper);
MyClass object = mapper.readJSON("{\"name\":\"Steve\", \"age\":77, \"other\": {\"name\":\"John\", \"age\":33}}", MyClass.class);
~~~

Now we would have `object.other` non-null (and assuming that it contains the correct properties, it would have loaded the appropriate data from the child object.).

###Interfaces and Classes without No-Arg Constructors

In order to use the patterns demonstrated above, you need to be using a concrete class with a non-arg constructor so that the DataMapper is able to instantiate the objects.  However, you can provide the data mapper with object factories that will be used to instantiate instances of the class as necessary:

~~~
mapper.setObjectFactory(new ObjectFactory(){
  public <T> T createObject(Class<T> cls){
      if ( MyClass.class.equals(cls) ){
          return new MyClass();
      } else {
          // etc...
      }
  }
});
~~~

###Reading to/from Maps

Support for reading JSON is actually just a wrapper around the core Map reading and writing support inside the `DataMapper` API.  When reading JSON, it first parses it into a tree of Maps and Lists (Using the JSONParser class), then it uses the `DataMapper` object to convert the map to or from POJOs.  Sometimes it is handy to simply convert to-from Maps without actually converting to/from JSON.  In that case you can use the `readMap()` or `writeMap()` methods.

~~~
MyClass object = mapper.readMap(map, MyClass.class);
~~~

This will properly handle the cases where map data is in a format that came from parsing JSON (e.g. Lists) or as data that can more directly be mapped to the properties of `MyClass` (e.g. `String[]`).

When writing to maps, you can choose whether you just want a shallow copy (e.g. don't convert child objects, arrays, maps, etc.. to JSON-friendly format - just leave them alone), or a deep copy (e.g. convert all arrays corresponding list types, only copy objects that have a mapper registered, and perform the appropriate conversion for those nested objects).  

**Shallow Copy**:

~~~
mapper.setOutputJSONReady(false);
Map map = mapper.writeMap(object);
// or write to existing map:  mapper.writeMap(map, object);
~~~

**Deep Copy**:

~~~
mapper.setOutputJSONReady(true);
Map map = mapper.writeMap(object);
// or write to existing map:  mapper.writeMap(map, object);
~~~

##How does it work?

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


##See Also

* [JavaDocs](https://rawgit.com/shannah/cn1-data-utils/master/dist/javadoc/index.html)
* [README](../README.md)
