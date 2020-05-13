package hackerrank.amazon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/find-the-running-median/problem
 *
 * @author z003xfbr
 *
 */
public class FindRunningMedian {

  /**
   * Keeps smaller or equals elements than the last median in a maxHeap (order desc).
   * Keeps greater elements than the last median in a minHeap (order asc).
   * Every time the heaps are unbalanced, changes the last element to the other heap.
   * That guarantees that the elements in the middle of the array are always on the top of the heap.
   *
   * To calculate the median:
   * - if heaps have the same size, sums the first element of each and divide by 2
   * - if not, get the element on the top of the bigger heap
   *
   * Time complexity O(log n) for PriorityQueue operations, but O(n) for the for loop where n is the number of elements,
   * which results in O(n)
   *
   * @param a
   * @return
   */
  static double[] runningMedian(final int[] a) {

    if (a == null || a.length <= 0) {
      return new double[0];
    }

    final List<Double> medians = new ArrayList<>();
    final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    double median = 0;

    for (final int current : a) {

      if (current <= median) {
        maxHeap.add(current);
      } else {
        minHeap.add(current);
      }

      if (minHeap.size() - maxHeap.size() > 1) {
        final Integer element = minHeap.poll();
        maxHeap.add(element);
      } else if (maxHeap.size() - minHeap.size() > 1) {
        final Integer element = maxHeap.poll();
        minHeap.add(element);
      }

      if (minHeap.size() == maxHeap.size()) {
        median = (minHeap.peek() + maxHeap.peek()) / 2d;
      } else if (minHeap.size() > maxHeap.size()) {
        median = minHeap.peek();
      } else {
        median = maxHeap.peek();
      }

      medians.add(median);

    }

    final double[] result = new double[medians.size()];
    for (int i = 0; i < medians.size(); i++) {
      result[i] = medians.get(i);
    }

    return result;

  }

  public static void main(final String[] args) throws IOException {

    final File file = new File("C:\\Users\\z003xfbr\\Downloads\\find-the-running-median-testcases\\input\\input01.txt");
    final Scanner scanner = new Scanner(file);

    final int aCount = Integer.parseInt(scanner.nextLine().trim());

    final int[] a = new int[aCount];

    for (int aItr = 0; aItr < aCount; aItr++) {
      final int aItem = Integer.parseInt(scanner.nextLine().trim());
      a[aItr] = aItem;
    }

    final double[] result = runningMedian(a);

    for (final double element : result) {
      System.out.println(element);
    }

  }
}
