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

		finalizer.finishSentence("dog ?");
	}

	@Test
	public void shouldReturnIfSentenceIsComplete() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		String[] newSentencess = finalizer.finishSentence("dog chases cat");

		assertEquals(1, newSentencess.length);
		assertContains(newSentencess, "dog chases cat");
	}

	@Test
	public void shouldFinishLastNoun() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		String[] newSentencess = finalizer.finishSentence("dog chases ?");

		assertContains(newSentencess, "dog chases cat");
		assertContains(newSentencess, "dog chases dog");
	}

	@Test
	public void shouldFinishMiddleVerb() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		String[] newSentencess = finalizer.finishSentence("dog ? cat");

		assertContains(newSentencess, "dog chases cat");
	}

	@Test
	public void shouldCompleteMultipleMissing() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		String[] newSentencess = finalizer.finishSentence("? ? ?");

		assertContains(newSentencess, "dog chases cat");
	}

	@Test
	public void shouldSelectPlural() {
		TheGreatSentencesFinalizerClass finalizer = new TheGreatSentencesFinalizerClass("nvn", diktionary);

		String[] newSentencess = finalizer.finishSentence("dogs ? cat");

		assertContains(newSentencess, "dogs chase cat");
		assertNotContains(newSentencess, "dogs chases cat");
	}


	private void assertNotContains(String[] sentenceCollections, String sentence) {
		if (Arrays.asList(sentenceCollections).contains(sentence)) {
			fail();
		}
	}

	private void assertContains(String[] sentenceCollections, String sentence) {
		assertContains(Arrays.asList(sentenceCollections), sentence);
	}

	private void assertContains(List<String> sentenceCollections, String sentence) {
		if (! sentenceCollections.contains(sentence)) {
			fail();
		}
	}
}