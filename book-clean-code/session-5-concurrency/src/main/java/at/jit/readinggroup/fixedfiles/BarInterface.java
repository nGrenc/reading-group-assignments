package at.jit.readinggroup.fixedfiles;

public interface BarInterface {

	enum OrderType {
		ORDER_WATER, ORDER_COFFEE;
	}

	BarManager barManagerInstance = new BarManager();

	CupOfBeverage[] fulfilBulkOrder(OrderType orderType, int quantity);
}
