package com.barclays.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.barclays.routing.BaggageRoute;

/**
 * @author Abhimanyu
 * 
 *         This is entry point of the program this class takes the input from
 *         the console and converts it into a 2D matrix representing a graph
 *         with each edge representing distance from one node to other.
 * 
 *
 */
public class Main {

	/**
	 * This method is the entry point of the program.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		BaggageRoute route = BaggageRoute.getInstance();
		Map<Integer, Map<Integer, Integer>> graphMap = new HashMap<Integer, Map<Integer, Integer>>();
		String[] findShortestPathFor = null;
		try {
			findShortestPathFor = acceptInput(graphMap);
		} catch (IOException e) {
			System.err.println("An Error occured while taking input, please re-run the program");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(
					"Please make sure the Section input is in the order; Conveyor System, Departures and Bags");
		}
		int[][] graph = convertToGraphArray(graphMap);
		route.findAndPrintOptimizedRoute(graph, findShortestPathFor);

	}

	private static int[][] convertToGraphArray(Map<Integer, Map<Integer, Integer>> graphMap) {

		int[][] graph = new int[graphMap.size()][graphMap.size()];

		for (int i = 0; i < graph.length; i++) {
			Map<Integer, Integer> map = graphMap.get(i);
			for (int j = 0; j < graph.length; j++) {
				// to make sure all the non existent edges are marked -1
				if (i != j && graph[i][j] == 0)
					graph[i][j] = -1;
				if (null != map && null != map.get(j)) {
					graph[i][j] = map.get(j);

				}
			}
		}

		return graph;
	}

	/**
	 * 
	 * The method takes input from the user with the assumption that sections will
	 * be in order -> Conveyor System, Departures and Bags.
	 * 
	 * The System will stop accepting input once it encounters a blank line. to make
	 * it simple press enter button twice.
	 * 
	 * @param graphMap
	 * @return
	 * @throws IOException
	 */
	public static String[] acceptInput(Map<Integer, Map<Integer, Integer>> graphMap) throws IOException {
		BaggageRoute route = BaggageRoute.getInstance();
		System.out.println("Please enter the input followed by a blank line and press enter (new line char)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		line = br.readLine();
		int counter = 0;
		ArrayList<String> input = new ArrayList<String>();

		while (!line.isEmpty()) {
			if (line.contains("Section")) {
				counter++;
			} else if (counter == 1) {
				String[] args = line.split(" ");
				// following method was called twice to make the graph bi-directional
				addNodeAndEdgeToMap(graphMap, args[0], args[1], args[2]);
				addNodeAndEdgeToMap(graphMap, args[1], args[0], args[2]);
			} else if (counter == 2) {
				String[] args = line.split(" ");
				route.mapFlightToGate(args[0].trim(), args[1].trim());
			} else {
				input.add(line);
			}
			line = br.readLine();
		}
		return input.toArray(new String[input.size()]);

	}

	private static void addNodeAndEdgeToMap(Map<Integer, Map<Integer, Integer>> graphMap, String node1, String node2,
			String distance) {
		BaggageRoute route = BaggageRoute.getInstance();

		Map<Integer, Integer> edgeMap = graphMap.get(route.getNodeNumber(node1.trim()));
		if (edgeMap == null) {
			edgeMap = new HashMap<Integer, Integer>();
			graphMap.put(route.getNodeNumber(node1.trim()), edgeMap);
			edgeMap = graphMap.get(route.getNodeNumber(node1.trim()));
		}
		if (null == edgeMap.get(route.getNodeNumber(node2.trim()))) {
			edgeMap.put(route.getNodeNumber(node2.trim()), getInt(distance));
		}

	}

	private static Integer getInt(String string) {
		return Integer.valueOf(string.trim());
	}
}
