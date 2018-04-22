package com.barclays.routing;

import java.util.ArrayList;

/**
 * @author Abhimanyu
 * 
 *         This class is the response class of the Dijkstra's implementation
 *         where the path represents all the nodes from source to destination,
 *         and total travel time includes the least distance from source node to
 *         the destination node.
 *
 */
public class ShortestPath {

	private ArrayList<Integer> path;

	private int totalTravelTime;

	public ArrayList<Integer> getPath() {
		return path;
	}

	public void setPath(ArrayList<Integer> path) {
		this.path = path;
	}

	public int getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(int totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	/**
	 * The method converts back the numerical node values to the meaningful
	 * terminal/gate number which where initially converted to ease the calculations
	 * 
	 * @param baggageRoute
	 * @return a string representing the shortest path and the total shortest
	 *         distance for routing
	 */
	public String covertDijkstrasPathToBaggagePath(BaggageRoute baggageRoute) {
		StringBuilder sb = new StringBuilder("");
		for (int nodeNumber : path) {
			sb.append(baggageRoute.getNode(nodeNumber));
			sb.append(" ");
		}
		sb.append(": " + totalTravelTime);
		return sb.toString();

	}

}
