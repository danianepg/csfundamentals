package leetcode.hashtables;

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
 *         A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 * 
 *         Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is
 *         defined as ONE single character changed in the gene string.
 * 
 *         For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * 
 *         Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to
 *         make it a valid gene string.
 * 
 *         Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations
 *         needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * 
 *         Note:
 * 
 *         Starting point is assumed to be valid, so it might not be included in the bank.
 *         If multiple mutations are needed, all mutations during in the sequence must be valid.
 *         You may assume start and end string is not the same.
 * 
 * 
 *         Example 1:
 * 
 *         start: "AACCGGTT"
 *         end: "AACCGGTA"
 *         bank: ["AACCGGTA"]
 * 
 *         return: 1
 * 
 * 
 *         Example 2:
 * 
 *         start: "AACCGGTT"
 *         end: "AAACGGTA"
 *         bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 
 *         return: 2
 * 
 * 
 *         Example 3:
 * 
 *         start: "AAAAACCC"
 *         end: "AACCCCCC"
 *         bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 
 *         return: 3
 *
 */
public class MinimunGeneticMutation {

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

    return 0;
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

  public static void main(final String[] args) {

    final String start = "AAAAACCC";
    final String end = "AACCCCCC";
    final String[] bank = { "AAAACCCC", "AAACCCCC", "AACCCCCC" };

    System.out.println(minMutation(start, end, bank));
  }

}
