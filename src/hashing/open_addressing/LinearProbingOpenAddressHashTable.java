/**
 * 
 */
package hashing.open_addressing;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class LinearProbingOpenAddressHashTable<K, V> extends OpenAddressHashTable<K, V> {

	public LinearProbingOpenAddressHashTable(int size) {
		super(size);
	}

	@Override
	public final int hashFunction(K key, int j) throws Exception {
		return (hashFunction(key)+j)%getSize();
	}

}
