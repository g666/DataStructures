/**
 * 
 */
package util;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class LinkStack<E> implements StackInterface<E> {
	
	private Cell currentItem;

	@Override
	public int size() {
		int s = 0;
		Cell c = currentItem;
		while(c!=null){
			s++;
			c = c.next;
		}
		return s;
	}

	@Override
	public E top() {
		return !isEmpty()?currentItem.item:null;
	}

	@Override
	public void pop() {
		if(!isEmpty())
			currentItem = currentItem.next;
		else
			throw new IllegalStateException("this stack is empty.");
	}

	@Override
	public void push(E item) {
		Cell c = new Cell(item, currentItem);
		currentItem = c;
	}

	@Override
	public boolean isEmpty() {
		return currentItem==null;
	}

	@Override
	public boolean isFull() {
		return false;
	}
	
	class Cell{
		private E item;
		private Cell next;
		
		public Cell(E item, LinkStack<E>.Cell next) {
			this.item = item;
			this.next = next;
		}
		
	}

}
