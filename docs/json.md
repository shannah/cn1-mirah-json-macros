#JSON Parsing

Use the `data_mapper` macro in conjunction with the `DataMapper` class to parse JSON data directly to Java Objects (POJOs).

###Features

1. Can convert JSON data to POJOs.
2. Can convert POJOs to JSON data.
3. Supports all primitive types (`int`,`float`,`double`,`short`,`long`,`byte`,`boolean`,`char`), and boxed types (`Integer`, `Float`, `Double`, `Short`, `Long`, `Byte`, `Character`, and `Boolean`), dates (`Date`), and any custom types that have had a corresponding `DataMapper` class created and registered.
4. Support for custom date formats for both reading and writing dates.
5. Read and write POJOs to/from `java.util.Map`.  Optionally recursively write convert children to Maps/Lists.
6. Optional custom property mapping if the property names in the JSON data are different than the property names in the POJO.

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


