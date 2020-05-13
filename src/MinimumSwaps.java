package hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumSwaps {

  public static class Element {

    private int n;

    private int curPos;

    public Element(final int n, final int curPos) {
      this.n = n;
      this.curPos = curPos;
    }

    public int getN() {
      return this.n;
    }

    public void setN(final int n) {
      this.n = n;
    }

    public int getCurPos() {
      return this.curPos;
    }

    public void setCurPos(final int curPos) {
      this.curPos = curPos;
    }

    @Override
    public String toString() {
      return "Element [n=" + this.n + ", curPos=" + this.curPos + "]";
    }

  }

  static int minimumSwaps(final int[] arr) {

    final List<Element> el = new ArrayList<>();
    final Map<Integer, Element> map = new HashMap<>();
    int changes = 0;

    for (int i = 0; i < arr.length; i++) {
      final Element e = new Element(arr[i], i);
      el.add(e);
      map.put(e.getCurPos(), e);
    }

    final Comparator<Element> sortAsc = Comparator.comparingInt(Element::getN);
    el.sort(sortAsc);

    for (int i = 0; i < arr.length - 1; i++) {
      final Element curEl = el.get(i);
      final int curPos = curEl.getCurPos();

      System.out.println("Comparing element " + curEl.getN());

      if (curPos != i) {

        final Element elemOnMyPos = map.get(i);
        curEl.setCurPos(elemOnMyPos.getCurPos());
        elemOnMyPos.setCurPos(curPos);

        map.put(i, curEl);
        map.put(curPos, elemOnMyPos);

        changes++;

        System.out.println("\t Change position from " + curPos + " to " + curEl.getCurPos());
      }
    }

    return changes;
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File(
        "E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\minimum-swaps-2-testcases\\input\\input02.txt");

    final BufferedReader br = new BufferedReader(new FileReader(file));

    final String st = br.readLine();
    final int size = Integer.parseInt(st);

    final int arr[] = new int[size];
    final String[] lineArr = br.readLine().split(" ");

    for (int i = 0; i < lineArr.length; i++) {
      arr[i] = Integer.parseInt(lineArr[i]);
    }

    final int result = minimumSwaps(arr);
    System.out.println("RESULT " + result);

    br.close();

    // while ((String lin = br.readLine()) != null) {
    // System.out.println(lin);
    // }
    //

  }
}
