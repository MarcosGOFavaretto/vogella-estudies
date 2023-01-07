
@FunctionalInterface
interface MyFuncionalInterface {
    public double calculate(double num1, double num2);
}

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