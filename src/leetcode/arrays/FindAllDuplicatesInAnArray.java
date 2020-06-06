package leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * Medium
 *
 * @author danianepg
 *
 *         Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear
 *         once.
 *
 *         Find all the elements that appear twice in this array.
 *
 *         Could you do it without extra space and in O(n) runtime?
 *
 *         Example:
 *         Input:
 *         [4,3,2,7,8,2,3,1]
 *
 *         Output:
 *         [2,3]
 *
 */
public class FindAllDuplicatesInAnArray {

  /**
   * Since the maximum number on my array will be also the array size, I can simply invert the signal of the number on
   * the position corresponding to my current number's proper position. If I already find a number that is negative, it
   * means that it was already looked and therefore is duplicate. I can use a List to give the answer because the
   * problem statement says that numbers appear at most twice.
   *
   * Time complexity: O(n)
   * Space complexity O(n) ?
   *
   *
   * @param nums
   * @return
   */
  public static List<Integer> findDuplicates(final int[] nums) {

    final List<Integer> result = new ArrayList<>();

    if (nums == null || nums.length == 0) {
      return result;
    }

    for (int i = 0; i < nums.length; i++) {

      final int index = Math.abs(nums[i]) - 1;
      if (nums[index] < 0) {
        result.add(Math.abs(nums[i]));
      }

      nums[index] = -nums[index];

    }

    return result;

  }

  public static void main(final String[] args) {
    final int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1, 3, 3 };
    System.out.println(findDuplicates(nums));
  }

}
