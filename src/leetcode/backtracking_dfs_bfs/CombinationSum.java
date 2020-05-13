package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * Medium
 *
 * @author z003xfbr
 *
 *         Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all
 *         unique combinations in candidates where the candidate numbers sums to target.
 *
 *         The same repeated number may be chosen from candidates unlimited number of times.
 *
 *         Note:
 *
 *         All numbers (including target) will be positive integers.
 *         The solution set must not contain duplicate combinations.
 *         Example 1:
 *
 *         Input: candidates = [2,3,6,7], target = 7,
 *         A solution set is:
 *         [
 *         [7],
 *         [2,2,3]
 *         ]
 *
 *         Example 2:
 *
 *         Input: candidates = [2,3,5], target = 8,
 *         A solution set is:
 *         [
 *         [2,2,2,2],
 *         [2,3,3],
 *         [3,5]
 *         ]
 *
 */
public class CombinationSum {

  /**
   * Backtrack solution.
   *
   * Whenever the sum reaches the target, the temporary array holding the solutions will be copied to the final result
   * array.
   *
   * @param candidates
   * @param target
   * @return
   */
  public static List<List<Integer>> combinationSum(final int[] candidates, final int target) {

    final List<List<Integer>> allComb = new ArrayList<>();
    if (candidates == null || candidates.length == 0) {
      return allComb;
    }

    Arrays.sort(candidates);
    backtrack(allComb, new ArrayList<>(), candidates, target, 0, 0);
    return allComb;
  }

  public static void backtrack(final List<List<Integer>> allComb, final List<Integer> oneComb, final int[] candidates,
      final int target, final int index, final int curSum) {

    if (curSum == target) {
      System.out.println(oneComb);
      allComb.add(new ArrayList<>(oneComb));
    }

    System.out.println("");

    for (int i = index; i < candidates.length; i++) {

      System.out.println("candidates[" + i + "] = " + candidates[i]);

      // The condition to stop adding the number to itself is when the sum is greater than the target
      final int tempSum = curSum + candidates[i];
      if (tempSum > target) {
        return;
      }

      // To make sure that the candidate numbers will keep adding themselves, we add it to the temporary array and pass
      // the index as the same as the current index.
      // This way we will backtrack the combinations with itself.
      oneComb.add(candidates[i]);
      backtrack(allComb, oneComb, candidates, target, i, tempSum);
      oneComb.remove(oneComb.size() - 1);

    }

  }

  public static void main(final String[] args) {
//    final int[] candidates = { 2, 3, 6, 7 };
//    final int target = 7;

    final int[] candidates = { 2, 3, 5 };
    final int target = 8;

    final List<List<Integer>> comb = combinationSum(candidates, target);
    System.out.println(comb);

  }

}
