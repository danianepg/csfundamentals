package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * Medium
 *
 * @author Daniane P. Gomes
 *
 *         Given a binary tree and a sum, find all root-to-leaf paths where each
 *         path's sum equals the given sum.
 *
 *         Note: A leaf is a node with no children.
 *
 *         Example:
 *
 *         Given the below binary tree and sum = 22,
 *
 *         @formatter:off
 *
 *               5
 *              /  \
 *            4     8
 *           /     / \
 *         11     13  4
 *         / \       / \
 *        7   2     5   1
 *
 *         @formatter:on
 *
 *         Return:
 *
 *         [ [5,4,11,2], [5,8,4,5] ]
 *
 */
public class PathSumII {

  public List<List<Integer>> pathSum(final TreeNode root, final int sum) {

    final List<List<Integer>> ans = new ArrayList<>();

    if (root != null) {
      this.dfs(root, sum, ans, new ArrayList<>());
    }

    return ans;
  }

  /**
   * Traverse the tree adding the root node to a temp list.
   * When a leaf node is found and the target (units remaining) is equals to the node value, add the temp array to the
   * final answer.
   * Call the function recursively for left e right while overwriting the target value with the units remaining to reach
   * the target sum.
   * Remove the last element to avoid adding same node twice.
   *
   * @param root
   * @param target
   * @param ans
   * @param tempList
   */
  public void dfs(final TreeNode root, final int target, final List<List<Integer>> ans, final List<Integer> tempList) {

    tempList.add(root.val);

    System.out.println("root.val=" + root.val + " target=" + target);

    if (root.left == null && root.right == null && target == root.val) {
      ans.add(new ArrayList<>(tempList));
    }

    if (root.left != null) {
      this.dfs(root.left, target - root.val, ans, tempList);
    }

    if (root.right != null) {
      this.dfs(root.right, target - root.val, ans, tempList);
    }

    tempList.remove(tempList.size() - 1);

  }

  public static void main(final String[] args) {
    final int sum = 22;
    final List<Integer> arr = Arrays.asList(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 5, 1);

//		int sum = 1;
//		List<Integer> arr = Arrays.asList(1);

//		int sum = 1;
//		List<Integer> arr = Arrays.asList(1,2);

    final PathSumII p = new PathSumII();
    TreeNode root = new TreeNode(0);
    root = TreeNode.insertLevelOrder(arr, root, 0);
    System.out.println("RETURN " + p.pathSum(root, sum));
  }

}
