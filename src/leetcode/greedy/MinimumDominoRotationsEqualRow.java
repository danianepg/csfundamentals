package leetcode.greedy;

/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 *
 * @author z003xfbr
 *
 *         In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino. (A domino is a
 *         tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 *         We may rotate the i-th domino, so that A[i] and B[i] swap values.
 *
 *         Return the minimum number of rotations so that all the values in A are the same, or all the values in B are
 *         the same.
 *
 *         If it cannot be done, return -1.
 *
 *         Example 1:
 *         Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 *         Output: 2
 *         Explanation:
 *         The first figure represents the dominoes as given by A and B: before we do any rotations.
 *         If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated
 *         by the second figure.
 * 
 *         Example 2:
 *         Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 *         Output: -1
 *         Explanation:
 *         In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 *
 *         Note:
 *
 *         1 <= A[i], B[i] <= 6
 *         2 <= A.length == B.length <= 20000
 *
 */
public class MinimumDominoRotationsEqualRow {

  public static int minDominoRotations(final int[] A, final int[] B) {

    return minDominoRotatiosMathSolution(A, B);
//    return minDominoRotationsHash(A, B);

  }

  private static int minDominoRotatiosMathSolution(final int[] A, final int[] B) {
    if (A == null || B == null) {
      return -1;
    }

    final int[] occurencesA = new int[7];
    final int[] occurencesB = new int[7];
    final int[] occurencesSame = new int[7];

    for (int i = 0; i < A.length; i++) {
      occurencesA[A[i]]++;
      occurencesB[B[i]]++;

      if (A[i] == B[i]) {
        occurencesSame[A[i]]++;
      }

    }

    for (int i = 1; i <= occurencesA.length; i++) {

      if (occurencesA[i] + occurencesB[i] - occurencesSame[i] >= A.length) {
        return Math.min(occurencesA[i], occurencesB[i]) - occurencesSame[i];
      }
    }

    return -1;
  }

  public static void main(final String[] args) {
//    final int[] A = { 3, 5, 1, 2, 3 }; // -1
//    final int[] B = { 3, 6, 3, 3, 4 };

    final int[] A = { 2, 1, 2, 4, 2, 2 }; // 2
    final int[] B = { 5, 2, 6, 2, 3, 2 };

//    final int[] A = { 1, 2, 1, 1, 1, 2, 2, 2 }; // 1
//    final int[] B = { 2, 1, 2, 2, 2, 2, 2, 2 };

    System.out.println(minDominoRotations(A, B));
  }

}
