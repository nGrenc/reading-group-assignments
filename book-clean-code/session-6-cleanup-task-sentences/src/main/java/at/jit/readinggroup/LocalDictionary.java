package at.jit.readinggroup;

import java.io.IOException;
import java.util.Properties;

public class LocalDictionary {

	private final static String WORD_DELIMITER = ",";

	private String[] nouns;
	private String[] verbs;
	private String[] nounsPlural;
	private String[] verbsPlural;

	public LocalDictionary(String sourceFile) {
		try {
			Properties prop = new Properties();
			prop.load(LocalDictionary.class.getClassLoader().getResourceAsStream(sourceFile));
			loadWordsFromProperties(prop);
		} catch (IOException | NullPointerException e) {
			throw new LocalDictionaryException("Property file [" + sourceFile + "] not found.");
		}
	}

	private void loadWordsFromProperties(Properties properties) {
		nouns = properties.getProperty("nouns").split(WORD_DELIMITER);
		verbs = properties.getProperty("verbs").split(WORD_DELIMITER);
		nounsPlural = properties.getProperty("nouns_plu").split(WORD_DELIMITER);
		verbsPlural = properties.getProperty("verbs_plu").split(WORD_DELIMITER);
	}

	public String[] nouns() {
		return nouns;
	}

	public String[] nounsPlural() {
		return nounsPlural;
	}

	public String[] verbs() {
		return verbs;
	}

	public String[] verbsPlural() {
		return verbsPlural;
	}

	class LocalDictionaryException extends RuntimeException {
		LocalDictionaryException(String errorMessage) {
			super(errorMessage);
		}
	}
}
