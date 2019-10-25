package at.jit.readinggroup;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Diktionary {

	private static final String WORD_DELIMITER = ",";

	private List<String> nouns;
	private List<String> verbs;
	private List<String> nouns_plu;
	private List<String> verbs_plu;

	public Diktionary(String sourceFile) {
		try {
			Properties prop = new Properties();
			prop.load(Diktionary.class.getClassLoader().getResourceAsStream(sourceFile));

			nouns = Arrays.asList(prop.getProperty("nouns").split(WORD_DELIMITER));
			verbs = Arrays.asList(prop.getProperty("verbs").split(WORD_DELIMITER));
			nouns_plu = Arrays.asList(prop.getProperty("nouns_plu").split(WORD_DELIMITER));
			verbs_plu = Arrays.asList(prop.getProperty("verbs_plu").split(WORD_DELIMITER));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> nouns() {
		return nouns;
	}

	public List<String> nounsPlural() {
		return nouns_plu;
	}


	public List<String> verbs() {
		return verbs;
	}


	public List<String> verbsPlural() {
		return verbs_plu;
	}

}
