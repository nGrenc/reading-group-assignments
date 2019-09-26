package at.jit.readinggroup;

import at.jit.readinggroup.fixedfiles.BarInterface;
import at.jit.readinggroup.fixedfiles.CupOfBeverage;
import at.jit.readinggroup.fixedfiles.CupOfCoffee;
import at.jit.readinggroup.fixedfiles.CupOfWater;
import org.junit.Test;

import static at.jit.readinggroup.fixedfiles.BarInterface.OrderType.ORDER_COFFEE;
import static at.jit.readinggroup.fixedfiles.BarInterface.OrderType.ORDER_WATER;
import static org.junit.Assert.*;

public class ConcurrencyTaskTest {

	BarInterface jutInTimeCafe = new Bar();

	@Test
	public void shouldFinishAllWaterOrders() {
		int noWaters = 3;
		CupOfBeverage[] servedWaters = jutInTimeCafe.fulfilBulkOrder(ORDER_WATER, noWaters);
		assertEquals(noWaters, servedWaters.length);
		assertEveryElementIsCorrectType(servedWaters, CupOfWater.class);
	}

	@Test
	public void shouldFinishAllCoffeeOrders() {
		int noCoffees = 3;
		CupOfBeverage[] servedCoffees = jutInTimeCafe.fulfilBulkOrder(ORDER_COFFEE, noCoffees);
		assertEquals(noCoffees, servedCoffees.length);
		assertEveryElementIsCorrectType(servedCoffees, CupOfCoffee.class);
	}

	@Test
	public void shouldFinishAllWaterOrdersEvenIfThereAreMany() {
		int noWaters = 300;
		CupOfBeverage[] servedWaters = jutInTimeCafe.fulfilBulkOrder(ORDER_WATER, noWaters);
		assertEquals(noWaters, servedWaters.length);
		assertEveryElementIsCorrectType(servedWaters, CupOfWater.class);
	}

	@Test
	public void shouldFinishAllCoffeeOrdersEvenIfThereAreMany() {
		int noCoffees = 300;
		CupOfBeverage[] servedCoffees = jutInTimeCafe.fulfilBulkOrder(ORDER_COFFEE, noCoffees);
		assertEquals(noCoffees, servedCoffees.length);
		assertEveryElementIsCorrectType(servedCoffees, CupOfCoffee.class);
	}


	private void assertEveryElementIsCorrectType(Object[] objectArray, Class<?> requiredType) {
		for (Object object : objectArray) {
			assertEquals(requiredType, object.getClass());
		}
	}

}