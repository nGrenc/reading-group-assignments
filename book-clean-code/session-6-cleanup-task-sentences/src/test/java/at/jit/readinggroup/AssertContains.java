package at.jit.readinggroup;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class AssertContains {

	public static void assertContains(String target, String[] sources) {
		if (target == null || sources == null) {
			throw new RuntimeException("Target and sources Strings cannot be null!");
		}

		for (String source : sources) {
			if (target.equals(source)) {
				return;
			}
		}
		fail(String.format("String [%s] not found in String array [%s], but it is expected.", target, Arrays.toString(sources)));
	}

	public static void assertNotContains(String target, String[] sources) {
		if (target == null || sources == null) {
			throw new RuntimeException("Target and sources Strings cannot be null!");
		}

		for (String source : sources) {
			if (target.equals(source)) {
				fail(String.format("String [%s] found in String array [%s], but it is not expected.", target, Arrays.toString(sources)));
			}
		}
	}
}
