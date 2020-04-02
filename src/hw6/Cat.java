package hw6;

public class Cat extends Animal {
    private static int counter = 0;
    private final int catMaxRunLength = 200;
    private final double catMaxJumpHeight = 2;
    public Cat(String name) {
        super(name);
        counter++;
        setId(counter);
        setMaxRunLength(catMaxRunLength - 50 + r.nextInt(101));
        setMaxJumpHeight(catMaxJumpHeight - 0.5 + r.nextDouble());
    }

    @Override
    void swim(int length) {
        System.out.println("Shhhhhh!!!!");
    }
    @Override
    public String toString() {
        return "Cat{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", maxRunLength=" + getMaxRunLength() +
                ", maxSwimLength=" + getMaxSwimLength() +
                ", maxJumpHeight=" + getMaxJumpHeight() +
                '}';
    }
}
