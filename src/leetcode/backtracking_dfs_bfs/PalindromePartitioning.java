package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * Medium
 *
 * @author danianepg
 *
 *         Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 *         Return all possible palindrome partitioning of s.
 *
 *         Example:
 *
 *         Input: "aab"
 *         Output:
 *         [
 *         ["aa","b"],
 *         ["a","a","b"]
 *         ]
 *
 */
public class PalindromePartitioning {

  /**
   * Another backtrack solution with the extra step to check if the combination is palindrome.
   *
   * To check if it is palindrome we have a slinding window on the word that keeps comparing the first and last
   * position, decrementing the window until it checks all the characters.
   *
   * The condition to break the recursion is when a word is not palindrome.
   *
   * @param s
   * @return
   */
  public static List<List<String>> partition(final String s) {

    final List<List<String>> allPalindromes = new ArrayList<>();
    backtrack(s, 0, new ArrayList<>(), allPalindromes);

    return allPalindromes;
  }

  public static void backtrack(final String word, final int start, final List<String> groups,
      final List<List<String>> allPalindromes) {

    if (start == word.length()) {
      allPalindromes.add(new ArrayList<>(groups));
      return;
    }

    for (int i = start + 1; i <= word.length(); i++) {

      if (!isPalindrome(word, start, i)) {
        continue;
      }

      groups.add(word.substring(start, i));
      backtrack(word, i, groups, allPalindromes);
      groups.remove(groups.size() - 1);
    }

  }

  public static Boolean isPalindrome(final String word, final int start, final int end) {

    int i = start;
    int j = end - 1;

    while (i <= j && j >= 0 && i < word.length()) {

      final char charStart = word.charAt(i);
      final char charEnd = word.charAt(j);

      if (charStart != charEnd) {
        return Boolean.FALSE;
      }

      i++;
      j--;
    }

    return Boolean.TRUE;
  }

  public static void main(final String[] args) {
    System.out.println(partition("aab"));
  }

}
