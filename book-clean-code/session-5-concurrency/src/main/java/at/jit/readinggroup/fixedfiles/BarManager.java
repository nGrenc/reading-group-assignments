package at.jit.readinggroup.fixedfiles;

import static at.jit.readinggroup.fixedfiles.CommonTimeHandler.*;

public final class BarManager {

	private int freeCoffeeMachines;

	BarManager() {
		freeCoffeeMachines = 5;
	}

	public final void goToToilet() {
		passTheTime(toiletBusinessTime);
	}

	public final CupOfWater useWaterTap(Barista barista) {
		if (barista.hasBadBladder()) {
			goToToilet();
		}

		// Just pour a glass of water, jeez!
		passTheTime(waterPouringTime);
		return new CupOfWater();
	}

	public final CupOfCoffee useEspressoMachine(Barista barista) {
		if (barista.hasBadBladder()) {
			goToToilet();
		}

		if (freeCoffeeMachines > 0) {
			freeCoffeeMachines--;
			passTheTime(coffeeMachineUseTime);
			freeCoffeeMachines++;
			return new CupOfCoffee();
		}
		if (freeCoffeeMachines < 0) {
			System.err.println("We are using more coffee machines than we have! How?");
			System.exit(1);
		}
		return null;
	}
}
