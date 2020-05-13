package leetcode.backtracking_dfs_bfs;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Easy
 *
 * @author Daniane P. Gomes
 *
 *         Given an array where elements are sorted in ascending order, convert
 *         it to a height balanced BST.
 *
 *         For this problem, a height-balanced binary tree is defined as a
 *         binary tree in which the depth of the two subtrees of every node
 *         never differ by more than 1.
 *
 *         Example:
 *
 *         Given the sorted array: [-10,-3,0,5,9],
 *
 *         One possible answer is: [0,-3,9,-10,null,5], which represents the
 *         following height balanced BST:
 *
 *
 *        @formatter:off
 *
 *          0
 *         / \
 *       -3   9
 *       /   /
 *     -10  5
 *
 *        @formatter:on
 *
 */
public class ConvertSortedArrayToBinarySearchTree {

  public static TreeNode sortedArrayToBST(final int[] nums) {

    if (nums == null || nums.length <= 0) {
      return null;
    }

    return buildTree(nums, 0, nums.length - 1);
  }

  /**
   * Since the array is sorted, all we need to do is to get its midpoint and keep going recursively getting the
   * midpoints and set them as left and right elements. The recursion will take care of adding the elements to its right
   * place. At every depth of the recursion we should consider for left node the initial position of the array until
   * midpoint -1 and for the right node, midpoint+1 until the end of the array. The recursion will be broken whenever
   * the left pointer is > than the right pointer.
   *
   * @param nums
   * @param left
   * @param right
   * @return
   */
  public static TreeNode buildTree(final int[] nums, final int left, final int right) {
    if (left > right) {
      return null;
    }

    final int midPoint = (left + right) / 2;

    System.out
        .println("midPoint " + midPoint + " left " + left + " right " + right + " nums[midPoint] " + nums[midPoint]);

    final TreeNode root = new TreeNode(nums[midPoint]);
    root.left = buildTree(nums, left, midPoint - 1);
    root.right = buildTree(nums, midPoint + 1, right);
    return root;

  }

  public static void main(final String[] args) {
    final int[] nums = { -10, -3, 0, 5, 9 };
    final TreeNode tree = sortedArrayToBST(nums);

    TreeNode.preOrder(tree);
  }

}
