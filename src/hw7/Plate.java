package hw7;

public class Plate {
    private int food, maxFood;

    public Plate(int food, int maxFood) {
        this.maxFood = maxFood;
        if (food > maxFood){
            this.food = maxFood;
            System.out.println("You sprinkled " + (food - maxFood) + " food");
        } else {
            this.food = food;
        }
    }

    public boolean decreaseFood(int n) {
        if(food >= n){
            food -= n;
            return true;
        } else {
            return false;
        }
    }

    public void info() {
        System.out.println("----Plate Info----\n  Max food: " + maxFood
                + "\nCurrent food: " + food + "\n------------------");
    }

    public void addFood(int food){
        if (food > (maxFood - this.food)){
            System.out.println("You sprinkled " + (food - maxFood + this.food) + " food");
            this.food = maxFood;
        } else {
            this.food += food;
        }
    }
}
