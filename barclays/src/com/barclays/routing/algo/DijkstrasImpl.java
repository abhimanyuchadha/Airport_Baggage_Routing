package com.barclays.routing.algo;

import java.util.ArrayList;
import java.util.HashSet;

import com.barclays.routing.BaggageRoute;
import com.barclays.routing.ShortestPath;

/**
 * @author Abhimanyu
 * 
 *         This class is an implementation of Dijkstra's algorithm to find the
 *         path between two nodes with least distance. Although it is
 *         implemented to find out the least possible distance for a baggage at
 *         airport to be routed, but the code is generic enough to be re-used
 *         for any other application.
 * 
 *
 */
public class DijkstrasImpl {

	/**
	 * @param graph
	 * @param source
	 * @param destination
	 * @return the shortest path between source and destination based on the
	 *         distance between them
	 */
	public static ShortestPath findShortestPath(int[][] graph, int source, int destination) {
		ShortestPath shortestPath = null;
		HashSet<Integer> setOfNodes = new HashSet<Integer>();
		Integer[] parentNodeArray = new Integer[graph.length];
		int[] shortestDistanceFromSource = new int[graph.length];

		for (int i = 0; i < graph.length; i++) {
			shortestDistanceFromSource[i] = Integer.MAX_VALUE;
			setOfNodes.add(i);
		}
		setOfNodes.remove(source);
		parentNodeArray[source] = null;
		shortestDistanceFromSource[source] = 0;
		int currentNode = source;
		// it is 0 at, from source to source i.e. the distance between the same node
		int parentNodeDistance = shortestDistanceFromSource[source];
		while (setOfNodes.size() > 0) {
			for (int i = 0; i < graph[currentNode].length; i++) {
				if (setOfNodes.contains(i) && graph[currentNode][i] != -1) {
					int distance = graph[currentNode][i] + parentNodeDistance;
					if (distance < shortestDistanceFromSource[i])
						shortestDistanceFromSource[i] = distance;
					parentNodeArray[i] = currentNode;
				}
			}
			int minNode = getMinimumNode(shortestDistanceFromSource, setOfNodes);
			currentNode = minNode;
			setOfNodes.remove(currentNode);
			// update distance from parent_node to source node, find the parent of the
			// current node and then find parent node's distance
			parentNodeDistance = shortestDistanceFromSource[currentNode];
		}
		shortestPath = getShortestPathAndDistance(source, destination, parentNodeArray, shortestDistanceFromSource);
		return shortestPath;
	}

	/**
	 * The method collaborates the shortest path and total shortest distance from
	 * source to destination node
	 * 
	 * @param source
	 * @param destination
	 * @param parentNodeArray
	 * @param shortestDistanceFromSource
	 * @return an instance of ShortestPath
	 */
	private static ShortestPath getShortestPathAndDistance(int source, int destination, Integer[] parentNodeArray,
			int[] shortestDistanceFromSource) {
		ShortestPath shortestPath = new ShortestPath();
		ArrayList<Integer> path = new ArrayList<>();
		addParentToList(path, destination, parentNodeArray);
		shortestPath.setPath(path);
		shortestPath.setTotalTravelTime(shortestDistanceFromSource[destination]);
		return shortestPath;
	}

	/**
	 * The method traverses through the array to find the parent of the destination
	 * until the source node is reached.
	 * 
	 * @param path
	 * @param destination
	 * @param parentNodeArray
	 */
	private static void addParentToList(ArrayList<Integer> path, Integer destination, Integer[] parentNodeArray) {
		// TODO Auto-generated method stub
		if (destination == null) {
			return;
		}
		addParentToList(path, parentNodeArray[destination], parentNodeArray);
		path.add(destination);
	}

	/**
	 * The method finds the minimum of the nodes in the set, this is the part of the
	 * greedy approach adopted by the Dijkstra's algorithm
	 * 
	 * @param shortestDistanceFromSource
	 * @param setOfNodes
	 * @return the minimum node number in the intersection of the shortestDistance
	 *         array and setOfNodes
	 */
	private static int getMinimumNode(int[] shortestDistanceFromSource, HashSet<Integer> setOfNodes) {
		int min = Integer.MAX_VALUE;
		int node = -1;
		for (int i = 0; i < shortestDistanceFromSource.length; i++) {
			if (setOfNodes.contains(i) && shortestDistanceFromSource[i] < min) {
				min = shortestDistanceFromSource[i];
				node = i;
			}
		}
		return node;
	}
}
