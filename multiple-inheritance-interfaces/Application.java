
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

class MySuperClass { // Super Class
    public void myMethod(String myParameter) {
        System.out.println("Super Class Message: " + myParameter);
    }
}

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