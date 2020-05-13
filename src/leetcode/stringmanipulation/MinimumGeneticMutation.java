package leetcode.stringmanipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-genetic-mutation/
 *
 * @author z003xfbr
 *
 */
public class MinimumGeneticMutation {

  static class MyPair {

    private String word;

    private Integer step;

    public MyPair(final String word, final Integer step) {
      super();
      this.word = word;
      this.step = step;
    }

    public String getWord() {
      return this.word;
    }

    public void setWord(final String word) {
      this.word = word;
    }

    public Integer getStep() {
      return this.step;
    }

    public void setStep(final Integer step) {
      this.step = step;
    }

  }

  public static int minMutation(final String start, final String end, final String[] bank) {

    if (bank.length == 1 && bank[0].equals(end)) {
      return 1;
    }

    final Integer wordLength = start.length();
    final HashMap<String, List<String>> transformed = transformBank(bank, wordLength);

    final Queue<MyPair> myQueue = new LinkedList<>();
    final MyPair myPair = new MyPair(start, 1);
    myQueue.add(myPair);

    final HashMap<String, Boolean> visited = new HashMap<>();
    visited.put(start, Boolean.TRUE);

    while (!myQueue.isEmpty()) {

      final MyPair node = myQueue.remove();
      final String word = node.getWord();
      final Integer step = node.getStep();

      for (int i = 0; i < wordLength; i++) {
        final String processedWord = getTransformed(word, wordLength, i);

        final List<String> possibilities = transformed.getOrDefault(processedWord, new ArrayList<>());
        for (final String candidate : possibilities) {

          if (candidate.equals(end)) {
            return step;
          }

          if (!visited.containsKey(candidate)) {
            myQueue.add(new MyPair(candidate, step + 1));
            visited.put(candidate, Boolean.TRUE);
          }

        }
      }
    }

    return -1;
  }

  private static HashMap<String, List<String>> transformBank(final String[] bank, final Integer wordLength) {
    final HashMap<String, List<String>> transformed = new HashMap<>();

    for (final String word : bank) {
      for (int i = 0; i < wordLength; i++) {

        final String newWord = getTransformed(word, wordLength, i);
        final List<String> transformedLst = transformed.getOrDefault(newWord, new ArrayList<>());
        transformedLst.add(word);
        transformed.put(newWord, transformedLst);
      }
    }
    return transformed;
  }

  private static String getTransformed(final String word, final int wordLength, final int i) {
    final String newWord = word.substring(0, i) + "*" + word.substring(i + 1, wordLength);
    return newWord;
  }
}
