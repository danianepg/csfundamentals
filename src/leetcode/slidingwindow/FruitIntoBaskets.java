package leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * Medium
 *
 * @author z003xfbr
 *
 *         In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 *         You start at any tree of your choice, then repeatedly perform the following steps:
 *
 *         Add one piece of fruit from this tree to your baskets. If you cannot, stop.
 *         Move to the next tree to the right of the current tree. If there is no tree to the right, stop.
 *         Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then
 *         step 2, then back to step 1, then step 2, and so on until you stop.
 *
 *         You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry
 *         one type of fruit each.
 *
 *         What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 *         Example 1:
 *
 *         Input: [1,2,1]
 *         Output: 3
 *         Explanation: We can collect [1,2,1].
 *
 *         Example 2:
 *         Input: [0,1,2,2]
 *         Output: 3
 *         Explanation: We can collect [1,2,2].
 *         If we started at the first tree, we would only collect [0, 1].
 *
 *         Example 3:
 *         Input: [1,2,3,2,2]
 *         Output: 4
 *         Explanation: We can collect [2,3,2,2].
 *         If we started at the first tree, we would only collect [1, 2].
 *
 *         Example 4:
 *
 *         Input: [3,3,3,1,2,1,1,2,3,3,4]
 *         Output: 5
 *         Explanation: We can collect [1,2,1,1,2].
 *         If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *         What is the length of longest subarray that contains up to two distinct integers?
 *
 */
public class FruitIntoBaskets {

  /**
   * We have to maximize the number of the same "fruit" in a maximum of "maxBaskets" different types.
   *
   * Keep adding fruits to the basket. When the basket is full, but we find a different fruit, iterate over the basket
   * decrementing the first fruits quantities. If the fruit disappears from the basket, just remove it.
   *
   * @param tree
   * @return
   */
  public static int totalFruit(final int[] tree) {

    final Map<Integer, Integer> basket = new HashMap<>();
    final int maxBaskets = 2;
    int maxFruits = 0;
    int start = 0;

    for (int i = 0; i < tree.length; i++) {

      // If there's a new fruit but we already filled all the baskets, move the window.
      if (!basket.containsKey(tree[i]) && basket.size() == maxBaskets) {

        while (basket.size() == maxBaskets) {

          basket.put(tree[start], basket.get(tree[start]) - 1);

          final int elemAux = tree[start];
          start++;
          if (basket.get(elemAux) == 0) {
            basket.remove(tree[start - 1]);
            break;
          }

        }
      }

      basket.put(tree[i], basket.getOrDefault(tree[i], 0) + 1);
      // Update the max qtd of fruits from the current position
      maxFruits = Math.max(maxFruits, i - start + 1);
    }

    return maxFruits;

  }

  public static void main(final String[] args) {

//    final int[] tree = { 1, 2, 1 };
//    final int[] tree = { 0, 1, 2, 2 };
//    final int[] tree = { 1, 2, 3, 2, 2 };
    final int[] tree = { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 };
    // 0 1 2 3 4 5 6 7 8 9 10
    System.out.println(totalFruit(tree));

  }

}
