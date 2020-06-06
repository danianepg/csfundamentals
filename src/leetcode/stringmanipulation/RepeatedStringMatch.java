package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/repeated-string-match/
 * 
 * @author danianepg
 *
 */
public class RepeatedStringMatch {

  public static int repeatedStringMatch(final String A, final String B) {

    if (B.length() < A.length() && A.contains(B)) {
      return 1;
    }

    // We need at least 1 repetition for safety. However when we're dividing numbers that don't have result 0, we need
    // another extra copy, therefore we add 2.
    final int end = B.length() / A.length() + 2;
    String str = A;

    for (int i = 1; i <= end; i++) {

      if (str.length() >= B.length() && str.contains(B)) {
        return i;
      }

      str += A;

    }

    return -1;
  }

  public static void main(final String[] args) {
//    final String A = "abcd";
//    final String B = "cdabcdab"; // 3

//    final String A = "a";
//    final String B = "a"; // 1

//    final String A = "aa";
//    final String B = "a";// 1

//    final String A = "a";
//    final String B = "aa";// 2

//    final String A = "aaaaaaaaaaaaaaaaaaaaaab";
//    final String B = "ba"; // 2

//    final String A = "abababaaba";
//    final String B = "aabaaba";// 2

//    final String A = "abcbc";
//    final String B = "cabcbca";// 3

    final String A = "abc";
    final String B = "cabcabcabca";

    System.out.println(repeatedStringMatch(A, B));
  }

}
