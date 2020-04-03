package hw6;

public class Dog extends Animal {
    private static int counter = 0;
    private final int dogMaxRunLength = 500, dogMaxSwimLength = 10;
    private final double dogMaxJumpHeight = 0.5;
    public Dog(String name) {
        super(name);
        counter++;
        setId(counter);
        setMaxRunLength(dogMaxRunLength - 100 + r.nextInt(201));
        setMaxJumpHeight(dogMaxJumpHeight - 0.3 + r.nextDouble() * 0.6);
        setMaxSwimLength(dogMaxSwimLength - 3 + r.nextInt(7));
    }
    @Override
    public String toString() {
        return "Dog{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", maxRunLength=" + getMaxRunLength() +
                ", maxSwimLength=" + getMaxSwimLength() +
                ", maxJumpHeight=" + getMaxJumpHeight() +
                '}';
    }
}
