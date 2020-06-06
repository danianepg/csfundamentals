package leetcode.trees;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Medium
 *
 * @author danianepg
 *
 *         Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted
 *         order, not the kth distinct element.
 *
 *         Example 1:
 *         Input: [3,2,1,5,6,4] and k = 2
 *         Output: 5
 *
 *         Example 2:
 *         Input: [3,2,3,1,2,4,5,5,6] and k = 4
 *         Output: 4
 *         Note:
 *         You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 */
public class KthLargestElementInAnArray {

  public static int findKthLargestQuickSelect(final int[] nums, final int k) {

    final int n = nums.length;
    int left = 0;
    int right = n - 1;

    final Random rand = new Random(0);

    while (left <= right) {
      final int pivotIndex = rand.nextInt(right - left + 1) + left;
      final int finalPositionOfPivot = partition(nums, left, right, pivotIndex);

      if (finalPositionOfPivot == n - k) {
        return nums[finalPositionOfPivot];

      } else if (finalPositionOfPivot > n - k) {
        right = finalPositionOfPivot - 1;

      } else {
        left = finalPositionOfPivot + 1;
      }
    }

    return -1;

  }

  private static int partition(final int[] nums, final int left, final int right, final int pivotIndex) {

    final int pivot = nums[pivotIndex];
    int lesserItemsTailIndex = left;
    swap(nums, pivotIndex, right);

    for (int i = left; i < right; i++) {

      if (nums[i] < pivot) {
        swap(nums, i, lesserItemsTailIndex);
        lesserItemsTailIndex++;
      }
    }

    /*
     * Ok...partitioning is done. Swap the pivot item BACK into the space we just
     * partitioned at the 'lesserItemsTailIndex'...that's where the pivot item
     * belongs
     * In the middle of the "sandwich".
     */
    swap(nums, right, lesserItemsTailIndex);

    return lesserItemsTailIndex;
  }

  /**
   * Because of the PriorityQueue, time complexity is O(log n)
   * We keep adding elements to the PriorityQueue, until its size is bigger than the desired k. At the end just return
   * the first element of the queue.
   * the first element of the queue.
   *
   * @param nums
   * @param k
   * @return
   */
  public static int findKthLargestPriorityQueue(final int[] nums, final int k) {

    final PriorityQueue<Integer> queue = new PriorityQueue<>();

    for (final int num : nums) {
      queue.add(num);
      if (queue.size() > k) {
        queue.poll();
      }
    }

    return queue.peek();
  }

  /**
   * Uses the quicksort to order the array and then finds the kth largest element. It takes on avg O(nlogn) and worst
   * O(n^2)
   *
   * @param nums
   * @param k
   * @return
   */
  public static int findKthLargest(final int[] nums, final int k) {

    if (nums == null || nums.length == 0 || nums.length < k) {
      return -1;
    }

    quicksort(nums, 0, nums.length - 1);
    final int kth = nums.length - k;
    return nums[kth];
  }

  private static void quicksort(final int[] nums, final int left, final int right) {

    if (left < right) {
      final int pivot = partitioning(nums, left, right);
      quicksort(nums, left, pivot - 1);
      quicksort(nums, pivot + 1, right);
    }

  }

  private static int partitioning(final int[] nums, final int left, final int right) {
    final int pivot = nums[right];
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (nums[j] <= pivot) {
        i++;
        swap(nums, i, j);
      }
    }

    // After all the swaps, put the pivot between all the elements that are less and greater than it.
    swap(nums, i + 1, right);

    // offers the next position as new pivot
    return i + 1;

  }

  private static void swap(final int[] nums, final int first, final int second) {
    final int temp = nums[first];
    nums[first] = nums[second];
    nums[second] = temp;

  }

  public static void main(final String[] args) {
    final int[] nums = { 3, 2, 1, 5, 6, 4 };
    final int k = 2; // 5

//    final int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
//    final int k = 4; // 4
    System.out.println(findKthLargestQuickSelect(nums, k));
  }

}
