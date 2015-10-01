/**
 * 
 */
package list;



/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Pointer<E> implements Position<E>{

	protected Pointer<E> next, previous;
	protected E element;

	public Pointer(E element, Pointer<E> next, Pointer<E> previous) {
		this.element = element;
		this.next = next;
		this.previous = previous;
	}

	public Pointer(E element, Pointer<E> previous) {
		super();
		this.element = element;
		this.previous = previous;
	}

	public Pointer(E element) {
		this.element = element;
	}

	public Pointer() {
	}

	public void setElement(E element) {
		this.element = element;
	}
	
	public E element() {
		return element;
	}

	public Pointer<E> getNext() {
		return next;
	}

	public Pointer<E> getPrevious() {
		return previous;
	}

	public void setNext(Pointer<E> next) {
		this.next = next;
	}

	public void setPrevious(Pointer<E> previous) {
		this.previous = previous;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(element);
		Pointer<E> n = next;
		while(n!=null){
			s.append(" --> "+n.element());
			n=n.getNext();
		}
		return s.toString();
	}
	
	public int size(){
		int s=1;
		Pointer<E> n = next;
		while(n!=null){
			s++;
			n=n.getNext();
		}
		return s;
	}
	
	public Pointer<E> getNextElementAt(int index) throws IndexOutOfBoundsException{
		Pointer<E> n = this;
		while(index>1 && n!=null)
		{
			n = n.getNext();
			index--;
		}
		return n;
	}
	
	public Pointer<E> copyNextElementAt(int index) throws IndexOutOfBoundsException{
		Pointer<E> n = this;
		while(index>0)
		{
			n = n.getNext();
			index--;
		}
		Pointer<E> nn = new Pointer<E>(n.element());
		return nn;
	}
	
}
