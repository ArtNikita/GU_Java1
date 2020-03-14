package hw1;

public class Main {
    public static void main(String[] args) {
        //primitives
        byte b = 2;
        short s = 3;
        int i = 9;
        long l = 3L;
        float f = 0.1f;
        double d = 3.37;
        char a = 'a', a1 = 97;
        boolean bool = true;

        //reference
        String gitHub = "https://github.com/ArtNikita";
    }

    private static double someExpressionMethod (int a, int b, int c, int d) {
        return a * (b + ((double)c / d));
    }

    private static boolean checkIfSumBetween (int a, int b){
        return (a + b) >= 10 && (a + b) <= 20;
    }

    private static void checkSign (int a){
        if (a >= 0){
            System.out.println(a + " is positive.");
        } else{
            System.out.println(a + " is negative.");
        }
    }

    private static boolean isNegative (int a){
        return a < 0;
    }

    private static void greeting (String name){
        System.out.println("Hello, " + name + "!");
    }

    private static void isLeap (int year){
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
            System.out.println(year + " - a leap year!!!");
        } else{
            System.out.println(year + " - not a leap year :(");
        }
    }
}