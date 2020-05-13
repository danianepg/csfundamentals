package leetcode.util;

import java.util.LinkedList;
import java.util.Queue;

public class Util {

  private Util() {

  }

  public static TreeNode stringToTreeNode(String input) {
    input = input.trim();
    input = input.substring(1, input.length() - 1);
    if (input.length() == 0) {
      return null;
    }

    final String[] parts = input.split(",");
    String item = parts[0];
    final TreeNode root = new TreeNode(Integer.parseInt(item));
    final Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);

    int index = 1;
    while (!nodeQueue.isEmpty()) {
      final TreeNode node = nodeQueue.remove();

      if (index == parts.length) {
        break;
      }

      item = parts[index++];
      item = item.trim();
      if (!item.equals("null")) {
        final int leftNumber = Integer.parseInt(item);
        node.left = new TreeNode(leftNumber);
        nodeQueue.add(node.left);
      }

      if (index == parts.length) {
        break;
      }

      item = parts[index++];
      item = item.trim();
      if (!item.equals("null")) {
        final int rightNumber = Integer.parseInt(item);
        node.right = new TreeNode(rightNumber);
        nodeQueue.add(node.right);
      }
    }
    return root;
  }

  public static int[] stringToIntegerArray(String input) {
    input = input.trim();
    input = input.substring(1, input.length() - 1);
    if (input.length() == 0) {
      return new int[0];
    }

    final String[] parts = input.split(",");
    final int[] output = new int[parts.length];
    for (int index = 0; index < parts.length; index++) {
      final String part = parts[index].trim();
      output[index] = Integer.parseInt(part);
    }
    return output;
  }

}
