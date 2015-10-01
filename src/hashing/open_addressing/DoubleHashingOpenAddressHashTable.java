/**
 * 
 */
package hashing.open_addressing;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class DoubleHashingOpenAddressHashTable<K, V> extends OpenAddressHashTable<K, V> {

	public DoubleHashingOpenAddressHashTable(int size) {
		super(size);
	}

	@Override
	public final int hashFunction(K key, int j) throws Exception {
		return (hashFunction(key)+j*secondHashFunction(key))%size;
	}

	public abstract int secondHashFunction(K key);

}
