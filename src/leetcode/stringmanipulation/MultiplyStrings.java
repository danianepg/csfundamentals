package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/multiply-strings/
 * 
 * @author danianepg
 *
 */
public class MultiplyStrings {

  public static String multiply(final String num1, final String num2) {

    final int op1 = num1.length();
    final int op2 = num2.length();
    final int[] pos = new int[op1 + op2];

//    printArray(pos);

    for (int i = op1 - 1; i >= 0; i--) {
      for (int j = op2 - 1; j >= 0; j--) {

        final int mult = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        final int p1 = i + j;
        final int p2 = i + j + 1;
        final int sum = mult + pos[p2];

//        System.out.println("\nMULT " + (num1.charAt(i) - '0') + " x " + (num2.charAt(j) - '0') + " = " + mult);
//        System.out.println("[i][j] = [" + i + "][" + j + "] -> p1 " + p1 + " - p2 " + p2);
//        System.out.println("SUM (" + mult + ") + " + pos[p2] + " = " + sum);

        pos[p1] += sum / 10;
        pos[p2] = (sum) % 10;

//        System.out.println("pos[p1] = " + pos[p1]);
//        System.out.println("pos[p2] = " + pos[p2]);
//        printArray(pos);

      }
    }

    final StringBuilder sb = new StringBuilder();
    for (final int p : pos) {
      if (sb.length() > 0 || p > 0) {
        sb.append(p);
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return "0";
  }

  public static void printArray(final int[] arr) {
    System.out.print("[");
    for (final int element : arr) {
      System.out.print(element + ", ");
    }
    System.out.print("]\n");

  }

  public static void main(final String[] args) {

//    final String num1 = "123456789";
//    final String num2 = "987654321";
    final String num1 = "123";
    final String num2 = "456";

    System.out.println(multiply(num1, num2));
  }

}
