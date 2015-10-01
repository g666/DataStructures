/**
 * 
 */
package hashing.chaining;

import java.util.Random;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class MultiplicationMethodChainedHashTable<K, V> extends ChainedHashTable<K, V> {

	private boolean randomMultiplication;
	private Random rdm;

	public MultiplicationMethodChainedHashTable(int length, boolean randomMultiplication) {
		super(length);
		this.randomMultiplication = randomMultiplication;
		if(this.randomMultiplication)
			rdm = new Random();
	}
	
	@Override
	public int hashFunction(K key) throws Exception {
		double a = 0;
		if(randomMultiplication){
			if(this.randomMultiplication)
				rdm = new Random();
			
			a = rdm.nextDouble();
		}else
			a = 0.618;
		
		return (int) ((key.hashCode()*a)%1);
	}
	
	public boolean isRandomMultiplication() {
		return randomMultiplication;
	}

}
