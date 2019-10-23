package at.jit.readinggroup;

import org.junit.Test;

import static at.jit.readinggroup.AssertContains.assertContains;
import static org.junit.Assert.*;

public class LocalDictionaryTest {

	@Test(expected = LocalDictionary.LocalDictionaryException.class)
	public void throwsExceptionWhenFileIsNotExisting() {
		new LocalDictionary("missing_file.properties");
	}

	@Test
	public void loadsWordsCorrectly() {
		LocalDictionary localDictionary = new LocalDictionary("word.properties");

		assertEquals(2, localDictionary.nouns().length);
		assertEquals(2, localDictionary.verbs().length);
		assertEquals(2, localDictionary.nounsPlural().length);
		assertEquals(2, localDictionary.verbsPlural().length);

		assertContains("dog", localDictionary.nouns());
		assertContains("cat", localDictionary.nouns());
		assertContains("chases", localDictionary.verbs());
		assertContains("avoids", localDictionary.verbs());
		assertContains("dogs", localDictionary.nounsPlural());
		assertContains("cats", localDictionary.nounsPlural());
		assertContains("chase", localDictionary.verbsPlural());
		assertContains("avoid", localDictionary.verbsPlural());
	}

}
