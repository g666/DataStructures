/**
 * 
 */
package hashing.chaining;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class DivisionMethodChainedHashTable<K, V> extends ChainedHashTable<K, V> {

	public DivisionMethodChainedHashTable(int size) {
		super(size);
	}
	
	@Override
	public int hashFunction(K key) throws Exception {
		return key.hashCode()%size;
	}

}
