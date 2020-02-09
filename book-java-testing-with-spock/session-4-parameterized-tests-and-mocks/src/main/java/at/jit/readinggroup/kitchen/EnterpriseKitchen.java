package at.jit.readinggroup.kitchen;

import at.jit.readinggroup.kitchen.model.EnterprisePantry;
import at.jit.readinggroup.kitchen.model.EnterpriseStaff;
import at.jit.readinggroup.kitchen.model.EnterpriseWarehouse;
import at.jit.readinggroup.kitchen.model.Food;

public class EnterpriseKitchen {

    private EnterpriseWarehouse warehouse;
    private EnterprisePantry pantry;

    public boolean isOpenAt(int hourOfDay) {
        return (hourOfDay >= 7 && hourOfDay <= 15);
    }

    public EnterpriseStaff onDutyCleaner(int hourOfDay) {
        if (hourOfDay % 2 == 0) {
            return new EnterpriseStaff("Travis");
        } else {
            return new EnterpriseStaff("Malcolm");
        }
    }

    public void prepareFoodFor(EnterpriseStaff person) {
        int hungerPoints = 50;
        while (hungerPoints > 0) {
            if (person.isLactoseIntolerant()) {
                Food pizza = preparePizza();
                person.eat(pizza);
                hungerPoints -= pizza.getWeight();
            } else {
                Food lasagna = prepareLasagna();
                person.eat(lasagna);
                hungerPoints -= lasagna.getWeight();
            }
        }
    }

    private Food preparePizza() {
        int requiredDough = 30;
        int requiredGarlic = 5;

        Food dough = warehouse.getDough(requiredDough);
        Food garlic = pantry.getGarilc(requiredGarlic);

        return mixIngredientsInto("Pizza", dough, garlic);
    }

    private Food prepareLasagna() {
        int requiredDough = 50;
        int requiredMilk = 10;

        Food dough = warehouse.getDough(requiredDough);
        Food milk = warehouse.getMilk(requiredMilk);

        return mixIngredientsInto("Lasagna", dough, milk);
    }

    private Food mixIngredientsInto(String newFoodName, Food... ingredients) {
        int combinedWeight = 0;
        boolean lactose = false;
        for (Food ingredient : ingredients) {
            combinedWeight += ingredient.getWeight();
            if (ingredient.containsLactose()) {
                lactose = true;
            }
        }
        return new Food(newFoodName, combinedWeight).setContainsLactose(lactose);
    }
}
