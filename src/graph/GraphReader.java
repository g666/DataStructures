/**
 * 
 */
package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class GraphReader {

	public static void read(
			File file, Graph<String, String> graph,
			boolean isDirected, boolean isWeighted) throws Exception{
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = buf.readLine();
		while(line!=null){
			if(line.equals("Vertices")){
				String ver = buf.readLine();
				while(!ver.equals("Edges")){
					graph.insertVertex(ver);
					ver=buf.readLine();
				}
				line = ver;
			}else if(line.equals("Edges")){
				String ver = buf.readLine();
				while(ver!=null){
					StringTokenizer tok = new StringTokenizer(ver, ";");
					String v = tok.nextToken();
					String w = tok.nextToken();
					int weight = 0;
					try {
						if(isWeighted)
							weight = Integer.parseInt(tok.nextToken());
					} catch (Exception e) {
						weight = 0;
					}
					graph.insertEdge(
							graph.getVertex(v), 
							graph.getVertex(w), "edge").setWeight(weight);
					if(!isDirected)
						graph.insertEdge(
								graph.getVertex(w), 
								graph.getVertex(v), "edge").setWeight(weight);
					ver=buf.readLine();
				}
				line = null;
			}
		}
		buf.close();
	}
	
}
