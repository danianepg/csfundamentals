package leetcode.hashtables;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 *
 * @author danianepg
 *
 *         Write an algorithm to determine if a number is "happy".
 *
 *         A happy number is a number defined by the following process: Starting with any positive integer, replace the
 *         number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it
 *         will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process
 *         ends in 1 are happy numbers.
 *
 *         Example:
 *
 *         Input: 19
 *         Output: true
 *         Explanation:
 *         1^2 + 9^2 = 82
 *         8^2 + 2^2 = 68
 *         6^2 + 8^2 = 100
 *         1^2 + 0^2 + 0^2 = 1
 *
 */
public class HappyNumbers {

  public static boolean isHappy(int n) {

    if (n <= 0) {
      return false;
    }

    if (n == 1) {
      return true;
    }

    final Set<Integer> visited = new HashSet<>();

    while (n > 1) {
      int sumDigits = 0;

      while (n > 0) {
        final int lastDigit = n % 10;
        sumDigits += lastDigit * lastDigit;
        n = n / 10;
      }

      if (sumDigits == 1) {
        return true;
      }

      if (visited.contains(sumDigits)) {
        return false;
      }

      visited.add(sumDigits);
      n = sumDigits;
    }

    return true;

  }

  public static void main(final String[] args) {
    System.out.println(isHappy(123));
  }

}
