package leetcode.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import leetcode.util.TreeNode;

/**
 * https://leetcode.com/problems/delete-nodes-and-return-forest
 * Medium
 *
 * @author danianepg
 *
 *         Given the root of a binary tree, each node in the tree has a distinct value.
 *
 *         After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 *         Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 *         Example 1:
 *
 *         @formatter:off
 *
 *                      1
 *                   /     \
 *                  2       3
 *                 / \     / \
 *                4   5   6   7
 *
 *         @formatter:on
 *
 *         Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 *         Output: [[1,2,null,4],[6],[7]]
 *
 *
 *         Constraints:
 *
 *         The number of nodes in the given tree is at most 1000.
 *         Each node has a distinct value between 1 and 1000.
 *         to_delete.length <= 1000
 *         to_delete contains distinct values between 1 and 1000.
 *
 */
public class DeleteNodesAndReturnForest {

  public static List<TreeNode> delNodes(final TreeNode root, final int[] to_delete) {

    final List<TreeNode> forestLst = new ArrayList<>();
    final List<Integer> toDelete = Arrays.stream(to_delete).boxed().collect(Collectors.toList());

    forestLst.add(root);
    forestBFS(root, null, forestLst, toDelete);

    return forestLst;
  }

  public static void forestBFS(final TreeNode node, final TreeNode parent, final List<TreeNode> forestLst,
      final List<Integer> toDelete) {

    if (node == null) {
      return;
    }

    if (toDelete.contains(node.val)) {
      forestLst.remove(node);

      if (node.left != null) {
        forestLst.add(node.left);
      }

      if (node.right != null) {
        forestLst.add(node.right);
      }

      if (parent != null) {
        if (parent.left == node) {
          parent.left = null;
        } else {
          parent.right = null;
        }
      }
    }

    forestBFS(node.left, node, forestLst, toDelete);
    forestBFS(node.right, node, forestLst, toDelete);

  }

  public static void main(final String[] args) {

    final TreeNode t1 = new TreeNode(1);
    final TreeNode t2 = new TreeNode(2);
    final TreeNode t3 = new TreeNode(3);
    final TreeNode t4 = new TreeNode(4);
    final TreeNode t5 = new TreeNode(5);
    final TreeNode t6 = new TreeNode(6);
    final TreeNode t7 = new TreeNode(7);

    t1.left = t2;
    t1.right = t3;

    t2.left = t4;
    t2.right = t5;

    t3.left = t6;
    t3.right = t7;

    final int[] to_delete = { 3, 5 };

    System.out.println(delNodes(t1, to_delete));

  }

}
