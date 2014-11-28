#JSON Parsing

Use the `data_mapper` macro in conjunction with the `DataMapper` class to parse JSON data directly to Java Objects (POJOs).

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
MyClass object = mapper.readJSON("{'name':'Steve', 'age':77}", MyClass.class);
~~~

The resulting object would, as you might suspect, have `name`="Steve" and `age`=77.

##Read JSON From URL

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
MyClass object = mapper.readJSON("{'username':'Steve', 'person_age':77}", MyClass.class);
~~~

When the JSON data have a different heirarchy:

~~~
MyClassMapper mapper = new MyClassMapper();
mapper.setFieldMapper('age', '/details/age');
MyClass object = mapper.readJSON("{'name':'Steve', 'details': {'age':77}}", MyClass.class);
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
MyClass object = mapper.readJSON("{'name':'Steve', 'age':77, 'child': {'name':'John', 'age':33}}", MyClass.class);
~~~

In this case, `object.name` is "Steve" and `object.child.name` is "John".


