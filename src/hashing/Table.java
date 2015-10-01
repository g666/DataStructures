/**
 * 
 */
package hashing;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public interface Table<K, V> {

	public void insert(K key, V value) throws Exception;
	
	public void delete(K key, V value) throws Exception;
	
	public int search(K key, V value) throws Exception;
	
	public int hashFunction(K key) throws Exception;
	
}
