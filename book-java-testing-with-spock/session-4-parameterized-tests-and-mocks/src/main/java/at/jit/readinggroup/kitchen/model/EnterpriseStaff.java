package at.jit.readinggroup.kitchen.model;

public class EnterpriseStaff {

    private String name;
    private boolean lactoseIntolerant;

    public EnterpriseStaff(String name) {
        this.name = name;
        this.lactoseIntolerant = false;
    }

    public EnterpriseStaff lactoseIntolerant() {
        this.lactoseIntolerant = true;
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean isLactoseIntolerant() {
        return lactoseIntolerant;
    }

    public void eat(Food food) {
        System.out.println(name + " ate a " + food.getFoodName());
    }

}
