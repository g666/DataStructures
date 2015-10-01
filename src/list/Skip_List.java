package list;


/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Skip_List<E> extends SimpleList<E> implements PositionListInterface<E, SkipListNode<E>> {

	private SkipListNode<E> head, trailer;

	public Skip_List() {
		size = 0;
		head = new SkipListNode<E>(null, null, null);
		trailer = new SkipListNode<E>(null, null, head);
		head.setNext(trailer);
	}

	private SkipListNode<E> checkPosition(Position<E> p) throws Exception {
		if (p == null)
			throw new Exception("Null position passed to NodeList");
		if (p == head)
			throw new Exception ("The head node is not a valid position");
		if (p == trailer)
			throw new Exception ("The trailer node is not a valid position");
		try {
			SkipListNode<E> temp = (SkipListNode<E>) p;
			if ((temp.getPrevious() == null) || (temp.getNext() == null))
				throw new Exception("Position does not belong to a valid NodeList");
			return temp;
		} catch (ClassCastException e) {
			throw new Exception("Position is of wrong type for this list");
		}
	}

	public SkipListNode<E> first() throws Exception {
		if (isEmpty())
			throw new Exception("List is empty");
		return head.getNext();
	}

	public SkipListNode<E> last() throws Exception {
		if (isEmpty())
			throw new Exception("List is empty");
		return trailer.getPrevious();
	}

	public SkipListNode<E> prev(SkipListNode<E> p) throws Exception {
		SkipListNode<E> v = checkPosition(p);
		SkipListNode<E> prev = v.getPrevious();
		if (prev == head)
			throw new Exception("Cannot advance past the beginning of the list");
		return prev;
	}

	public SkipListNode<E> next(SkipListNode<E> p) throws Exception {
		SkipListNode<E> v = checkPosition(p);
		SkipListNode<E> next = v.getNext();
		if (next == trailer)
			throw new Exception("Cannot advance past the end of the list");
		return next;
	}

	public SkipListNode<E> addBefore(SkipListNode<E> p, E element)  throws Exception {
		SkipListNode<E> v = checkPosition(p);
		size++;
		SkipListNode<E> newNode = new SkipListNode<E>(element, v, v.getPrevious());
		v.getPrevious().setNext(newNode);
		v.setPrevious(newNode);
		return newNode;
	}

	public SkipListNode<E> addAfter(SkipListNode<E> p, E element)  throws Exception {
		SkipListNode<E> v = checkPosition(p);
		size++;
		SkipListNode<E> newNode = new SkipListNode<E>(element, v.getNext(), v);
		v.getNext().setPrevious(newNode);
		v.setNext(newNode);
		return newNode;
	}

	public  SkipListNode<E> addFirst(E element) {
		size++;
		SkipListNode<E> newNode = new SkipListNode<E>(element, head.getNext(), head);
		head.getNext().setPrevious(newNode);
		head.setNext(newNode);
		return newNode;
	}

	public SkipListNode<E> addLast(E element) {
		size++;
		SkipListNode<E> oldLast = trailer.getPrevious();
		SkipListNode<E> newNode = new SkipListNode<E>(element, trailer, oldLast);
		oldLast.setNext(newNode);
		trailer.setPrevious(newNode);
		return newNode;
	}

	public E remove(SkipListNode<E> p) throws Exception {
		SkipListNode<E> v = checkPosition(p);
		size--;
		SkipListNode<E> vPrev = v.getPrevious();
		SkipListNode<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrevious(vPrev);
		E vElem = v.element();
		v.setNext(null);
		v.setPrevious(null);
		return vElem;
	}

	public E set(SkipListNode<E> p, E element) throws Exception {
		SkipListNode<E> v = checkPosition(p);
		E oldElt = v.element();
		v.setElement(element);
		return oldElt;
	}

	public boolean isFirst(SkipListNode<E> p)
			throws Exception {  
		SkipListNode<E> v = checkPosition(p);
		return v.getPrevious() == head;
	}

	public boolean isLast(SkipListNode<E> p) throws Exception {  
		SkipListNode<E> v = checkPosition(p);
		return v.getNext() == trailer;
	}

	public void swapElements(SkipListNode<E> a, SkipListNode<E> b) throws Exception {
		SkipListNode<E> pA = checkPosition(a);
		SkipListNode<E> pB = checkPosition(b);
		E temp = pA.element();
		pA.setElement(pB.element());
		pB.setElement(temp);
	}

	public void add(E e){
		addFirst(e);
	}

	public void delete(){
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

	public void setHead(SkipListNode<E> head) {
		this.head.getNext().setElement(head.element());
	}

	public void remove(E e){
		SkipListNode<E> p = head.getNext();
		boolean found=false;
		while(p!=null && !found){
			if(p.element().equals(e)){
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
	}

	public SkipListNode<E> getHead() {
		return head.getNext();
	}
	
	public static void main(String[] args) {
		Skip_List<Integer> s = new Skip_List<Integer>();
		s.add(4);
		s.addLast(7);
		s.addFirst(3);
		System.out.println(s);
	}

}