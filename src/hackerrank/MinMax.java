package hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MinMax {

  static int maxMin(final int k, final int[] arr) {
    Arrays.sort(arr);
    int minu = arr[arr.length - 1];
    int num = 0;
    for (int i = 0; i <= arr.length - k; i++) {
      num = arr[i + k - 1] - arr[i];
      if (num <= minu) {
        minu = num;
      }
    }

    return minu;
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File(
        "E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\angry-children-testcases\\input\\input02.txt");

    final BufferedReader br = new BufferedReader(new FileReader(file));

    final int size = Integer.parseInt(br.readLine());
    final int subArraySize = Integer.parseInt(br.readLine());

    final int[] arr = new int[size];

    for (int i = 0; i < size; i++) {
      final String line = br.readLine();
      arr[i] = Integer.parseInt(line);
    }

    final int result = maxMin(subArraySize, arr);
    System.out.println("RESULT " + result);

    br.close();

  }
}
