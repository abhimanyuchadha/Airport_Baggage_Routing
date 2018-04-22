package com.barclays.util;

import java.util.Map;

/**
 * 
 * @author Abhimanyu
 *
 *         This is a utilit class where all the re-usable methods can be kept.
 */
public class ApplicationUtils {

	private ApplicationUtils() {
		throw new RuntimeException();
	}
	
	/**
	 * The method converts map to the adjacency matrix for a graph.
	 * @param graphMap
	 * @return two dimension array representing a graph
	 */
	public static int[][] convertToGraphArray(Map<Integer, Map<Integer, Integer>> graphMap) {

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
	 * @param input
	 * @return Integer value of the string
	 */
	public static Integer getInt(String input) {
		if(null!=input && !input.isEmpty())
			return Integer.valueOf(input.trim());
		else 
			return -1;
	}
}
