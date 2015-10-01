package util;

/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 * @param <E>
 */
public interface StackInterface <E>{

	public int size();
	
	public E top();
	
	public void pop();
	
	public void push(E item);
	
	public boolean isEmpty();
	
	public boolean isFull();
}
