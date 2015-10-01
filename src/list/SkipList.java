/**
 * 
 */
package list;

import java.util.Comparator;
import java.util.Random;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class SkipList<E> {

	private int heigth;
	private int size;
	private SkipListNode<E> head, trailer;
	private Comparator<E> comp;
	private Random rdm;

	public SkipList(Comparator<E> comp) {
		this.comp = comp;
		size = 0;
		heigth = 0;
		rdm = new Random();
		head = new SkipListNode<E>(null, null, null);
		trailer = new SkipListNode<E>(null, null, null);
		head.setNext(trailer);
		trailer.setPrevious(head);
	}

	public int getHeigth() {
		return heigth;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public int getSize() {
		return size;
	}

	/**
	 * Find the largest element p <= element on the lowest level of the Skip List
	 * 
	 * @param element
	 * @return
	 */
	public SkipListNode<E> search(E element){
		boolean found = false;
		SkipListNode<E> p = null;
		try {
			p = head.getDown();
			if(p.getNext().getNext()!=null)
				p = p.getNext();
		} catch (Exception e) {
			p = head;
		}
		while(!found && p.element()!=null){
			if(comp.compare(p.element(), element)<0)
			{
				if(p.getNext().element()==null)
				{
					if(p.getDown()==null)
						found=true;
					else
						p = p.getDown();
				}
				else
					p = p.getNext();
			}
			else if(comp.compare(p.element(), element)>0)
			{
				p = p.getPrevious();
				if(p.getDown()==null)
					found=true;
				else
					p = p.getDown();
			}
			else
			{
				if(p.getDown()==null)
					found=true;
				else
					p = p.getDown();
			}
		}
		return p;
	}

	public void insert(E element){
		if(size==0){
			heigth++;
			SkipListNode<E> newNode = new SkipListNode<E>(element, trailer, head);
			head.setNext(newNode);
			trailer.setPrevious(newNode);
			SkipListNode<E> h_head = new SkipListNode<E>(null, null, null);
			SkipListNode<E> h_trailer = new SkipListNode<E>(null, null, null);
			h_head.setNext(h_trailer);
			h_trailer.setPrevious(h_head);
			head.setUp(h_head);
			h_head.setDown(head);
			trailer.setUp(h_trailer);
			h_trailer.setDown(trailer);
			head = h_head;
			trailer = h_trailer;
		}
		else{
			int h = tossAcoin();
			SkipListNode<E> p = search(element);
			SkipListNode<E> newNode = new SkipListNode<E>(element, p.getNext(), p);
			p.getNext().setPrevious(newNode);
			p.setNext(newNode);

			for(int i=0; i<h; i++){
				SkipListNode<E> p_up_by_left = up_by_left(newNode);
				SkipListNode<E> p_up_by_right = up_by_right(newNode);
				SkipListNode<E> newNode_up = new SkipListNode<E>(element, p_up_by_right, p_up_by_left);
				p_up_by_right.setPrevious(newNode_up);
				p_up_by_left.setNext(newNode_up);
				newNode.setUp(newNode_up);
				newNode_up.setDown(newNode);
				newNode = newNode_up;
				if(head.getNext().element()!=null){
					heigth++;
					SkipListNode<E> h_head = new SkipListNode<E>(null, null, null);
					SkipListNode<E> h_trailer = new SkipListNode<E>(null, null, null);
					h_head.setNext(h_trailer);
					h_trailer.setPrevious(h_head);
					head.setUp(h_head);
					h_head.setDown(head);
					trailer.setUp(h_trailer);
					h_trailer.setDown(trailer);
					head = h_head;
					trailer = h_trailer;
//					p_up_by_left = up_by_left(newNode_up);
//					p_up_by_right = up_by_right(newNode_up);
				}
			}
			if(head.getNext().element()!=null){
				heigth++;
				SkipListNode<E> h_head = new SkipListNode<E>(null, null, null);
				SkipListNode<E> h_trailer = new SkipListNode<E>(null, null, null);
				h_head.setNext(h_trailer);
				h_trailer.setPrevious(h_head);
				head.setUp(h_head);
				h_head.setDown(head);
				trailer.setUp(h_trailer);
				h_trailer.setDown(trailer);
				head = h_head;
				trailer = h_trailer;
			}
		}

		size++;
	}

	private SkipListNode<E> up_by_left(SkipListNode<E> p){
		while(p.getUp()==null)
			p = p.getPrevious();
		return p.getUp();
	}

	private SkipListNode<E> up_by_right(SkipListNode<E> p){
		while(p.getUp()==null)
			p = p.getNext();
		return p.getUp();
	}

	private int tossAcoin(){
		int h = 0;
		int i = 0;
		while(i<size){
			if(rdm.nextInt(2)==0)
				h++;
			i++;
		}

		return h;
	}

	public String toString() {
		SkipListNode<E> p = head;
		while(p.getDown()!=null)
			p = p.getDown();
		return p.toString();
	}
	
	public void delete(E element){
		SkipListNode<E> s = search(element);
		if(s.element()!=null && element!=null && comp.compare(s.element(), element)==0){
			while(s!=null){
				SkipListNode<E> next = s.getNext();
				SkipListNode<E> prev = s.getPrevious();
				prev.setNext(next);
				next.setPrevious(prev);
				if(next.element()==null && prev.element()==null){
					head = head.getDown();
					trailer = trailer.getDown();
				}
				s = s.getUp();
			}
		}else
			throw new IllegalArgumentException("Element not found");
	}

	public static void main(String[] args) {
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		};
		SkipList<Integer> s = new SkipList<Integer>(comp);

		s.insert(7);
		s.insert(4);
		s.insert(5);
		s.insert(8);
		s.insert(50);
		s.insert(18);
		s.insert(51);
		s.delete(50);
		System.out.println(s);
	}

}
