/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import list.Pointer;
import list.Position;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class GraphAdjacencyListStructure<V, E> implements Graph<V, E>{

	protected GraphPositionList<V> vertices;
	protected GraphPositionList<E> edges;

	public GraphAdjacencyListStructure() {
		vertices = new GraphPositionList<V>();
		edges = new GraphPositionList<E>();
	}
	
	@Override
	public int numOfEdges() {
		return edges.size();
	}
	
	@Override
	public int numOfVertices() {
		return vertices.size();
	}

	private AugmentedEdge<E, V> castEdge(Edge<E, V> e) throws Exception {
		return (AugmentedEdge<E, V>) e;
	}

	@SuppressWarnings("unchecked")
	private AugmentedVertex<V, E> castVertex(Vertex<V> v) throws Exception {
		return (AugmentedVertex<V, E>) v;
	}

	@SuppressWarnings("unchecked")
	public Vertex<V> insertVertex(V ele) throws Exception {
		Position<PositionAwareType<V>> pos = 
				vertices.addPos(ele, (Class<? extends PositionAwareType<V>>) AugmentedVertex.class);
		return (AugmentedVertex<V, E>) pos.element();
	}

	public Edge<E, V> insertEdge(Vertex<V> vv, Vertex<V> ww, E ele) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		AugmentedVertex<V, E> w = castVertex(ww);
		@SuppressWarnings("unchecked")
		Position<PositionAwareType<E>> pos = 
		edges.addPos(ele, (Class<? extends PositionAwareType<E>>) AugmentedEdge.class);
		@SuppressWarnings("unchecked")
		AugmentedEdge<E, V> eg = (AugmentedEdge<E, V>) pos.element();
		eg.setSource(v);
		eg.setDestination(w);
		v.getAdjacentVertices().addPos(eg);
		eg.setSourcePosition(v.getAdjacentVertices().getHeader().getPosition());
		w.getAdjacentVertices().addPos(eg);
		eg.setDestinationPosition(w.getAdjacentVertices().getHeader().getPosition());
		return eg;
	}

	@SuppressWarnings("unchecked")
	public void removeEdge(Edge<E, V> ee) throws Exception {
		AugmentedEdge<E, V> e = castEdge(ee);
		e.getSource().getAdjacentVertices().removePos((Position<PositionAwareType<E>>) e.getSourcePosition());
		e.getDestination().getAdjacentVertices().removePos((Position<PositionAwareType<E>>) e.getDestinationPosition());
		edges.removePos((Position<PositionAwareType<E>>) e.getPosition());
	}

	@SuppressWarnings("unchecked")
	public void removeVertex(Vertex<V> vv) throws Exception {
		Iterator<Edge<E, V>> it = incidentEdges(vv);
		while(it.hasNext())
			removeEdge(it.next());
		AugmentedVertex<V, E> v = castVertex(vv);
		v.removeAll();
		vertices.removePos((Position<PositionAwareType<V>>) v.getPosition());
	}

	@SuppressWarnings("unchecked")
	private AugmentedVertex<V, E> searchVertex(V v){
		AugmentedVertex<V, E> head = (AugmentedVertex<V, E>) vertices.getHeader();
		AugmentedVertex<V, E> found = null;
		while(head!=null){
			if(head.getInfo().equals(v))
			{
				found = head;
				head = null;
			}
			try {
				head = (AugmentedVertex<V, E>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}

		return found;
	}

	@SuppressWarnings("unchecked")
	public Iterator<Edge<E, V>> incidentEdges(Vertex<V> vv) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().getHeader();
		while(head_edg!=null){
			if(head_edg.getDestination().equals(v)
					|| head_edg.getSource().equals(v))
			{
				coll.add(head_edg);
			}
			try {
				head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public Iterator<Vertex<V>> adjacentVertices(Vertex<V> vv) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().getHeader();
		while(head_edg!=null){
			if(head_edg.getSource().equals(v))
				coll.add(head_edg.getDestination());
			try {
				head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}

		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public Iterator<Vertex<V>> vertices() {
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		AugmentedVertex<V, E> head = (AugmentedVertex<V, E>) vertices.getHeader();
		while(head!=null){
			coll.add(head);
			try {
				head = (AugmentedVertex<V, E>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public Iterator<Edge<E, V>> edges() {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) edges.getHeader();
		while(head_edg!=null){
			coll.add(head_edg);
			try {
				head_edg = (AugmentedEdge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public Iterator<Edge<E, V>> edges(String type) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) edges.getHeader();
		while(head_edg!=null){
			if(head_edg.getType().equals(type))
				coll.add(head_edg);
			try {
				head_edg = (AugmentedEdge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public Vertex<V> opposite(Vertex<V> vv, Edge<E, V> e) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		AugmentedVertex<V, E> vt;
		if(e.getSource().equals(v))
			vt = (AugmentedVertex<V, E>) e.getDestination();
		else if(e.getDestination().equals(v))
			vt = (AugmentedVertex<V, E>) e.getSource();
		else
			vt = null;
		return vt;
	}

	@SuppressWarnings("unchecked")
	public boolean areAdjacent(Vertex<V> vv, Vertex<V> ww) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		AugmentedVertex<V, E> w = castVertex(ww);
		boolean b = false;
		AugmentedEdge<E, V> eg = (AugmentedEdge<E, V>) v.getAdjacentVertices().getHeader();
		while(eg!=null && !b){
			if(eg.getDestination().equals(w) || eg.getSource().equals(w))
				b = true;
			try {
				eg = (AugmentedEdge<E, V>) v.getAdjacentVertices().next((Pointer<E>) eg.getPosition()).element();
			} catch (Exception exc) {
				eg=null;
			}
		}
		return b;
	}

	public void replace(Vertex<V> v, V ele) {
		v.setInfo(ele);
	}

	@SuppressWarnings("unchecked")
	public String printEdges(){
		String s = "";
		AugmentedEdge<E, V> eg = (AugmentedEdge<E, V>) edges.getHeader();
		while(eg!=null){
			s+= eg.toString()+" ";
			try {
				eg = (AugmentedEdge<E, V>) edges.next((Pointer<E>) eg.getPosition()).element();
			} catch (Exception exc) {
				eg=null;
			}
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public String printEdges(String type){
		String s = "";
		AugmentedEdge<E, V> eg = (AugmentedEdge<E, V>) edges.getHeader();
		while(eg!=null){
			if(eg.getType().equals(type))
				s+= eg.getSource().getInfo()+" -> "+eg.getDestination().getInfo()+"\n";
			try {
				eg = (AugmentedEdge<E, V>) edges.next((Pointer<E>) eg.getPosition()).element();
			} catch (Exception exc) {
				eg=null;
			}
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public String printVertices(){
		String s = "[ ";
		AugmentedVertex<V, E> head = (AugmentedVertex<V, E>) vertices.getHeader();
		while(head!=null){
			s+=head.toString()+" -- ";
			try {
				head = (AugmentedVertex<V, E>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}
		return s.substring(0, s.length()-4)+" ]";
	}

	public AugmentedVertex<V, E> getVertex(V ele) {
		AugmentedVertex<V, E> v = searchVertex(ele);
		return v;
	}

	@SuppressWarnings("unchecked")
	public Iterator<Edge<E, V>> inComingEdges(Vertex<V> vv) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().getHeader();
		while(head_edg!=null){
			if(head_edg.getDestination().equals(v))
			{
				coll.add(head_edg);
			}
			try {
				head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public Iterator<Edge<E, V>> outComingEdges(Vertex<V> vv) throws Exception {
		AugmentedVertex<V, E> v = castVertex(vv);
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().getHeader();
		while(head_edg!=null){
			if(head_edg.getSource().equals(v))
			{
				coll.add(head_edg);
			}
			try {
				head_edg = (AugmentedEdge<E, V>) v.getAdjacentVertices().next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	public void reverseDirection() {
		AugmentedEdge<E, V> h = (AugmentedEdge<E, V>) edges.getHeader();
		while(h!=null){
			Vertex<V> temp = h.getSource();
			h.setSource(h.getDestination());
			h.setDestination(temp);
			Position<? extends PositionAwareType<E>> temp_pos = h.getSourcePosition();
			h.setSourcePosition(h.getDestinationPosition());
			h.setDestinationPosition(temp_pos);
			try {
				h = (AugmentedEdge<E, V>) edges.next((Pointer<E>) h.getPosition()).element();
			} catch (Exception exc) {
				h=null;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
//		GraphAdjacencyListStructure<String, String> g = 
//				new GraphAdjacencyListStructure<String, String>();
//		AugmentedVertex<String, String> v1 = (AugmentedVertex<String, String>) g.insertVertex("a");
//		AugmentedVertex<String, String> v2 = (AugmentedVertex<String, String>) g.insertVertex("b");
//		AugmentedVertex<String, String> v3 = (AugmentedVertex<String, String>) g.insertVertex("c");
//		g.insertEdge(v1, v2, "e1");
//		g.insertEdge(v1, v3, "e2");
//		g.reverseDirection();
//		System.out.println(g.printVertices());
//		System.out.println();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vertex<V>> list_vertices() {
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		AugmentedVertex<V, E> head = (AugmentedVertex<V, E>) vertices.getHeader();
		while(head!=null){
			coll.add(head);
			try {
				head = (AugmentedVertex<V, E>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}
		return coll;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Edge<E, V>> list_edges() {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		AugmentedEdge<E, V> head_edg = (AugmentedEdge<E, V>) edges.getHeader();
		while(head_edg!=null){
			coll.add(head_edg);
			try {
				head_edg = (AugmentedEdge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll;
	}

}
