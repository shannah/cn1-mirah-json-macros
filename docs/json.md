#JSON Parsing

Use the `data_mapper` macro in conjunction with the `DataMapper` class to parse JSON data directly to Java Objects (POJOs).

E.g.

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

