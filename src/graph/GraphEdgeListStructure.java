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
public class GraphEdgeListStructure<V, E> implements Graph<V, E>{

	protected GraphPositionList<V> vertices;
	protected GraphPositionList<E> edges;

	public GraphEdgeListStructure() {
		vertices = new GraphPositionList<V>();
		edges = new GraphPositionList<E>();
	}
	
	@Override
	public int numOfEdges() {
		return edges.getHead().size();
	}
	
	@Override
	public int numOfVertices() {
		return vertices.size();
	}

	@Override
	public Vertex<V> insertVertex(V ele) throws Exception {
		@SuppressWarnings("unchecked")
		Position<PositionAwareType<V>> pos = 
		vertices.addPos(ele, (Class<? extends PositionAwareType<V>>) Vertex.class);
		return (Vertex<V>) pos.element();
	}

	@Override
	public Edge<E, V> insertEdge(Vertex<V> v, Vertex<V> w, E ele) throws Exception {
		@SuppressWarnings("unchecked")
		Position<PositionAwareType<E>> pos = 
		edges.addPos(ele, (Class<? extends PositionAwareType<E>>) Edge.class);
		@SuppressWarnings("unchecked")
		Edge<E, V> eg = (Edge<E, V>) pos.element();
		eg.setSource(v);
		eg.setDestination(w);
		return eg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeVertex(Vertex<V> v) throws Exception {
		Iterator<Edge<E, V>> it = incidentEdges(v);
		while(it.hasNext()){
			removeEdge(it.next());
		}
		vertices.removePos((Position<PositionAwareType<V>>) v.getPosition());	
	}

	@SuppressWarnings("unchecked")
	private Vertex<V> searchVertex(V v){
		Vertex<V> head = (Vertex<V>) vertices.getHeader();
		Vertex<V> found = null;
		while(head!=null){
			if(head.getInfo().equals(v))
			{
				found = head;
				head = null;
			}
			try {
				head = (Vertex<V>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}

		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeEdge(Edge<E, V> e) throws Exception {
		edges.removePos((Position<PositionAwareType<E>>) e.getPosition());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Edge<E, V>> incidentEdges(Vertex<V> v) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			if(head_edg.getDestination().equals(v)
					|| head_edg.getSource().equals(v))
			{
				coll.add(head_edg);
			}
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Vertex<V>> adjacentVertices(Vertex<V> v) {
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			if(head_edg.getSource().equals(v))
				coll.add((Vertex<V>) head_edg.getDestination());
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}

		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Vertex<V>> vertices() {
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		Vertex<V> head = (Vertex<V>) vertices.getHeader();
		while(head!=null){
			coll.add(head);
			try {
				head = (Vertex<V>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Edge<E, V>> edges() {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			coll.add(head_edg);
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Edge<E, V>> edges(String type) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			if(head_edg.getType().equals(type))
				coll.add(head_edg);
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) {
		Vertex<V> vt;
		if(e.getSource().equals(v))
			vt = (Vertex<V>) e.getDestination();
		else if(e.getDestination().equals(v))
			vt = (Vertex<V>) e.getSource();
		else
			vt = null;
		return vt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		boolean b = false;
		Edge<E, V> eg = (Edge<E, V>) edges.getHeader();
		while(eg!=null && !b){
			if((eg.getDestination().equals(v) || eg.getSource().equals(v))
					&& (eg.getDestination().equals(w) || eg.getSource().equals(w)))
				b = true;
			try {
				eg = (Edge<E, V>) edges.next((Pointer<E>) eg.getPosition()).element();
			} catch (Exception exc) {
				eg=null;
			}
		}
		return b;
	}

	@Override
	public void replace(Vertex<V> v, V ele) {
		v.setInfo(ele);
	}

	@SuppressWarnings("unchecked")
	public String printEdges(){
		String s = "";
		Edge<E, V> eg = (Edge<E, V>) edges.getHeader();
		while(eg!=null){
			s+= eg.toString()+" ";
			try {
				eg = (Edge<E, V>) edges.next((Pointer<E>) eg.getPosition()).element();
			} catch (Exception exc) {
				eg=null;
			}
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public String printEdges(String type){
		String s = "";
		Edge<E, V> eg = (Edge<E, V>) edges.getHeader();
		while(eg!=null){
			if(eg.getType().equals(type))
				s+= eg.getSource().getInfo()+" -> "+eg.getDestination().getInfo()+"\n";
			try {
				eg = (Edge<E, V>) edges.next((Pointer<E>) eg.getPosition()).element();
			} catch (Exception exc) {
				eg=null;
			}
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public String printVertices(){
		String s = "[ ";
		Vertex<V> head = (Vertex<V>) vertices.getHeader();
		while(head!=null){
			s+=head.toString()+" -- ";
			try {
				head = (Vertex<V>) vertices.next((Pointer<V>) head.getPosition()).element();
			} catch (Exception e) {
				head=null;
			}
		}
		return s.substring(0, s.length()-4)+" ]";
	}

	public Vertex<V> getVertex(V ele) {
		Vertex<V> v = searchVertex(ele);
		return v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Edge<E, V>> inComingEdges(Vertex<V> v) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			if(head_edg.getDestination().equals(v))
			{
				coll.add(head_edg);
			}
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Edge<E, V>> outComingEdges(Vertex<V> v) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			if(head_edg.getSource().equals(v))
			{
				coll.add(head_edg);
			}
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reverseDirection() {
		Edge<E, V> h = (Edge<E, V>) edges.getHeader();
		while(h!=null){
			Vertex<V> temp = h.getSource();
			h.setSource(h.getDestination());
			h.setDestination(temp);
			try {
				h = (Edge<E, V>) edges.next((Pointer<E>) h.getPosition()).element();
			} catch (Exception exc) {
				h=null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vertex<V>> list_vertices() {
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		Vertex<V> head = (Vertex<V>) vertices.getHeader();
		while(head!=null){
			coll.add(head);
			try {
				head = (Vertex<V>) vertices.next((Pointer<V>) head.getPosition()).element();
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
		Edge<E, V> head_edg = (Edge<E, V>) edges.getHeader();
		while(head_edg!=null){
			coll.add(head_edg);
			try {
				head_edg = (Edge<E, V>) edges.next((Pointer<E>) head_edg.getPosition()).element();
			} catch (Exception exc) {
				head_edg=null;
			}
		}
		return coll;
	}

}
