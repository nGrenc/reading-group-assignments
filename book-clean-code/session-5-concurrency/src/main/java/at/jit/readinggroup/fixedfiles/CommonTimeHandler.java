package at.jit.readinggroup.fixedfiles;

public class CommonTimeHandler {

	public static final int toiletBusinessTime 	= 50; // Quick business

	public static final int waterPouringTime 	= 200;
	public static final int coffeeMachineUseTime 	= 500;


	public static void passTheTime(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {}
	}

}
