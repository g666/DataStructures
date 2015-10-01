package graph;

import list.Pointer;


/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Node<E> extends Pointer<E> {

	public Node(Node<E> newPrev, Node<E> newNext, E elem) {
		super(elem, newNext, newPrev);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(element);
		Node<E> n = (Node<E>) next;
		while(n!=null){
			s.append(" --> "+n.element());
			n=(Node<E>) n.getNext();
		}
		return s.toString();
	}

}