package at.jit.readinggroup;

import at.jit.readinggroup.fixedfiles.CupOfBeverage;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderScope {

	private Set<Thread> hiredBaristas = new HashSet<>();
	private Set<CupOfBeverage> beveragesServed = new HashSet<>();

	public synchronized void addNewOrder(CupOfBeverage beverage) {
		beveragesServed.add(beverage);
	}

	public CupOfBeverage[] allBeverages() {
		return beveragesServed.toArray(new CupOfBeverage[0]);
	}

	public void hireNewBarista(Thread barista) {
		hiredBaristas.add(barista);
	}

	public boolean areAllBaristasDone() {
		return currentlyWorkingBaristas().size() == 0;
	}

	public Set<Thread> currentlyWorkingBaristas() {
		return hiredBaristas.stream().filter(barista -> barista.isAlive()).collect(Collectors.toSet());
	}
}
