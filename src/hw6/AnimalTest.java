package hw6;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimalTest {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>(Arrays.asList(new Cat("Vasya"), new Cat("Barsik"),
                new Cat("Sir Wins"), new Dog("Ozborn"), new Dog("Berta"), new Dog("Lusha")));
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
