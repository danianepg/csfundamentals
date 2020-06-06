package leetcode.stringmanipulation;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 *
 * @author danianepg
 *
 */
public class LongestWordDictionary {

  public static String longestWord(final String[] words) {

    final Set<String> mySet = new HashSet<>();
    for (final String word : words) {
      mySet.add(word);
    }

    // Slower than the code above
    // final Set<String> mySet = Arrays.stream(wordsLst).collect(Collectors.toSet());

    String lastWord = "";

    for (final String tempWord : mySet) {

      final Integer lastWordLen = lastWord.length();

      if (tempWord.length() > lastWordLen || (tempWord.length() == lastWordLen && tempWord.compareTo(lastWord) < 0)) {

        final Boolean hasParts = checkParts(mySet, tempWord);
        if (hasParts) {
          lastWord = tempWord;
        }

      }

    }

    return lastWord;

  }

  private static Boolean checkParts(final Set<String> wordsLst, final String tempWord) {
    Boolean hasParts = Boolean.TRUE;

    for (int j = 1; j <= tempWord.length(); j++) {
      final String tmpPart = tempWord.substring(0, j);

      if (!wordsLst.contains(tmpPart)) {
        hasParts = Boolean.FALSE;
        break;
      }
    }

    return hasParts;
  }

  public static void main(final String[] args) {
    final String[] words = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
    System.out.println(longestWord(words));
  }

}
