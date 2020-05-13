package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/license-key-formatting/
 *
 * @author Daniane P. Gomes
 *
 *         You are given a license key represented as a string S which consists only alphanumeric character and dashes.
 *         The string is separated into N+1 groups by N dashes.
 *
 *         Given a number K, we would want to reformat the strings such that each group contains exactly K characters,
 *         except for the first group which could be shorter than K, but still must contain at least one character.
 *         Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted
 *         to uppercase.
 *
 *         Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 *         Example 1:
 *         Input: S = "5F3Z-2e-9-w", K = 4
 *
 *         Output: "5F3Z-2E9W"
 *
 *         Explanation: The string S has been split into two parts, each part has 4 characters.
 *         Note that the two extra dashes are not needed and can be removed.
 *         Example 2:
 *         Input: S = "2-5g-3-J", K = 2
 *
 *         Output: "2-5G-3J"
 *
 *         Explanation: The string S has been split into three parts, each part has 2 characters except the first part
 *         as it could be shorter as mentioned above.
 *         Note:
 *         The length of string S will not exceed 12,000, and K is a positive integer.
 *         String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 *         String S is non-empty.
 *
 */
public class LicenseKeyFormatting {

  public static String licenseKeyFormatting(String s, final int k) {

    if (s != null && !s.isEmpty()) {
      s = s.replace("-", "");
    } else {
      return "";
    }

    final char[] arrCh = s.toCharArray();
    final StringBuilder licenseKey = new StringBuilder();
    int index = 0;

    for (int i = arrCh.length - 1; i >= 0; i--) {

      if (index == k) {
        licenseKey.append("-");
        index = 0;
      }

      licenseKey.append(arrCh[i]);
      index++;

    }

    return licenseKey.reverse().toString().toUpperCase();

  }

  public static void main(final String[] args) {
    final String s = "---";
    final int k = 3;

//		String s = "--a-a-a-a--";
//		int k = 2;

//		String s = "5F3Z-2e-9-w";
//		int k = 4;
//
    // String s = "2-5g-3-J";
    // int k = 2;

    System.out.println(licenseKeyFormatting(s, k));
  }

}
