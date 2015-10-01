/**
 * 
 */
package hashing.open_addressing;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class QuadraticProbingOpenAddressHashTable<K, V> extends OpenAddressHashTable<K, V> {

	public QuadraticProbingOpenAddressHashTable(int size) {
		super(size);
	}

	@Override
	public final int hashFunction(K key, int j) throws Exception {
		int c_1 = 1;
		int c_2 = 3;
		return (hashFunction(key) + c_1*j + c_2*j*j)%size;
	}

}
