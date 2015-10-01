/**
 * 
 */
package graph;

import list.Position;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class AugmentedEdge<E, V> extends Edge<E, V> {

	private Position<? extends PositionAwareType<E>> sourcePosition, destinationPosition;
	
	public AugmentedEdge() {
		super();
	}
	
	public Position<? extends PositionAwareType<E>> getDestinationPosition() {
		return destinationPosition;
	}
	
	public Position<? extends PositionAwareType<E>> getSourcePosition() {
		return sourcePosition;
	}
	
	public void setDestinationPosition(
			Position<? extends PositionAwareType<E>> destinationPosition) {
		this.destinationPosition = destinationPosition;
	}
	
	public void setSourcePosition(
			Position<? extends PositionAwareType<E>> sourcePosition) {
		this.sourcePosition = sourcePosition;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AugmentedVertex<V, E> getDestination() {
		return (AugmentedVertex<V, E>) super.getDestination();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AugmentedVertex<V, E> getSource() {
		return (AugmentedVertex<V, E>) super.getSource();
	}

}
