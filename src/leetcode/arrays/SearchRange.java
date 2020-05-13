package leetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Medium
 *
 * @author z003xfbr
 *
 *         Given an array of integers nums sorted in ascending order, find the starting and ending position of a given
 *         target value.
 *
 *         Your algorithm's runtime complexity must be in the order of O(log n).
 *
 *         If the target is not found in the array, return [-1, -1].
 *
 *         Example 1:
 *
 *         Input: nums = [5,7,7,8,8,10], target = 8
 *         Output: [3,4]
 *         Example 2:
 *
 *         Input: nums = [5,7,7,8,8,10], target = 6
 *         Output: [-1,-1]
 *
 */
public class SearchRange {

  /**
   * This can be solved using brute force from start to end until it finds a match and then from end to start. That
   * solution would be O(n). However, since the array is sorted we can do a binary search and solve this in O(log n).
   *
   * The extremeInsertionIndex method keeps calculating the midpoint. The variable left indicates if it should calculate
   * from middle to start or middle to end. At the end, variable lo will hold the position of the number.
   *
   * @param nums
   * @param target
   * @param left
   * @return
   */
  private static int extremeInsertionIndex(final int[] nums, final int target, final boolean left) {

    int lo = 0;
    int hi = nums.length;

    while (lo < hi) {
      final int mid = (lo + hi) / 2;
      if (nums[mid] > target || (left && target == nums[mid])) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    return lo;
  }

  public static int[] searchRange(final int[] nums, final int target) {
    final int[] output = { -1, -1 };

    final int leftIndex = extremeInsertionIndex(nums, target, true);
    if (leftIndex == nums.length || nums[leftIndex] != target) {
      return output;
    }

    output[0] = leftIndex;
    output[1] = extremeInsertionIndex(nums, target, false) - 1;

    return output;
  }

  public static int[] searchRangeLinear(final int[] nums, final int target) {

    final int[] output = { -1, -1 };
    final HashMap<Integer, List<Integer>> elements = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      final int elem = nums[i];
      final List<Integer> positions = elements.getOrDefault(elem, new ArrayList<>());
      positions.add(i);
      elements.put(elem, positions);
    }

    final List<Integer> positionsLst = elements.getOrDefault(target, new ArrayList<>());
    if (!positionsLst.isEmpty()) {
      output[0] = positionsLst.get(0);

      if (positionsLst.size() > 1) {
        output[1] = positionsLst.get(positionsLst.size() - 1);
      } else {
        output[1] = positionsLst.get(0);
      }
    }

    return output;

  }

  public static void main(final String[] args) {

//    final int[] nums = { 5, 7, 7, 8, 8, 10 };
//    final int target = 8;

//    final int[] nums = { 5,7,7,8,8,10 };
//    final int target = 6;

//    final int[] nums = { 1 };
//    final int target = 1;

//    final int[] nums = { 1, 3 };
//    final int target = 1; // Expected [0,0]

//    final int[] nums = { 1, 2, 3 };
//    final int target = 1; // Expected [0,0]

//    final int[] nums = { 5, 7, 7, 8, 8, 10 };
//    final int target = 6; // Expected [-1,-1]

//    final int[] nums = { 1, 2, 2 };
//    final int target = 2; // Expected [1,2]
//
//    final int[] nums = { 3, 3, 3 };
//    final int target = 3; // Expected [0,2]

    final int[] nums = { 1, 2, 3 };
    final int target = 3; // Expected [2,2]

    System.out.println(searchRange(nums, target));

  }

}
