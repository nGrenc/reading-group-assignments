package at.jit.readinggroup;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TheGreatSentencesFinalizerClass {

    private String schema;
    private Diktionary diktionary;

    public TheGreatSentencesFinalizerClass(String schema, Diktionary dik) {
        this.schema = schema;
        this.diktionary = dik;

        validateSchema(schema);
    }

    void validateSchema(String schema) {
        for (char a : schema.toCharArray())
            if (a != 'n' && a != 'v')
                throw new InvalidSchemaException();
    }

    public List<String> findAllSentences(String sentence) {
        List<String> sentences = new ArrayList<>();
        sentences.add(sentence);
        List<String> sentenceWords = Arrays.asList(sentence.split(" "));
        if (sentenceWords.size() != schema.length()) {
            throw new InvalidSentenceStructureException();
        }
        sentences = replaceMissingWordInSentence(sentences);
            while (sentence.contains("?")) {
                sentences = replaceMissingWordInSentence(sentences);
                sentence = sentences.get(0);
            }
        return sentences;
    }

    public List<String> replaceMissingWordInSentence(List<String> sentences) {
        List<String> newSentences = new ArrayList<>();
        for(String sentence : sentences) {
            List<String> sentenceWords = Arrays.asList(sentence.split(" "));
            int position = sentenceWords.indexOf("?");
            if (position != -1) {
                String nextWord = position == sentenceWords.size() - 1 ?
                        null : sentenceWords.get(position + 1);
                String previousWord = position == 0 ?
                        null : sentenceWords.get(position - 1);
                List<String> possibleWords = readDictionary(position, nextWord, previousWord);
                newSentences.addAll(makeSentencesForMissingWord(sentenceWords, position, possibleWords));
            } else {
                newSentences.add(sentence);
            }
        }
        return newSentences;
    }

    private List<String> makeSentencesForMissingWord(List<String> sentenceWords, int positionOfMissingWord, List<String> dictionaryWords) {
        List<String> sentences = new ArrayList<>();
        for (String word : dictionaryWords) {
            List<String> clonedSentence = new ArrayList<>(sentenceWords);
            clonedSentence.set(positionOfMissingWord, word);
            sentences.add(String.join(" ", clonedSentence));
        }
        return sentences;
    }

    private List<String> readDictionary(int curentPosition, String nextWord, String previousWord) {
        String plural = "s";
        List<String> possibleWords = new ArrayList<>();
        if (schema.charAt(curentPosition) == 'n') {
            if (curentPosition == 0 && !nextWord.equals("?")) {
                possibleWords = nextWord.endsWith(plural) ? diktionary.nouns() : diktionary.nounsPlural();
            } else {
                possibleWords = Stream.concat(diktionary.nounsPlural().stream(), diktionary.nouns().stream()).collect(Collectors.toList());
            }
        }
        if (schema.charAt(curentPosition) == 'v' && previousWord != null) {
                possibleWords = previousWord.endsWith(plural) ? diktionary.verbsPlural() : diktionary.verbs();
        }

        return possibleWords;
    }

    class InvalidSchemaException extends RuntimeException {
    }

    class InvalidSentenceStructureException extends RuntimeException {
    }

}
