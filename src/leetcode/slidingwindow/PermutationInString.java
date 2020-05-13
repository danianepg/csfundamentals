package leetcode.slidingwindow;

/**
 * https://leetcode.com/problems/permutation-in-string/
 * Medium
 * Bosta. Não entendi.
 * 
 * @author z003xfbr
 *
 *         Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other
 *         words, one of the first string's permutations is the substring of the second string.
 *
 *         Example 1:
 *
 *         Input: s1 = "ab" s2 = "eidbaooo"
 *         Output: True
 *         Explanation: s2 contains one permutation of s1 ("ba").
 *         Example 2:
 *
 *         Input:s1= "ab" s2 = "eidboaoo"
 *         Output: False
 *
 *
 *         Note:
 *
 *         The input strings only contain lower case letters.
 *         The length of both given strings is in range [1, 10,000].
 *
 */
public class PermutationInString {

//  public static boolean checkInclusion(final String s1, final String s2) {
//
//    if (s1.length() > s2.length()) {
//      return false;
//    }
//
//    final int[] s1Qtd = new int[26];
//    final int[] s2Qtd = new int[26];
//
//    for (int i = 0; i < s1.length(); i++) {
//      s1Qtd[s1.charAt(i) - 'a']++;
//      s2Qtd[s2.charAt(i) - 'a']++;
//    }
//
//    int count = 0;
//    for (int i = 0; i < 26; i++) {
//      if (s1Qtd[i] == s2Qtd[i]) {
//        count++;
//      }
//    }
//
//    for (int i = 0; i < s2.length() - s1.length(); i++) {
//
//      final int r = s2.charAt(i + s1.length()) - 'a';
//      final int l = s2.charAt(i) - 'a';
//
//      if (count == 26) {
//        return true;
//      }
//
//      s2Qtd[r]++;
//      if (s2Qtd[r] == s1Qtd[r]) {
//        count++;
//      } else if (s2Qtd[r] == s1Qtd[r] + 1) {
//        count--;
//      }
//
//      s2Qtd[l]--;
//      if (s2Qtd[l] == s1Qtd[l]) {
//        count++;
//      } else if (s2Qtd[l] == s1Qtd[l] - 1) {
//        count--;
//      }
//
//    }
//
//    return count == 26;
//  }

  /**
   * Map all the characters to an array by adding the number of necessary characters and decrementing the number already
   * found on the target.
   * Traverse the new map counting the number of characters there're to fit.
   *
   *
   * @param source
   * @param target
   * @return
   */
  public static boolean checkInclusion(final String source, final String target) {

    final int sourceLength = source.length();
    final int targetLength = target.length();

    if (sourceLength > targetLength) {
      return false;
    }

    final int[] mapCharMatches = new int[26];
    for (int i = 0; i < sourceLength; i++) {
      mapCharMatches[source.charAt(i) - 'a']++;
      mapCharMatches[target.charAt(i) - 'a']--;
    }

    int toFit = 0;
    for (int i = 0; i < 26; i++) {
      if (mapCharMatches[i] > 0) {
        toFit += mapCharMatches[i];
      }
    }

    if (toFit == 0) {
      return true;
    }

    for (int i = sourceLength; i < targetLength; i++) {

      final int tempCharPos = target.charAt(i - sourceLength) - 'a';
      System.out.println("Char " + target.charAt(i - sourceLength) + " times " + mapCharMatches[tempCharPos]);
      mapCharMatches[tempCharPos]++;

      if (mapCharMatches[tempCharPos] > 0) {
        System.out.println("add to fit");
        toFit++;
      }

      if (mapCharMatches[target.charAt(i) - 'a'] > 0) {
        System.out.println("dec to fit");
        toFit--;
      }
      System.out.println("CHAR " + target.charAt(i) + " --");
      mapCharMatches[target.charAt(i) - 'a']--;

      if (toFit == 0) {
        return true;
      }

    }

    return false;
  }

  public static boolean checkInclusionAccepted(final String s1, final String s2) {
    final int len1 = s1.length();
    final int len2 = s2.length();
    if (len1 > len2) {
      return false;
    }

    final int[] count = new int[26];
    for (int i = 0; i < len1; i++) {
      count[s1.charAt(i) - 'a']++;
      count[s2.charAt(i) - 'a']--;
    }
    if (allZero(count)) {
      return true;
    }

    for (int i = len1; i < len2; i++) {
      count[s2.charAt(i) - 'a']--;
      count[s2.charAt(i - len1) - 'a']++;
      if (allZero(count)) {
        return true;
      }
    }

    return false;
  }

  private static boolean allZero(final int[] count) {
    for (int i = 0; i < 26; i++) {
      if (count[i] != 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(final String[] args) {
//    final String s1 = "ab";
//    final String s2 = "eidboaoo";

    final String s1 = "ab";
    final String s2 = "idboao";
    System.out.println(checkInclusion(s1, s2));
  }
}
