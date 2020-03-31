package hw5;

public class Population {

    private Cat adam = new Cat("Adam", Cat.Sex.MAN);
    private Cat eva = new Cat("Eva", Cat.Sex.WOMAN);

    public Population() {
        for (int i = 0; i < 10; i++) {
            adam.makeChild(eva);
        }
        for (Cat cat : adam.getChildren()) {
            for (Cat cat1 : adam.getChildren()) {
                if (!cat.getSex().equals(cat1.getSex())) {
                    deepPopulationIncrease(cat1, cat, 5);
                }
            }
        }
    }

    public void deepPopulationIncrease(Cat cat1, Cat cat2, int depth) {
        if (depth <= 0) {
            return;
        }
        if (!cat1.getSex().equals(cat2.getSex())) {
            cat1.makeChild(cat2);
            cat1.makeChild(cat2);
            Cat firstChild = cat1.getChildren().get(0);
            Cat secondChild = cat1.getChildren().get(1);
            deepPopulationIncrease(firstChild, secondChild, depth - 1);
        }
    }

    public void printPopulationTree() {
        String result = adam.getName() + " : {\n\tchildren : {\n";
        for (int i = 0; i < adam.getChildren().size(); i ++) {
            result += "\t\tCat" + adam.getChildren().get(i).getId() + " : {\n\t\t\tchildren : {\n";
            for (int j = 0; j < adam.getChildren().get(i).getChildren().size(); j ++){
                result += "\t\t\t\tCat" + adam.getChildren().get(i).getChildren().get(j).getId();
                if (j == adam.getChildren().get(i).getChildren().size() - 1){
                    continue;
                }
                result += ",\n";
            }
            result += "\n\t\t\t}\n\t\t}";
            if (i == adam.getChildren().size() - 1){
                continue;
            }
            result += ",\n";
        }
        result += "\n\t}\n}";
        System.out.println(result);
    }
}
