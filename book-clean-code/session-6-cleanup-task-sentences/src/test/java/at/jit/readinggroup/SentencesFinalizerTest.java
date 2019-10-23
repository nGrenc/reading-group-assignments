package at.jit.readinggroup;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static at.jit.readinggroup.AssertContains.assertContains;
import static at.jit.readinggroup.AssertContains.assertNotContains;
import static org.junit.Assert.*;

public class SentencesFinalizerTest {

	private LocalDictionary localDictionary;

	@Before
	public void setup() {
		this.localDictionary = new LocalDictionary("word.properties");
	}


	@Test (expected = SentencesFinalizer.InvalidSchemaException.class)
	public void shouldFailWhenSchemaIsUnknown() {
		new SentencesFinalizer("nm", localDictionary);
	}

	@Test (expected = SentencesFinalizer.InvalidSentenceStructureException.class)
	public void shouldFailWhenSentenceDoesNotMatchSchema() {
		SentencesFinalizer finalizer = new SentencesFinalizer("nvn", localDictionary);

		finalizer.finishSentence("dog ?");
	}

	@Test
	public void shouldReturnIfSentenceIsComplete() {
		SentencesFinalizer finalizer = new SentencesFinalizer("nvn", localDictionary);

		String[] newSentences = finalizer.finishSentence("dog chases cat");

		assertEquals(1, newSentences.length);
		assertContains("dog chases cat", newSentences);
	}

	@Test
	public void shouldFinishLastNoun() {
		SentencesFinalizer finalizer = new SentencesFinalizer("nvn", localDictionary);

		String[] newSentences = finalizer.finishSentence("dog chases ?");

		assertContains("dog chases cat", newSentences);
		assertContains("dog chases dog", newSentences);
	}

	@Test
	public void shouldFinishMiddleVerb() {
		SentencesFinalizer finalizer = new SentencesFinalizer("nvn", localDictionary);

		String[] newSentences = finalizer.finishSentence("dog ? cat");

		assertContains("dog chases cat", newSentences);
	}

	@Test
	public void shouldCompleteMultipleMissing() {
		SentencesFinalizer finalizer = new SentencesFinalizer("nvn", localDictionary);

		String[] newSentences = finalizer.finishSentence("? ? ?");

		assertContains("dog chases cat", newSentences);
	}

	@Test
	public void shouldSelectPlural() {
		SentencesFinalizer finalizer = new SentencesFinalizer("nvn", localDictionary);

		String[] newSentences = finalizer.finishSentence("dogs ? cat");

		assertContains("dogs chase cat", newSentences);
		assertNotContains("dogs chases cat", newSentences);
	}

	@Test
	public void shouldStartUnknownWithPluralAndSingular() {
		SentencesFinalizer finalizer = new SentencesFinalizer("vn", localDictionary);

		String[] newSentences = finalizer.finishSentence("? cat");

		assertContains("chase cat", newSentences);
		assertContains("chases cat", newSentences);
	}

}