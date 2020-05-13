package leetcode.util;

import java.util.List;

public class TreeNode {

  public int val;

  public TreeNode left;

  public TreeNode right;

  public TreeNode(final int x) {
    this.val = x;
  }

  @Override
  public String toString() {
    return "" + this.val + "";
  }

  public void toTree() {
    System.out.println(this.toString(new StringBuilder(), true, new StringBuilder()).toString());
  }

  public StringBuilder toString(final StringBuilder prefix, final boolean isTail, final StringBuilder sb) {
    if (this.right != null) {
      this.right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
    }
    sb.append(prefix).append(isTail ? "|___" : "|---").append(this.val).append("\n");
    if (this.left != null) {
      this.left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
    }
    return sb;
  }

  public static TreeNode insertLevelOrder(final List<Integer> arr, TreeNode root, final int i) {
    // Base case for recursion
    if (i < arr.size()) {

      if (arr.get(i) != null) {
        final TreeNode temp = new TreeNode(arr.get(i));
        root = temp;

        // insert left child
        root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

        // insert right child
        root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
      }
    }
    return root;
  }

  // Function to print tree nodes in InOrder fashion
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

}
