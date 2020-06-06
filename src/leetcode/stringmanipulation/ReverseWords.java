package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * 
 * @author danianepg
 *
 *         Given an input string, reverse the string word by word.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: "the sky is blue"
 *         Output: "blue is sky the"
 *         Example 2:
 * 
 *         Input: " hello world! "
 *         Output: "world! hello"
 *         Explanation: Your reversed string should not contain leading or trailing spaces.
 *         Example 3:
 * 
 *         Input: "a good example"
 *         Output: "example good a"
 *         Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * 
 * 
 *         Note:
 * 
 *         A word is defined as a sequence of non-space characters.
 *         Input string may contain leading or trailing spaces. However, your reversed string should not contain leading
 *         or trailing spaces.
 *         You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 */
public class ReverseWords {

  public static String reverseWords(final String s) {

    final String[] arr = s.split(" ");
    final StringBuilder sb = new StringBuilder();
    for (int i = arr.length - 1; i >= 0; i--) {
      final String str = arr[i].trim();
      if (!str.isEmpty()) {
        sb.append(str + " ");
      }
    }

    return sb.toString().trim();

  }

  public static void main(final String[] args) {
    System.out.println(reverseWords("a good   example"));
  }

}
