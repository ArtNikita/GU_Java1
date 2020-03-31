package hw5;

import java.util.ArrayList;
import java.util.Random;

public class Cat {

    enum Sex {
        MAN,
        WOMAN
    }

    private int id = 0;
    private static  int counter = 0;
    private Random r = new Random();
    private String name;
    private Sex sex;
    // for Adam and Eva mother = null, father = null
    private Cat mother, father;
    private ArrayList<Cat> children;
    private boolean isAlive;

    public String getName() {
        return name;
    }

    public Cat getMother() {
        return mother;
    }

    public Cat getFather() {
        return father;
    }

    public Sex getSex() {
        return sex;
    }

    public Cat(String name, Sex sex) {
        counter++;
        id = counter;
        isAlive = true;
        this.name = name;
        this.sex = sex;
        children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Cat(Sex sex) {
        counter++;
        id = counter;
        isAlive = true;
        this.sex = sex;
        children = new ArrayList<>();
    }

    public ArrayList<Cat> getChildren() {
        return children;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void makeChild(Cat otherCat) {
        if (this.getSex() == otherCat.getSex()) {
            System.out.println("The creator did not endow you with this ability.");
            if (this.getSex() == Sex.MAN) {
                System.out.println("By the way. You are dead now..\nx_x");
                isAlive = false;
            }
        } else {
            Cat child = new Cat(Sex.values()[r.nextInt(Sex.values().length)]);
            child.father = (this.getSex() == Sex.MAN)?this:otherCat;
            child.mother = (this.getSex() == Sex.WOMAN)?this:otherCat;
            this.children.add(child);
            otherCat.children.add(child);
        }
    }

    @Override
    public String toString() {
        if (!isAlive) {
            return "RIP";
        }
        return "Cat{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", mother=" + mother +
                ", father=" + father +
                ", children=" + children +
                '}';
    }
}
