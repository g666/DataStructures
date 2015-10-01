/**
 * 
 */
package disjoint_set;

import list.Pointer;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class PointerSet<E> extends Pointer<E> {

	private Set<E> set;
	
	public PointerSet() {
		super();
	}

	public PointerSet(E element, Pointer<E> next, Pointer<E> previous) {
		super(element, next, previous);
	}

	public PointerSet(E element, Pointer<E> previous) {
		super(element, previous);
	}

	public PointerSet(E element) {
		super(element);
	}

	public Set<E> getSet() {
		return set;
	}
	
	public void setSet(Set<E> set) {
		this.set = set;
	}
	
}
