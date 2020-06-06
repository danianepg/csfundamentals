package leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which, when
 * removed along with associated edges, makes the graph disconnected (or more precisely, increases the number of
 * connected components in the graph). The task is to find all articulation points in the given graph.
 *
 * Input:
 * The input to the function/method consists of three arguments:
 *
 * numNodes, an integer representing the number of nodes in the graph.
 * numEdges, an integer representing the number of edges in the graph.
 * edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
 * Output:
 * Return a list of integers representing the critical nodes.
 *
 * https://leetcode.com/discuss/interview-question/436073/
 *
 * @author danianepg
 *
 */
public class CriticalRouters {

  static int time = 0;

  // Tarjan's algorithm
  // O(numNodes + numEdges)
  public List<Integer> getCriticalRouters(final int numRouters, final int numLinks, final int[][] links) {

    final Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < numRouters; i++) {
      map.put(i, new HashSet<>());
    }

    for (final int[] link : links) {
      map.get(link[0]).add(link[1]);
      map.get(link[1]).add(link[0]);
    }

    final Set<Integer> set = new HashSet<>();

    // Node with the smallest/ lowest node id reachable from that node when doing a dfs (including itself)
    final int[] low = new int[numRouters];
    final int[] ids = new int[numRouters];
    final int[] parent = new int[numRouters];

    Arrays.fill(ids, -1);
    Arrays.fill(parent, -1);

    for (int i = 0; i < numRouters; i++) {
      // If the node was not visited yet, calls dfs() cursively to find articulations
      if (ids[i] == -1) {
        this.dfs(map, low, ids, parent, i, set);
      }
    }

    return new ArrayList<>(set);

  }

  private void dfs(final Map<Integer, Set<Integer>> map, final int[] low, final int[] ids, final int[] parent,
      final int cur, final Set<Integer> answer) {

    int children = 0;
    time++;

    // Initialize discovery time and low value
    low[cur] = time;
    ids[cur] = low[cur];

    for (final int neighbor : map.get(cur)) {
      if (ids[neighbor] == -1) {

        children++;
        parent[neighbor] = cur;

        this.dfs(map, low, ids, parent, neighbor, answer);

        // Check if the subtree rooted with neighbor has a connection to
        // one of the ancestors of cur
        low[cur] = Math.min(low[cur], low[neighbor]);

        // It's a critical router if:
        // (1) parent[cur] is root of DFS tree and has two or more children.
        // (2) If parent[cur] is NOT root and low value of one of its child
        // is more than discovery value of parent[cur] .
        if ((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[neighbor] >= ids[cur])) {
          answer.add(cur);
        }

      } else if (neighbor != parent[cur]) {

        // Update low value of cur for parent function calls.
        low[cur] = Math.min(low[cur], ids[neighbor]);
      }

    }

  }

  public static void main(final String[] args) {
    final CriticalRouters obj = new CriticalRouters();

    final int numRouters1 = 7;
    final int numLinks1 = 7;
    final int[][] links1 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 2, 5 }, { 5, 6 }, { 3, 4 } };

    final List<Integer> res = obj.getCriticalRouters(numRouters1, numLinks1, links1);

    for (final int i : res) {
      System.out.print(i + " ");
    }
    System.out.println();

//    final int numRouters2 = 5;
//    final int numLinks2 = 5;
//    final int[][] links2 = { { 1, 2 }, { 0, 1 }, { 2, 0 }, { 0, 3 }, { 3, 4 } };
//
//    final List<Integer> res2 = obj.getCriticalRouters(numRouters2, numLinks2, links2);
//    for (final int i : res2) {
//      System.out.print(i + " ");
//    }
//    System.out.println();
//
//    final int numRouters3 = 4;
//    final int numLinks3 = 4;
//    final int[][] links3 = { { 0, 1 }, { 1, 2 }, { 2, 3 } };
//
//    final List<Integer> res3 = obj.getCriticalRouters(numRouters3, numLinks3, links3);
//    for (final int i : res3) {
//      System.out.print(i + " ");
//    }
//    System.out.println();
//
//    final int numRouters4 = 7;
//    final int numLinks4 = 8;
//    final int[][] links4 = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 6 }, { 3, 5 }, { 4, 5 } };
//
//    final List<Integer> res4 = obj.getCriticalRouters(numRouters4, numLinks4, links4);
//    for (final int i : res4) {
//      System.out.print(i + " ");
//    }
//    System.out.println();
  }

}
