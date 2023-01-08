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

        System.out.println("The sum between " + num1 + " and " + num2 + " is:");
        System.out.println("Anonymous Class: " + resultWithAnonymousClass);
        System.out.println("Lamba: " + resultWithLambda);
    }
}
```

For run that code, we can use the following commands at the location of `Application.java` file.

```
javac Application.java; java Application 1 1
```

After run it, the result showed at terminal is:
```
The sum between 1.0 and 1.0 is:
Anonymous Class: 2.0
Lamba: 2.0
```

Notice that the implementation maded for `calcWithAnonymousClass` needed of much more lines to make the same thing as `calcWithLamba`.

That topic needed of some searches to be completed: [Marcelo Carvalho post](https://medium.com/@mvalho/java-8-functional-interfaces-tornando-o-java-mais-legal-72401462d0e2), [Oracle Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html) and [Pankaj post on Digital Ocean](https://www.digitalocean.com/community/tutorials/java-8-functional-interfaces).

### 19.2. [String Pool](https://www.vogella.com/tutorials/JavaIntroduction/article.html#javastringpool)

Strings in Java are immutable. It means that its value once declared cannot be changed anymore. The String variables just stores references to the String value on Heap memory, and it can be changed, like we used to do during the execution of our program.

When a literal String is declared, it is saved on a specific local of the Heap memory: the `String Pool`.

To clarify it better, let's declare a new String variable on our Java class.

```java
public class Application {
    public static void main(String[] args) {
        String myFirstString = "Hello";
    }
}
```

When we declare a String variable and initialize it with the literal way, that is what happen at the memory allocated to our application.

![Line1](https://user-images.githubusercontent.com/71560285/211213239-0c6494ba-df89-442d-8460-0f3685979a52.png)

Declaring another String variable using the same literal attribution, another local on String Pool won't be created, since the JVM knows that there already exists a String value declared as `Hello`.

**Code**
```java
public class Application {
    public static void main(String[] args) {
        String myFirstString = "Hello";
        String mySecondString = "Hello";
    }
}
```

If we look at memory:

![Line2](https://user-images.githubusercontent.com/71560285/211213463-f14388ea-88ed-46d7-acff-c81c07ee075b.png)

At the beginning, the String Pool is empty. After declaring the `myFirstString`, JVM looks inside it and search for the value "Hello". If it still doesn't exist, JVM stores that value and return the address. If already exists, returns the address of the value.

To make sure that `myFirstString` and `mySecondString` are appointing to the same local of memory, we can compare them with the `==` operator, which returns true if they have the same address.

**Code**
```java
public class Application {
    public static void main(String[] args) {
        String myFirstString = "Hello";
        String mySecondString = "Hello";

        System.out.println("Has same address: " + (myFirstString == mySecondString));
    }
}
```

**Result**
```
Has same address: true
```

If we change both values, the `Hello` will not be removed from memory, it just will not have a pointer with its address, and another allocation on String Pool will be created, having the new declared value.

**Code**
```java
public class Application {
    public static void main(String[] args) {
        String myFirstString = "Hello";
        String mySecondString = "Hello";

        myFirstString = "World";
        mySecondString = "World";

        System.out.println("Has same address: " + (myFirstString == mySecondString));
        System.out.println("Has same address as \"Hello\" value: " + (myFirstString == "Hello"));
    }
}
```

**Result**
```
Has same address: true
Has same address as "Hello" value: false
```

**Memory Representation**

![Line3](https://user-images.githubusercontent.com/71560285/211213258-32b92f42-baf2-4ce0-8be3-9bfd9f13df38.png)

There we can see the immutable propriety, since the value stored did not change after an alteration of variables's value attribution, and other local on String Pool was occupied with the new value.

It is important to say that if we declare a String variable with the `new` keyword, that value will not be created at String Pool, instead of it, will be saved at Heap Space every time a String is declared with the `new` keyword.

**Code**
```java
public class Application {
    public static void main(String[] args) {
        String myLiteralString = "Hello";
        String myNewString = new String("Hello");

        System.out.println("Has same address: " + (myLiteralString == myNewString));
    }
}
```

**Result**
```
Has same address: false
```

**Memory Representation**

![Line4](https://user-images.githubusercontent.com/71560285/211213264-93b03eb4-6bb3-46f7-8293-db27d6e7c482.png)

To allocate that value on String Pool, we can manually use the `String.intern()` method.

**Code**
```java
public class Application {
    public static void main(String[] args) {
        String myLiteralString = "Hello";
        String myNewString = new String("Hello");

        System.out.println("Has same address: " + (myLiteralString == myNewString));

        myNewString = myNewString.intern();
        System.out.println("Has same address after intern: " + (myLiteralString == myNewString));
    }
}
```

**Result**
```
Has same address: false
Has same address after intern: true
```

**Memory Representation**

![Line5](https://user-images.githubusercontent.com/71560285/211213273-fe89dc92-e5e9-41cc-8c16-6a74ddf2ae3f.png)

#### Advantages
- The use of String Pool improves the performance of our application, since it works as a cache, allowing the JVM to also spend less memory.
- Allows reusability of String values.

#### Disadvantages
- Every time a String is changed, another value will be allocated in memory, and the old value will be still existing, using unnecessary space. When working with some String variable that changes a lot, that can be a memory problem.

It is possible to use Strings on a mutable way through Java `StringBuilders`. Using that resource, the String value will not be stored on String Pool and the allocated value will be changed at the same address, not being reallocated.

That topic about String Pool is based on [Scaler](https://www.scaler.com/topics/java/string-pool-in-java/), [Baeldung](https://www.baeldung.com/java-string-pool), [Coding with John](https://www.youtube.com/watch?v=Bj9Mx_Lx3q4) and [Vogella](https://www.vogella.com/tutorials/JavaIntroduction/article.html#javastringpool).
