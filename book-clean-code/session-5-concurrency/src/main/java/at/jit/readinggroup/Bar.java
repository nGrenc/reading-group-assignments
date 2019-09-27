package at.jit.readinggroup;

import at.jit.readinggroup.fixedfiles.BarInterface;
import at.jit.readinggroup.fixedfiles.Barista;
import at.jit.readinggroup.fixedfiles.CupOfBeverage;

import static at.jit.readinggroup.fixedfiles.BarInterface.OrderType.ORDER_WATER;
import static at.jit.readinggroup.fixedfiles.CommonTimeHandler.passTheTime;

public class Bar implements BarInterface {


	@Override
	public CupOfBeverage[] fulfilBulkOrder(OrderType orderType, int quantity) {
		OrderScope orderScope = new OrderScope();
		for (int i = 1; i <= quantity; i++) {
			hireBaristaAsync(i, orderType, orderScope);
		}
		closeRestaurant(orderScope);
		System.out.println("Today we served " + quantity + " orders.");
		return orderScope.allBeverages();
	}


	public void hireBaristaAsync(Integer baristaId, OrderType orderType, OrderScope orderScope) {
		Thread t = new Thread(() -> {
			Barista employee = new Barista(baristaId);
			System.out.println(String.format("Barista %02d started work day!", baristaId));

			CupOfBeverage beverage = null;
			while (beverage == null) {
				if (orderType == ORDER_WATER) {
					beverage = barManagerInstance.useWaterTap(employee, employee.getBaristaId());
				} else {

					for (int i = 0; i < barManagerInstance.allCoffeeMachines().length; i++) {
						EspressoMachine machine = barManagerInstance.allCoffeeMachines()[i];
						if (! machine.isBusy()) {
							beverage = barManagerInstance.useEspressoMachine(employee, employee.getBaristaId(), i);
							break;
						}
					}
				}

				//passTheTime(5);
			}
			orderScope.addNewOrder(beverage);

			System.out.println(String.format("Barista %02d served coffee %02d!", baristaId, baristaId));
		});
		orderScope.hireNewBarista(t);
		t.start();
	}


	public void hireBarista(int baristaId, OrderType orderType, OrderScope orderScope) {
		Barista employee = new Barista(baristaId);
		System.out.println(String.format("Barista %02d started work day!", baristaId));

		CupOfBeverage beverage;
		if (orderType == ORDER_WATER) {
			beverage = barManagerInstance.useWaterTap(employee, employee.getBaristaId());
		} else {
			beverage = barManagerInstance.useEspressoMachine(employee, employee.getBaristaId(), 1);
		}
		orderScope.addNewOrder(beverage);

		System.out.println(String.format("Barista %02d served coffee %02d!", baristaId, baristaId));
	}



	public void closeRestaurant(OrderScope orderScope) {
		boolean canClose = false;
		long closingTime = System.currentTimeMillis() + 40000;
		while (!canClose && closingTime > System.currentTimeMillis()) {
			passTheTime(500);
			canClose = orderScope.areAllBaristasDone();
			System.out.println("Waiting for more: " + (closingTime - System.currentTimeMillis()));
		}

		if (canClose) {
			return;
		} else {
			System.err.println(orderScope.currentlyWorkingBaristas().size() + " baristas never finished their jobs. ");
			orderScope.currentlyWorkingBaristas().forEach(barista -> barista.stop());
		}
	}


}