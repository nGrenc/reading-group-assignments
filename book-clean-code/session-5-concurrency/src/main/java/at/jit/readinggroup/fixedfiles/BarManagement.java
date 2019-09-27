package at.jit.readinggroup.fixedfiles;

import at.jit.readinggroup.EspressoMachine;

import static at.jit.readinggroup.fixedfiles.CommonTimeHandler.*;

public final class BarManagement {

	private final EspressoMachine[] coffeeMachines;

	BarManagement() {
		coffeeMachines = new EspressoMachine[] {
			new EspressoMachine(), new EspressoMachine(), new EspressoMachine(), new EspressoMachine(), new EspressoMachine()
		};
	}

	public final void goToToilet() {
		passTheTime(toiletBusinessTime);
	}

	public final CupOfWater useWaterTap(Barista barista, int orderId) {
		if (barista.hasBadBladder()) {
			goToToilet();
		}

		// Just pour a glass of water, jeez!
		passTheTime(waterPouringTime);

		return new CupOfWater(orderId);
	}

	public final CupOfCoffee useEspressoMachine(Barista barista, int orderId, int espressoMachineId) {
		if (barista.hasBadBladder()) {
			goToToilet();
		}

		EspressoMachine machine = coffeeMachines[espressoMachineId];
		if (! machine.isBusy()) {
			Coffee coffee = machine.useMachine(orderId);
			return (coffee == null) ? null : new CupOfCoffee(coffee.orderId);
		}
		return null;
	}

	public EspressoMachine[] allCoffeeMachines() {
		return coffeeMachines;
	}
}
