/**
 * 
 */
package graph;

import list.Position;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class PositionAwareType<E> {

	protected E info;
	protected Position<? extends PositionAwareType<E>> position;
	
	public PositionAwareType() {
	}
	
	public Position<? extends PositionAwareType<E>> getPosition() {
		return position;
	}
	
	public void setPosition(Position<PositionAwareType<E>> position) {
		this.position = position;
	}
	
	public E getInfo() {
		return info;
	}
	
	public void setInfo(E info) {
		this.info = info;
	}
}
