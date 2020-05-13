package leetcode.backtracking_dfs_bfs;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/stickers-to-spell-word/
 * Hard
 *
 * @author z003xfbr
 *
 *         We are given N different types of stickers. Each sticker has a lowercase English word on it.
 *
 *         You would like to spell out the given target string by cutting individual letters from your collection of
 *         stickers and rearranging them.
 *
 *         You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 *
 *         What is the minimum number of stickers that you need to spell out the target? If the task is impossible,
 *         return -1.
 *
 *         Example 1:
 *
 *         Input:
 *
 *         ["with", "example", "science"], "thehat"
 *         Output:
 *
 *         3
 *         Explanation:
 *
 *         We can use 2 "with" stickers, and 1 "example" sticker.
 *         After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 *         Also, this is the minimum number of stickers necessary to form the target string.
 *         Example 2:
 *
 *         Input:
 *
 *         ["notice", "possible"], "basicbasic"
 *         Output:
 *
 *         -1
 *         Explanation:
 *
 *         We can't form the target "basicbasic" from cutting letters from the given stickers.
 *         Note:
 *
 *         stickers has length in the range [1, 50].
 *         stickers consists of lowercase English words (without apostrophes).
 *         target has length in the range [1, 15], and consists of lowercase English letters.
 *         In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target
 *         was chosen as a concatenation of two random words.
 *         The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved
 *         within 35ms on average.
 *
 */
public class StickersToSpellWord {

  private static int[][] maps;

  private static Map<String, Integer> memo;

  /**
   * Convert the stickers and the target in char arrays.
   * For each letter of each stickers, iterate over each letter of the target word and creates a new word target with
   * the characters missing.
   * Pass the missing part to the recursion
   *
   * @param stickers
   * @param target
   * @return
   */
  public static int minStickers(final String[] stickers, final String target) {

    memo = new HashMap<>();
    memo.put("", 0);

    // For each sticker creates an array counting the number of different letters that it contains
    maps = new int[stickers.length][26];
    for (int i = 0; i < stickers.length; i++) {
      maps[i] = toMap(stickers[i]);
    }

    return dfs(target);
  }

  private static int[] toMap(final String word) {
    final int[] dict = new int[26];

    for (final char ch : word.toCharArray()) {
      dict[ch - 'a']++;
      System.out.println(ch + " - " + dict[ch - 'a']);
    }
    return dict;
  }

  public static int dfs(final String target) {

    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    int min = Integer.MAX_VALUE;

    // Converts the target word in an array of char with the number of letters that it contains
    final int[] targetMap = toMap(target);

    for (final int[] map : maps) {

      // if the current word from sticker doesnt have the letter, skip it
      if (map[target.charAt(0) - 'a'] <= 0) {
        continue;
      }

      System.out.println("target = " + target);

      final StringBuilder newTarget = new StringBuilder();

      // traverse the array containing the target word to check the number of necessary letters to mount the word
      for (int i = 0; i < 26; i++) {
        if (targetMap[i] > 0) {

          System.out.println("targetMap[" + i + "] ( " + (char) ('a' + i) + " ) = " + targetMap[i]);

          // Number of letters missing out the current sticker
          final int diff = targetMap[i] - map[i];
          final int cond = Math.max(diff, 0);

          // rewrite the target word with the letters not found
          for (int time = 0; time < cond; time++) {
            newTarget.append((char) ('a' + i));
          }
        }
      }

      // call the function recursively with the new target that contains the missing letters
      final int cur = dfs(newTarget.toString());
      if (cur != -1) {
        min = Math.min(cur, min);
      }

    }

    // Updates the map with the number of stickers used to form this part of the word
    final int result = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
    System.out.println("put " + target + " = " + result);
    memo.put(target, result);

    return result;

  }

  public static void main(final String[] args) {
//    final String[] stickers = { "notice", "possible" };
//    final String target = "basicbasic";

    final String[] stickers = { "with", "example", "science" };
    final String target = "thehat";

    System.out.println(minStickers(stickers, target));

  }

}
