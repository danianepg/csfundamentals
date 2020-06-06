package leetcode.greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/
 *
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 *
 * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person. (It is guaranteed each person can be carried by a
 * boat.)
 *
 *
 * Example 1:
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 *
 * Example 2:
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 *
 * Example 3:
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 *
 * @author danianepg
 *
 */
public class BoatsToSavePeople {

  public static int numRescueBoats(final int[] people, final int limit) {

    if (people.length == 1) {
      return 1;
    }

    Arrays.sort(people);
    int i = 0;
    int j = people.length - 1;
    int numBoats = 0;

    while (i <= j) {
      numBoats++;
      if (people[i] + people[j] <= limit) {
        i++;
      }
      j--;
    }

    return numBoats;
  }

  public static void main(final String[] args) {

//    final int[] people = { 5, 1, 4, 2 };
//    final int limit = 6; // 2

//    final int[] people = { 3, 5, 3, 4 };
//    final int limit = 5; // 4

//    final int[] people = { 2, 4 };
//    final int limit = 5; // 2

//    final int[] people = { 9, 4, 6 };
//    final int limit = 9; // 3

//    final int[] people = { 2, 2 };
//    final int limit = 6; // 1

//    final int[] people = { 3, 2, 2, 1 };
//    final int limit = 3; // 3

//    final int[] people = { 7, 3, 2 };
//    final int limit = 8; // 2

//    final int[] people = { 3, 1, 7 };
//    final int limit = 7; // 2

    final int[] people = { 3, 2, 3, 2, 2 };
    final int limit = 6; // 2

    System.out.println(numRescueBoats(people, limit));
  }
}
