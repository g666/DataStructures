/**
 * 
 */
package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import list.Position;



/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class GraphAdjacencyMatrixStructure<V, E> extends GraphEdgeListStructure<V, E> implements Graph<V, E>{

	protected Edge<E, V>[][] adjacencyMatrix;
	private int matrixLenght;
	private List<Integer> indexNotUsed;

	@SuppressWarnings("unchecked")
	public GraphAdjacencyMatrixStructure() {
		super();
		indexNotUsed = new ArrayList<Integer>();
		matrixLenght = 0;
		adjacencyMatrix = (Edge<E, V>[][]) Array.newInstance(Edge.class, matrixLenght, matrixLenght);
	}

	@SuppressWarnings("unchecked")
	@Override
	public VertexForAdjacencyMatrix<V> insertVertex(V ele) throws Exception {
		Position<PositionAwareType<V>> pos = 
				vertices.addPos(ele, (Class<? extends PositionAwareType<V>>) VertexForAdjacencyMatrix.class);
		VertexForAdjacencyMatrix<V> v = (VertexForAdjacencyMatrix<V>) pos.element();
		if(indexNotUsed.isEmpty()){
			v.setMatrixIndex(matrixLenght);
			if(matrixLenght==0){
				adjacencyMatrix = new Edge[1][1];
				adjacencyMatrix[0][0] = null;
				matrixLenght++;
			}else{
				for(int i=0; i<matrixLenght; i++){
					adjacencyMatrix[i] = Arrays.copyOf(adjacencyMatrix[i], matrixLenght+1);
					//				adjacencyMatrix[i][2]=0;
				}
				matrixLenght++;
				adjacencyMatrix = Arrays.copyOf(adjacencyMatrix, matrixLenght);
				adjacencyMatrix[matrixLenght-1] = (Edge<E, V>[]) Array.newInstance(Edge.class, matrixLenght);
				//			for(int i=0; i<matrixLenght;i++)
				//				adjacencyMatrix[2][i]=0;
			}
		}else{
			int index = indexNotUsed.get(0);
			indexNotUsed.remove(0);
			v.setMatrixIndex(index);
		}
		return v;
	}

	@Override
	public Edge<E, V> insertEdge(Vertex<V> v, Vertex<V> w, E ele) throws Exception {
		Edge<E, V> eg = super.insertEdge(v, w, ele);
		VertexForAdjacencyMatrix<V> vv = (VertexForAdjacencyMatrix<V>) v;
		VertexForAdjacencyMatrix<V> ww = (VertexForAdjacencyMatrix<V>) w;
		adjacencyMatrix[vv.getMatrixIndex()][ww.getMatrixIndex()] = eg;
		adjacencyMatrix[ww.getMatrixIndex()][vv.getMatrixIndex()] = eg;
		return eg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeVertex(Vertex<V> v) throws Exception {
		Iterator<Edge<E, V>> it = incidentEdges(v);
		while(it.hasNext())
			removeEdge(it.next());
		indexNotUsed.add(new Integer(((VertexForAdjacencyMatrix<V>)v).getMatrixIndex()));
		vertices.removePos((Position<PositionAwareType<V>>) v.getPosition());	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeEdge(Edge<E, V> e) throws Exception {
		edges.removePos((Position<PositionAwareType<E>>) e.getPosition());
		adjacencyMatrix
		[((VertexForAdjacencyMatrix<V>)e.getSource()).getMatrixIndex()]
				[((VertexForAdjacencyMatrix<V>)e.getDestination()).getMatrixIndex()] = null;
		adjacencyMatrix
		[((VertexForAdjacencyMatrix<V>)e.getDestination()).getMatrixIndex()]
				[((VertexForAdjacencyMatrix<V>)e.getSource()).getMatrixIndex()] = null;
	}

	@Override
	public Iterator<Edge<E, V>> incidentEdges(Vertex<V> v) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		for(int i=0; i<matrixLenght; i++){
			Edge<E, V> head_edg = adjacencyMatrix[((VertexForAdjacencyMatrix<V>)v).getMatrixIndex()][i];
			if(head_edg!=null){
				if(head_edg.getDestination().equals(v)
						|| head_edg.getSource().equals(v))
				{
					coll.add(head_edg);
				}
			}
		}
		return coll.iterator();
	}

	@Override
	public Iterator<Vertex<V>> adjacentVertices(Vertex<V> v) {
		List<Vertex<V>> coll = new ArrayList<Vertex<V>>();
		for(int i=0; i<matrixLenght; i++){
			Edge<E, V> head_edg = adjacencyMatrix[((VertexForAdjacencyMatrix<V>)v).getMatrixIndex()][i];
			if(head_edg!=null){
				if(head_edg.getSource().equals(v))
				{
					coll.add(head_edg.getDestination());
				}
			}
		}
		return coll.iterator();
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		for(int i=0; i<matrixLenght; i++){
			Edge<E, V> eg = adjacencyMatrix[((VertexForAdjacencyMatrix<V>)v).getMatrixIndex()][i];
			if(eg!=null)
				if((eg.getDestination().equals(v) || eg.getSource().equals(v))
						&& (eg.getDestination().equals(w) || eg.getSource().equals(w)))
					return true;
		}
		return false;
	}

	@Override
	public Iterator<Edge<E, V>> inComingEdges(Vertex<V> v) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		for(int i=0; i<matrixLenght; i++){
			Edge<E, V> head_edg = adjacencyMatrix[((VertexForAdjacencyMatrix<V>)v).getMatrixIndex()][i];
			if(head_edg!=null){
				if(head_edg.getDestination().equals(v))
				{
					coll.add(head_edg);
				}
			}
		}
		return coll.iterator();
	}

	@Override
	public Iterator<Edge<E, V>> outComingEdges(Vertex<V> v) {
		List<Edge<E, V>> coll = new ArrayList<Edge<E, V>>();
		for(int i=0; i<matrixLenght; i++){
			Edge<E, V> head_edg = adjacencyMatrix[((VertexForAdjacencyMatrix<V>)v).getMatrixIndex()][i];
			if(head_edg!=null){
				if(head_edg.getSource().equals(v))
				{
					coll.add(head_edg);
				}
			}
		}
		return coll.iterator();
	}

}
