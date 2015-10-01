/**
 * 
 */
package list;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class SkipListNode<E> extends Pointer<E>{

//	public static final String posInf = "posInf";
//	public static final String Inf = "negInf";
	private SkipListNode<E> up, down;
	
	public SkipListNode() {
		super();
		up = null;
		down = null;
	}

	public SkipListNode(E element, Pointer<E> next, Pointer<E> previous) {
		super(element, next, previous);
		up = null;
		down = null;
	}

	public SkipListNode(E element, Pointer<E> previous) {
		super(element, previous);
		up = null;
		down = null;
	}

	public SkipListNode(E element) {
		super(element);
		up = null;
		down = null;
	}

	@Override
	public SkipListNode<E> getNext() {
		return (SkipListNode<E>) super.getNext();
	}
	
	@Override
	public SkipListNode<E> getPrevious() {
		return (SkipListNode<E>) super.getPrevious();
	}
	
	public void setDown(SkipListNode<E> down) {
		this.down = down;
	}
	
	public void setUp(SkipListNode<E> up) {
		this.up = up;
	}
	
	public SkipListNode<E> getDown() {
		return down;
	}
	
	public SkipListNode<E> getUp() {
		return up;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		SkipListNode<E> n = this;
		while(n!=null){
			s.append(n.element()==null?"---":n.element());
			SkipListNode<E> u = n.getUp();
			while(u!=null){
				s.append("  "+(u.element()==null?"---":u.element()));
				u=u.getUp();
			}
			s.append("\n");
			n=n.getNext();
		}
		return s.toString();
	}
	
}
