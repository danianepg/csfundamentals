package leetcode.trees;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.util.TreeNode;
import leetcode.util.Util;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * Easy - Bosta e confuso
 *
 * @author z003xfbr
 *
 *         Given a binary tree, find its minimum depth.
 *
 *         The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf
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
 *             15   7
 *
 *         @formatter:on
 *
 *         return its minimum depth = 2.
 *
 */
public class MinimumDepthBinaryTree {

  public static int minDepth(final TreeNode root) {

    return minDepthBFS(root);
//    return minDepthDFS(root);
  }

  public static int minDepthBFS(final TreeNode root) {

    if (root == null) {
      return 0;
    }

    int depth = 1;
    final Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      final int size = queue.size();

      for (int i = 0; i < size; i++) {
        final TreeNode node = queue.poll();

        if (node.left == null && node.right == null) {
          return depth;
        }

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      depth++;
    }

    return depth;

  }

  public int minDepthDFS(final TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    }

    if (root.left != null && root.right == null) {
      return minDepth(root.left) + 1;
    }

    if (root.right != null && root.left == null) {
      return minDepth(root.right) + 1;
    }

    final int deptLeft = minDepth(root.left);
    final int deptRight = minDepth(root.right);

    final int minDept = Math.min(deptLeft, deptRight);
    return minDept + 1;
  }

  public static void main(final String[] args) {
//    System.out.println(minDepth(Util.stringToTreeNode("[]"))); // 0
    System.out.println(minDepth(Util.stringToTreeNode("[3,9,20,null,null,15,7]"))); // 2
  }

}
