package leetcode.stringmanipulation;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 * 
 * @author danianepg
 *
 *         Given a list of strings words representing an English Dictionary, find the longest word in words that can be
 *         built one character at a time by other words in words. If there is more than one possible answer, return the
 *         longest word with the smallest lexicographical order.
 * 
 *         If there is no answer, return the empty string.
 *         Example 1:
 *         Input:
 *         words = ["w","wo","wor","worl", "world"]
 *         Output: "world"
 *         Explanation:
 *         The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 *         Example 2:
 *         Input:
 *         words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 *         Output: "apple"
 *         Explanation:
 *         Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is
 *         lexicographically smaller than "apply".
 *         Note:
 * 
 *         All the strings in the input will only contain lowercase letters.
 *         The length of words will be in the range [1, 1000].
 *         The length of words[i] will be in the range [1, 30].
 *
 */
public class LongestWord {

  final String s = "abpppleegasedrrkdasdasdasgdasdadasodasdao";

  final Set<String> subsequences = new HashSet<>(Arrays.asList("able", "ale", "apple", "bale", "kangaroo", "banana",
      "kale", "gap", "grape", "dog", "doggo", "kappledoroo"));

  public void getLongestV4() {
    final Instant start = Instant.now();

    final Map<String, Integer> wordAndCharFound = this.subsequences.parallelStream()
        .collect(Collectors.toMap(Function.identity(), subsequence -> 0));

    final List<String> foundLst = new ArrayList<>();

    for (final char ch : this.s.toCharArray()) {

      wordAndCharFound.forEach((key, value) -> {
        if (key.contains(String.valueOf(ch))) {
          final Integer currentValue = wordAndCharFound.get(key) + 1;

          if (!foundLst.contains(key) && currentValue == key.length()) {
            foundLst.add(key);
          }
          wordAndCharFound.replace(key, currentValue);
        }
      });
    }

    final String biggest = foundLst.stream().max(Comparator.comparing(String::length)).get();
    System.out.println(biggest);

    final Instant end = Instant.now();
    System.err.println("V4 Duration: " + Duration.between(start, end).toNanos());
  }

  public void getLongestV3() {

    final Instant start = Instant.now();

    // Pre-process s: Build a map letter for s
    final Map<String, List<Integer>> mapChar = this.preprocessS();

    final List<String> listOrdered = this.subsequences.stream().collect(Collectors.toList());
    Collections.sort(listOrdered, (s1, s2) -> Integer.compare(s2.length(), s1.length()));

    final List<Integer> positionsLst = new ArrayList<>();

    for (final String sequence : listOrdered) {
      for (final char ch : sequence.toCharArray()) {

        if (mapChar.containsKey(String.valueOf(ch))) {
          final List<Integer> values = mapChar.get(String.valueOf(ch));
          positionsLst.add(values.get(0));
        } else {
          break;
        }
      }

      if (positionsLst.size() == sequence.length()) {
        for (final Integer i : positionsLst) {
          System.out.print(this.s.charAt(i) + "-");
        }
        break;

      } else {
        positionsLst.clear();
      }
    }

    final Instant end = Instant.now();
    System.err.println("V3 Duration: " + Duration.between(start, end).toNanos() + "\n");

  }

  private Map<String, List<Integer>> preprocessS() {
    final Map<String, List<Integer>> mapChar = new HashMap<>();
    for (int i = 0; i < this.s.length(); i++) {

      final String currentChar = String.valueOf(this.s.charAt(i));

      if (mapChar.containsKey(currentChar)) {
        mapChar.get(currentChar).add(i);
      } else {
        final List<Integer> indexesLst = new ArrayList<>();
        indexesLst.add(i);
        mapChar.put(currentChar, indexesLst);
      }
    }
    return mapChar;
  }

  public void getLongestV2() {
    final Instant start = Instant.now();

    final List<String> listOrdered = this.subsequences.stream().collect(Collectors.toList());
    Collections.sort(listOrdered, (s1, s2) -> Integer.compare(s2.length(), s1.length()));

//    final Predicate<String> isSizeSmaller = (subsequence -> subsequence.length() <= this.s.length());

//    listOrdered.stream().filter(isSizeSmaller).forEachOrdered(System.out::println);

    for (final String sequence : listOrdered) {

      String remainingChar = this.s;
      String currentWord = "";

      for (final char ch : sequence.toCharArray()) {

        final int indexOf = remainingChar.indexOf(ch);

        if (indexOf >= 0) {
          currentWord += ch;
          remainingChar = remainingChar.substring(indexOf + 1, remainingChar.length());

          if (remainingChar.length() < sequence.length()) {
            break;
          }
        }
      }

      System.out.println(currentWord);
      break;

    }

    final Instant end = Instant.now();
    System.err.println("v2 Duration: " + Duration.between(start, end).toNanos());

  }

  public void getLongestV1() {
    final Instant start = Instant.now();

    String lastWord = "";

    for (final String sequence : this.subsequences) {

      String remainingChar = this.s;
      String currentWord = "";

      for (final char ch : sequence.toCharArray()) {

        final int indexOf = remainingChar.indexOf(ch);

        if (indexOf >= 0) {
          currentWord += ch;
          remainingChar = remainingChar.substring(indexOf + 1, remainingChar.length());
        }
      }

      if (currentWord.length() > lastWord.length()) {
        lastWord = currentWord;
      }

    }

    System.out.println("lastWord " + lastWord);

    final Instant end = Instant.now();
    System.err.println("v1 Duration: " + Duration.between(start, end).toNanos());

  }

  public static void main(final String[] args) {

    final LongestWord longestWord = new LongestWord();
    longestWord.getLongestV1();
    longestWord.getLongestV2();
    longestWord.getLongestV3();
    longestWord.getLongestV4();
  }

}
