/**
 * 
 */
package graph;

import java.util.Iterator;
import java.util.List;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public interface Graph <V, E>{
	
	public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws Exception;
	
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws Exception;
	
	public void replace(Vertex<V> v, V ele);
	
	public Vertex<V> insertVertex(V ele) throws Exception;
	
	public Edge<E, V> insertEdge(Vertex<V> v, Vertex<V> w, E ele) throws Exception;
	
	public void removeVertex(Vertex<V> v) throws Exception;
	
	public void removeEdge(Edge<E, V> e) throws Exception;
	
	public Iterator<Edge<E, V>> incidentEdges(Vertex<V> v) throws Exception;

	public Iterator<Edge<E, V>> inComingEdges(Vertex<V> v) throws Exception;

	public Iterator<Edge<E, V>> outComingEdges(Vertex<V> v) throws Exception;

	public Iterator<Vertex<V>> adjacentVertices(Vertex<V> v) throws Exception;
	
	public Iterator<Vertex<V>> vertices();
	
	public Iterator<Edge<E, V>> edges();

	public List<Vertex<V>> list_vertices();
	
	public List<Edge<E, V>> list_edges();

	public Iterator<Edge<E, V>> edges(String type);
	
	public void reverseDirection();

	public Vertex<V> getVertex(V ele);
	
	public int numOfVertices();
	
	public int numOfEdges();
	
}
