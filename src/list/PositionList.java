package list;



/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class PositionList<E> extends SimpleList<E> implements PositionListInterface<E, Pointer<E>>{

	protected Pointer<E> trailer;

	public PositionList() {
		size = 0;
		head = new Pointer<E>(null, null, null);
		trailer = new Pointer<E>(null, null, head);
		head.setNext(trailer);
	}

	protected Pointer<E> checkPosition(Position<E> p) throws Exception {
		if (p == null)
			throw new Exception("Null position passed to NodeList");
		if (p == head)
			throw new Exception ("The head node is not a valid position");
		if (p == trailer)
			throw new Exception ("The trailer node is not a valid position");
		try {
			Pointer<E> temp = (Pointer<E>) p;
			if ((temp.getPrevious() == null) || (temp.getNext() == null))
				throw new Exception("Position does not belong to a valid NodeList");
			return temp;
		} catch (ClassCastException e) {
			throw new Exception("Position is of wrong type for this list");
		}
	}
	
	public Pointer<E> prev(Pointer<E> p) throws Exception {
		Pointer<E> v = checkPosition(p);
		Pointer<E> prev = v.getPrevious();
		if (prev == head)
			throw new Exception("Cannot advance past the beginning of the list");
		return prev;
	}

	public Pointer<E> next(Pointer<E> p) throws Exception {
		Pointer<E> v = checkPosition(p);
		Pointer<E> next = v.getNext();
		if (next == trailer)
			throw new Exception("Cannot advance past the end of the list");
		return next;
	}

	public Pointer<E> addBefore(Pointer<E> p, E element)  throws Exception {
		Pointer<E> v = checkPosition(p);
		size++;
		Pointer<E> newNode = new Pointer<E>(element, v, v.getPrevious());
		v.getPrevious().setNext(newNode);
		v.setPrevious(newNode);
		return newNode;
	}

	public Pointer<E> addAfter(Pointer<E> p, E element)  throws Exception {
		Pointer<E> v = checkPosition(p);
		size++;
		Pointer<E> newNode = new Pointer<E>(element, v.getNext(), v);
		v.getNext().setPrevious(newNode);
		v.setNext(newNode);
		return newNode;
	}

	public  Pointer<E> addFirst(E element) throws Exception {
		size++;
		Pointer<E> newNode = new Pointer<E>(element, head.getNext(), head);
		head.getNext().setPrevious(newNode);
		head.setNext(newNode);
		return newNode;
	}

	public Pointer<E> addLast(E element) {
		size++;
		Pointer<E> oldLast = trailer.getPrevious();
		Pointer<E> newNode = new Pointer<E>(element, trailer, oldLast);
		oldLast.setNext(newNode);
		trailer.setPrevious(newNode);
		return newNode;
	}

	public E remove(Pointer<E> p) throws Exception {
		Pointer<E> v = checkPosition(p);
		size--;
		Pointer<E> vPrev = v.getPrevious();
		Pointer<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrevious(vPrev);
		E vElem = v.element();
		v.setNext(null);
		v.setPrevious(null);
		return vElem;
	}

	public E set(Pointer<E> p, E element) throws Exception {
		Pointer<E> v = checkPosition(p);
		E oldElt = v.element();
		v.setElement(element);
		return oldElt;
	}

	public boolean isFirst(Pointer<E> p)
			throws Exception {  
		Pointer<E> v = checkPosition(p);
		return v.getPrevious() == head;
	}

	public boolean isLast(Pointer<E> p) throws Exception {  
		Pointer<E> v = checkPosition(p);
		return v.getNext() == trailer;
	}

	public void swapElements(Pointer<E> a, Pointer<E> b) throws Exception {
		Pointer<E> pA = checkPosition(a);
		Pointer<E> pB = checkPosition(b);
		E temp = pA.element();
		pA.setElement(pB.element());
		pB.setElement(temp);
	}

	public void add(E e) throws Exception{
		addFirst(e);
	}

	public void delete() throws Exception{
		try {
			remove(first());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return head.getNext().toString();
	}

	public void setHead(Pointer<E> head) {
		this.head.getNext().setElement(head.element());
	}

	public void remove(E e) throws Exception{
		if(e!=null){
			Pointer<E> p = head.getNext();
			boolean found=false;
			while(p!=null && !found){
				if(p.element()!=null && p.element().equals(e)){
					found=true;
					if(p.getPrevious()==null){
						if(p.getNext()!=null){
							head.getNext().getNext().setPrevious(null);
							head.setNext(head.getNext().getNext());
						}else
							p=null;
					}else if(p.getNext()==null){
						p.getPrevious().setNext(null);
					}else{
						p.getPrevious().setNext(p.getNext());
						p.getNext().setPrevious(p.getPrevious());
					}
				}
				else
					p = p.getNext();
			}
			
			size--;
		}
	}

	public Pointer<E> getHead() {
		return head.getNext();
	}

	public Pointer<E> first() throws Exception {
		if (isEmpty())
			throw new Exception("List is empty");
		return head.getNext();
	}

	public Pointer<E> last() throws Exception {
		if (isEmpty())
			throw new Exception("List is empty");
		return trailer.getPrevious();
	}

}