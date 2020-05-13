package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * Medium
 *
 * @author z003xfbr
 *
 *         Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 *         Example:
 *
 *         Input: n = 4, k = 2
 *         Output:
 *         [
 *         [2,4],
 *         [3,4],
 *         [2,3],
 *         [1,2],
 *         [1,3],
 *         [1,4],
 *         ]
 *
 */
public class Combinations {

  /**
   * The problem is asking for all the combinations of k numbers from 1 to k. It requires a backtracking solution, where
   * I have a List of List that will hold the final result, a temp list, current position to start iterate, n and k as
   * the parameters received
   * O(n^min{k,n-k})
   *
   * @param n
   * @param k
   * @return
   */
  public static List<List<Integer>> combine(final int n, final int k) {

    final List<List<Integer>> allCombinations = new ArrayList<>();

    if (k > n) {
      return allCombinations;
    }

    dfs(allCombinations, new ArrayList<>(), 1, n, k);

    return allCombinations;
  }

  /**
   * For each number from 1 until k, we iterate, add the numbers to a temp list and call the function recursively. When
   * the temp list reaches the desired size, it is added to the final list result.
   *
   * @param result
   * @param cur
   * @param pos
   * @param n
   * @param k
   */
  public static void dfs(final List<List<Integer>> result, final List<Integer> cur, final int pos, final int n,
      final int k) {

    if (cur.size() == k) {
      result.add(new ArrayList<>(cur));
      return;
    }

    for (int i = pos; i <= n; i++) {
      cur.add(i);
      // System.out.println("pos " + pos + " i=" + i);
      dfs(result, cur, i + 1, n, k);
      // We remove the last element from the list so in the next iteration it wont be verified again. This is necessary
      // only when we are using the list received by parameter. If we create a new list on every iteration remove is no
      // necessary.
      cur.remove(cur.size() - 1);
    }
  }

  public static void main(final String[] args) {
    final List<List<Integer>> comb = combine(4, 2);
    System.out.println(comb);

  }

}
