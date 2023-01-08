
class Application {

    public static void main(String[] args) {

        // Declaring two String literal variables with same value.
        String a = "Hello";
        String b = "Hello";

        // Verifing if both has the same address.
        System.out.println();
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Has same address: " + (a == b));

        // Declaring a String variable with new keyword
        String c = new String("Hello");

        // Verifing if both has the same address.
        System.out.println();
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("Has same address with \"new\" keyword: " + (a == c));

        c = c.intern();
        System.out.println();
        System.out.println("Has same address after intern: " + (a == c));

    }

}