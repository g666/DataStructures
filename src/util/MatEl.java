/**
 * 
 */
package util;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class MatEl<T extends Comparable<T>> {

	private T element;
	private int col, row;
	
	/**
	 * @param element
	 * @param col
	 * @param row
	 */
	public MatEl(T element, int row, int col) {
		super();
		this.element = element;
		this.col = col;
		this.row = row;
	}
	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}
	/**
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}
	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
}
