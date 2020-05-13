package leetcode.trees;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/
 * Medium
 *
 * @author z003xfbr
 *
 *         Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins
 *         total.
 *
 *         In one move, we may choose two adjacent nodes and move one coin from one node to another. (The move may be
 *         from parent to child, or from child to parent.)
 *
 *         Return the number of moves required to make every node have exactly one coin.
 *
 *         Example 1:
 *
 *         Input: [3,0,0]
 *         Output: 2
 *         Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 *         Example 2:
 *
 *         Input: [0,3,0]
 *         Output: 3
 *         Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move
 *         one coin from the root of the tree to the right child.
 *         Example 3:
 *
 *         Input: [1,0,2]
 *         Output: 2
 *         Example 4:
 *
 *         Input: [1,0,0,null,3]
 *         Output: 4
 *
 */
public class DistributeCoinsInBinaryTree {

  private static int changes;

  public static int distributeCoins(final TreeNode root) {
    changes = 0;
    dfs(root);
    return changes;
  }

  // Time Complexity: O(N) , where N is the number of nodes in the tree.
  // Space Complexity: O(H), where H is the height of the tree.
  public static int dfs(final TreeNode node) {

    if (node == null) {
      return 0;
    }

    final int nodeValLeft = dfs(node.left);
    final int nodeValRight = dfs(node.right);

    changes += Math.abs(nodeValLeft) + Math.abs(nodeValRight);

    // The magic math is here. Node val + nodes on the left + nodes on the right - 1 (because 1 coin stays) is the
    // number of moves to make this node contain exactly 1 coin.
    return node.val + nodeValLeft + nodeValRight - 1;

  }

  public static void main(final String[] args) {

    final TreeNode n1 = new TreeNode(0);
    final TreeNode n2 = new TreeNode(3);
    final TreeNode n3 = new TreeNode(0);
    n1.left = n2;
    n1.right = n3;
    final int moves = distributeCoins(n1);

//    final TreeNode n1 = new TreeNode(3);
//    final TreeNode n2 = new TreeNode(0);
//    final TreeNode n3 = new TreeNode(0);
//    n1.left = n2;
//    n1.right = n3;
//    final int moves = distributeCoins(n1);

//    final TreeNode n1 = new TreeNode(1);
//    final TreeNode n2 = new TreeNode(0);
//    final TreeNode n2_2 = new TreeNode(3);
//    final TreeNode n3 = new TreeNode(0);
//    n1.left = n2;
//    n1.right = n3;
//    n2.right = n2_2;
//    final int moves = distributeCoins(n1);

    System.out.println(moves);

  }
}
