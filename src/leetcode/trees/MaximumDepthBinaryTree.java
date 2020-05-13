package leetcode.trees;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.util.TreeNode;
import leetcode.util.Util;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * Easy
 *
 * @author z003xfbr
 *
 *         Given a binary tree, find its maximum depth.
 *
 *         The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf
 *         node.
 *
 *         Note: A leaf is a node with no children.
 *
 *         Example:
 *
 *         Given binary tree [3,9,20,null,null,15,7],
 *
 *         @formatter:off
 *
 *             3
 *            / \
 *           9   20
 *               / \
 *              15  7
 *
 *         @formatter:on
 *
 *         return its depth = 3.
 *
 */
public class MaximumDepthBinaryTree {

  public static int maxDepth(final TreeNode root) {

//    return maxDepthBFS(root);
    return maxDepthDFS(root);
  }

  public static int maxDepthDFS(final TreeNode root) {

    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    }

    if (root.left != null && root.right == null) {
      return maxDepthDFS(root.left) + 1;
    }

    if (root.right != null && root.left == null) {
      return maxDepthDFS(root.right) + 1;
    }

    final int maxDepthLeft = maxDepthDFS(root.left);
    final int maxDepthRight = maxDepthDFS(root.right);

    return Math.max(maxDepthLeft, maxDepthRight) + 1;

  }

  public static int maxDepthBFS(final TreeNode root) {

    if (root == null) {
      return 0;
    }

    int depth = 0;
    final Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {

      for (int i = queue.size(); i > 0; i--) {

        final TreeNode node = queue.poll();

        if (node.right != null) {
          queue.offer(node.right);
        }

        if (node.left != null) {
          queue.offer(node.left);
        }

      }

      depth++;

    }

    return depth;
  }

  public static void main(final String[] args) {
    final TreeNode n = Util.stringToTreeNode("[3,9,20,null,null,15,7]");

    System.out.println(maxDepth(n));
  }

}
