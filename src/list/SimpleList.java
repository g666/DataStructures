package list;


/**
 * 
 */

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class SimpleList<T> extends AbstractList<T> implements SimpleListInterface<T> {

	public void add(T t) throws Exception{
		Pointer<T> n = new Pointer<T>(t);
		if(head==null)
			head=n;
		else{
			n.setNext(head);
			n.setPrevious(null);
			head.setPrevious(n);
			head=n;
		}
		size++;
	}

	public void delete() throws Exception{
		head = head.getNext();
		head.setPrevious(null);
		size--;
	}

	public String toString() {
		return head.toString();
	}

//	public static void main(String[] args) {
//		SimpleList<Integer> s = new SimpleList<Integer>();
//		SimpleList<Integer> s1 = s;
//		s.add(5);
//		s1.add(15);
//		s.add(7);
//		System.out.println(s.toString());
//		s.remove(7);
//		System.out.println(s.toString());
//
//	}

}
