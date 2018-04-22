package barclays;

import java.util.HashMap;

/**
 * @author Abhimanyu
 * This class is a singleton class for mapping the string nodes/ flight number to numerical values to ease the calculations.
 * This is basically a mediator between the input Main class and the shortest distance class. 
 */
public class BaggageRoute {

	private static BaggageRoute instance = new BaggageRoute();

	public static BaggageRoute getInstance() {
		if (instance == null) {
			instance = new BaggageRoute();
		}
		return instance;
	}

	private BaggageRoute() {

	}

	private HashMap<String, Integer> nodeMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> reverseNodeMap = new HashMap<Integer, String>();
	private Integer nodeValue = 0;
	private HashMap<String, String> flightToGate = new HashMap<String, String>();
	private DijkstrasImpl dijkstrasImpl = new DijkstrasImpl();

	{
		flightToGate.put("ARRIVAL", "BaggageClaim");
	}


	/**
	 * @param key
	 * 
	 *            Maps the input nodes to integer values for constant time element
	 *            access in graph
	 */
	private void mapNode(String key) {
		if (null == nodeMap.get(key)) {
			nodeMap.put(key, nodeValue);
			reverseNodeMap.put(nodeValue, key);
			nodeValue++;
		}
	}

	/**
	 * @param nodeValue
	 * @return the actual gate/ node value, which was mapped with integers
	 */
	public String getNode(Integer nodeNumber) {
		return reverseNodeMap.get(nodeNumber);
	}

	/**
	 * @param node
	 * @return the integer to which the node is mapped
	 */
	public Integer getNodeNumber(String node) {
		if (null == nodeMap.get(node)) {
			mapNode(node);
		}
		return nodeMap.get(node);
	}

	/**
	 * @param flight
	 * @param gate
	 * 
	 *            Maps the input flight_id with the gate number
	 */
	public void mapFlightToGate(String flight, String gate) {
		flightToGate.put(flight, gate);
	}

	/**
	 * @param flightId
	 * @return the gate number associated with the flight Id
	 */
	public String getNode(String flightId) {
		return flightToGate.get(flightId);
	}

	/**
	 * @param graph
	 * @param inputArray
	 *            is the raw input of the Bags section
	 *            prints the output of calculateOptimizedRoute method for every bag entered
	 * 
	 */
	public void findAndPrintOptimizedRoute(int[][] graph, String[] inputArray) {

		for (String input : inputArray) {
			String output = calculateOptimizedRoute(graph, input);
			System.out.println(output);
		}

	}

	/**
	 * @param graph
	 * @param input
	 *            is the single line input from the bags section, where the source and destination for the baggage is mentioned
	 * @return a string representing a bag number, the shortest path and the total shortest distance for routing 
	 */
	private String calculateOptimizedRoute(int[][] graph, String input) {

		String[] args = input.split(" ");
		int source = getNodeNumber(args[1].trim());
		int destination = getNodeNumber(getNode(args[2].trim()));
		ShortestPath dijkstrasPath = dijkstrasImpl.findShortestPath(graph, source, destination);
		String output = args[0] + " " + dijkstrasPath.covertDijkstrasPathToBaggagePath(getInstance());
		return output;
	}

}
