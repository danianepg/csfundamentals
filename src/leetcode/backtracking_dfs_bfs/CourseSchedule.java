package leetcode.backtracking_dfs_bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule/
 * Medium
 *
 * @author z003xfbr
 *
 *         There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 *         Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 *         expressed as a pair: [0,1]
 *
 *         Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all
 *         courses?
 *
 *         Example 1:
 *
 *         Input: 2, [[1,0]]
 *         Output: true
 *         Explanation: There are a total of 2 courses to take.
 *         To take course 1 you should have finished course 0. So it is possible.
 *         Example 2:
 *
 *         Input: 2, [[1,0],[0,1]]
 *         Output: false
 *         Explanation: There are a total of 2 courses to take.
 *         To take course 1 you should have finished course 0, and to take course 0 you should
 *         also have finished course 1. So it is impossible.
 *         Note:
 *
 *         The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
 *         how a graph is represented.
 *         You may assume that there are no duplicate edges in the input prerequisites.
 *
 */
public class CourseSchedule {

  /**
   * This is a problem related to Topological sort. The prerequisites represent a graph and we have to check if there's
   * a cycle on the graph. If there's one, it's not possible to finish the course. One to solve this is to use in-degree
   * approach. (another is DFS)
   *
   * Save all the subjects on an array. The array position refers to the subject and the number it holds refers to how
   * many time this subject appears on the course. We will use an auxiliary stack to deal with each subject.
   *
   * While popping from the stack, we traverse the main array again checking the prerequisites and when they are equals
   * to to the subject popped, we decrement the in-degree of the main subject. When it reaches zero, we push the main
   * subject to the stack.
   *
   * To check if it is possible to finish the course, while popping we save the number of subjects popped and at the end
   * we compare it to the number received as parameter.
   *
   * Time complexity: Worst case O(V*E) vertices * edges
   *
   * @param numCourses
   * @param prerequisites
   * @return
   */
  public static boolean canFinishInDegree(final int numCourses, final int[][] prerequisites) {
    final int[] inDegree = new int[numCourses];
    int count = 0;

    for (int i = 0; i < prerequisites.length; i++) {
      inDegree[prerequisites[i][0]]++;
    }

    final LinkedList<Integer> stack = new LinkedList<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        stack.push(i);
      }
    }

    while (!stack.isEmpty()) {
      final int cur = stack.pop();
      count++;

      for (int i = 0; i < prerequisites.length; i++) {
        if (prerequisites[i][1] == cur) {
          inDegree[prerequisites[i][0]]--;
          if (inDegree[prerequisites[i][0]] == 0) {
            stack.push(prerequisites[i][0]);
          }
        }
      }
    }

    return count == numCourses;

  }

  public static boolean canFinish(final int numCourses, final int[][] prerequisites) {

    if (prerequisites == null || prerequisites.length <= 0) {
      return true;
    }

    final Map<Integer, Set<Integer>> nodes = new HashMap<>();

    for (final int[] elems : prerequisites) {
      if (elems.length == 2) {

        final int key = elems[0];
        final int dependency = elems[1];

        final Set<Integer> dependencyLst = nodes.getOrDefault(dependency, new HashSet<>());
        dependencyLst.add(key);
        nodes.put(dependency, dependencyLst);
      }

    }

    if (nodes.size() <= 0) {
      return true;
    }

    final HashMap<Integer, Boolean> visited = new HashMap<>();
    final HashMap<Integer, Boolean> onStack = new HashMap<>();

    for (final int[] prerequisite : prerequisites) {
      final int current = prerequisite[0];
      dfs(nodes, current, visited, onStack);
    }

    final long possible = onStack.entrySet().stream().filter(key -> !key.getValue()).count();

    return (possible < numCourses && possible != onStack.size()) ? Boolean.FALSE : Boolean.TRUE;

  }

  public static boolean dfs(final Map<Integer, Set<Integer>> nodes, final int current,
      final HashMap<Integer, Boolean> visited, final HashMap<Integer, Boolean> onStack) {

    onStack.put(current, true); // mark current on the stack of a post-order traversal

    for (final int parent : nodes.getOrDefault(current, new HashSet<>())) {

      System.out.println("parent " + parent);

      if (onStack.getOrDefault(parent, false)) { // one of the dependency is still not popped from the stack
        return false;
      }

      if (!visited.getOrDefault(parent, false) && !dfs(nodes, parent, visited, onStack)) {
        return false;
      }

    }

    visited.put(current, true);
    onStack.put(current, false);

    return true;
  }

  public static void main(final String[] args) {
//    final int numCourses = 3;
//    final int[][] prerequisites = { { 10, 20 }, { 20, 10 } };
//    final int[][] prerequisites = { { 1, 0 }, { 2, 1 }, { 2, 3 }, { 3, 2 }, { 4, 5 }, { 5, 1 } };

//    final int numCourses = 1;
//    final int[][] prerequisites = {}; // true
//
//    final int numCourses = 3;
//    final int[][] prerequisites = { { 1, 0 } }; // true

//    final int numCourses = 4;
//    final int[][] prerequisites = { { 2, 0 }, { 1, 0 }, { 3, 1 }, { 3, 2 }, { 1, 3 } }; // false

    final int numCourses = 2;
    final int[][] prerequisites = {}; // 1,0

    System.out.println(canFinishInDegree(numCourses, prerequisites));
  }

}
