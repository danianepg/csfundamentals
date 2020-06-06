package leetcode.trees;

import leetcode.util.TreeNode;
import leetcode.util.Util;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * Easy
 *
 * @author danianepg
 *
 *         Given a binary tree, determine if it is height-balanced.
 *
 *         For this problem, a height-balanced binary tree is defined as:
 *
 *         a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 *         @formatter:off
 *
 *         Example 1:
 *
 *         Given the following tree [3,9,20,null,null,15,7]:
 *
 *              3
 *             / \
 *            9   20
 *                / \
 *              15   7
 *         Return true.
 *
 *         Example 2:
 *
 *         Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *              1
 *             / \
 *            2   2
 *           / \
 *          3   3
 *         / \
 *        4   4
 *
 *         Return false.
 *
 *         @formatter:on
 */
public class BalancedBinaryTree {

  /**
   * Traverse the binary tree counting the depth of it. Divide in 2 methods so one of them can calculate the depth and
   * the other can compare if its balanced.
   * Time complexity O(nlogn) ?
   *
   * @param root
   * @return
   */
  public static boolean isBalanced(final TreeNode root) {

    if (root == null) {
      return true;
    }

    if (root.left == null && root.right == null) {
      return true;
    }

    final int depthLeft = depthDFS(root.left);
    final int depthRight = depthDFS(root.right);

    if (Math.abs(depthLeft - depthRight) > 1) {
      return false;
    }

    return isBalanced(root.left) && isBalanced(root.right);

  }

  public static int depthDFS(final TreeNode root) {

    if (root == null) {
      return 0;
    }

    final int depthLeft = depthDFS(root.left);
    final int depthRight = depthDFS(root.right);

    if (Math.abs(depthLeft - depthRight) > 1) {
      return -2;
    }

    return Math.max(depthLeft, depthRight) + 1;

  }

  public static void main(final String[] args) {
    final TreeNode root1 = Util.stringToTreeNode("[3,9,20,null,null,15,7]");
    final TreeNode root2 = Util.stringToTreeNode("[1,2,2,3,3,null,null,4,4]");
    final TreeNode root3 = Util.stringToTreeNode("[1,2]");
    final TreeNode root4 = Util.stringToTreeNode("[1,2,2,3,null,null,3,4,null,null,4]");

    System.out.println(isBalanced(root1));// true
    System.out.println(isBalanced(root2));// false
    System.out.println(isBalanced(root3));// true
    System.out.println(isBalanced(root4));// false
  }

}
