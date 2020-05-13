package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 *
 * @author Daniane P. Gomes
 *
 *         Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9
 *         can be used and each combination should be a unique set of numbers.
 *
 *         Note:
 *
 *         All numbers will be positive integers.
 *         The solution set must not contain duplicate combinations.
 *         Example 1:
 *
 *         Input: k = 3, n = 7
 *         Output: [[1,2,4]]
 *         Example 2:
 *
 *         Input: k = 3, n = 9
 *         Output: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class CombinationSumIII {

  public static List<List<Integer>> combinationSum3(final int k, final int n) {
    final List<List<Integer>> list = new ArrayList<>();
    backtrack(list, new ArrayList<Integer>(), k, n, 1);
    return list;
  }

  /**
   * Same backtrack solution, but the condition to break the recursion is the size of a temporary list. To make sure
   * that the will be reached, we can use a variable remain that will hold the units remaining to reach the desired sum.
   * It could be used as a sum too.
   *
   * @param list
   * @param tempList
   * @param k
   * @param remain
   * @param start
   */
  private static void backtrack(final List<List<Integer>> list, final List<Integer> tempList, final int k,
      final int remain, final int start) {

    System.out.println("remain " + remain + " - start " + start);

    if (tempList.size() > k) {
      return; /** no solution */

    } else if (tempList.size() == k && remain == 0) {
      System.out.println("\t ### ADD");
      list.add(new ArrayList<>(tempList));

    } else {
      for (int i = start; i <= 9; i++) {
        tempList.add(i);
        System.out.println("\t ### start again " + i + " - remain " + remain);
        backtrack(list, tempList, k, remain - i, i + 1);
        tempList.remove(tempList.size() - 1);
      }
    }
  }

  public static void main(final String[] args) {
    final List<List<Integer>> allCombinations = combinationSum3(3, 7);
    System.out.println(allCombinations);
  }

}
