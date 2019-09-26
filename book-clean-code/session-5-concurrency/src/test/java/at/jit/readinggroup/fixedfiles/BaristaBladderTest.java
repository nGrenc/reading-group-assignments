package at.jit.readinggroup.fixedfiles;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BaristaBladderTest {

	public BarManager manager = new BarManager();

	@Test
	public void baristaWithBadBladderShouldTakeLongerToPrepareWater() {
		// given: two baristas
		Barista baristaWithGoodBladder = new Barista(1, false);
		Barista baristaWithBadBladder = new Barista(2, true);

		// when: water is ordered
		long startOrder;
		startOrder = System.currentTimeMillis();
		manager.useWaterTap(baristaWithGoodBladder);
		long timeSpentPreparingWater = System.currentTimeMillis() - startOrder;

		startOrder = System.currentTimeMillis();
		manager.useWaterTap(baristaWithBadBladder);
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
		manager.useEspressoMachine(baristaWithGoodBladder);
		long timeSpentPreparingCoffee = System.currentTimeMillis() - startOrder;

		startOrder = System.currentTimeMillis();
		manager.useEspressoMachine(baristaWithBadBladder);
		long timeSpentOnToiletAndPreparingCoffee = System.currentTimeMillis() - startOrder;

		// then: it took longer for barista with bad bladder
		assertTrue(timeSpentOnToiletAndPreparingCoffee > timeSpentPreparingCoffee);
	}

}