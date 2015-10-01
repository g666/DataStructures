/**
 * 
 */
package graph;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class TestGraph {
	
	GraphEdgeListStructure<String, String> g;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		g = new GraphEdgeListStructure<String, String>();
		GraphReader.read(new File("src/graph/graph_10.txt"), g, true, true);
	}

	@Test
	public void test() {
		try {
			List<Edge<String, String>> list = GraphSearch.dijkstra(g, g.getVertex("a"));
			for(int i=0; i<list.size(); i++)
				System.out.println(list.get(i));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
