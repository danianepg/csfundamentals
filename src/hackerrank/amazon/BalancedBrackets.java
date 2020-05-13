package hackerrank.amazon;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/balanced-brackets
 * 
 * @author z003xfbr
 *
 */
public class BalancedBrackets {

  private static final String YES = "YES";

  private static final String NO = "NO";

  // Complete the isBalanced function below.
  static String isBalanced(final String s) {

    final Map<Character, Character> pairs = new HashMap<>();
    pairs.put('(', ')');
    pairs.put('{', '}');
    pairs.put('[', ']');

    final List<Character> closing = Arrays.asList(')', '}', ']');

    final Stack<Character> stack = new Stack<>();

    for (final char myStr : s.toCharArray()) {

      stack.add(myStr);

      if (closing.contains(myStr)) {
        if (stack.size() >= 2) {
          final Character last = stack.pop();
          final Character before = stack.pop();

          if (!pairs.getOrDefault(before, '0').equals(last)) {
            return BalancedBrackets.NO;
          }
        }
      }
    }

    if (stack.isEmpty()) {
      return BalancedBrackets.YES;
    }

    return BalancedBrackets.NO;
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File("C:\\Users\\z003xfbr\\Downloads\\balanced-brackets-testcases\\input\\input1.txt");
    final Scanner scanner = new Scanner(file);

    final int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      final String s = scanner.nextLine();

      final String result = isBalanced(s);
      System.out.println(result);

    }

    scanner.close();
  }
}
