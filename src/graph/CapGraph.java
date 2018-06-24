/**
 * 
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	
	

	private HashMap<Integer, GraphNode> intToNode;
	
	public CapGraph() {
		intToNode = new HashMap<Integer, GraphNode>();
	}
	
	public HashMap<Integer, GraphNode> getIntToNode() {
		return intToNode;
	}
	
	
	

	@Override
	public String toString() {
		String string = new String();
		for(int i : intToNode.keySet()) {
			GraphNode node = intToNode.get(i);
			string+=node.toString()+"\n";
		}
		return string;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if(intToNode.containsKey(num)) {
			throw new IllegalArgumentException("Node already present");
		}
		GraphNode newNode = new GraphNode(num);
		intToNode.put(num, newNode);

	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if(!intToNode.containsKey(from) || !intToNode.containsKey(to)) {
			throw new IllegalArgumentException("Node not present in Graph");
		}
		GraphNode startNode = intToNode.get(from);
		GraphNode endNode = intToNode.get(to);
		if(startNode.getNeighbours().contains(endNode)) {
			throw new IllegalArgumentException("edge already present");
		}
		startNode.addNeighbours(endNode);
		GraphEdge newEdge = new GraphEdge(startNode, endNode);
		startNode.addEdges(newEdge);

	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
