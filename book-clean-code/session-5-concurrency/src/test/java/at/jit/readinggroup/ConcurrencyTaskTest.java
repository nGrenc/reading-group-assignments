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

	BarInterface justInTimeCafe = new Bar();

	@Test
	public void shouldFinishAllWaterOrders() {
		int noWaters = 3;
		CupOfBeverage[] servedWaters = justInTimeCafe.fulfilBulkOrder(ORDER_WATER, noWaters);
		assertEquals(noWaters, servedWaters.length);
		assertEveryElementIsCorrectType(servedWaters, CupOfWater.class);
		assertAllOrdersAreFulfilled(servedWaters, noWaters);
	}

	@Test
	public void shouldFinishAllCoffeeOrders() {
		int noCoffees = 3;
		CupOfBeverage[] servedCoffees = justInTimeCafe.fulfilBulkOrder(ORDER_COFFEE, noCoffees);
		assertEquals(noCoffees, servedCoffees.length);
		assertEveryElementIsCorrectType(servedCoffees, CupOfCoffee.class);
		assertAllOrdersAreFulfilled(servedCoffees, noCoffees);
	}

	@Test
	public void shouldFinishAllWaterOrdersEvenIfThereAreMany() {
		int noWaters = 300;
		CupOfBeverage[] servedWaters = justInTimeCafe.fulfilBulkOrder(ORDER_WATER, noWaters);
		assertEquals(noWaters, servedWaters.length);
		assertEveryElementIsCorrectType(servedWaters, CupOfWater.class);
		assertAllOrdersAreFulfilled(servedWaters, noWaters);
	}

	@Test
	public void shouldFinishAllCoffeeOrdersEvenIfThereAreMany() {
		int noCoffees = 300;
		CupOfBeverage[] servedCoffees = justInTimeCafe.fulfilBulkOrder(ORDER_COFFEE, noCoffees);
		assertEquals(noCoffees, servedCoffees.length);
		assertEveryElementIsCorrectType(servedCoffees, CupOfCoffee.class);
		assertAllOrdersAreFulfilled(servedCoffees, noCoffees);
	}

	@Test
	public void evenLargeOrderShouldBeFinishedIn30seconds() {
		int noCoffees = 300;
		long expectedOrderFinish = System.currentTimeMillis() + 30000;
		justInTimeCafe.fulfilBulkOrder(ORDER_COFFEE, noCoffees);
		assertTrue(expectedOrderFinish > System.currentTimeMillis());
	}


	private void assertEveryElementIsCorrectType(Object[] objectArray, Class<?> requiredType) {
		for (Object object : objectArray) {
			assertEquals(requiredType, object.getClass());
		}
	}

	private void assertAllOrdersAreFulfilled(CupOfBeverage[] beverages, int noOrders) {
		assertEquals(noOrders, beverages.length);
		for (int i = 1; i <= noOrders; i++) {
			boolean contains = false;
			for (CupOfBeverage beverage : beverages) {
				contains = (contains) ? true : (beverage.getOrderId() == i);
			}
			if (! contains) {
				fail();
			}
		}
	}

}