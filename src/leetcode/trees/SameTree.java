package leetcode.trees;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/same-tree/
 * Easy
 *
 * @author z003xfbr
 *
 *         Given two binary trees, write a function to check if they are the same or not.
 *
 *         Two binary trees are considered the same if they are structurally identical and the nodes have the same
 *         value.
 *
 */
public class SameTree {

  /**
   * Time complexity : O(N), where N is a number of nodes in the tree, since one visits each node exactly once.
   *
   * Space complexity : O(log(N)) in the best case of completely balanced tree and O(N) in the worst case of completely
   * unbalanced tree, to keep a recursion stack.
   *
   * Traverse the tree comparing the values. Call it recursively and return the result of comparisons between left and
   * right.
   *
   * @param p
   * @param q
   * @return
   */
  public static boolean isSameTree(final TreeNode p, final TreeNode q) {

    if (p == null && q == null) {
      return Boolean.TRUE;
    }

    if (p == null || q == null) {
      return Boolean.FALSE;
    }

    if (p.val != q.val) {
      return Boolean.FALSE;
    }

    final Boolean isLeftValid = isSameTree(p.left, q.left);
    final Boolean isRightValid = isSameTree(p.right, q.right);

    return isLeftValid && isRightValid;

  }

  public static void main(final String[] args) {
    final TreeNode p = new TreeNode(1);
    final TreeNode p1 = new TreeNode(2);
    p.left = p1;

    final TreeNode q = new TreeNode(1);
    final TreeNode q1 = new TreeNode(2);
    q.right = q1;

    System.out.println(isSameTree(p, q));
  }
}
