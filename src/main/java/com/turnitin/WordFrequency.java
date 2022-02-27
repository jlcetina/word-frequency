package com.turnitin;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Word Frequency Coding Exercise
 * <p>
 * Write a Java program to calculate word frequency.
 * <p>
 * Given a piece of text, it should output each word in the text and the number of times it occurred.
 * <p>
 * The words should be sorted by frequency, in descending order.  Words with the same frequency can be in any order.
 * <p>
 * For example for the input “the cat is in the bag”, output should be:
 * <p>
 * 2 the
 * 1 cat
 * 1 is
 * 1 in
 * 1 bag
 *
 * @author Jose Cetina
 * @since 2/26/2022
 */
public class WordFrequency {

    public WordFrequency() {
    }

    /**
     * Print the frequency of each word that is contained in the given text.
     * <p>
     * For example for the input “the cat is in the bag”, output should be:
     * <p>
     * 2 the
     * 1 cat
     * 1 is
     * 1 in
     * 1 bag
     *
     * @throws IllegalArgumentException if the given phrase is null or empty
     */
    public void printFrequency(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Invalid text");
        }

        if (text.isBlank()) {
            throw new IllegalArgumentException("Please provide a non empty text");
        }

        System.out.println("\nGiven input: " + text);

        HashMap<String, Integer> groupedWords = this.groupWordsAndCalculateFrequency(text);
        orderAndPrintMap(groupedWords);
    }

    /**
     * This method use a Matcher in order to get the group of alphanumerics characters,
     * then each group is saved in a Map.
     *
     * @param text String to
     * @return Map<String, Integer> where the key will be each word and the value the number of occurrences.
     */
    private HashMap<String, Integer> groupWordsAndCalculateFrequency(String text) {
        Matcher matcher = Pattern.compile("\\w+").matcher(text);
        HashMap<String, Integer> map = new HashMap<>();

        while (matcher.find()) {
            String group = matcher.group();
            int frequency = 1;
            if (map.containsKey(group)) {
                frequency = map.get(group) + 1;
            }
            map.put(group, frequency);
        }
        return map;
    }

    /**
     * This method orders the given map in a reverse order and then traverse the map using
     * a for each and print the key (word) and value (frequencies)
     *
     * @param map Map with Words (key) and Frequencies (value)
     */
    private void orderAndPrintMap(HashMap<String, Integer> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(v -> System.out.println(v.getValue() + " " + v.getKey()));
    }

}
