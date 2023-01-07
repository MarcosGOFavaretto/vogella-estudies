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

### 5.6. [Functional Interfaces](https://www.vogella.com/tutorials/JavaIntroduction/article.html#javadef_functionalinterfaces)

Functional Interfaces are simple Interfaces with only one abstract method. The big thing to use then is for implement its behavier without create another class for it or even need of Anonymous Classes. Which can reduce the number of lines of code and make it more easily to read.

Every Interface with has only one abstract method is a Funcional Interface. However, use the `@FunctionalInterface` Annotation at the Interface's declaration is a good practice, since that one makes the Java Compiler throws an error in case the annotated Interface has more than one abstract method or if its type isn't an `interface`.

Another indirect advantage of using that type of implementations is the support of Streams with parallel operations which can improve our software performance.

Let's create a simple example. Like said before, an Interface can be a Functional Interface if it has only one abstract method. With it on mind, the Interface `MyFuncionalInterface` has the following code:

```java
@FunctionalInterface // Makes the compiler throw error.
interface MyFuncionalInterface {
    public double calculate(double num1, double num2);
}
```

To implement that `calculate` method, we can use Anonymous Classes and Lambda expressions.

```java
class Application {
    public static void main(String args[]) {

        double num1 = Double.valueOf(args[0]);
        double num2 = Double.valueOf(args[1]);

        MyFuncionalInterface calcWithAnonymousClass = new MyFuncionalInterface() {
            @Override
            public double calculate(double num1, double num2) {
                return num1 + num2;
            }
        };
        MyFuncionalInterface calcWithLamba = (parameter1, parameter2) -> parameter1 + parameter2;

        double resultWithAnonymousClass = calcWithAnonymousClass.calculate(num1, num2);
        double resultWithLambda = calcWithLamba.calculate(num1, num2);

        System.out.println("The sum between " + num1 + " and " + num2 + " is :");
        System.out.println("Anonymous Class: " + resultWithAnonymousClass);
        System.out.println("Lamba: " + resultWithLambda);
    }
}
```

Notice that the implementation maded for `calcWithAnonymousClass` needed of much more lines to make the same thing as `calcWithLamba`.

That topic needed of some searches to be completed: [Marcelo Carvalho post](https://medium.com/@mvalho/java-8-functional-interfaces-tornando-o-java-mais-legal-72401462d0e2), [Oracle Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html) and [Pankaj post on Digital Ocean](https://www.digitalocean.com/community/tutorials/java-8-functional-interfaces).