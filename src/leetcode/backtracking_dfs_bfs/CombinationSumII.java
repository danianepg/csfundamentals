package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Medium
 *
 * @author danianepg
 *
 *         Given a collection of candidate numbers (candidates) and a target number (target), find all unique
 *         combinations in candidates where the candidate numbers sums to target.
 *
 *         Each number in candidates may only be used once in the combination.
 *
 *         Note:
 *
 *         All numbers (including target) will be positive integers.
 *         The solution set must not contain duplicate combinations.
 *         Example 1:
 *
 *         Input: candidates = [10,1,2,7,6,1,5], target = 8,
 *         A solution set is:
 *         [
 *         [1, 7],
 *         [1, 2, 5],
 *         [2, 6],
 *         [1, 1, 6]
 *         ]
 *         Example 2:
 *
 *         Input: candidates = [2,5,2,1,2], target = 5,
 *         A solution set is:
 *         [
 *         [1,2,2],
 *         [5]
 *         ]
 *
 */
public class CombinationSumII {

  public static List<List<Integer>> combinationSum2(final int[] candidates, final int target) {
    final List<List<Integer>> allComb = new ArrayList<>();
    Arrays.sort(candidates);
    backtracking(allComb, new ArrayList<>(), candidates, target, 0, 0);
    return allComb;
  }

  /**
   * Same solution as the CombinatioSum problem, but at this one we can't have repeated numbers on the combinations. To
   * reach that, it is necessary to pass the index as my next element instead of the current element.
   *
   * The loop is broken whenever the tempSum is greater than the target.
   *
   * Time complexity O(2^n) ?
   *
   * @param allComb
   * @param oneComb
   * @param candidates
   * @param target
   * @param curSum
   * @param index
   */
  public static void backtracking(final List<List<Integer>> allComb, final List<Integer> oneComb,
      final int[] candidates, final int target, final int curSum, final int index) {

    if (curSum == target && !allComb.contains(oneComb)) {
      allComb.add(new ArrayList<>(oneComb));
    }

    for (int i = index; i < candidates.length; i++) {

      final int tempCand = candidates[i];
      final int tempSum = curSum + tempCand;

      if (tempSum > target) {
        return;
      }

      oneComb.add(tempCand);
      backtracking(allComb, oneComb, candidates, target, tempSum, i + 1);
      oneComb.remove(oneComb.size() - 1);

    }

  }

  public static void main(final String[] args) {
    final int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
    final int target = 8;

//    final int[] candidates = { 2, 5, 2, 1, 2 };
//    final int target = 5;

    System.out.println(combinationSum2(candidates, target));

  }

}
