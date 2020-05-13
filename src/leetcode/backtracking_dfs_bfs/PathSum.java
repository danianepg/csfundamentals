package leetcode.backtracking_dfs_bfs;

import java.util.Arrays;
import java.util.List;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 * Easy
 *
 * @author Daniane P. Gomes
 *
 *         Given a binary tree and a sum, determine if the tree has a
 *         root-to-leaf path such that adding up all the values along the path
 *         equals the given sum.
 *
 *         Note: A leaf is a node with no children.
 *
 *         Example:
 *
 *         Given the below binary tree and sum = 22,
 *
 *         @formatter:off
 *                5
 *              /   \
 *             4     8
 *            /     /  \
 *           11    13   4
 *          /  \         \
 *         7    2         1
 *
 *         @formatter:on
 *
 *         return true, as there exist a root-to-leaf path 5->4->11->2 which sum
 *         is 22.
 *
 */
public class PathSum {

  /**
   * Just traverse the binary tree and when the node has no children, compare the sum. In this algorithm instead of
   * adding up the values, we are subtracting the node values from the original sum. When a leaf is found (no left and
   * right nodes associated), if the value
   * is 0, it means that there's a path.
   *
   * We traverse the tree recursively calling left and right side.
   *
   * @param root
   * @param sum
   * @return
   */
  public static boolean hasPathSum(final TreeNode root, final int sum) {
    if (root == null) {
      return false;
    }

    if (root.left == null && root.right == null && sum - root.val == 0) {
      return true;
    }

    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
  }

  public static void main(final String[] args) {
//		int sum = 23;
//		List<Integer> arr = Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,null,1);

//		int sum = 1;
//		List<Integer> arr = Arrays.asList(1);

    final int sum = 1;
    final List<Integer> arr = Arrays.asList(1, 2);

    TreeNode root = new TreeNode(0);
    root = TreeNode.insertLevelOrder(arr, root, 0);
    System.out.println("RETURN " + hasPathSum(root, sum));
  }

}
