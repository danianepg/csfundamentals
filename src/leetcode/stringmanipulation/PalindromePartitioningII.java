package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * 
 * @author z003xfbr
 *
 */
public class PalindromePartitioningII {

  public static int minCut(final String s) {

    if (s.isEmpty()) {
      return 0;
    }

    final int wordLength = s.length();
    final int[] minLst = new int[wordLength + 1];
    minLst[0] = 0;

    for (int i = 1; i <= wordLength; i++) {
      minLst[i] = s.length();

      for (int k = 0; k < i; k++) {
        final String strAux = s.substring(k, i);

        if (isPalindrome(strAux)) {
          final int min = Math.min(minLst[k] + 1, minLst[i]);
          minLst[i] = min;
        }
      }
    }

    int minRet = minLst[wordLength] - 1;
    if (minRet < 0) {
      minRet = 0;
    }

    return minRet;
  }

  public static Boolean isPalindrome(final String word) {

    int start = 0;
    int end = word.length() - 1;

    while (start <= end) {

      final char charStart = word.charAt(start);
      final char charEnd = word.charAt(end);

      if (charStart != charEnd) {
        return Boolean.FALSE;
      }

      start++;
      end--;
    }

    return Boolean.TRUE;
  }

  public static void main(final String[] args) {
    // Expected 1
    System.out.println(minCut("ab"));
  }
}
