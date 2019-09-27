package at.jit.readinggroup;

import at.jit.readinggroup.fixedfiles.Coffee;
import at.jit.readinggroup.fixedfiles.CoffeeMachine;

public class EspressoMachine extends CoffeeMachine {

	public Coffee useMachine(int coffeeOrderId) {
		return this.makeOrder(coffeeOrderId);
	}

}
