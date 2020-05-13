package hackerrank.amazon;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * http://hackerrank.com/challenges/swap-nodes-algo/problem
 *
 * @author z003xfbr
 *
 */
public class SwapNodes {

  /*
   * Complete the swapNodes function below.
   */
  static int[][] swapNodes(final int[][] indexes, final int[] queries) {

    return new int[0][];

  }

  public static void main(final String[] args) throws IOException {

    final File file = new File("C:\\Users\\z003xfbr\\Downloads\\swap-nodes-algo-testcases\\input\\input00.txt");
    final Scanner scanner = new Scanner(file);

    final int n = Integer.parseInt(scanner.nextLine().trim());

    final int[][] indexes = new int[n][2];

    for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
      final String[] indexesRowItems = scanner.nextLine().split(" ");

      for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
        final int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
        indexes[indexesRowItr][indexesColumnItr] = indexesItem;
      }
    }

    final int queriesCount = Integer.parseInt(scanner.nextLine().trim());

    final int[] queries = new int[queriesCount];

    for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
      final int queriesItem = Integer.parseInt(scanner.nextLine().trim());
      queries[queriesItr] = queriesItem;
    }

    final int[][] result = swapNodes(indexes, queries);

    for (final int[] element : result) {
      for (final int element2 : element) {
        System.out.println(String.valueOf(element2));
      }

    }

  }

}
