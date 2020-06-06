package leetcode.trees;

import java.util.ArrayList;
import java.util.List;

import leetcode.util.TreeNode;
import leetcode.util.Util;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Medium
 *
 * @author danianepg
 *
 *         Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 *         Note:
 *         You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
  @formatter:off
 *          Example 1:
 *
 *         Input: root = [3,1,4,null,2], k = 1
 *            3
 *           / \
 *          1   4
 *           \
 *            2
 *         Output: 1
 *
 *         Example 2:
 *         Input: root = [5,3,6,2,4,null,null,1], k = 3
 *             5
 *            / \
 *           3   6
 *          / \
 *         2   4
 *        /
 *        1
 *
 *         Output: 3
 *         Follow up:
 *         What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
 *         frequently? How would you optimize the kthSmallest routine?
 *
  @formatter:on

 */
public class KthSmallestElementInABST {

  public static int kthSmallest(final TreeNode root, final int k) {
    final List<Integer> arr = inorder(root, new ArrayList<>());
    return arr.get(k - 1);
  }

  // Time and space: O(n)
  private static List<Integer> inorder(final TreeNode root, final List<Integer> arr) {
    if (root == null) {
      return arr;
    }

    inorder(root.left, arr);
    arr.add(root.val);
    inorder(root.right, arr);
    return arr;
  }

  public static void main(final String[] args) {
    final TreeNode root = Util.stringToTreeNode("[3,1,4,null,2]");
    final int k = 1;
    System.out.println(kthSmallest(root, k));
  }

}
