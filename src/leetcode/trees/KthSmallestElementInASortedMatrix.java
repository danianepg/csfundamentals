package leetcode.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * Medium
 * 
 * @author z003xfbr
 *
 *         Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest
 *         element in the matrix.
 * 
 *         Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 *         Example:
 * 
 *         matrix = [
 *         [ 1, 5, 9],
 *         [10, 11, 13],
 *         [12, 13, 15]
 *         ],
 *         k = 8,
 * 
 *         return 13.
 *         Note:
 *         You may assume k is always valid, 1 ≤ k ≤ n2.
 *
 */
public class KthSmallestElementInASortedMatrix {

  /**
   * Brute force naive O(n log n) because of Collections.sort
   *
   * @param matrix
   * @param k
   * @return
   */
  public static int kthSmallestBruteForce(final int[][] matrix, final int k) {

    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    final List<Integer> elemLst = new ArrayList<>();

    for (final int[] lin : matrix) {
      for (final int element : lin) {
        elemLst.add(element);
      }
    }

    Collections.sort(elemLst);
    if (k > 0) {
      return elemLst.get(k - 1);
    }

    return 0;
  }

  /**
   * Binary search, similar to quick sort approach
   *
   * @param matrix
   * @param k
   * @return
   */
  public static int kthSmallest(final int[][] matrix, final int k) {

    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return -1;
    }

    final int row = matrix.length;
    final int col = matrix[0].length;

    int left = matrix[0][0];
    int right = matrix[row - 1][col - 1];

    while (left < right) {
      // Mid point is a media of smallest and biggest number on this row.
      final int mid = left + (right - left) / 2;

      // Number of elements in the array that are smaller than or equals to my mid pointer
      final int count = count(matrix, row, col, mid);

      System.out.println("LEFT=" + left + " - RIGHT=" + right + " - MID=" + mid + " count <= " + count);

      // If the quantity is bigger than the kth desired number, the kth number must be on the somewhere in ahead of me,
      // so advance with left pointer.
      // else, it means that we have passed of the kth desired element, so come back, cutting the array having the right
      // pointer at the middle.
      if (count < k) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // When right < left, it means that all the array has being visited and left pointer must hold the desired element
    return left;
  }

  /**
   * Number of elements <= mid
   *
   * @param matrix
   * @param m
   * @param n
   * @param mid
   * @return
   */
  public static int count(final int[][] matrix, final int row, final int col, final int mid) {
    int count = 0;
    int j = col - 1;

    for (int i = 0; i < row; i++) {
      while (j >= 0 && matrix[i][j] > mid) {
        j--;
      }
      count += j + 1;
    }

    return count;
  }

  public static void main(final String[] args) {
    final int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
    final int k = 8; // 13
//
//    final int[][] matrix = { { 1, 2 }, { 1, 3 } };
//    final int k = 2; // 1

    System.out.println(kthSmallest(matrix, k));
  }

}
