package at.jit.readinggroup.fixedfiles;

import static at.jit.readinggroup.fixedfiles.CommonTimeHandler.*;

public class CoffeeMachine {

	private Integer currentOrderId = null;

	public final boolean isBusy() {
		return currentOrderId != null;
	}

	public final Coffee makeOrder(int orderId) {
		passTheTime(coffeeMachineWarmupTime);

		currentOrderId = orderId;
		passTheTime(coffeeMachineUseTime);

		Coffee coffee = new Coffee(currentOrderId);
		currentOrderId = null;
		return coffee;
	}

}

