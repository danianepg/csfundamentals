package leetcode.bitwise;

/**
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 *
 * @author z003xfbr
 *
 *         Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
 *         inclusive.
 *
 *         Example 1:
 *         Input: [5,7]
 *         Output: 4
 *
 *         Example 2:
 *         Input: [0,1]
 *         Output: 0
 *
 */
public class BitwiseANDofNumbersRange {

  /**
   * Each number odd number has last bit equal to 1 and even as 0
   * If m == 4 and n = 14 then there has to be a number between 4 and 14 which has
   * last bit equal to 1
   * This is the reason we can right shift both m and n at each iteration of while loop as long as m != n;
   *
   * @param m
   * @param n
   * @return
   */
  public static int rangeBitwiseAnd(int m, int n) {

    int shifts = 0;

    while (m != n) {
      m = m >> 1;
      n = n >> 1;

      System.out.println("m >> 1 = " + m);
      System.out.println("n >> 1 = " + n);

      shifts++;
    }

    return m << shifts;
  }

  public static void main(final String[] args) {
    final int m = 5;
    final int n = 7;
    System.out.println(rangeBitwiseAnd(m, n));
  }
}
