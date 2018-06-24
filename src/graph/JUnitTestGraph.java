package graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.GraphLoader;

public class JUnitTestGraph {
	
	private CapGraph graphTester;

	@Before
	public void setUp() throws Exception {
		graphTester = new CapGraph();
		GraphLoader.loadGraph(graphTester, "data/small_test_graph.txt");
		System.out.println(graphTester);
		
	}
	
	
	/*
	 * Test if the graph has the correct vertices and check if duplicate vertices are handled
	 */
	@Test
	public void testVertices() {
		assertEquals("Check number of Nodes", 14, graphTester.getIntToNode().size());
		
		try {
			graphTester.addVertex(14);
			fail("Didnt throw exception");
		}
		catch(IllegalArgumentException e) {
			
		}
		graphTester.addVertex(16);
		assertEquals("Check number of Nodes", 15, graphTester.getIntToNode().size());
	}
	
	/*
	 * Test if edges of the graph are added correctly
	 */
	@Test
	public void testEdges() {
		int countNumberOfEdges = 0;
		for(int i : graphTester.getIntToNode().keySet()) {
			GraphNode node = graphTester.getIntToNode().get(i);
			countNumberOfEdges+=node.getNeighbours().size();
		}
		assertEquals("Check number of Nodes", 34, countNumberOfEdges);
		try {
			graphTester.addEdge(23, 1);
			fail("Didnt throw Node not present exception");
			graphTester.addEdge(1, 21);
			fail("Didnt throw Node not present exception");
			graphTester.addEdge(1, 3);
			fail("Didnt throw edge already present exception");

		}
		catch(IllegalArgumentException e) {
			
		}
		graphTester.addEdge(1, 7);
		int temp  = graphTester.getIntToNode().get(1).getNeighbours().size();
		assertEquals("Check if 1 has 3 Nodes which was first 2", 3, temp);
	}

}
