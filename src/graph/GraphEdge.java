package graph;

public class GraphEdge {
	
	private GraphNode startNode;
	private GraphNode endNode;
	
	public GraphEdge(GraphNode startNode, GraphNode endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	/**
	 * Given one of the nodes involved in this edge, get the other one
	 * @param node The node on one side of this edge
	 * @return the other node involved in this edge
	 */
	public GraphNode getOtherNode(GraphNode node) {
	
		if (node.equals(startNode)) 
			return endNode;
		else if (node.equals(endNode))
			return startNode;
		throw new IllegalArgumentException("Looking for " +
			"a point that is not in the edge");
	}
	
	public GraphNode getEndNode() {
		return endNode;
	}
	
	public GraphNode getStartNode() {
		return startNode;
	}
	
	


}
