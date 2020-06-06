package leetcode.stringmanipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * @author danianepg
 *
 */
public class MinimumWindowSubstring {

//  public static String minWindow(String searchString, String t) {
//
//    searchString = searchString.toLowerCase();
//    t = t.toLowerCase();
//
//    final char[] requiredChar = new char[26];
//    for (int i = 0; i < requiredChar.length; i++) {
//      requiredChar[i] = '0';
//    }
//
//    for (final char elem : t.toCharArray()) {
//      requiredChar[elem - 'a']++;
//    }
//
//    final char[] matchChar = new char[26];
//    for (int i = 0; i < matchChar.length; i++) {
//      matchChar[i] = '0';
//    }
//
//    int left = 0;
//    int right = 0;
//    final int numberOfCharToMatch = t.length();
//    int numberCharFound = 0;
//    int windowSize = numberOfCharToMatch;
//
//    int minLeft = 0;
//    int minRight = 0;
//
//    while (right < searchString.length()) {
//
//      final char tempChar = searchString.charAt(right);
//      matchChar[tempChar - 'a']++;
//
//      System.out.println("tempChar " + tempChar + " - " + matchChar[tempChar - 'a']);
//
//      if (requiredChar[tempChar - 'a'] > 0 && requiredChar[tempChar - 'a'] == matchChar[tempChar - 'a']) {
//        numberCharFound++;
//      }
//
//      while (numberCharFound == numberOfCharToMatch && left <= right) {
//
//        final char tempLeft = searchString.charAt(left);
//
//        final int tempWindowSize = right - left + 1;
//        if (tempWindowSize < windowSize) {
//          windowSize = tempWindowSize;
//          minLeft = left;
//          minRight = right;
//        }
//
//        matchChar[tempChar]--;
//
//        if (requiredChar[tempLeft - 'a'] > 0 && matchChar[tempChar - 'a'] < requiredChar[tempChar - 'a']) {
//          numberCharFound--;
//        }
//
//        left++;
//
//      }
//
//      right++;
//
//    }
//
//    String ans = "";
//    if (minLeft > 0 && minRight > 0) {
//      ans = searchString.substring(minLeft, minRight + 1);
//    }
//
//    return ans;
//  }

