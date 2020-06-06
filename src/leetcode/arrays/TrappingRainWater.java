package leetcode.arrays;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Hard
 *
 * @author danianepg
 *
 *         Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how
 *         much water it is able to trap after raining.
 *
 *
 *         The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain
 *         water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 *         Example:
 *
 *         Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 *         Output: 6
 *
 */
public class TrappingRainWater {

  /**
   * Both solutions are O(n)
   * On the solution with pointers we use pointers to iterate from ini to end and end to ini. We also save the max
   * height already found on both sides. For the pointer that holds the minimum "wall", we calculate the difference
   * between it and the biggest wall already found. If the element itself is a bigger wall, we just change it as the
   * biggest wall.
   *
   * Iterate over the array treating the small positions first. Then check if it is a wall or a hole. If it is bigger
   * than the last biggest, it treats it as wall, otherwise is hole and we subtract the height from the last wall to
   * know the water trapped.
   *
   * @param height
   * @return
   */
  public static int trapPointers(final int[] height) {

    if (height == null || height.length == 0) {
      return 0;
    }

    int waterTrapped = 0;
    int left = 0;
    int right = height.length - 1;
    int leftMax = 0;
    int rightMax = 0;

    while (left < right) {

      System.out.println("\nleft " + left + " right " + right);
      System.out.println(height[left] + " < " + height[right]);

      if (height[left] < height[right]) {

        if (height[left] >= leftMax) {
          leftMax = height[left];
        } else {
          waterTrapped += leftMax - height[left];
          System.out.println("left waterTrapped " + waterTrapped);
        }
        left++;
      } else {
        if (height[right] >= rightMax) {
          rightMax = height[right];
        } else {
          waterTrapped += rightMax - height[right];
          System.out.println("right waterTrapped " + waterTrapped);
        }
        right--;
      }

      System.out.println("leftMax " + leftMax + " - rightMax " + rightMax);
    }

    return waterTrapped;
  }

  public static int trapStack(final int[] height) {

    final Stack<Integer> stack = new Stack<>();
    int waterTrapped = 0;

    for (int i = 0; i < height.length; i++) {

      final int current = height[i];

      System.out.println("\ni=" + i + " - current " + current);

      if (!stack.isEmpty()) {
        System.out.println(" height de " + stack.peek());
      }

      while (!stack.isEmpty() && current > height[stack.peek()]) {
        final Integer middle = stack.pop();

        if (stack.isEmpty()) {
          break;
        }

        final Integer leftBorder = stack.peek();

        System.out.println("\tmiddle " + middle);
        final Integer distance = (i - leftBorder) - 1;
        System.out.println("\tdistance " + distance + " =====> (i " + i + " - leftBorder " + leftBorder + ")");

        final Integer heightElement = Math.min(current, height[leftBorder]) - height[middle];
        System.out.println("\theightElement " + heightElement + " =====> min entre " + current + " e"
            + height[leftBorder] + " - " + height[middle]);

        waterTrapped += distance * heightElement;
        System.out.println("\twaterTrapped " + waterTrapped);

      }

      stack.add(i);
      System.out.println("ADD " + i);

    }

    return waterTrapped;
  }

  public static void main(final String[] args) {
    final int[] water = { 0, 1, 0, 0, 1, 0, 1, 3, 2, 1, 2, 1 };
//		int[] water = { 0, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0 };
    System.out.println(trapPointers(water));
  }

}
