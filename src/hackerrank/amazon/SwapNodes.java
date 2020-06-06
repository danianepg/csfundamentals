package hackerrank.amazon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * http://hackerrank.com/challenges/swap-nodes-algo/problem
 *
 * @author danianepg
 *
 */
public class SwapNodes {

	public static class Node {

		Node left;

		Node right;

		int data;

		int depth;

		Node(final int data, final int depth) {
			this.data = data;
			this.depth = depth;
			this.left = null;
			this.right = null;

		}

		@Override
		public String toString() {
			return "Node [data=" + this.data + ", depth=" + this.depth + "]";
		}

	}

	/*
	 * Complete the swapNodes function below.
	 */
	static int[][] swapNodes(final int[][] indexes, final int[] queries) {

		System.out.println(indexes);

		final int numNodes = indexes.length;
		final int numQueries = queries.length;
		int index = 0;

		final Node root = new Node(1, 1);
		Node current = root;

		final Queue<Node> tree = new LinkedList<>();
		tree.offer(root);

		while (index < numNodes) {

			current = tree.poll();

			final int left = indexes[index][0];
			final int right = indexes[index][1];

			if (left == -1) {
				current.left = null;
			} else {
				current.left = new Node(left, current.depth + 1);
				tree.offer(current.left);
			}

			if (right == -1) {
				current.right = null;
			} else {
				current.right = new Node(right, current.depth + 1);
				tree.offer(current.right);
			}

			index++;

		}

		final List<List<Integer>> elements = new ArrayList<>();
		for (final int depth : queries) {

			// final Node rootAux = root;

			final List<Integer> element = new ArrayList<>();
			dfsInOrder(root, element, depth);
			// System.out.println("");
			elements.add(element);

		}

		final int[][] result = new int[numQueries][numNodes];

		for (int i = 0; i < elements.size(); i++) {
			for (int j = 0; j < elements.get(i).size(); j++) {
				result[i][j] = elements.get(i).get(j);
			}
		}

		return result;

	}

	private static void dfsInOrder(final Node root, final List<Integer> elem, final int depth) {

		if (root != null) {

			final Node tempLeft = root.left;
			final Node tempRight = root.right;

			// swap
			if (root.depth >= depth && root.depth % depth == 0) {
				root.left = tempRight;
				root.right = tempLeft;
			}

			dfsInOrder(root.left, elem, depth);

			elem.add(root.data);
			// System.out.print(root.data + " ");

			dfsInOrder(root.right, elem, depth);

		}

	}

	public static void main(final String[] args) throws IOException {

		final File file = new File("C:\\Users\\danianepg\\Downloads\\swap-nodes-algo-testcases\\input\\input02.txt");
		final Scanner scanner = new Scanner(file);

		final int n = Integer.parseInt(scanner.nextLine().trim());

		final int[][] indexes = new int[n][2];

		for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
			final String[] indexesRowItems = scanner.nextLine().split(" ");

			for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
				final int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
				indexes[indexesRowItr][indexesColumnItr] = indexesItem;
			}
		}

		final int queriesCount = Integer.parseInt(scanner.nextLine().trim());

		final int[] queries = new int[queriesCount];

		for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
			final int queriesItem = Integer.parseInt(scanner.nextLine().trim());
			queries[queriesItr] = queriesItem;
		}

		scanner.close();

		final int[][] result = swapNodes(indexes, queries);

		for (final int[] element : result) {
			for (final int element2 : element) {
				System.out.print(String.valueOf(element2 + " "));
			}
			System.out.println("\n");

		}

	}

}
