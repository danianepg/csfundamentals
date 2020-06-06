package leetcode.backtracking_dfs_bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 * Medium
 *
 * @author danianepg
 *
 *         There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 *         Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 *         expressed as a pair: [0,1]
 *
 *         Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
 *         take to finish all courses.
 *
 *         There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
 *         courses, return an empty array.
 *
 *         Example 1:
 *
 *         Input: 2, [[1,0]]
 *         Output: [0,1]
 *         Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *         course 0. So the correct course order is [0,1] .
 *         Example 2:
 *
 *         Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 *         Output: [0,1,2,3] or [0,2,1,3]
 *         Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *         courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *         So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 *
 *         Note:
 *         The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
 *         how a graph is represented. You may assume that there are no duplicate edges in the input prerequisites.
 *
 */
public class CourseScheduleII {

  /**
   * Same in-degree solution of CourseSchedule, but with an extra array to save the order. The elements will be added to
   * this extra array in the order they're popped from the stack. At the end of the algorithm we have to do an extra
   * check to return the order array only if the index used to control the positions is equals to the number of courses
   * desired.
   *
   * @param numCourses
   * @param prerequisites
   * @return
   */
  public static int[] findOrder2(final int numCourses, final int[][] prerequisites) {
    final int[] indegree = new int[numCourses];
    final int[] order = new int[numCourses];

    // Map to each course the nodes pointing to it. Also counts the indegree number of each node.
    for (int i = 0; i < prerequisites.length; i++) {
      indegree[prerequisites[i][0]]++;
    }

    // All nodes that have indegree 0 are added to the q
    final Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    int index = 0;
    while (!q.isEmpty()) {

      final int cur = q.poll();
      order[index] = cur;
      index++;

      for (int i = 0; i < prerequisites.length; i++) {
        if (prerequisites[i][1] == cur) {
          indegree[prerequisites[i][0]]--;
          if (indegree[prerequisites[i][0]] == 0) {
            q.add(prerequisites[i][0]);
          }
        }
      }
    }

    if (index == numCourses) {
      return order;
    }

    return new int[0];

  }

  public static int[] findOrder(final int numCourses, final int[][] prerequisites) {
    final Map<Integer, List<Integer>> adjList = new HashMap<>();
    final int[] indegree = new int[numCourses];
    final int[] topologicalOrder = new int[numCourses];

    // Create the adjacency list representation of the graph
    for (final int[] prerequisite : prerequisites) {
      final int dest = prerequisite[0];
      final int src = prerequisite[1];
      final List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
      lst.add(dest);
      adjList.put(src, lst);

      // Record in-degree of each vertex
      indegree[dest] += 1;
    }

    // Add all vertices with 0 in-degree to the queue
    final Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    int i = 0;
    // Process until the Q becomes empty
    while (!q.isEmpty()) {
      final int node = q.remove();
      topologicalOrder[i++] = node;

      // Reduce the in-degree of each neighbor by 1
      if (adjList.containsKey(node)) {
        for (final Integer neighbor : adjList.get(node)) {
          indegree[neighbor]--;

          // If in-degree of a neighbor becomes 0, add it to the Q
          if (indegree[neighbor] == 0) {
            q.add(neighbor);
          }
        }
      }
    }

    // Check to see if topological sort is possible or not.
    if (i == numCourses) {
      return topologicalOrder;
    }

    return new int[0];

  }

  public static void main(final String[] args) {

//    final int numCourses = 4;
//    final int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }; // 0,1,2,3

    final int numCourses = 2;
    final int[][] prerequisites = {}; // 1,0

    final int[] order = findOrder(numCourses, prerequisites);
    System.out.println(order);

  }

}
