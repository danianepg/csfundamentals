package leetcode.stringmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/word-ladder/
 *
 * @author danianepg
 *
 */
public class WordLadder {

  public static int ladderLength(final String beginWord, final String endWord, final List<String> wordList) {

    final int wordsLength = beginWord.length();
    final HashMap<String, List<String>> allComb = transformWord(wordList, wordsLength);
    final Queue<MyPair> myQueue = new LinkedList<>();
    final MyPair m = new MyPair(beginWord, 1);
    myQueue.add(m);

    final HashMap<String, Boolean> visited = new HashMap<>();
    visited.put(beginWord, true);

    while (!myQueue.isEmpty()) {
      final MyPair node = myQueue.remove();
      final String word = node.getWord();
      final int level = node.getLevel();

      System.out.println("Word " + word);

      for (int i = 0; i < wordsLength; i++) {
        final String intermediateWord = getWordProcessed(wordsLength, word, i);

        System.out.println("intermediateWord " + intermediateWord);

        for (final String adjacentWord : allComb.getOrDefault(intermediateWord, new ArrayList<String>())) {

          System.out.println("adjacentWord " + adjacentWord);

          if (adjacentWord.equals(endWord)) {
            return level + 1;
          }

          if (!visited.containsKey(adjacentWord)) {
            System.out.println("\t ADD TO QUEUE " + adjacentWord);
            visited.put(adjacentWord, true);
            myQueue.add(new MyPair(adjacentWord, level + 1));
          }

        }

      }

    }

    return 0;

  }

  private static HashMap<String, List<String>> transformWord(final List<String> wordList, final int wordsLength) {

    final HashMap<String, List<String>> allComb = new HashMap<>();

    for (final String word : wordList) {
      for (int i = 0; i < wordsLength; i++) {

        final String newWord = getWordProcessed(wordsLength, word, i);
        final List<String> transformations = allComb.getOrDefault(newWord, new ArrayList<>());
        transformations.add(word);
        allComb.put(newWord, transformations);

      }
    }

    return allComb;
  }

  private static String getWordProcessed(final int wordsLength, final String word, final int i) {
    return word.substring(0, i) + "*" + word.substring(i + 1, wordsLength);
  }

  static class MyPair {

    private String word;

    private Integer level;

    public MyPair() {

    }

    public MyPair(final String word, final Integer level) {
      super();
      this.word = word;
      this.level = level;
    }

    public String getWord() {
      return this.word;
    }

    public void setWord(final String word) {
      this.word = word;
    }

    public Integer getLevel() {
      return this.level;
    }

    public void setLevel(final Integer level) {
      this.level = level;
    }

    @Override
    public String toString() {
      return "MyPair [word=" + this.word + ", level=" + this.level + "]";
    }

  }

  public static void main(final String[] args) {
    final String beginWord = "hit";
    final String endWord = "cog";
    final List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

    System.out.println(ladderLength(beginWord, endWord, wordList));
  }

}
