##Codename One Beans

The `BeanClass`, `BeanObject` classes and `bean_class` macro provide reflection-ish support for Codename One applications.  Codename One does not offer reflection because it would require the entire java class library to be included with the application - since the compiler cannot tell until runtime which classes are needed.  The `bean_class` macro allows us to include functionality the normally requires reflect, by making use of "compile-time" reflection.

The `bean_class` macro allows you to generate a sort of class descriptor for any class without having to write all of the boilerplate code yourself.  You can then wrap instances of this class inside a `BeanObject` which implements the `java.util.Map` interface.  I.e., you will be able to interact with your POJOs (Plain-old-java-objects) as if they were just `Map` objects.  This is very convenient for things like copying objects, parsing JSON, binding to UI widgets, mapping to database schemas, and much more.  

###Simple Example

MyClass.java
~~~
package com.example;
public class MyClass {
  public String name;
  public int age;
}
~~~

MyMappers.mirah:
~~~
package com.example
bean_class MyClass:MyBeanClass
~~~

Main.java

~~~
// First register your Bean class... Probably put this in your app's init() method
BeanClass.register(MyClass.class, new MyBeanClass());

// Create a new MyClass Object: person1
MyClass person1 = new MyClass();
person1.name = "Steve";
person1.age = 12;

// Now wrap the person1 object inside a Bean
BeanObject bean1 = BeanClass.wrap(person1);

// We can use it like a Map now
bean1.get("name");  // Steve
bean1.get("age");   // 12

// We can update the fields too
bean1.put("name", "John");
person1.name;  // John

// Create a 2nd object and wrap it in a bean
MyClass person2 = new MyClass();
BeanObject bean2 = BeanClass.wrap(person2);

// Copy all of the data from the first person into the second person
bean2.putAll(bean1);
person2.name; // John
bean2.get("name"); // John
person2.age; // 12
~~~

