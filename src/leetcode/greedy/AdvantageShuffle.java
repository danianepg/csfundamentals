package leetcode.greedy;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/advantage-shuffle/
 * Medium
 *
 * @author danianepg
 *
 *         Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for
 *         which A[i] > B[i].
 *
 *         Return any permutation of A that maximizes its advantage with respect to B.
 *
 *
 *
 *         Example 1:
 *
 *         Input: A = [2,7,11,15], B = [1,10,4,11]
 *         Output: [2,11,7,15]
 *         Example 2:
 *
 *         Input: A = [12,24,8,32], B = [13,25,32,11]
 *         Output: [24,32,8,12]
 *
 */
public class AdvantageShuffle {

  public static int[] advantageCountGreedy(final int[] A, final int[] B) {

    final int[] sortedA = A.clone();
    Arrays.sort(sortedA);
    final int[] sortedB = B.clone();
    Arrays.sort(sortedB);

    // assigned[b] = list of a that are assigned to beat b
    final Map<Integer, Deque<Integer>> assigned = new HashMap<>();
    for (final int b : B) {
      assigned.put(b, new LinkedList<>());
    }

    // remaining = list of a that are not assigned to any b
    final Deque<Integer> remaining = new LinkedList<>();

    // populate (assigned, remaining) appropriately
    // sortedB[j] is always the smallest unassigned element in B

    // If there's an element in A that is > than B, add to the assigned group. Otherwise, add to the remaining
    int j = 0;
    for (final int a : sortedA) {
      if (a > sortedB[j]) {
        assigned.get(sortedB[j++]).add(a);
      } else {
        remaining.add(a);
      }
    }

    // Assigned has the biggest elements in A and remainig has the unmatched elements.
    // Reconstruct the answer from annotations (assigned, remaining)
    final int[] ans = new int[B.length];

    for (int i = 0; i < B.length; ++i) {

      // if there is some a assigned to b...
      if (!assigned.get(B[i]).isEmpty()) {
        ans[i] = assigned.get(B[i]).pop();
      } else {
        ans[i] = remaining.pop();
      }
    }
    return ans;
  }

  public static int[] advantageCount(final int[] A, final int[] B) {
    final int[] advantages = new int[A.length];

    Arrays.sort(A);

    final PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[1] - a[1]));

    for (int i = 0; i < B.length; i++) {
      maxHeap.offer(new int[] { i, B[i] });
    }

    int slow = 0;
    int fast = A.length - 1;

    while (!maxHeap.isEmpty()) {
      final int[] b = maxHeap.poll();
      if (b[1] >= A[fast]) {
        advantages[b[0]] = A[slow];
        slow++;
      } else {
        advantages[b[0]] = A[fast];
        fast--;
      }
    }

    return advantages;
  }

  public static void main(final String[] args) {

    final int[] A = { 2, 0, 4, 1, 2 };
    final int[] B = { 1, 3, 0, 0, 2 };

    System.out.println(advantageCountGreedy(A, B));
  }

}
