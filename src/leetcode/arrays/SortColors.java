package leetcode.arrays;

/**
 * https://leetcode.com/problems/sort-colors/
 * Medium
 *
 * @author Daniane P. Gomes
 *
 *         Given an array with n objects colored red, white or blue, sort them
 *         in-place so that objects of the same color are adjacent, with the
 *         colors in the order red, white and blue.
 *
 *         Here, we will use the integers 0, 1, and 2 to represent the color
 *         red, white, and blue respectively.
 *
 *         Note: You are not suppose to use the library's sort function for this
 *         problem.
 *
 *         Example:
 *
 *         Input: [2,0,2,1,1,0] Output: [0,0,1,1,2,2] Follow up:
 *
 *         A rather straight forward solution is a two-pass algorithm using
 *         counting sort. First, iterate the array counting number of 0's, 1's,
 *         and 2's, then overwrite array with total number of 0's, then 1's and
 *         followed by 2's. Could you come up with a one-pass algorithm using
 *         only constant space?
 *
 */
public class SortColors {

  /**
   * We create an auxiliary array containing the number of time that each colour appears. Then we iterate over this aux
   * array, incrementing the index and changing the elements on the main array.
   *
   * @param nums
   */
  public static void sortColors(final int[] nums) {

    if (nums == null || nums.length <= 1) {
      return;
    }

    final int[] colors = new int[3];

    for (int i = 0; i < nums.length; i++) {
      colors[nums[i]]++;
    }

    int index = 0;
    for (int i = 0; i < colors.length; i++) {

      for (int j = 0; j < colors[i]; j++) {
        nums[index] = i;
        index++;
      }

    }

    System.out.println(nums);

  }

  /**
   * We iterate over the array with pointers at the beginning and at the end of it. Whenever we find a position that
   * contains 0, we move it to beginning, and when we find a position that contains a 2, we move it to the end. Pointer
   * low and high are used to save the next position to be swapped. Variable i is just an auxiliary to move within the
   * array.
   *
   * @param nums
   */
  public static void sortColorsSwap(final int[] nums) {

    if (nums == null || nums.length < 2) {
      return;
    }

    int low = 0;
    int high = nums.length - 1;
    int i = low;

    // When we are at the point that i touches the high pointer, it means that the whole array was visited.
    while (i <= high) {

      if (nums[i] == 0) {
        nums[i] = nums[low];
        // Since there's an if, it means that the element at this position was previously 0, so there's no need to save
        // it in a temp variable.
        nums[low] = 0;
        i++;
        low++;

      } else if (nums[i] == 2) {
        nums[i] = nums[high];
        // Since there's an if, it means that the element at this position was previously 2, so there's no need to save
        // it in a temp variable.
        nums[high] = 2;
        high--;

      } else {
        // If we find 1, we just advance to the next position
        i++;
      }

    }

    System.out.println(nums);
  }

  public static void main(final String[] args) {
    final int[] nums = { 2, 0, 2, 1, 1, 0 };
    sortColorsSwap(nums);
  }
}
