# Estudies about Java on the Vogella's site.

That project contains my annotations and test maded during estudies on Vogella's posts about Java.

# [Introduction To Java Programming](https://www.vogella.com/tutorials/JavaIntroduction/article.html#javadef_interface)

## 5. Interfaces
### 5.5. [Multiple Inheritance of Methods](https://www.vogella.com/tutorials/JavaIntroduction/article.html#javadef_interface_multiple)

Java follows an especific priority order to decide which method should be used among the classes (Super Classes or Interfaces) that have their implementations.

1. Overrides methods.
2. Super Class methods.
3. Subtype methods (instead of Supertypes one).

For example, let's consider the following Interfaces and Super Class:

```java
interface MyFirstInterface { // Supertype
    public default void myMethod(String myParameter) {
        System.out.println("First Message: " + myParameter);
    }
}

interface MySecondInterface extends MyFirstInterface { // Subtype
    public default void myMethod(String myParameter) {
        System.out.println("Second Message: " + myParameter);
    }
}

class MySuperClass {
    public void myMethod(String myParameter) { // Super class
        System.out.println("Super Class Message: " + myParameter);
    }
}
```

Using `MySuperClass` as Super Class, implemmenting both Interfaces and overriding the `myMethod` method in a test class, according to the priority order, it is expected that the overrided method will be executed.

**Code:**
```java
class Application extends MySuperClass implements MyFirstInterface, MySecondInterface {
    public static void main(String args[]) {
        Application myApp = new Application();
        myApp.myMethod("Hello World!");
    }

    @Override
    public void myMethod(String myParameter) {
        System.out.println("Overrided Message: " + myParameter);
    }
}
```

**Result:**
```
Overrided Message: Hello World!
```

Using `MySuperClass` as Super Class and implemmenting both Interfaces at the same test class, according to the priority order, it is expected that the Super Class's method will be executed.

**Code:**
```java
class Application extends MySuperClass implements MyFirstInterface, MySecondInterface {
    public static void main(String args[]) {
        Application myApp = new Application();
        myApp.myMethod("Hello World!");
    }
}
```

**Result:**
```
Super Class Message: Hello World!
```

Implemmenting only both Interfaces at the test class, according to the priority order, it is expected that the Subtype's method (`MySecondInterface.myMethod`) will be executed.

**Code:**
```java
class Application implements MyFirstInterface, MySecondInterface {
    public static void main(String args[]) {
        Application myApp = new Application();
        myApp.myMethod("Hello World!");
    }
}
```

**Result:**
```
Second Message: Hello World!
```

As shown above, this is the list of priorities that Java assumes when encountering these scenarios.