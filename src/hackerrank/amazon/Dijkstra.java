package hackerrank.amazon;

/**
 * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 *
 * Complexity O(V^2)
 *
 * @author z003xfbr
 *
 */

public class Dijkstra {

  private int V = 9;

  public int minDistance(final int[] dist, final Boolean[] sptSet) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;

    for (int v = 0; v < this.V; v++) {
      if (sptSet[v] == false && dist[v] <= min) {
        min = dist[v];
        minIndex = v;
      }
    }

    return minIndex;

  }

  void printSolution(final int dist[]) {
    System.out.println("Vertex \t\t Distance from Source");
    for (int i = 0; i < this.V; i++) {
      System.out.println(i + " \t\t " + dist[i]);
    }
  }

  public void dijkstra(final int[][] graph, final int src) {

    // each position has the shortest distance from src to i
    final int[] dist = new int[this.V];

    final Boolean[] sptSet = new Boolean[this.V];

    // initialize distances
    for (int i = 0; i < this.V; i++) {
      dist[i] = Integer.MAX_VALUE;
      sptSet[i] = false;
    }

    // distance to itself is 0
    dist[src] = 0;

    for (int count = 0; count < this.V - 1; count++) {

      // pick thee minimum distance vertex from the set of vertices not processed.
      final int u = this.minDistance(dist, sptSet);

      // mark the vertex as processed
      sptSet[u] = true;

      // udate value of adjacent vertices
      for (int v = 0; v < this.V; v++) {
        // update if its not in sptSet, theres a paht from u to v and total weight of path from src to v is smaller than
        // current
        if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
          dist[v] = dist[u] + graph[u][v];
        }
      }

    }

  }

  public static void main(final String[] args) {
    /* Let us create the example graph discussed above */
    final int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
        { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
        { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
        { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
    final Dijkstra t = new Dijkstra();
    t.dijkstra(graph, 0);
  }

}
