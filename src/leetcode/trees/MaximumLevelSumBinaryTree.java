package leetcode.trees;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 * Medium
 *
 * @author z003xfbr
 *
 *         Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 *         Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 *         Example 1:
 *
 *         @formatter:off
 *
 *                1
 *               / \
 *              7   0
 *             / \
 *            7  -8
 *
 *
 *         @formatter:on
 *
 *         Input: [1,7,0,7,-8,null,null]
 *         Output: 2
 *         Explanation:
 *         Level 1 sum = 1.
 *         Level 2 sum = 7 + 0 = 7.
 *         Level 3 sum = 7 + -8 = -1.
 *         So we return the level with the maximum sum which is level 2.
 *
 *
 *         Note:
 *
 *         The number of nodes in the given tree is between 1 and 10^4.
 *         -10^5 <= node.val <= 10^5
 *
 */
public class MaximumLevelSumBinaryTree {

  public static int maxLevelSum(final TreeNode root) {
    int sum = 0;
    int maxSum = Integer.MIN_VALUE;

    final Queue<TreeNode> queue = new LinkedList<>();

    if (root == null) {
      return 0;
    }

    queue.add(root);
    int level = 0;
    int k = 0;

    while (!queue.isEmpty()) {
      sum = 0;
      k++;
      final int n = queue.size();

      for (int i = 0; i < n; i++) {
        final TreeNode temp = queue.remove();
        sum += temp.val;

        if (temp.right != null) {
          queue.add(temp.right);
        }

        if (temp.left != null) {
          queue.add(temp.left);
        }

      }

      if (sum > maxSum) {
        maxSum = sum;
        level = k;
      }

    }

    return level;
  }

  public static void main(final String[] args) {

    final TreeNode t1 = new TreeNode(1);
    final TreeNode t2 = new TreeNode(7);
    final TreeNode t3 = new TreeNode(0);
    final TreeNode t4 = new TreeNode(7);
    final TreeNode t5 = new TreeNode(-8);
    t1.left = t2;
    t1.right = t3;
    t2.left = t4;
    t2.right = t5;

    System.out.println(maxLevelSum(t1));

  }

}
