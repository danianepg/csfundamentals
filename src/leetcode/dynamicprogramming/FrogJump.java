package leetcode.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/frog-jump/
 * Hard
 *
 * @author danianepg
 *
 *         A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a
 *         stone. The frog can jump on a stone, but it must not jump into the water.
 *
 *         Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to
 *         cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first
 *         jump must be 1 unit.
 *
 *         If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that
 *         the frog can only jump in the forward direction.
 *
 *         Note:
 *
 *         The number of stones is ≥ 2 and is < 1,100.
 *         Each stone's position will be a non-negative integer < 231.
 *         The first stone's position is always 0.
 *
 *         Example 1:
 *
 *         [0,1,3,5,6,8,12,17]
 *
 *         There are a total of 8 stones.
 *         The first stone at the 0th unit, second stone at the 1st unit,
 *         third stone at the 3rd unit, and so on...
 *         The last stone at the 17th unit.
 *
 *         Return true. The frog can jump to the last stone by jumping
 *         1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 *         2 units to the 4th stone, then 3 units to the 6th stone,
 *         4 units to the 7th stone, and 5 units to the 8th stone.
 *
 *         Example 2:
 *
 *         [0,1,2,3,4,8,9,11]
 *
 *         Return false. There is no way to jump to the last stone as
 *         the gap between the 5th and 6th stone is too large.
 *
 */
public class FrogJump {

  public static boolean canCross(final int[] stones) {

    // Math. It just works =/
    // Without this part, time limit exceeds
    for (int i = 3; i < stones.length; i++) {
      if (stones[i] > stones[i - 1] * 2) {
        return false;
      }
    }

    final Set<Integer> mapStones = new HashSet<>();
    for (final int s : stones) {
      mapStones.add(s);
    }

    final int endStone = stones[stones.length - 1];

    // I will use a stack to make sure that I'm checking the last element added and avoid extra work
    final Stack<Integer> positions = new Stack<>();
    final Stack<Integer> jumps = new Stack<>();
    positions.add(0);
    jumps.add(0);

    // I add one element of the array on my stack and from it, I verify if I can reach some in the list
    // If I can reach some, I add it to my stack
    // For every reachable element, I keep verifying if it can reach another one until eventually one will reach the
    // last one.

    while (!positions.isEmpty()) {
      final int position = positions.pop();
      final int jumpDistance = jumps.pop();

      // System.out.println("position " + position + " - jumpDistance " + jumpDistance);

      for (int i = jumpDistance - 1; i <= jumpDistance + 1; i++) {
        if (i <= 0) {
          continue;
        }

        final int nextPosition = position + i;
        if (nextPosition == endStone) {
          return true;
        } else if (mapStones.contains(nextPosition)) {

          // For each possible stone, add it on the stack with the number of jumps required to get to it
          // System.out.println("ADD " + nextPosition + " jumps " + i);
          positions.add(nextPosition);
          jumps.add(i);
        }

      }
    }

    return false;
  }

  public static void main(final String[] args) {
    final int[] stones = { 0, 1, 3, 5, 6, 8, 12, 17 };
    System.out.println(canCross(stones));
  }

}
