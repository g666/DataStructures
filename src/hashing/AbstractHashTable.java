/**
 * 
 */
package hashing;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class AbstractHashTable<K, V> implements Table<K, V> {

	protected int size;

	public AbstractHashTable(int size) {
		super();
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
