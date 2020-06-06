package leetcode.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Medium
 *
 * @author danianepg
 *
 *         Given a collection of intervals, merge all overlapping intervals.
 *
 *         Example 1:
 *
 *         Input: [[1,3],[2,6],[8,10],[15,18]]
 *         Output: [[1,6],[8,10],[15,18]]
 *         Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *         Example 2:
 *
 *         Input: [[1,4],[4,5]]
 *         Output: [[1,5]]
 *         Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 */
public class MergeIntervals {

  public static class Interval {

    int start;

    int end;

    public Interval(final int start, final int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return "{" + this.start + ", " + this.end + "}";
    }

  }

  /**
   * To be able to merge it, I need to first order the elements by their start position. Then I compare the end of the
   * previous element with the start of the current element.
   *
   * When end of the last elem is less than the start of the current, the previous element should be added to the merged
   * list.
   *
   * When the end of the last elem is greater than the start of the current, the start of the last should receive min
   * start between last and current and end should receive max end between last and current.
   *
   * Time complexity O(n log(n))
   * Space complexity O(n)
   *
   * @param intervals
   * @return
   */
  public static List<Interval> merge(final List<Interval> intervals) {

    final List<Interval> ans = new ArrayList<>();
    if (intervals.isEmpty()) {
      return ans;
    }

    Collections.sort(intervals, (a, b) -> (a.start - b.start));

    Interval interval = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {

      if (interval.end < intervals.get(i).start) {
        ans.add(interval);
        interval = intervals.get(i);
      } else {

        interval.start = Math.min(interval.start, intervals.get(i).start);
        interval.end = Math.max(interval.end, intervals.get(i).end);

      }
    }

    ans.add(interval);
    return ans;

  }

//  public static int[][] merge(final int[][] intervals) {
//
//    if (intervals == null || intervals.length <= 0) {
//      return new int[0][2];
//    }
//
//    final List<int[]> ans = new ArrayList<>();
//    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
//    int last[] = intervals[0];
//
//    for (int i = 1; i < intervals.length; i++) {
//
//      final int[] temp = new int[2];
//
//      if (last[1] < intervals[i][0]) {
//        last = intervals[i];
//        ans.add(temp);
//
//      } else {
//        temp[0] = Math.min(last[0], intervals[i][0]);
//        temp[1] = Math.max(last[1], intervals[i][1]);
//
//      }
//    }
//
//    ans.add(last);
//
//    final int[][] tempAns = new int[ans.size()][2];
//    int index = 0;
//
//    for (final int[] elem : ans) {
//      tempAns[index] = elem;
//      index++;
//    }
//
//    return tempAns;
//
//  }

  public static void printMatrix(final List<Interval> interval) {

    for (final Interval temp : interval) {
      System.out.print("{" + temp.start + "," + temp.end + "}, ");
    }

  }

  public static void printMatrix(final int[][] interval) {

    for (final int[] temp : interval) {
      System.out.print("{" + temp[0] + "," + temp[1] + "}, ");
    }

  }

  public static int[][] merge(final int[][] intervals) {
    final List<Interval> elem = new ArrayList<>();
    for (final int[] temp : intervals) {
      elem.add(new Interval(temp[0], temp[1]));
    }

    final List<Interval> ans = merge(elem);

    final int[][] tempAns = new int[ans.size()][2];
    int index = 0;

    for (final Interval i : ans) {
      tempAns[index][0] = i.start;
      tempAns[index][1] = i.end;
      index++;
    }

    return tempAns;

  }

  public static void main(final String[] args) {
    final int[][] intervals = { { 1, 4 }, { 0, 2 }, { 3, 5 } }; // [[0,5]]
//    final int[][] intervals = { { 1, 4 }, { 0, 4 } }; // [[0,4]]
//    final int[][] intervals = { { 1, 4 }, { 5, 6 } }; // [[1,4],[5,6]]
//    final int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } }; // [[1,6],[8,10],[15,18]]

//    final List<Interval> intervals = Arrays.asList(new Interval(1, 4), new Interval(0, 2), new Interval(3, 5));
//    final List<Interval> intervals = Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10),
//        new Interval(15, 18));
    printMatrix(merge(intervals));
  }

}
