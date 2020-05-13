package leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * Easy
 *
 * @author z003xfbr
 *
 *         Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 *         You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 *         Example:
 *
 *         Given nums = [2, 7, 11, 15], target = 9,
 *
 *         Because nums[0] + nums[1] = 2 + 7 = 9,
 *         return [0, 1].
 *
 *
 */
public class TwoSum {

  // Brute force, minha autoria. Time O(n^2) Space O(1)
  public static int[] twoSum(final int[] nums, final int target) {

    final int[] ans = { -1, -1 };

    for (int i = 0; i < nums.length; i++) {
      final int compl = target - nums[i];

      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] == compl) {
          ans[0] = i;
          ans[1] = j;
          break;
        }
      }

      if (ans[0] >= 0) {
        break;
      }

    }

    return ans;

  }

  /**
   * It can also be done in Time O(n) and space O(n) by saving the visited elements in a hash table
   * 
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSumHash(final int[] nums, final int target) {
    final Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {

      final int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[] { map.get(complement), i };
      }

      map.put(nums[i], i);
    }

    return new int[] { -1, -1 };
  }

  public static void main(final String[] args) {
    final int[] nums = { 0, 7, 2, 11, 15 };
    final int target = 9;
    twoSum(nums, target);
  }

}
