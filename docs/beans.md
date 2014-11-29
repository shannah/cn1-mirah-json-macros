##Codename One Beans

The `BeanClass`, `BeanObject` classes and `bean_class` macro provide reflection-ish support for Codename One applications.  Codename One does not offer reflection because it would require the entire java class library to be included with the application - since the compiler cannot tell until runtime which classes are needed.  The `bean_class` macro allows us to include functionality the normally requires reflect, by making use of "compile-time" reflection.

The `bean_class` macro allows you to generate a sort of class descriptor for any class without having to write all of the boilerplate code yourself.  You can then wrap instances of this class inside a `BeanObject` which implements the `java.util.Map` interface.  I.e., you will be able to interact with your POJOs (Plain-old-java-objects) as if they were just `Map` objects.  This is very convenient for things like copying objects, parsing JSON, binding to UI widgets, mapping to database schemas, and much more.  

###Simple Example

MyClass.java
~~~
public class MyClass {
  public String name;
  public int age;
}
~~~
