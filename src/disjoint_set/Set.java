/**
 * 
 */
package disjoint_set;

import list.Pointer;
import list.PositionList;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Set<E> extends PositionList<E> implements DisjointSet<E>{
	
	public Set() {
		size = 0;
		head = new PointerSet<E>(null, null, null);
		trailer = new PointerSet<E>(null, null, head);
		head.setNext(trailer);
		((PointerSet<E>)head).setSet(null);
		((PointerSet<E>)trailer).setSet(null);
	}
	
	public PointerSet<E> makeSet(E element) throws Exception {
		size++;
		PointerSet<E> newNode = new PointerSet<E>(element, head.getNext(), head);
		head.getNext().setPrevious(newNode);
		head.setNext(newNode);
		newNode.setSet(this);
		return newNode;
	}
	
	@Override
	public E remove(Pointer<E> p) throws Exception {
		((PointerSet<E>)p).setSet(null);
		return super.remove(p);
	}
	
	public void union(PointerSet<E> x, PointerSet<E> y) throws Exception{
		if(x.getSet().equals(this)){
			makeSet(y.element());
			y.getSet().remove(y);
		}
		else
			throw new IllegalArgumentException();
	}
	
	public Set<E> findSet(PointerSet<E> p){
		return p.getSet();
	}
	
	public static void main(String[] args) throws Exception{
		Set<Integer> s1 = new Set<Integer>();
		Set<Integer> s2 = new Set<Integer>();
		PointerSet<Integer> p1 = s1.makeSet(5);
		PointerSet<Integer> p2 = s2.makeSet(8);
		s1.union(p1, p2);
		System.out.println(s1);
		System.out.println(s2);
	}

}
