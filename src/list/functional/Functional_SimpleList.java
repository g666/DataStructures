package list.functional;

import list.Pointer;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Functional_SimpleList {

	public static <T> Pointer<T> add(Pointer<T> head, T val){
		Pointer<T> n = new Pointer<T>(val);
		if(head!=null){
			n.setNext(head);
			head.setPrevious(n);
		}
		return n;
	}
	
	public static <T> Pointer<T> delete(Pointer<T> head){
		Pointer<T> n = head.getNext();
		n.setPrevious(null);
		return n;
	}
	
	public static <T> void print(Pointer<T> head){
		System.out.println(head.toString());
	}
	
	public static void main(String[] args) {
		Pointer<Integer> n = Functional_SimpleList.add(null, 8);
//		FunctionalSimpleList.print(n);
		n = Functional_SimpleList.add(n, 6);
//		FunctionalSimpleList.print(n);
		Pointer<Integer> nx =Functional_SimpleList.add(n, 1);
		Functional_SimpleList.print(n);
//		n=FunctionalSimpleList.delete(n);
		Functional_SimpleList.print(nx);
		nx=Functional_SimpleList.delete(n);
		Functional_SimpleList.print(n);
		Functional_SimpleList.print(nx);
		}
}
