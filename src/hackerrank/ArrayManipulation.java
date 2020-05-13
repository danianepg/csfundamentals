package hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.hackerrank.com/challenges/crush/problem
 * 
 * @author z003xfbr
 *
 *         Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each of
 *         the array element between two given indices, inclusive. Once all operations have been performed, return the
 *         maximum value in your array.
 *
 *         For example, the length of your array of zeros . Your list of queries is as follows:
 *
 *         a b k
 *         1 5 3
 *         4 8 7
 *         6 9 1
 *         Add the values of between the indices and inclusive:
 *
 *         index-> 1 2 3 4 5 6 7 8 9 10
 *         [0,0,0, 0, 0,0,0,0,0, 0]
 *         [3,3,3, 3, 3,0,0,0,0, 0]
 *         [3,3,3,10,10,7,7,7,0, 0]
 *         [3,3,3,10,10,8,8,8,1, 0]
 *         The largest value is after all operations are performed.
 *
 *         Function Description
 *
 *         Complete the function arrayManipulation in the editor below. It must return an integer, the maximum value in
 *         the resulting array.
 *
 *         arrayManipulation has the following parameters:
 *
 *         n - the number of elements in your array
 *         queries - a two dimensional array of queries where each queries[i] contains three integers, a, b, and k.
 *         Input Format
 *
 *         The first line contains two space-separated integers and , the size of the array and the number of
 *         operations.
 *         Each of the next lines contains three space-separated integers , and , the left index, right index and
 *         summand.
 *
 *         Constraints
 *
 *         Output Format
 *
 *         Return the integer maximum value in the finished array.
 *
 *         Sample Input
 *
 *         5 3
 *         1 2 100
 *         2 5 100
 *         3 4 100
 *         Sample Output
 *
 *         200
 *         Explanation
 *
 *         After the first update list will be 100 100 0 0 0.
 *         After the second update list will be 100 200 100 100 100.
 *         After the third update list will be 100 200 200 200 100.
 *         The required answer will be .
 *
 */
public class ArrayManipulation {

  public static Comparator<Operation> SORT_BY_START_INDEX = Comparator.comparingInt(Operation::a);

  public static Comparator<Operation> SORT_BY_END_INDEX = Comparator.comparingInt(Operation::b);

  public static long compute(final int N, final Operation[] operations) {
    Arrays.sort(operations, SORT_BY_START_INDEX);

    final PriorityQueue<Operation> heap = new PriorityQueue<>(SORT_BY_END_INDEX);

    long accumulation = 0;
    long maxValue = 0;

    for (int i = 0; i < N; i++) {
      final int index = i + 1;
      System.out.println("index " + index);

      while (!heap.isEmpty()) {
        final Operation top = heap.peek();
        if (top.b() < index) {
          // take off and substract from accumulation
          System.out.println("WHILE: " + top.b() + "<" + index + " entao subtrai ");
          accumulation -= heap.poll().k();
        } else {
          System.out.println("WHILE: " + top.b() + "<" + index + ": break");
          break;
        }
      }

      for (int j = 0; j < operations.length; j++) {
        final Operation operation = operations[j];
        System.out.print("FOR j [" + j + "]");

        if (index < operation.a()) {
          System.out.println(index + "<" + operation.a() + " break");
          break;
        } else {
          System.out.println(index + "<" + operation.a() + " add " + operation.k());

          accumulation += operation.k();
          heap.offer(operation);
        }
      }

      maxValue = Math.max(maxValue, accumulation);
      System.out.println("MAX " + maxValue);
    }
    return maxValue;
  }

  // Complete the arrayManipulation function below.
  static long arrayManipulation(final int colunms, final int[][] queries) {

    final Operation[] opArr = new Operation[queries.length];
    for (int i = 0; i < queries.length; i++) {
      final Operation o = new Operation(queries[i][0], queries[i][1], queries[i][2]);
      opArr[i] = o;
    }

    return compute(colunms, opArr);

  }

  public static class Operation {

    final private int a;

    final private int b;

    final private long k;

    public Operation(final int a, final int b, final long k) {
      this.a = a;
      this.b = b;
      this.k = k;
    }

    public int a() {
      return this.a;
    }

    public int b() {
      return this.b;
    }

    public long k() {
      return this.k;
    }

    @Override
    public String toString() {
      return "[" + this.a + ", " + this.b + ", " + this.k + "]";
    }
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File("E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\input07.txt");

    final BufferedReader br = new BufferedReader(new FileReader(file));

    final String[] st = br.readLine().split(" ");
    final int n = Integer.parseInt(st[0]);
    final int m = Integer.parseInt(st[1]);
    final int[][] queries = new int[m][3];

    for (int i = 0; i < m; i++) {
      final String[] queriesRowItems = br.readLine().split(" ");

      for (int j = 0; j < 3; j++) {
        final int queriesItem = Integer.parseInt(queriesRowItems[j]);
        queries[i][j] = queriesItem;
      }
    }

    final long result = arrayManipulation(n, queries);
    System.out.println("RESULT " + result);

    br.close();

    // while ((String lin = br.readLine()) != null) {
    // System.out.println(lin);
    // }
    //

  }
}
