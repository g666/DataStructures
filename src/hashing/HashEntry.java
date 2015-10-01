/**
 * 
 */
package hashing;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class HashEntry<K, V> {

	public static final int DIRTY = 0;
	public static final int USED = 1;
	
	private int state;
	private K key;
	private V value;
	
	public HashEntry() {
		this.state = USED;
	}

	public HashEntry(K key, V value) {
		super();
		this.state = USED;
		this.key = key;
		this.value = value;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HashEntry){
			@SuppressWarnings("unchecked")
			HashEntry<K, V> h = (HashEntry<K, V>)obj;
			return key.equals(h.getKey()) && value.equals(h.getValue());
		}
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "["+key+", "+value+", "+(state==DIRTY?"Dirty":"Used")+"]";
	}
	
}
