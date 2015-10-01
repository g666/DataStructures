/**
 * 
 */
package list;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class AbstractList<E> implements DynamicList<E> {

	protected int size;
	protected Pointer<E> head;

	public AbstractList() {
		size = 0;
		head = null;
	}

	public Pointer<E> getHead() {
		return head;
	}

	public void setHead(Pointer<E> head) {
		this.head = head;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	public void remove(E e) throws Exception{
		Pointer<E> p = head;
		boolean found=false;
		while(p!=null && !found){
			if(p.element().equals(e)){
				found=true;
				if(p.getPrevious()==null){
					if(p.getNext()!=null){
						head.getNext().setPrevious(null);
						head=head.getNext();
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

	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() throws CloneNotSupportedException {
		AbstractList<E> s = null;
		try {
			s = this.getClass().newInstance();
			if(head!=null){
				Pointer<E> p = new Pointer<E>(head.element());
				p.setPrevious(null);
				s.setHead(p);
				Pointer<E> next = head.getNext();
				while(next!=null){
					Pointer<E> pt = new Pointer<E>(next.element());
					pt.setPrevious(p);
					p.setNext(pt);
					p = pt;
					next = next.getNext();
				}
			}
			return s;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

}
