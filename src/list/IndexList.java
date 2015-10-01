/**
 * 
 */
package list;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class IndexList<E> extends SimpleList<E> implements IndexListInterface<E> {

	@Override
	public void add(E t, int index) throws Exception {
		if(index<1)
			throw new IndexOutOfBoundsException("index must be >= 1");
		int pos = index>size?size:index;
		if(index==1)
			add(t);
		else{
			Pointer<E> current_h = head;
			Pointer<E> current = head;
			while(pos>1){
				current = current.getNext();
				pos--;
			}
			if(index>=size){
				Pointer<E> pt = new Pointer<E>(t, null, current);
				current.setNext(pt);	
			}
			else{
				Pointer<E> pt = new Pointer<E>(t, current, current.getPrevious());
				current.getPrevious().setNext(pt);
				current.setPrevious(pt);
			}
			head = current_h;
			size++;
		}
	}

	@Override
	public void delete(int index) throws Exception {
		if(index<1)
			throw new IndexOutOfBoundsException("index must be >= 1");
		if(index==1)
			delete();
		else{
			int pos = index>size?size:index;
			Pointer<E> current_h = head;
			Pointer<E> current = head;
			while(pos>1){
				current = current.getNext();
				pos--;
			}
			if(index>=size){
				current.getPrevious().setNext(null);
			}else{
				current.getPrevious().setNext(current.getNext());
				current.getNext().setPrevious(current.getPrevious());
			}
			head = current_h;
			size--;
		}
	}

	@Override
	public E get(int index) {
		if(index<1 || index>size)
			throw new IndexOutOfBoundsException("index must be >= 1");
		Pointer<E> current = head;
		while(index>1){
			current = current.getNext();
			index--;
		}
		return current.element();
	}
	
	public static void main(String[] args) throws Exception {
		IndexList<Integer> i = new IndexList<Integer>();
		i.add(4);
		i.add(3, i.size()+1);
		System.out.println(i);
		System.out.println(i.size());
		System.out.println(i.get(i.size()));
	}
}
