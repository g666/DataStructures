/**
 * 
 */
package graph;

import list.Pointer;
import list.Position;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class AugmentedVertex<V, E> extends Vertex<V> {

	private GraphPositionList<E> adjacentVertices;
	
	public AugmentedVertex() throws Exception {
		super();
		adjacentVertices = new GraphPositionList<E>();
	}
	
	/**
	 * @return the adjacentVertices
	 */
	public GraphPositionList<E> getAdjacentVertices() {
		return adjacentVertices;
	}
	
	@SuppressWarnings("unchecked")
	public void removeAll() throws Exception{
		AugmentedEdge<E, V> ae = (AugmentedEdge<E, V>) adjacentVertices.getHeader();
		while(ae!=null){
			if(this.equals(ae.getSource()))
				adjacentVertices.removePos((Position<PositionAwareType<E>>) ae.getSourcePosition());
			if(this.equals(ae.getDestination()))
				adjacentVertices.removePos((Position<PositionAwareType<E>>) ae.getDestinationPosition());
			try {
				ae =  (AugmentedEdge<E, V>) adjacentVertices.next((Pointer<E>) ae.getPosition()).element();
			} catch (Exception e) {
				ae = null;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		String s = super.toString()+"\n";
		PositionAwareType<E> p = adjacentVertices.getHeader();
		while(p!=null){
			AugmentedEdge<E, V> ae = (AugmentedEdge<E, V>) p;
			s+="\t"+ae.getInfo()+"("+ae.getSource().getInfo()+" --> "+
					ae.getDestination().getInfo()+")\n";
			try {
				p = (PositionAwareType<E>) adjacentVertices.next((Pointer<E>) p.getPosition()).element();
			} catch (Exception e) {
				p = null;
			}
		}
		return s;
	}
	
}
