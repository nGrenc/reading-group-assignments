package at.jit.readinggroup;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheGreatSentencesFinalizerClass {

	private String plural = "s";

	private String sc;
	private Diktionary dik;

	public TheGreatSentencesFinalizerClass(String schema, Diktionary dik) {
		this.sc = schema;
		this.dik = dik;

		validateSchema(schema);
	}

	void validateSchema(String schema) {
		for (char a : schema.toCharArray())
			if (a != 'n' && a != 'v')
				throw new InvalidSchemaException();
	}


	public String[] finishSentence(String sentence) {
		List<String> sentences = new ArrayList<>();
		String[] sParts = sentence.split(" ");
		if (sParts.length != sc.length()) {
			throw new InvalidSentenceStructureException();
		}

		if (! sentence.contains("?")) {
			return new String[] {sentence};
		}
		else {
			for (int x = 0; x < sc.length(); x++)
				if ("?".equals(sParts[x])) {

					if (sc.charAt(x) == 'n') {
						String[] nouns = ArrayUtils.addAll(dik.nounsPlural(), dik.nouns());
						for (String noun : nouns) {
							String[] sPartsCopy = sParts.clone();
							sPartsCopy[x] = noun;
							sentences.addAll(Arrays.asList(finishSentence(String.join(" ", sPartsCopy))));
						}
					}

					if (sc.charAt(x) == 'v') {
						String[] nouns = (x != 0 && sParts[x-1].endsWith(plural)) ? dik.verbsPlural() : dik.verbs();
						for (String noun : nouns) {
							String[] sPartsCopy = sParts.clone();
							sPartsCopy[x] = noun;
							sentences.addAll(Arrays.asList(finishSentence(String.join(" ", sPartsCopy))));
						}
					}

				}

		}
		return sentences.toArray(new String[0]);
	}


	class InvalidSchemaException extends RuntimeException {
	}
	class InvalidSentenceStructureException extends RuntimeException {
	}

}
