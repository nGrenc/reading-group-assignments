package at.jit.readinggroup.kitchen.model;

public interface EnterpriseWarehouse {

    Food getDough(int number);

    Food getMilk(int number);

    class EnterpriseWarehouseEmptyException extends RuntimeException {
    }
}
