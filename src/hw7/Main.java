package hw7;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        ArrayList<Cat> cats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cats.add(new Cat("Cat" + i, 10 + r.nextInt(11)));
        }
        Plate plate = new Plate(105, 100);
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat);
        }
        plate.info();
        plate.addFood(40);
        plate.info();
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat);
        }
        plate.info();
        plate.addFood(110);
        plate.info();
    }
}
