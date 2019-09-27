package at.jit.readinggroup.fixedfiles;

public interface BarInterface {

	enum OrderType {
		ORDER_WATER, ORDER_COFFEE;
	}

	BarManagement barManagerInstance = new BarManagement();

	CupOfBeverage[] fulfilBulkOrder(OrderType orderType, int quantity);
}
