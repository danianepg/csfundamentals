package leetcode.backtracking_dfs_bfs;

import java.util.HashMap;

import leetcode.util.TreeNode;

/**
 *
 * https://leetcode.com/problems/path-sum-iii/
 * Easy ???
 *
 * @author Daniane P. Gomes
 *
 *         You are given a binary tree in which each node contains an integer
 *         value.
 *
 *         Find the number of paths that sum to a given value.
 *
 *         The path does not need to start or end at the root or a leaf, but it
 *         must go downwards (traveling only from parent nodes to child nodes).
 *
 *         The tree has no more than 1,000 nodes and the values are in the range
 *         -1,000,000 to 1,000,000.
 *
 *         Example:
 *
 *         root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *         @formatter:off
 *
 *               10
 *              /  \
 *             5   -3
 *            / \    \
 *           3   2   11
 *          / \   \
 *         3  -2   1
 *
 *         @formatter:on
 *
 *         Return 3. The paths that sum to 8 are:
 *
 *         1. 5 -> 3
 *         2. 5 -> 2 -> 1
 *         3. -3 -> 11
 *
 */
public class PathSumIII {

  public int pathSum(final TreeNode root, final int sum) {
    final HashMap<Integer, Integer> preSum = new HashMap<>();
    preSum.put(0, 1);
    return this.helper(root, 0, sum, preSum);
  }

  /**
   * The map will store the frequency that some node add up to a certain number. If there's a number on the map which is
   * equals to the difference between the current sum and the target value, it means that at some point on the tree
   * there were numbers that add up to the target.
   *
   *
   *
   * @param root
   * @param currSum
   * @param target
   * @param preSum
   * @return
   */
  public int helper(final TreeNode root, int currSum, final int target, final HashMap<Integer, Integer> preSum) {
    if (root == null) {
      return 0;
    }

    // update the prefix sum by adding the current val
    currSum += root.val;

    // get the number of valid path, ended by the current node
    int res = preSum.getOrDefault(currSum - target, 0);

    // update the map with the current sum, so the map is good to be passed to the next recursion
    preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

    // add the 3 parts discussed to know the number of valid paths
    res += this.helper(root.left, currSum, target, preSum) + this.helper(root.right, currSum, target, preSum);

    // restore the map
    preSum.put(currSum, preSum.get(currSum) - 1);
    return res;
  }

  public static void main(final String[] args) {
    final int sum = 8;

    final TreeNode root = new TreeNode(10);
    final TreeNode n2 = new TreeNode(5);
    final TreeNode n3 = new TreeNode(-3);
    root.left = n2;
    root.right = n3;

    final TreeNode n4 = new TreeNode(3);
    final TreeNode n5 = new TreeNode(2);
    n2.left = n4;
    n2.right = n5;

    final TreeNode n6 = new TreeNode(3);
    final TreeNode n7 = new TreeNode(-2);
    n4.left = n6;
    n4.right = n7;

    final TreeNode n8 = new TreeNode(1);
    n5.right = n8;

    final TreeNode n9 = new TreeNode(11);
    n3.right = n9;

//		int sum = 1;
//		List<Integer> arr = Arrays.asList(1);

//		int sum = 1;
//		List<Integer> arr = Arrays.asList(1,2);

    final PathSumIII p = new PathSumIII();
    System.out.println("RETURN " + p.pathSum(root, sum));
  }

}
