package leetcode.greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * @author danianepg
 *
 *         There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is
 *         the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter
 *         and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There
 *         will be at most 104 balloons.
 *
 *         An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and
 *         xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be
 *         shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows
 *         that must be shot to burst all balloons.
 *
 *         Example:
 *
 *         Input:
 *         [[10,16], [2,8], [1,6], [7,12]]
 *
 *         Output:
 *         2
 *
 *         Explanation:
 *         One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow
 *         at x = 11 (bursting the other two balloons).
 *
 */
public class MinimumNumberOfArrowsToBurstBalloons {

  /**
   * Order by the end
   *
   * @param points
   * @return
   */
  public static int findMinArrowShots(final int[][] points) {
    if (points == null || points.length == 0) {
      return 0;
    }

    Arrays.sort(points, (p1, p2) -> p1[1] - p2[1]);
    int ans = 1;
    int lastArrow = points[0][1];

    for (int i = 1; i < points.length; i++) {
      if (points[i][0] > lastArrow) {
        lastArrow = points[i][1];
        ans++;
      }

    }

    return ans;
  }

  /**
   * Time complexity: O(nlogn)
   *
   * @param points
   * @return
   */
  public static int findMinArrowShotsIni(final int[][] points) {

    if (points == null || points.length == 0) {
      return 0;
    }

    // Order by the initial point ASC
    Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);
    int ans = 1;
    int lastArrow = points[0][1];

    for (int i = 1; i < points.length; i++) {
      if (points[i][0] <= lastArrow) {
        lastArrow = Math.min(lastArrow, points[i][1]);
      } else {
        lastArrow = points[i][1];
        ans++;
      }

    }

    return ans;

  }

  public static void main(final String[] args) {
    final int[][] points = { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 }, { 5, 7 } };
    System.out.println(findMinArrowShots(points));
  }
}
