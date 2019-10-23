package at.jit.readinggroup;

import java.util.Arrays;

public enum WordType {
	NOUN('n'),
	VERB('v');

	private char typeSymbol;

	WordType(char typeSymbol) {
		this.typeSymbol = typeSymbol;
	}

	public static boolean isValidType(char typeSymbol) {
		return findType(typeSymbol) != null;
	}

	public static WordType findType(char typeSymbol) {
		return Arrays.asList(WordType.values()).stream()
				.filter(wordType -> typeSymbol == wordType.typeSymbol)
				.findFirst().orElse(null);
	}
}
