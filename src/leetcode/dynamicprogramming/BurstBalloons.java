package leetcode.dynamicprogramming;

/**
 * https://leetcode.com/problems/burst-balloons/
 * Hard
 *
 * @author danianepg
 *
 *         Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array
 *         nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] *
 *         nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then
 *         becomes adjacent.
 *
 *         Find the maximum coins you can collect by bursting the balloons wisely.
 *
 *         Note:
 *
 *         You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 *         0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *         Example:
 *
 *         Input: [3,1,5,8]
 *         Output: 167
 *         Explanation: nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 *         coins = 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 167
 *
 */
public class BurstBalloons {

  public static int maxCoins(final int[] nums) {

    final int[][] dp = new int[nums.length][nums.length];
    return maxCoins(nums, 0, nums.length - 1, dp);

  }

  /**
   * Save at each position the max number that can be gotten bursting balloons from position start until end.
   * Iterate recursively from start until current position -1 and current +1 until the end and add it up to the
   * multiplication of current, left neighbor and right neighbor.
   *
   * Compare it to the value at nums[start] to save the maximum, save it to the current start,end and return it.
   *
   * @param nums
   * @param start
   * @param end
   * @param dp
   * @return
   */
  public static int maxCoins(final int[] nums, final int start, final int end, final int[][] dp) {

    if (start > end) {
      return 0;
    }

    if (dp[start][end] != 0) {
      return dp[start][end];
    }

    int max = nums[start];
    for (int i = start; i <= end; i++) {

      // @formatter:off
      final int coins = maxCoins(nums, start, i - 1, dp)
          + get(nums, start - 1) * get(nums, i) * get(nums, end + 1)
          + maxCoins(nums, i + 1, end, dp);
      // @formatter:on

      max = Math.max(max, coins);
    }

    dp[start][end] = max;
    return max;

  }

  public static int get(final int[] nums, final int i) {
    if (i == -1 || i == nums.length) {
      return 1;
    }
    return nums[i];
  }

  public static void main(final String[] args) {
    final int[] balloons = { 3, 1, 5, 8 };
    final int points = maxCoins(balloons);
    System.out.println(points);
  }
}
