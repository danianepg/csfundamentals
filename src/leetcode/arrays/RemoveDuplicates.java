package leetcode.arrays;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Easy
 *
 * @author z003xfbr
 *
 *         Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return
 *         the new length.
 *
 *         Do not allocate extra space for another array, you must do this by modifying the input array in-place with
 *         O(1) extra memory.
 *
 *         Example 1:
 *
 *         Given nums = [1,1,2],
 *
 *         Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 *
 *         It doesn't matter what you leave beyond the returned length.
 *         Example 2:
 *
 *         Given nums = [0,0,1,1,1,2,2,3,3,4],
 *
 *         Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3,
 *         and 4 respectively.
 *
 *         It doesn't matter what values are set beyond the returned length.
 *         Clarification:
 *
 *         Confused why the returned value is an integer but your answer is an array?
 *
 *         Note that the input array is passed in by reference, which means modification to the input array will be
 *         known to the caller as well.
 *
 *         Internally you can think of this:
 *
 *         // nums is passed in by reference. (i.e., without making a copy)
 *         int len = removeDuplicates(nums);
 *
 *         // any modification to nums in your function would be known by the caller.
 *         // using the length returned by your function, it prints the first len elements.
 *         for (int i = 0; i < len; i++) {
 *         print(nums[i]);
 *         }
 *
 */
public class RemoveDuplicates {

  public static void writeArr(final int[] nums) {
    for (final int num : nums) {
      System.out.print(num + ", ");
    }
    System.out.println("");
  }

  /**
   * We start using the index 1 because the first number will always be unique. We iterate over the array and if the
   * next number is different from the current, it means that we have a new unique number. So we add the next number
   * (the recently unique discovered) to next position of index and increment index.
   * Time complextiy : O(n)O(n).
   *
   * @param nums
   * @return
   */
  public static int removeDuplicates(final int[] nums) {

    if (nums == null) {
      return 0;
    } else if (nums.length < 2) {
      return 1;
    }

    int index = 1;
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] != nums[i + 1]) {
        nums[index++] = nums[i + 1];
      }
    }

    writeArr(nums);
    return index;
  }

  public static void main(final String[] args) {

    final int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
    System.out.println(removeDuplicates(nums));
  }
}
