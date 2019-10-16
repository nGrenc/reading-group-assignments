package at.jit.readinggroup;

import java.io.IOException;
import java.util.Properties;

public class Diktionary {

	private final static String WORD_DELIMITER = ",";

	private String[] nouns;
	private String[] verbs;
	private String[] nouns_plu;
	private String[] verbs_plu;

	public Diktionary(String sourceFile) {
		try {
			Properties prop = new Properties();
			prop.load(Diktionary.class.getClassLoader().getResourceAsStream(sourceFile));

			nouns = prop.getProperty("nouns").split(WORD_DELIMITER);
			verbs = prop.getProperty("verbs").split(WORD_DELIMITER);
			nouns_plu = prop.getProperty("nouns_plu").split(WORD_DELIMITER);
			verbs_plu = prop.getProperty("verbs_plu").split(WORD_DELIMITER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] nouns() {
		return nouns;
	}

	public String[] nounsPlural() {
		return nouns_plu;
	}


	public String[] verbs() {
		return verbs;
	}


	public String[] verbsPlural() {
		return verbs_plu;
	}

}
