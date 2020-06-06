package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Medium
 *
 * @author danianepg
 *
 *         Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by
 *         level).
 *
 *         For example:
 *         Given binary tree [3,9,20,null,null,15,7],
 *
 *         @formatter:off
 *
 *            3
 *           / \
 *          9   20
 *         / \
 *        15  7
 *
 *         return its level order traversal as:
 *         [
 *         [3],
 *         [9,20],
 *         [15,7]
 *         ]
 *
 *         @formatter:on
 *
 */
public class BinaryTreeLevelOrderTraversal {

  /**
   * We should traverse the tree using BFS but to save the node levels is the catch. Every time we iterate over the
   * queue, we should check its size.
   * Then we should iterate over it the same amount of times as the size, offer the children to the queue and add the
   * root to a temporary list. After the levels' iteration, we should add this list to a final list that will be the
   * answer.
   *
   * @param root
   * @return
   */
  public static List<List<Integer>> levelOrder(final TreeNode root) {

    // List that hold the final answer
    final List<List<Integer>> bfs = new ArrayList<>();
    final Queue<TreeNode> queue = new LinkedList<>();

    if (root == null) {
      return bfs;
    }

    queue.offer(root);
    while (!queue.isEmpty()) {

      final int level = queue.size();
      // Temporary list
      final List<Integer> levelLst = new ArrayList<>();

      for (int i = 0; i < level; i++) {
        if (queue.peek().left != null) {
          queue.offer(queue.peek().left);
        }
        if (queue.peek().right != null) {
          queue.offer(queue.peek().right);
        }
        levelLst.add(queue.poll().val);
      }
      bfs.add(levelLst);
    }

    return bfs;
  }

  public static void main(final String[] args) {

    // [1,2,3,4,null,null,5]

    // TreeNode left15 = new TreeNode(15);
    // TreeNode right7 = new TreeNode(7);
    //
    // TreeNode left9 = new TreeNode(9);
    // TreeNode right20 = new TreeNode(20);
    // right20.left = left15;
    // right20.right = right7;
    //
    // TreeNode root = new TreeNode(3);
    // root.left = left9;
    // root.right = right20;

    final leetcode.util.TreeNode n4 = new TreeNode(4);
    final TreeNode n2 = new TreeNode(2);
    n2.left = n4;

    final TreeNode n5 = new TreeNode(5);
    final TreeNode n3 = new TreeNode(3);
    n3.right = n5;

    final TreeNode n1 = new TreeNode(1);
    n1.left = n2;
    n1.right = n3;

    final List<List<Integer>> traversal = levelOrder(n1);
    System.out.println(traversal);

  }

}
