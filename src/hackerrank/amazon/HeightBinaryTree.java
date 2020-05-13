package hackerrank.amazon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem
 *
 * @author z003xfbr
 *
 */
public class HeightBinaryTree {

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

  /*
   * class Node
   * int data;
   * Node left;
   * Node right;
   */
  public static int height(final Node root) {

    int treeHeight = -1;

    if (root == null) {
      return treeHeight;
    }

    final Queue<Node> myQueue = new LinkedList<>();
    myQueue.offer(root);

    while (!myQueue.isEmpty()) {

      final int size = myQueue.size();

      for (int i = 0; i < size; i++) {

        final Node currentNode = myQueue.poll();

        if (currentNode.left != null) {
          myQueue.offer(currentNode.left);
        }

        if (currentNode.right != null) {
          myQueue.offer(currentNode.right);
        }

      }

      treeHeight++;

    }

    return treeHeight;
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
        "C:\\Users\\z003xfbr\\Downloads\\tree-height-of-a-binary-tree-testcases\\input\\input04.txt");

    final Scanner scan = new Scanner(file);
    int t = scan.nextInt();
    Node root = null;
    while (t-- > 0) {
      final int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    final int height = height(root);
    System.out.println(height);
  }

}
