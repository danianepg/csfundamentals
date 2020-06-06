package hackerrank.amazon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
 *
 * @author danianepg
 *
 */
public class LevelOrderTraversal {

  public static class Node {

    Node left;

    Node right;

    int data;

    Node(final int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  // O(vertices + edges)
  public static void levelOrder(final Node root) {

    final StringBuilder sb = new StringBuilder();

    if (root == null) {
      return;
    }

    final Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      final int size = queue.size();
      for (int i = 0; i < size; i++) {

        final Node aux = queue.poll();
        sb.append(aux.data + " ");

        if (aux.left != null) {
          queue.offer(aux.left);
        }

        if (aux.right != null) {
          queue.offer(aux.right);
        }
      }
    }

    System.out.println(sb.toString().trim());

  }

  public static Node insert(final Node root, final int data) {
    if (root == null) {
      return new Node(data);
    } else {
      Node cur;
      if (data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
      } else {
        cur = insert(root.right, data);
        root.right = cur;
      }
      return root;
    }
  }

  public static void main(final String[] args) throws FileNotFoundException {
    final File file = new File(
        "C:\\Users\\danianepg\\Downloads\\tree-level-order-traversal-testcases\\input\\input00.txt");
    final Scanner scan = new Scanner(file);
    int t = scan.nextInt();
    Node root = null;
    while (t-- > 0) {
      final int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    levelOrder(root);
  }

}
