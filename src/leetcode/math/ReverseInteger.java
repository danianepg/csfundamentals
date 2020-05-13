package leetcode.math;

/**
 * https://leetcode.com/problems/reverse-integer/
 * Easy
 *
 * @author z003xfbr
 *
 *         Given a 32-bit signed integer, reverse digits of an integer.
 *
 *         Example 1:
 *
 *         Input: 123
 *         Output: 321
 *         Example 2:
 *
 *         Input: -123
 *         Output: -321
 *         Example 3:
 *
 *         Input: 120
 *         Output: 21
 *         Note:
 *         Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
 *         range: [−231, 231 − 1]. For the purpose of this problem, assume that your function returns 0 when the
 *         reversed integer overflows.
 *
 */
public class ReverseInteger {

  public static int reverse(int x) {

    int result = 0;

    while (x != 0) {

      final int tail = x % 10;
      final int newResult = result * 10 + tail;

//      if ((newResult - tail) / 10 != result) {
      if (newResult <= Integer.MIN_VALUE || newResult >= Integer.MAX_VALUE) {
        return 0;
      }

      result = newResult;
      x = x / 10;
    }

    return result;

  }

  public static void main(final String[] args) {
    final int x = 1534236469;
    ;
    System.out.println(reverse(x));
  }

}
