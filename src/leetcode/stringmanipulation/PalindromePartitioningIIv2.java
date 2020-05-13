package leetcode.stringmanipulation;

public class PalindromePartitioningIIv2 {

  public static int minCut(final String s) {

    if (s.isEmpty() || s.length() <= 1) {
      return 0;
    }

    final boolean[][] palindromeMatrix = palindromeMatrix(s);
    final int len = s.length();
    final int[] cuts = new int[len];

    for (int i = 0; i < len; i++) {

      int tempMax = Integer.MAX_VALUE;

      if (palindromeMatrix[0][i]) {
        cuts[i] = 0;
      } else {
        for (int j = 0; j < i; j++) {
          if (palindromeMatrix[j + 1][i] && tempMax > cuts[j] + 1) {
            tempMax = cuts[j] + 1;
          }
        }
        cuts[i] = tempMax;
      }

    }

    int maxCuts = cuts[len - 1];
    if (maxCuts == Integer.MAX_VALUE) {
      maxCuts = 0;
    }

    return maxCuts;
  }

  private static boolean[][] palindromeMatrix(final String s) {

    final int n = s.length();
    final boolean[][] palindromeMatrix = new boolean[n][n];

    for (int curr_len = 3; curr_len <= n; curr_len++) {
      for (int i = 0; i < n - curr_len + 1; i++) {
        final int j = i + curr_len - 1;
        if (s.charAt(i) == s.charAt(j) // 1. The first and last characters should match
            && palindromeMatrix[i + 1][j - 1]) // 2. Rest of the substring should be a palindrome
        {
          palindromeMatrix[i][j] = true;
        }
      }
    }

    return palindromeMatrix;
  }

  public static void main(final String[] args) {
    // Expected 1
    System.out.println(minCut("banana"));
  }
}
