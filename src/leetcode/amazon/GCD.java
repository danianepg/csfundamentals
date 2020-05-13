package leetcode.amazon;

public class GCD {

  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public int generalizedGCD(final int num, final int[] arr) {
    // WRITE YOUR CODE HERE
    int gcd = 1;

    for (int i = arr[arr.length - 1]; i > 0; i--) {

      boolean divisible = true;

      for (int j = arr.length - 1; j >= 0; j--) {
        final int remain = arr[j] % i;

        if (remain != 0) {
          divisible = false;
        }
      }

      if (divisible) {
        gcd = Math.max(gcd, i);
      }

    }

    return gcd;

  }

  public static void main(final String[] args) {

    final GCD gcd = new GCD();
    final int[] arr = { 2, 4, 6, 8, 10 };
    System.out.println(gcd.generalizedGCD(5, arr));

    final int[] arr1 = { 2, 3, 4, 5, 6 };
    System.out.println(gcd.generalizedGCD(5, arr1));

    final int[] arr3 = { 5, 15, 10, 20 };
    System.out.println(gcd.generalizedGCD(5, arr3));

    final int[] arr4 = { 3, 15 };
    System.out.println(gcd.generalizedGCD(5, arr4));

  }

}
