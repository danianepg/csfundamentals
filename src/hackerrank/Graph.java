package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem
 * 
 * Consider an undirected graph consisting of nodes where each node is labeled
 * from to and the edge between any two nodes is always of length . We define
 * node to be the starting position for a BFS. Given a graph, determine the
 * distances from the start node to each of its descendants and return the list
 * in node number order, ascending. If a node is disconnected, it's distance
 * should be -1.
 * 
 * 
 * 
 * Function Description
 * 
 * Define a Graph class with the required methods to return a list of distances.
 * 
 * 
 * @author Daniane P. Gomes
 *
 */
public class Graph {

	private int edgeDistance = 6;
	// On each position save the other nodes that the current node is touching
	private List<List<Integer>> adjLst;
	private int size;

	public Graph(int size) {

		adjLst = new ArrayList<>();
		this.size = size;

		// Initialize the adjacency list.
		while (size-- > 0) {
			adjLst.add(new ArrayList<Integer>());
		}
	}

	public void addEdge(int first, int second) {
		adjLst.get(first).add(second);
		adjLst.get(second).add(first);
	}

	public int[] shortestReach(int startingPoint) {

		int[] distances = new int[size];
		Arrays.fill(distances, -1);
		Queue<Integer> queue = new LinkedList<>();

		queue.add(startingPoint);
		distances[startingPoint] = 0;
		HashSet<Integer> visited = new HashSet<>();
		visited.add(startingPoint);

		while (!queue.isEmpty()) {
			Integer current = queue.poll();

			for (int node : adjLst.get(current)) {

				if (!visited.contains(node)) {
					queue.offer(node);
					visited.add(node);
					distances[node] = distances[current] + edgeDistance;
				}
			}
		}

		return distances;

	}

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File(
				"E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\bfs\\ctci-bfs-shortest-reach-testcases\\input\\input08.txt");
		Scanner scanner = new Scanner(file);

		int queries = scanner.nextInt();

		for (int t = 0; t < queries; t++) {

			// Create a graph of size n where each edge weight is 6:
			Graph bfs = new Graph(scanner.nextInt());
			int m = scanner.nextInt();

			// read and set edges
			for (int i = 0; i < m; i++) {
				int u = scanner.nextInt() - 1;
				int v = scanner.nextInt() - 1;

				// add each edge to the graph
				bfs.addEdge(u, v);
			}

			// Find shortest reach from node s
			bfs.shortestReach(scanner.nextInt() - 1);
		}

		scanner.close();
	}

}
