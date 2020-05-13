package hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumToys {

  static Long maximumToys(final int[] prices, final int k) {

    Long maxToys = 0L;
    Long valueSpent = 0L;
    final List<Long> pricesOrdered = new ArrayList<>();

    for (final int price : prices) {
      pricesOrdered.add(Long.valueOf(price));
    }

    Collections.sort(pricesOrdered);

    for (final Long price : pricesOrdered) {

      if (price > k) {
        break;
      }

      valueSpent += price;

      if (valueSpent < k) {
        maxToys++;
      } else {
        return maxToys;
      }

    }

    return maxToys;
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File(
        "E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\mark-and-toys-testcases\\input\\input16.txt");

    final BufferedReader br = new BufferedReader(new FileReader(file));

    final String[] st = br.readLine().split(" ");
    final int size = Integer.parseInt(st[0]);
    final int value = Integer.parseInt(st[1]);

    final int arr[] = new int[size];
    final String[] lineArr = br.readLine().split(" ");

    for (int i = 0; i < lineArr.length; i++) {
      arr[i] = Integer.parseInt(lineArr[i]);
    }

    final Long result = maximumToys(arr, value);
    System.out.println("RESULT " + result);

    br.close();

  }
}