  public static String minWindow(final String searchString, final String t) {

    // The characters a satisfiable window must cover mapped to their frequency
    final Map<Character, Integer> requiredCharacters = buildMappingOfCharactersToOccurrences(t);

    /*
     * For our window. Map all characters in the window to their occurrence count. You
     * will see how we use this below.
     */
    final Map<Character, Integer> windowCharacterMapping = new HashMap<Character, Integer>();

    /*
     * 2 pointers. Left holds the left index of the window we are inspecting and right
     * holds the right index.
     * The approach is simple. We keep moving right (don't touch left) until the window
     * we hold satisfies all required characters. Then we take note whether the window
     * we see beats the smallest satisfiable window we have found so far.
     * We then contract the left pointer in while the window still satisfies all required
     * characters (at every point continuing to check if we have beaten the smallest window
     * ever seen to this point)
     * As soon as the window no longer satisfies, go back to expanding the right pointer.
     * We are finished when the right pointer runs over the array because we can't keep
     * expanding the window to satisfy at that point.
     */
    int left = 0;
    int right = 0;

    /*
     * 'totalCharFrequenciesToMatch' is the total characters we need to match frequency for
     * in the window. If I have 1 'a' in my window and I need 2 'a' chars...then the char
     * frequencies don't match.
     * 'charFrequenciesInWindowThatMatch' is the count of frequencies that we have satisfied.
     * When 'totalCharFrequenciesToMatch' == 'charFrequenciesInWindowThatMatch' then it can be
     * said that the current window satisfies that property of having all characters with matching
     * counts to the string t.
     */
    final int totalCharFrequenciesToMatch = requiredCharacters.size();
    int charFrequenciesInWindowThatMatch = 0;

    /*
     * We will keep track of the best window we have seen so far
     */
    int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
    String minWindow = "";

    while (right < searchString.length()) {

      /*
       * Add the character on the right pointer to the hashtable that maps the characters seen
       * in the window to their occurrence count
       */
      final char characterAtRightPointer = searchString.charAt(right);
      addCharacterToHashtableMapping(windowCharacterMapping, characterAtRightPointer);

      /*
       * Is this character part of the requirement?
       */
      final boolean rightCharIsARequirement = requiredCharacters.containsKey(characterAtRightPointer);
      if (rightCharIsARequirement) {
        /*
         * Does the current window frequency match the required frequency?
         */
        final boolean requirementForCharacterMet = requiredCharacters.get(characterAtRightPointer)
            .intValue() == windowCharacterMapping.get(characterAtRightPointer).intValue();
        if (requirementForCharacterMet) {
          /*
           * If so then we have one more frequency requirement that matches...remember when:
           * 'totalCharFrequenciesToMatch' == 'charFrequenciesInWindowThatMatch' then we know that
           * we have a satisfying window
           */
          charFrequenciesInWindowThatMatch++;
        }
      }

      /*
       * Does this window satisfy? Ok...if it does try contracting the left pointer inward until
       * we go over the right pointer.
       */
      while (charFrequenciesInWindowThatMatch == totalCharFrequenciesToMatch && left <= right) {

        /*
         * Put these things in plain English so you don't get confused
         */
        final char characterAtLeftPointer = searchString.charAt(left);
        final int windowSize = right - left + 1;

        /*
         * Have we beat the best satisfiable window seen so far? Ok...if so then update
         * the tracking variables
         */
        if (windowSize < minWindowLengthSeenSoFar) {
          minWindowLengthSeenSoFar = windowSize;
          minWindow = searchString.substring(left, right + 1);
        }

        /*
         * This character will get contracted out. It won't be in the window anymore once
         * left moves forward.
         */
        windowCharacterMapping.put(characterAtLeftPointer, windowCharacterMapping.get(characterAtLeftPointer) - 1);

        /*
         * Was this character part of the requirement? If so then its frequency changing matters to us.
         */
        final boolean leftCharIsARequirement = requiredCharacters.containsKey(characterAtLeftPointer);
        if (leftCharIsARequirement) {
          /*
           * Does the character frequence count not fall below the threshold of satisfying?
           */
          final boolean characterFailsRequirement = windowCharacterMapping.get(characterAtLeftPointer)
              .intValue() < requiredCharacters.get(characterAtLeftPointer).intValue();

          if (characterFailsRequirement) {
            /*
             * If so then we have one less character frequency mapping in the window that matches
             */
            charFrequenciesInWindowThatMatch--;
          }
        }

        /*
         * Move the left point forward. We will keep going until the window no longer satisfies.
         */
        left++;
      }

      /*
       * We have moved left as far as it could go. It either led to a window that no longer
       * satisfied or left passed the right pointer. Either way...advance the right pointer.
       */
      right++;
    }

    return minWindow;
  }

  private static Map<Character, Integer> buildMappingOfCharactersToOccurrences(final String s) {

    final Map<Character, Integer> map = new HashMap<Character, Integer>();

    for (int i = 0; i < s.length(); i++) {
      final int occurrencesOfCharacter = map.getOrDefault(s.charAt(i), 0);
      map.put(s.charAt(i), occurrencesOfCharacter + 1);
    }

    return map;
  }

  private static void addCharacterToHashtableMapping(final Map<Character, Integer> map, final Character c) {
    final int occurrences = map.getOrDefault(c, 0);
    map.put(c, occurrences + 1);
  }

  public static void main(final String[] args) {
    final String s = "ADOBECODEBANC";
    final String t = "ABC"; // BANC

//    final String s = "ADOBECODEBANNNNNCFGBXANC";
//    final String t = "ABC"; // BXANC

//    final String s = "a";
//    final String t = "aa"; // ""
//
//    final String s = "aa";
//    final String t = "a"; // "a"

//    final String s = "aa";
//    final String t = "aa"; // "aa"

//    final String s = "baaccbexxxbaabedddbaaab";
//    final String t = "babez"; // "baabe"

    System.out.println(minWindow(s, t));
  }

}
