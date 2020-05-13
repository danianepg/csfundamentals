package leetcode.backtracking_dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.util.TreeNode;

public class DfsBfsExamples {

  public static void inOrder(final TreeNode root) {
    if (root != null) {
      inOrder(root.left);
      System.out.print(root.val + " ");
      inOrder(root.right);
    }
  }

  public static void preOrder(final TreeNode root) {
    if (root != null) {
      System.out.print(root.val + " ");
      preOrder(root.left);
      preOrder(root.right);
    }
  }

  public static void postOrder(final TreeNode root) {
    if (root != null) {
      postOrder(root.left);
      postOrder(root.right);
      System.out.print(root.val + " ");
    }
  }

  public static void bfs(final TreeNode root) {

    final Queue<TreeNode> queue = new LinkedList<>();

    if (root != null) {
      queue.offer(root);
    }

    while (!queue.isEmpty()) {
      final TreeNode temp = queue.poll();
      System.out.print(temp.val + " ");

      if (temp.left != null) {
        queue.offer(temp.left);
      }

      if (temp.right != null) {
        queue.offer(temp.right);
      }

    }
  }

  public static void main(final String[] args) {

    final TreeNode n1 = new TreeNode(1);
    final TreeNode n2 = new TreeNode(2);
    final TreeNode n3 = new TreeNode(3);
    final TreeNode n4 = new TreeNode(4);
    final TreeNode n5 = new TreeNode(5);
    final TreeNode n6 = new TreeNode(6);
    final TreeNode n7 = new TreeNode(7);
    final TreeNode n12 = new TreeNode(12);
    final TreeNode n18 = new TreeNode(18);
    final TreeNode n19 = new TreeNode(19);

    n4.left = n6;
    n4.right = n7;
    n5.left = n12;
    n2.left = n4;
    n2.right = n5;
    n3.left = n18;
    n3.right = n19;
    n1.left = n2;
    n1.right = n3;

    System.out.println("InOrder");
    inOrder(n1);

    System.out.println("\nPreOrder");
    preOrder(n1);

    System.out.println("\nPostOrder");
    postOrder(n1);

    System.out.println("\nBFS");
    bfs(n1);

  }

}
