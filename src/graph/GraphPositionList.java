package graph;

import list.Pointer;
import list.Position;
import list.PositionList;



/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 * @param <T>
 *
 */
public class GraphPositionList<E> extends PositionList<E>{

	@SuppressWarnings("unchecked")
	public GraphPositionList() {
		size = 0;
		head = (Pointer<E>) new Node<PositionAwareType<E>>(null, null, null);
		trailer = (Pointer<E>) new Node<PositionAwareType<E>>((Node<PositionAwareType<E>>) head, null, null);
		head.setNext(trailer);
	}

	@SuppressWarnings("unchecked")
	public Position<PositionAwareType<E>> addPos(E element, Class<? extends PositionAwareType<E>> clazz) throws InstantiationException, IllegalAccessException {
		size++;
		PositionAwareType<E> pos = clazz.newInstance();
		pos.setInfo(element);
		Node<PositionAwareType<E>> newNode = 
				new Node<PositionAwareType<E>>((Node<PositionAwareType<E>>) head, (Node<PositionAwareType<E>>) head.getNext(), pos);
		pos.setPosition((Position<PositionAwareType<E>>) newNode);
		head.getNext().setPrevious((Pointer<E>) newNode);
		head.setNext((Pointer<E>) newNode);
		return newNode;
	}
	
	@SuppressWarnings("unchecked")
	public Position<PositionAwareType<E>> addPos(PositionAwareType<E> pos) throws InstantiationException, IllegalAccessException {
		size++;
		Node<PositionAwareType<E>> newNode = 
				new Node<PositionAwareType<E>>((Node<PositionAwareType<E>>) head, (Node<PositionAwareType<E>>) head.getNext(), pos);
		head.getNext().setPrevious((Pointer<E>) newNode);
		head.setNext((Pointer<E>) newNode);
		return newNode;
	}

	@SuppressWarnings("unchecked")
	public PositionAwareType<E> removePos(Position<PositionAwareType<E>> p) throws Exception {
		Node<PositionAwareType<E>> v = (Node<PositionAwareType<E>>) checkPosition((Position<E>) p);
		size--;
		Node<PositionAwareType<E>> vPrev = (Node<PositionAwareType<E>>) v.getPrevious();
		Node<PositionAwareType<E>> vNext = (Node<PositionAwareType<E>>) v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrevious(vPrev);
		PositionAwareType<E> pos = v.element();
		pos.setPosition(null);
		return pos;
	}

	@SuppressWarnings("unchecked")
	public E setPos(Position<PositionAwareType<E>> p, E element) throws Exception {
		Node<PositionAwareType<E>> v = (Node<PositionAwareType<E>>) checkPosition((Position<E>) p);
		E ele = v.element().getInfo();
		v.element().setInfo(element);
		return ele;
	}
	
	@SuppressWarnings("unchecked")
	public PositionAwareType<E> getHeader() {
		return (PositionAwareType<E>) head.getNext().element();
	}
	
	@Override
	public String toString() {
		return head.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(E e) throws InstantiationException, IllegalAccessException {
		addPos((PositionAwareType<E>) e);
	}
	
	/* (non-Javadoc)
	 * @see list.PositionList#addAfter(list.Pointer, java.lang.Object)
	 */
	@Override
	public Pointer<E> addAfter(Pointer<E> p, E element) throws Exception {
		// TODO Auto-generated method stub
		return super.addAfter(p, element);
	}
	
	/* (non-Javadoc)
	 * @see list.PositionList#addBefore(list.Pointer, java.lang.Object)
	 */
	@Override
	public Pointer<E> addBefore(Pointer<E> p, E element) throws Exception {
		// TODO Auto-generated method stub
		return super.addBefore(p, element);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pointer<E> addFirst(E element) throws Exception {
		return (Pointer<E>) addPos((PositionAwareType<E>) element);
	}
	
	@Override
	public Pointer<E> addLast(E element) {
		// TODO Auto-generated method stub
		return super.addLast(element);
	}
	
	@Override
	public void delete() throws Exception {
		if(size>0)
			remove(head.getNext());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pointer<E> next(Pointer<E> p) throws Exception {
		Node<PositionAwareType<E>> v = (Node<PositionAwareType<E>>) checkPosition(p);
		Node<PositionAwareType<E>> next = (Node<PositionAwareType<E>>) v.getNext();
		if (next == trailer)
			throw new Exception("Cannot advance past the end of the list");
		return (Pointer<E>) next;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pointer<E> prev(Pointer<E> p) throws Exception {
		Node<PositionAwareType<E>> v = (Node<PositionAwareType<E>>) checkPosition(p);
		Node<PositionAwareType<E>> prev = (Node<PositionAwareType<E>>) v.getPrevious();
		if (prev == head)
			throw new Exception("Cannot advance past the beginning of the list");
		return (Pointer<E>) prev;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void remove(E e) throws Exception {
		// TODO Auto-generated method stub
		removePos((Position<PositionAwareType<E>>) e);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E remove(Pointer<E> p) throws Exception {
		return (E) removePos((Position<PositionAwareType<E>>) p);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E set(Pointer<E> p, E element) throws Exception {
		return setPos((Position<PositionAwareType<E>>) p, element);
	}

}