/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
		if(!intToNode.containsKey(center)) {
			throw new IllegalArgumentException("Node does not exit is graph, can't fetch Egonet");
		}
		CapGraph egoNetGraph = new CapGraph();

		egoNetGraph.addVertex(center);
		
		for(GraphNode currNode : intToNode.get(center).getNeighbours()) {
			egoNetGraph.addVertex(currNode.getUserNumber());
		}
		for(GraphNode currNode : intToNode.get(center).getNeighbours()) {
			egoNetGraph.addEdge(center, currNode.getUserNumber());
		}
		for(GraphNode currNode : egoNetGraph.intToNode.get(center).getNeighbours()) {
			GraphNode parentNode = intToNode.get(currNode.getUserNumber());
			for(GraphNode subNode : parentNode.getNeighbours()) {
				if(egoNetGraph.intToNode.containsKey(subNode.getUserNumber())) {
					egoNetGraph.addEdge(currNode.getUserNumber(), subNode.getUserNumber());
				}
			}
		}
		return egoNetGraph;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		List<Graph> listToReturn = new ArrayList<Graph>();
		
		// Step 1
		System.out.println("Input graph is : ");
		System.out.println(this);
		Stack<GraphNode> verticesStack = getVerticesStack();
		Stack<GraphNode> finished = new Stack<GraphNode>();
		finished  = dfsGetFinishedStack(this, verticesStack);
		
		System.out.println("Step 1 finished Stack : "+finished);
		
		// Step 2
		CapGraph gTranspose = getGraphTranspose(this);
		System.out.println("Transpose graph is : ");
		System.out.println(gTranspose);
		
		// Step 3
		Stack<GraphNode> vertices = finished;
		System.out.println("Step 3 vertices Stack : "+vertices);
		
		listToReturn = dfs(gTranspose, vertices);
		
		System.out.println("SCC size = "+listToReturn.size());
		
		return listToReturn;
	}

	private List<Graph> dfs(CapGraph gTranspose, Stack<GraphNode> vertices) {
		// TODO Auto-generated method stub
		System.out.println("Graph starts");
		List<Graph> listOfSCC = new ArrayList<Graph>();
		Set<GraphNode> visited = new HashSet<GraphNode>();
		Stack<GraphNode> finished = new Stack<GraphNode>();
		while(!vertices.isEmpty()) {
			GraphNode v = vertices.pop();
			GraphNode getVerticeFromTransposeGraph = gTranspose.intToNode.get(v.getUserNumber());
			if(!visited.contains(getVerticeFromTransposeGraph)) {
				Graph newSCC = new CapGraph();
				Set<GraphNode> nodesOfSCC = new HashSet<GraphNode>();
				
				nodesOfSCC.add(getVerticeFromTransposeGraph);
				System.out.println("Starting to search SCC for "+getVerticeFromTransposeGraph.getUserNumber());
				dfsVisitToGetSCC(gTranspose, getVerticeFromTransposeGraph, visited, finished, nodesOfSCC);
				constructSCC(newSCC, nodesOfSCC);
				listOfSCC.add(newSCC);
				
			}
		}
		System.out.println("Graph ends");
		System.out.println();
		return listOfSCC;
		
	}

	private void constructSCC(Graph newSCC, Set<GraphNode> nodesOfSCC) {
		// TODO Auto-generated method stub
		for(GraphNode currNode : nodesOfSCC) {
			newSCC.addVertex(currNode.getUserNumber());
			System.out.println(currNode);
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		for(GraphNode currNode : nodesOfSCC) {
			for(GraphNode subNode :nodesOfSCC) {
				GraphNode ogFirstNode = this.intToNode.get(currNode.getUserNumber());
				GraphNode ogSecondNode = this.intToNode.get(subNode.getUserNumber());
				if(ogFirstNode.hasEdge(ogFirstNode, ogSecondNode)) {
					newSCC.addEdge(currNode.getUserNumber(), subNode.getUserNumber());
				}	
			}
		}	
	}

	private void dfsVisitToGetSCC(Graph gTransposeVisit, GraphNode v, Set<GraphNode> visited, Stack<GraphNode> finished, Set<GraphNode> nodesOfSCC) {
		// TODO Auto-generated method stub
		visited.add(v);
		System.out.println("Added "+v.getUserNumber()+"to visited");
		System.out.println("Searching "+v.getUserNumber()+" neighbours...");
		for(GraphNode n : v.getNeighbours()) {
			if(!visited.contains(n)) {
				System.out.println(n.getUserNumber()+" is neigbour of "+v.getUserNumber());
				nodesOfSCC.add(n);
				dfsVisitToGetSCC(gTransposeVisit, n, visited, finished, nodesOfSCC);
			}
		}
		finished.push(v);
			
	}

	private CapGraph getGraphTranspose(CapGraph graph) {
		// TODO Auto-generated method stub
		CapGraph gT = new CapGraph();
		for(int i : graph.intToNode.keySet()) {
			gT.addVertex(i);
		}
		for(int i : graph.intToNode.keySet()) {
			GraphNode curr = graph.intToNode.get(i);
			for(GraphNode itr : curr.getNeighbours()) {
				gT.addEdge(itr.getUserNumber(), curr.getUserNumber());
			}
		}
//		System.out.println("Testing transpose111111111");
//		for(int i : gT.intToNode.keySet()) {
//			System.out.println(gT.intToNode.get(i));
//		}
		return gT;
	}

	private Stack<GraphNode> getVerticesStack() {
		// TODO Auto-generated method stub
		Stack<GraphNode> vertices = new Stack<GraphNode>();
		for(int i : intToNode.keySet()) {
			vertices.push(intToNode.get(i));
		}
		return vertices;
	}

	private Stack<GraphNode> dfsGetFinishedStack(CapGraph graph, Stack<GraphNode> verticesStack) {
		// TODO Auto-generated method stub
		Set<GraphNode> visited = new HashSet<GraphNode>();
		Stack<GraphNode> finished = new Stack<GraphNode>();
		while(!verticesStack.isEmpty()) {
			GraphNode v = verticesStack.pop();
			if(!visited.contains(v)) {
				dfsVisit(graph, v, visited, finished);
			}
		}
		return finished;
	}

	private void dfsVisit(CapGraph graphVisit, GraphNode v, Set<GraphNode> visited, Stack<GraphNode> finished) {
		// TODO Auto-generated method stub
		visited.add(v);
		for(GraphNode n : v.getNeighbours()) {
			if(!visited.contains(n)) {
				dfsVisit(graphVisit, n, visited, finished);
			}
		}
		finished.push(v);
		
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		HashMap<Integer, HashSet<Integer>> exportedHashMap = new HashMap<Integer, HashSet<Integer>>();
		for(int i : intToNode.keySet()) {
			HashSet<Integer> currHashSet = new HashSet<Integer>();
			for(GraphNode currNode : intToNode.get(i).getNeighbours()) {
				currHashSet.add(currNode.getUserNumber());
			}
			exportedHashMap.put(i, currHashSet);
		}
		return exportedHashMap;
	}

}
