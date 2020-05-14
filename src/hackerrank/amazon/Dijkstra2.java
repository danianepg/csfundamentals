package hackerrank.amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra2 {

  public class Node {

    private String name;

    private LinkedList<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(final String name) {
      this.name = name;
    }

    public void addDestination(final Node destination, final int distance) {
      this.adjacentNodes.put(destination, distance);
    }

    public String getName() {
      return this.name;
    }

    public void setName(final String name) {
      this.name = name;
    }

    public Map<Node, Integer> getAdjacentNodes() {
      return this.adjacentNodes;
    }

    public void setAdjacentNodes(final Map<Node, Integer> adjacentNodes) {
      this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
      return this.distance;
    }

    public void setDistance(final Integer distance) {
      this.distance = distance;
    }

    public List<Node> getShortestPath() {
      return this.shortestPath;
    }

    public void setShortestPath(final LinkedList<Node> shortestPath) {
      this.shortestPath = shortestPath;
    }

  }

  public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(final Node nodeA) {
      this.nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
      return this.nodes;
    }

    public void setNodes(final Set<Node> nodes) {
      this.nodes = nodes;
    }
  }

  public static Graph calculateShortestPathFromSource(final Graph graph, final Node source) {

    source.setDistance(0);

    final Set<Node> settledNodes = new HashSet<>();
    final Set<Node> unsettledNodes = new HashSet<>();
    unsettledNodes.add(source);

    while (unsettledNodes.size() != 0) {
      final Node currentNode = getLowestDistanceNode(unsettledNodes);
      unsettledNodes.remove(currentNode);
      for (final Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
        final Node adjacentNode = adjacencyPair.getKey();
        final Integer edgeWeigh = adjacencyPair.getValue();

        if (!settledNodes.contains(adjacentNode)) {
          CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
          unsettledNodes.add(adjacentNode);
        }
      }
      settledNodes.add(currentNode);
    }
    return graph;
  }

  private static void CalculateMinimumDistance(final Node evaluationNode, final Integer edgeWeigh,
      final Node sourceNode) {
    final Integer sourceDistance = sourceNode.getDistance();
    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
      evaluationNode.setDistance(sourceDistance + edgeWeigh);
      final LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
      shortestPath.add(sourceNode);
      evaluationNode.setShortestPath(shortestPath);
    }
  }

  private static Node getLowestDistanceNode(final Set<Node> unsettledNodes) {
    Node lowestDistanceNode = null;
    int lowestDistance = Integer.MAX_VALUE;
    for (final Node node : unsettledNodes) {
      final int nodeDistance = node.getDistance();
      if (nodeDistance < lowestDistance) {
        lowestDistance = nodeDistance;
        lowestDistanceNode = node;
      }
    }
    return lowestDistanceNode;
  }

}
