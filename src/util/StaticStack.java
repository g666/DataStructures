package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 * @param <E>
 */
public class StaticStack<E> implements StackInterface<E>{

	private List<E> arr;
	private int size;
	
	public StaticStack(int size) {
		this.size = size;
		arr = new ArrayList<E>(size);
	}
	
	@Override
	public int size() {
		return arr.size();
	}

	@Override
	public E top() {
		return !isEmpty()?arr.get(0):null;
	}

	@Override
	public void pop() {
		if(!isEmpty())
			arr.remove(0);
		else throw new IllegalStateException("This stack is empty.");
	}

	@Override
	public void push(E item) {
		if(!isFull())
			throw new IllegalStateException("This stack is fully.");
		else
			arr.add(0, item);
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public boolean isFull() {
		return size()==size;
	}

}
