package at.jit.readinggroup.kitchen.model;

public class Food {

    private String foodName;
    private boolean containsLactose;
    private int weight;

    public Food(String name, int weight) {
        this.foodName = name;
        this.weight = weight;
    }

    public String getFoodName() {
        return foodName;
    }

    public boolean containsLactose() {
        return containsLactose;
    }

    public Food setContainsLactose(boolean containsLactose) {
        this.containsLactose = containsLactose;
        return this;
    }

    public int getWeight() {
        return weight;
    }

}
