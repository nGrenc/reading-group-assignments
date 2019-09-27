package at.jit.readinggroup.fixedfiles;

public class CommonTimeHandler {

	public static final int toiletBusinessTime 	= 20; // Quick business

	public static final int waterPouringTime 	= 50;
	public static final int coffeeMachineWarmupTime = 50;
	public static final int coffeeMachineUseTime = 100;


	public static void passTheTime(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {}
	}

}
