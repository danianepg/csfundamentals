package leetcode.hashtables;

/**
 * https://leetcode.com/problems/count-primes
 *
 * @author Daniane P. Gomes
 *
 *         Count the number of prime numbers less than a non-negative number, n.
 * 
 *         Example:
 * 
 *         Input: 10
 *         Output: 4
 *         Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 */
public class CountPrimes {

  public static int countPrimes(final int n) {

    final boolean[] primes = new boolean[n];
    int primeCount = 0;

    for (int i = 2; i * i < primes.length; i++) {
      if (!primes[i]) {
        for (int j = i; j * i < primes.length; j++) {
          primes[i * j] = true;
        }
      }
    }

    for (int i = 2; i < primes.length; i++) {
      if (!primes[i]) {
        primeCount++;
      }
    }

    return primeCount;
  }

  public static void main(final String[] args) {
    System.out.println(countPrimes(10));
  }

}
