package hw6;

import java.util.Random;

class Animal {
    private int maxRunLength, maxSwimLength, id;
    private double maxJumpHeight;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    Random r = new Random();

    void setMaxRunLength(int maxRunLength) {
        this.maxRunLength = maxRunLength;
    }

    void setMaxSwimLength(int maxSwimLength) {
        this.maxSwimLength = maxSwimLength;
    }

    void setMaxJumpHeight(double maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    Animal(String name) {
        this.name = name;
    }

    void run(int length) {
        if (length <= maxRunLength) {
            System.out.println(name + " ran " + length + " meters!");
        } else {
            System.out.println("it's too far..");
        }
    }

    void swim(int length) {
        if (length <= maxSwimLength) {
            System.out.println(name + " swam " + length + " meters!");
        } else {
            System.out.println("it's too far..");
        }
    }

    void jump(double height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " jumped " + height + " meters!");
        } else {
            System.out.println("it's too high..");
        }
    }

    public int getMaxRunLength() {
        return maxRunLength;
    }

    public int getMaxSwimLength() {
        return maxSwimLength;
    }

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public String getName() {
        return name;
    }
}
