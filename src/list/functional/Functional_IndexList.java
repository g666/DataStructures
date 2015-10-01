package list.functional;

import list.Pointer;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Functional_IndexList {

	public static <T> Pointer<T> add(Pointer<T> head, T val, int index){
		Pointer<T> n = new Pointer<T>(val);
		if(head==null)
			return n;
		Pointer<T> current = head;
		int pos=1;
		while(current.getNext()!=null && pos<index)
		{
			//			System.out.println(current.toString()+"  "+pos);

			current = current.getNext();
			pos++;
		}
		System.out.println(pos);
		if(pos==index)
		{
			current.getPrevious().setNext(n);
			n.setPrevious(current.getPrevious());
			n.setNext(current);
			current.setPrevious(n);
		}else{
			current.setNext(n);
			n.setPrevious(current);
		}
		return head;
	}

	public static <T> Pointer<T> delete(Pointer<T> head, int index){
		Pointer<T> next = head.getNext();
		while(index>1 && next!=null)
		{
			next = next.getNext();
			index--;
		}
		Pointer<T> n = next.getNext();
		n.setPrevious(null);
		return n;
	}

	public static <T> void print(Pointer<T> head){
		System.out.println(head.toString());
	}

	public static void main(String[] args) {
		Pointer<Integer> n = Functional_IndexList.add(null, 8,1);
		//		Functional_SimpleList.print(n);
		n = Functional_IndexList.add(n, 6,2);
		n = Functional_IndexList.add(n, 7,3);
		//		Functional_SimpleList.print(n);
		Pointer<Integer> nx = Functional_IndexList.add(n, 2,2);
		Functional_SimpleList.print(n);
		Functional_SimpleList.print(nx);
		n = Functional_IndexList.delete(nx,2);
		Functional_SimpleList.print(n);
		Functional_SimpleList.print(nx);
		//		Pointer<Integer> nx =FunctionalIndexList.add(n, 1);
		//		FunctionalIndexList.print(n);
		//		n=FunctionalSimpleList.delete(n);
		//		FunctionalIndexList.print(nx);
		//		nx=FunctionalIndexList.delete(n);
		//		FunctionalIndexList.print(n);
		//		FunctionalIndexList.print(nx);
	}
}
