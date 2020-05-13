package leetcode.amazon;

import java.util.ArrayList;
import java.util.List;

public class Cells {

  public List<Integer> cellCompete(final int[] states, int days) {

    final List<Integer> answer = new ArrayList<>();

    for (final int i : states) {
      answer.add(i);
    }

    while (days > 0) {

      final List<Integer> answerCopy = new ArrayList<>(answer);
      answer.clear();

      for (int i = 0; i < answerCopy.size(); i++) {

        int prev;
        int next;

        if (i == 0) {
          prev = 0;
        } else {
          prev = answerCopy.get(i - 1);
        }

        if (i == answerCopy.size() - 1) {
          next = 0;
        } else {
          next = answerCopy.get(i + 1);
        }

        if (prev == next) {
          answer.add(0);
        } else {
          answer.add(1);
        }

      }

      days--;
    }

    return answer;
  }

  public static void main(final String[] args) {
    final Cells cell = new Cells();
    final int days = 2;
    final int[] states = { 1, 1, 1, 0, 1, 1, 1, 1 };
    System.out.println(cell.cellCompete(states, days));

  }
}
