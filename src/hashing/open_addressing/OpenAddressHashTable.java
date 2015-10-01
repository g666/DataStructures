/**
 * 
 */
package hashing.open_addressing;

import hashing.AbstractHashTable;
import hashing.HashEntry;

import java.lang.reflect.Array;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class OpenAddressHashTable<K, V> extends AbstractHashTable<K, V> {

	protected HashEntry<K, V>[] table;

	@SuppressWarnings("unchecked")
	public OpenAddressHashTable(int size) {
		super(size);
		table = (HashEntry<K, V>[]) Array.newInstance(HashEntry.class, size);
	}
	
	@Override
	public final void insert(K key, V value) throws Exception {
		if(key!=null && value!=null){
			int i=0;
			boolean inserted = false;
			while(i!=table.length && !inserted){
				int j = hashFunction(key, i);
				if(table[j]==null || table[j].getState()==HashEntry.DIRTY)
				{
					table[j] = new HashEntry<K, V>(key, value);
					inserted = true;
				}
				else
					i++;
			}
			if(!inserted)
				throw new Exception("Hash table overflow before insert:\nkey: "+key+", value: "+value);
		}else
			throw new IllegalArgumentException("Arguments can't be null");
	}

	@Override
	public final void delete(K key, V value) throws Exception {
		if(key!=null && value!=null){
			int i = search(key, value);
			if(i!=-1){
				HashEntry<K, V> h = table[i];
				if(h.getState()==HashEntry.USED && h.getValue().equals(value))
					table[i] = null;
			}
		}else
			throw new IllegalArgumentException("Arguments can't be null");
	}

	@Override
	public final int search(K key, V value) throws Exception {
		if(key!=null && value!=null){
			int i = 0;
			int j = hashFunction(key, i);
			while(table[j]!=null || i!=table.length){
				if(table[j].getState()==HashEntry.USED && table[j].getKey().equals(key))
					return j;
				else
					i++;
			}
			return -1;
		}else
			throw new IllegalArgumentException("Arguments can't be null");
	}

	@Override
	public int hashFunction(K key) throws Exception {
		return key.hashCode();
	}
	
	public abstract int hashFunction(K key, int j) throws Exception;

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i<table.length; i++){
			HashEntry<K, V> p = table[i];
			s.append((p!=null?p.toString():"--")+"\n");
		}
		return s.toString();
	}

	public static void main(String[] args) throws Exception{
		LinearProbingOpenAddressHashTable<Integer, String> l = 
				new LinearProbingOpenAddressHashTable<Integer, String>(8) {
					
					@Override
					public int hashFunction(Integer key) throws Exception {
						return key%size;
					}
				};
				
		l.insert(4, "4");
		l.insert(5, "5");
		l.insert(6, "6");
		l.insert(1, "1");
		l.delete(6, "6");
		l.insert(2, "2");
		l.insert(7, "7");
		l.insert(8, "8");
		System.out.println(l);
	}
	
}
