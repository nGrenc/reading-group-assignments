package at.jit.readinggroup;

import at.jit.readinggroup.fixedfiles.BarInterface;
import at.jit.readinggroup.fixedfiles.CupOfBeverage;

public class Bar implements BarInterface {

	@Override
	public CupOfBeverage[] fulfilBulkOrder(OrderType orderType, int quantity) {
		return new CupOfBeverage[0];
	}

}