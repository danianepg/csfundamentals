package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/long-pressed-name/
 *
 * @author z003xfbr
 *
 */
public class RepeatedSubstringPattern {

  // substring method : O(n)

  public static boolean repeatedSubstringPattern(final String s) {
    System.out.println("S= " + s);

    if (s.isEmpty() || s.length() <= 1) {
      return false;
    }

    final int len = s.length();

    for (int i = len / 2; i >= 1; i--) {

      // Only groups chunks with possible sizes for the size of my string
      // A string with length 10 can't have groups of substrings with length 3. Only 5, 2 or 1.
      if (len % i == 0) {
        final int numberOfPartsToCompare = len / i;
        final String basePart = s.substring(0, i);
        int indexSubParts;

        System.out.println("i=" + i + " - basePart " + basePart + " numberOfPartsToCompare " + numberOfPartsToCompare);

        for (indexSubParts = 1; indexSubParts < numberOfPartsToCompare; indexSubParts++) {
          System.out.println(
              "\tj=" + indexSubParts + " - i=" + i + " -> " + s.substring(indexSubParts * i, i + indexSubParts * i));
          if (!basePart.equals(s.substring(indexSubParts * i, i + indexSubParts * i))) {
            break;
          }
        }

        // If at the end all parts were compared without breaking the loop, it means that all parts were equal to
        // basePart
        System.out.println("\nFIM indexSubParts = " + indexSubParts);
        if (indexSubParts == numberOfPartsToCompare) {
          return true;
        }

      }
    }

    return false;

  }

  public static boolean repeatedSubstringPatternV2(final String s) {
    final String s2 = s + s;
    return s2.substring(1, s2.length() - 1).contains(s);
  }

  public static void main(final String[] args) {
//    final String s = "abab"; // true
//    final String s = "aba"; // false
//    final String s = "abcabcabcabc"; // true
//    final String s = "abcabcabcabd"; // false
//    final String s = "ababab"; // true
//    final String s = "a"; // false
//    final String s = "ab"; // false
//    final String s = "abcabc"; // true
//    final String s = "abaababaab"; // true
    final String s = "abcabcabca"; // false
//    final String s = "aababa"; // false
    System.out.println(repeatedSubstringPattern(s));
  }

}
