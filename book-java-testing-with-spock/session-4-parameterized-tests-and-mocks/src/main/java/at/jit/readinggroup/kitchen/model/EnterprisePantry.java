package at.jit.readinggroup.kitchen.model;

public class EnterprisePantry  {

    private int garlicStock = 50;

    public Food getGarilc(int weight) {
        if (weight > garlicStock) {
            throw new EnterpriseWarehouse.EnterpriseWarehouseEmptyException();
        }

        throw new RuntimeException("Door is stuck - cannot get garlic");

        // garlicStock -= weight;
        // return new Food("Garlic", weight);
    }
}
