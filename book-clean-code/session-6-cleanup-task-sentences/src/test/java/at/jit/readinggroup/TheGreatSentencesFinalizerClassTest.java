package at.jit.readinggroup;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TheGreatSentencesFinalizerClassTest {

	private Diktionary diktionary;

	@Before
	public void setup() {
		this.diktionary = new Diktionary("word.properties");
	}


	@Test (expected = TheGreatSentencesFinalizerClass.InvalidSentenceStructureException.class)
	public void shouldFailWhenSentenceDoesNotMatchSchema() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		finalizer.findAllSentences("dog ?");
	}

	@Test (expected = TheGreatSentencesFinalizerClass.InvalidSchemaException.class)
	public void shouldFailWhenSchemaIsInvalid() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvnb", diktionary);

		finalizer.findAllSentences("dog ?");
	}

	@Test
	public void shouldReturnIfSentenceIsComplete() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("dog chases cat");

		List<String> expected = new ArrayList<>();
		expected.add("dog chases cat");

		assertEquals(expected,actual);
	}

	@Test
	public void shouldFinishMiddleVerb() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("dog ? cat");

		List<String> expected = new ArrayList<>();
		expected.add("dog chases cat");
		expected.add("dog avoids cat");

		assertTrue(expected.size() == actual.size() &&
				expected.containsAll(actual) && actual.containsAll(expected));
	}

	@Test
	public void shouldSelectNounsPlural() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("? chase cat");

		List<String> expected = new ArrayList<>();
		expected.add("dogs chase cat");
		expected.add("cats chase cat");

		assertTrue(expected.size() == actual.size() &&
				expected.containsAll(actual) && actual.containsAll(expected));

	}

	@Test
	public void shouldSelectNounsSingular() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("? chases cat");

		List<String> expected = new ArrayList<>();
		expected.add("dog chases cat");
		expected.add("cat chases cat");

		assertTrue(expected.size() == actual.size() &&
				expected.containsAll(actual) && actual.containsAll(expected));
	}

	@Test
	public void shouldFinishLastNoun() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("dog chases ?");

		List<String> expected = new ArrayList<>();
		expected.add("dog chases cat");
		expected.add("dog chases dog");
		expected.add("dog chases cats");
		expected.add("dog chases dogs");

		assertTrue(expected.size() == actual.size() &&
				expected.containsAll(actual) && actual.containsAll(expected));
	}

	@Test
	public void shouldFinishMissingVerbAndNoun() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("dog ? ?");

		List<String> expected = new ArrayList<>();
		expected.add("dog chases cat");
		expected.add("dog chases dog");
		expected.add("dog chases cats");
		expected.add("dog chases dogs");

		expected.add("dog avoids cat");
		expected.add("dog avoids dog");
		expected.add("dog avoids cats");
		expected.add("dog avoids dogs");

		assertTrue(expected.size() == actual.size() &&
				expected.containsAll(actual) && actual.containsAll(expected));
	}


	@Test
	public void shouldCompleteMultipleMissing() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		List<String> actual = finalizer.findAllSentences("? ? ?");

		List<String> expected = new ArrayList<>();
		expected.add("dog chases cat");
		expected.add("dog chases dog");
		expected.add("dog chases cats");
		expected.add("dog chases dogs");

		expected.add("dogs chase cat");
		expected.add("dogs chase dog");
		expected.add("dogs chase cats");
		expected.add("dogs chase dogs");

		expected.add("cat chases cat");
		expected.add("cat chases dog");
		expected.add("cat chases cats");
		expected.add("cat chases dogs");

		expected.add("cats chase cat");
		expected.add("cats chase dog");
		expected.add("cats chase cats");
		expected.add("cats chase dogs");

		expected.add("dog avoids cat");
		expected.add("dog avoids dog");
		expected.add("dog avoids cats");
		expected.add("dog avoids dogs");

		expected.add("dogs avoid cat");
		expected.add("dogs avoid dog");
		expected.add("dogs avoid cats");
		expected.add("dogs avoid dogs");

		expected.add("cat avoids cat");
		expected.add("cat avoids dog");
		expected.add("cat avoids cats");
		expected.add("cat avoids dogs");

		expected.add("cats avoid cat");
		expected.add("cats avoid dog");
		expected.add("cats avoid cats");
		expected.add("cats avoid dogs");

		assertTrue(expected.size() == actual.size() &&
				expected.containsAll(actual) && actual.containsAll(expected));
	}
}