package at.jit.readinggroup.fixedfiles;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BaristaBladderTest {

	public BarManagement manager = new BarManagement();

	private int unimportantMachineId = 2;
	private int unimportantOrderValue = 5;

	@Test
	public void baristaWithBadBladderShouldTakeLongerToPrepareWater() {
		// given: two baristas
		Barista baristaWithGoodBladder = new Barista(1, false);
		Barista baristaWithBadBladder = new Barista(2, true);

		// when: water is ordered
		long startOrder;
		startOrder = System.currentTimeMillis();
		manager.useWaterTap(baristaWithGoodBladder, unimportantOrderValue);
		long timeSpentPreparingWater = System.currentTimeMillis() - startOrder;

		startOrder = System.currentTimeMillis();
		manager.useWaterTap(baristaWithBadBladder, unimportantOrderValue);
		long timeSpentOnToiletAndPreparingWater = System.currentTimeMillis() - startOrder;

		// then: it took longer for barista with bad bladder
		assertTrue(timeSpentOnToiletAndPreparingWater > timeSpentPreparingWater);
	}

	@Test
	public void baristaWithBadBladderShouldTakeLongerToPrepareCoffee() {
		// given: two baristas
		Barista baristaWithGoodBladder = new Barista(1, false);
		Barista baristaWithBadBladder = new Barista(2, true);

		// when: coffee is ordered
		long startOrder;
		startOrder = System.currentTimeMillis();
		manager.useEspressoMachine(baristaWithGoodBladder, unimportantOrderValue, unimportantMachineId);
		long timeSpentPreparingCoffee = System.currentTimeMillis() - startOrder;

		startOrder = System.currentTimeMillis();
		manager.useEspressoMachine(baristaWithBadBladder, unimportantOrderValue, unimportantMachineId);
		long timeSpentOnToiletAndPreparingCoffee = System.currentTimeMillis() - startOrder;

		// then: it took longer for barista with bad bladder
		assertTrue(timeSpentOnToiletAndPreparingCoffee > timeSpentPreparingCoffee);
	}

}