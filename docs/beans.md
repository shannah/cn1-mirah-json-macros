#Codename One Beans

> A `Map` interface for POJOs

[Back to README](../README.md)

The `BeanClass`, `BeanObject` classes and `bean_class` macro provide reflection-ish support for Codename One applications.  Codename One does not offer reflection because it would require the entire java class library to be included with the application - since the compiler cannot tell until runtime which classes are needed.  The `bean_class` macro allows us to include functionality the normally requires reflection, by making use of "compile-time" reflection.

The `bean_class` macro allows you to generate a sort of class descriptor for any class without having to write all of the boilerplate code yourself.  You can then wrap instances of this class inside a `BeanObject` which implements the `java.util.Map` interface.  I.e., you will be able to interact with your POJOs (Plain-old-java-objects) as if they were just `Map` objects.  This is very convenient for things like copying objects, parsing JSON, binding to UI widgets, mapping to database schemas, and much more.  

##Features

1. Reflection-like support for Codename One
2. Generate class descriptors that allow you to see the properties of a given class at runtime.
3. Wrappers for POJOs that implement `java.util.Map` so that you can interact with POJOs using a Map api.
4. Support for public properties, getters, and setters.
5. Properties are readable and/or writable based on whether it can be mutated via a public API.  E.g. a property that is public, will be both readable and writable.  A property that is private, and has a getter, but no setter, will be read-only, and a property that only has a setter, will be write-only (can't be read).

##Simple Example

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

##How it works?

The `BeanClass` and `BeanObject` classes provide an API that can be implemented to provide a generic class descriptor and wrapper for Java objects.  In order to implement a class descriptor (or BeanClass), you subclass `BeanClass`, and implement appropriate `BeanClass.Property` classes for each property of the class.  Doing this by hand is not really feasible, as it is a lot of boiler-plate code.  This is where Mirah comes in.  The `bean_class` Mirah macro performs compile-time reflection to automatically generate a `BeanClass` subclass for the specified class.  If scans the class definition, at compile time to figure out the public properties, getters, and setters that are available, and it implements appropriate `BeanClass.Property` classes to be able to update objects of the specified class using a `Map` API.

###Performance Costs?

Since all of this is done at compile-time, the performance should be just as good as if you had implemented a wrapper of this type by hand.  There is a small cost of interacting with an object through its bean wrapper, since it has to use an addiitonal lookup to find the correct property, but it should fare well with compared to other solutions that promote decoupling.

###Code-size Costs?

For every BeanClass you generate using the `bean_class` macro, one `.class` file will be generated for the `BeanClass` subclass, and one `.class` file will be generated for each property also.  (I.e. if you look, you'll see quite a number of anonymous inner classes generated inside the bean class).  Don't go crazy and generate descriptors for every class in your app - since this space overhead does exist.

###What is this useful for?

You'll see.  Some things that come to mind are:

1. Copying/cloning objects without having to explicitly copy each property.
2. Generating UI elements based on the properties in a POJO.
3. Mapping to database schemas or converting to other interchange formats.

##See Also

* [JavaDocs](https://rawgit.com/shannah/cn1-data-utils/master/dist/javadoc/index.html)
* [README](../README.md)
