package hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {

  static long countTriplets(final List<Long> arr, final long r) {

    final Map<Long, Long> t2 = new HashMap<>();
    final Map<Long, Long> t3 = new HashMap<>();
    long result = 0L;

    for (final Long a : arr) {

      result += t3.getOrDefault(a, 0L);
      System.out.println("a = " + a + " visto em t3 " + t3.getOrDefault(a, 0L) + " vezes ");

      if (t2.containsKey(a)) {
        System.out.println("\t a = " + a + "ja visto em t2 " + t2.get(a) + " vezes ");
        System.out.println("\t entao adiciona " + (a * r) + " a t3 += " + t3.getOrDefault(a * r, 0L) + " vezes ");

        t3.put(a * r, t3.getOrDefault(a * r, 0L) + t2.get(a));
      }

      System.out.println("Sempre adiciona ao t2 o proximo [" + a * r + "] 1 += " + t2.getOrDefault(a * r, 0L));
      t2.put(a * r, t2.getOrDefault(a * r, 0L) + 1);
    }

    return result;

  }

  public static void main(final String[] args) throws IOException {

    final File file = new File(
        "E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\count-triplets\\input\\input01.txt");

    final BufferedReader br = new BufferedReader(new FileReader(file));

    final String[] st = br.readLine().split(" ");
    Integer.parseInt(st[0]);
    final long ratio = Long.valueOf(st[1]);

    final List<Long> arr = new ArrayList<>();
    final String[] lineArr = br.readLine().split(" ");

    for (final String element : lineArr) {
      arr.add(Long.valueOf(element));
    }

    final long result = countTriplets(arr, ratio);
    System.out.println("RESULT " + result);

    br.close();

  }
}
