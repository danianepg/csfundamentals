package hackerrank.amazon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/contacts/problem
 *
 * @author z003xfbr
 *
 */
public class Contacts {

  private static final String ADD = "add";

  private static final String FIND = "find";

  /*
   * Complete the contacts function below.
   */
  static int[] contacts(final String[][] queries) {

    if (queries == null || queries.length <= 0) {
      return new int[0];
    }

    final List<Integer> resultCount = new ArrayList<>();
    final Map<String, Integer> map = new HashMap<>();

    for (final String[] query : queries) {
      if (query != null && query.length >= 2) {

        final String action = query[0];
        final String word = query[1];

        if (action.equalsIgnoreCase(ADD)) {
          add(map, word);
        } else if (action.equalsIgnoreCase(FIND)) {
          resultCount.add(map.getOrDefault(word, 0));
        }
      }
    }

    final int[] result = convertResult(resultCount);
    return result;
  }

  private static int[] convertResult(final List<Integer> resultCount) {
    final int[] result = new int[resultCount.size()];
    int index = 0;

    for (final Integer unit : resultCount) {
      result[index] = unit;
      index++;
    }
    return result;
  }

  private static void add(final Map<String, Integer> map, final String word) {

    final char[] charArr = word.toCharArray();
    final StringBuilder stackChar = new StringBuilder(String.valueOf(charArr[0]));

    for (int i = 1; i < charArr.length; i++) {

      final Integer occurrences = map.getOrDefault(stackChar.toString(), 0) + 1;
      map.put(stackChar.toString(), occurrences);
      stackChar.append(String.valueOf(charArr[i]));

    }

    final Integer occurrences = map.getOrDefault(stackChar.toString(), 0) + 1;
    map.put(stackChar.toString(), occurrences);
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File("C:\\Users\\z003xfbr\\Downloads\\contacts-testcases\\input\\input11.txt");
    final Scanner scanner = new Scanner(file);

    final int queriesRows = Integer.parseInt(scanner.nextLine().trim());

    final String[][] queries = new String[queriesRows][2];

    for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
      final String[] queriesRowItems = scanner.nextLine().split(" ");

      for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
        final String queriesItem = queriesRowItems[queriesColumnItr];
        queries[queriesRowItr][queriesColumnItr] = queriesItem;
      }
    }

    final int[] result = contacts(queries);

    for (final int i : result) {
      System.out.println(i);
    }
  }

}
