package graph;

import java.util.HashSet;
import java.util.Set;

public class GraphNode {
	
	private int userNumber;
	private Set<GraphNode> neighbours;
	private Set<GraphEdge> edges;
	
	public GraphNode(int userNumber) {
		this.userNumber = userNumber;
		neighbours = new HashSet<GraphNode>();
		edges = new HashSet<GraphEdge>();
	}

	public Set<GraphNode> getNeighbours() {
		return neighbours;
	}
	
	public void  setNeighbours(Set<GraphNode> ogSet) {
		 this.neighbours.addAll(ogSet);
	}

	public void addNeighbours(GraphNode newNeighbour) {
		neighbours.add(newNeighbour);
	}

	public Set<GraphEdge> getEdges() {
		return edges;
	}
	
	public void setEdges(Set<GraphEdge> ogEdges) {
		this.edges.addAll(ogEdges);
	}

	public void addEdges(GraphEdge newEdge) {
		edges.add(newEdge);
	}

	public int getUserNumber() {
		return userNumber;
	}
	
	public boolean hasEdge(GraphNode thisNode, GraphNode otherNode) {
		if(thisNode.getNeighbours().contains(otherNode)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	
	@Override
	public String toString() {
		String string = new String();
		string+=this.userNumber+" : ";
		for(GraphNode node : neighbours) {
			string+=node.userNumber+" -> ";
		}
		return string;
	}

	/** Returns whether two nodes are equal.
	 * Nodes are considered equal if their user Numbers are the same, 
	 * even if their edge list is different.
	 * @param o the node to compare to
	 * @return true if these nodes are at the same location, false otherwise
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof GraphNode) || (o == null)) {
			return false;
		}
		GraphNode node = (GraphNode)o;
		return node.userNumber == this.userNumber;
	}
	
	

}
