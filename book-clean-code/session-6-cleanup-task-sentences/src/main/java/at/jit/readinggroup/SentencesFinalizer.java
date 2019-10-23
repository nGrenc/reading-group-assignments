package at.jit.readinggroup;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SentencesFinalizer {

	private static final String WORDS_DELIMITER = " ";
	private static final String PLURAL_MODIFIER = "s";

	private String schema;
	private LocalDictionary dictionary;

	public SentencesFinalizer(String schema, LocalDictionary dictionary) {
		this.schema = schema;
		this.dictionary = dictionary;

		validateSchema();
	}

	void validateSchema() {
		for (char schemaChar : this.schema.toCharArray()) {
			if (! WordType.isValidType(schemaChar)) {
				throw new InvalidSchemaException();
			}
		}
	}


	public String[] finishSentence(String sentence) {
		String[] sentenceParts = sentence.split(WORDS_DELIMITER);
		if (sentenceParts.length != schema.length()) {
			throw new InvalidSentenceStructureException();
		}

		return finishSentence(sentenceParts, 0).toArray(new String[0]);
	}

	public List<String> finishSentence(String[] splitSentence, int currentProcessingIndex) {

		// Recursion exit condition
		if (currentProcessingIndex >= splitSentence.length) {
			return Collections.singletonList(String.join(WORDS_DELIMITER, splitSentence));
		}

		int nextProcessingIndex = currentProcessingIndex + 1;
		if (! splitSentence[currentProcessingIndex].equals("?")) {
			return finishSentence(splitSentence, nextProcessingIndex);
		}
		else {
			List<String> sentences = new ArrayList<>();
			for (String word : viableWords(splitSentence, currentProcessingIndex)) {
				String[] splitSentenceCopy = splitSentence.clone();
				splitSentenceCopy[currentProcessingIndex] = word;
				sentences.addAll(finishSentence(splitSentenceCopy, nextProcessingIndex));
			}
			return sentences;
		}
	}

	private String[] viableWords(String[] splitSentence, int currentProcessingIndex) {
		switch (WordType.findType(schema.charAt(currentProcessingIndex))) {
			default:
			case NOUN:
				return ArrayUtils.addAll(dictionary.nounsPlural(), dictionary.nouns());
			case VERB:
				if (currentProcessingIndex == 0) {
					return ArrayUtils.addAll(dictionary.verbsPlural(), dictionary.verbs());
				} else if (splitSentence[currentProcessingIndex - 1].endsWith(PLURAL_MODIFIER)) {
					return dictionary.verbsPlural();
				} else {
					return dictionary.verbs();
				}
		}
	}


	class InvalidSchemaException extends RuntimeException {
	}
	class InvalidSentenceStructureException extends RuntimeException {
	}

}
