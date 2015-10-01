/**
 * 
 */
package hashing.chaining;

import hashing.AbstractHashTable;
import hashing.HashEntry;

import java.lang.reflect.Array;

import list.Pointer;
import list.PositionList;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class ChainedHashTable<K, V> extends AbstractHashTable<K, V> {

	protected PositionList<HashEntry<K, V>>[] table;

	@SuppressWarnings("unchecked")
	public ChainedHashTable(int size) {
		super(size);
		table = (PositionList<HashEntry<K, V>>[]) Array.newInstance(PositionList.class, size);
	}

	@Override
	public final void insert(K key, V value) throws Exception {
		if(key!=null && value!=null)
		{
			int index = hashFunction(key);
			PositionList<HashEntry<K, V>> p = table[index];
			if(p==null)
			{
				p = new PositionList<HashEntry<K,V>>();
				table[index] = p;
			}

			p.add(new HashEntry<K, V>(key, value));
		}else
			throw new IllegalArgumentException("Arguments can't be null");
	}

	@Override
	public final void delete(K key, V value) throws Exception {
		if(key!=null && value!=null)
		{
			PositionList<HashEntry<K, V>> p = table[hashFunction(key)];
			if(p!=null){
				p.remove(new HashEntry<K, V>(key, value));
			}
		}else
			throw new IllegalArgumentException("Arguments can't be null");
	}

	@Override
	public final int search(K key, V value) throws Exception {
		if(key!=null && value!=null){
			int i = hashFunction(key);
			PositionList<HashEntry<K, V>> p = table[i];
			if(p!=null){
				boolean found = false;
				Pointer<HashEntry<K, V>> head = p.getHead();
				while(head!=null && !found){
					HashEntry<K, V> h = head.element();
					if(h!=null && h.getValue().equals(value) && h.getKey().equals(key))
						found = true;
					else
						head = head.getNext();
				}
				return i;
			}else
				return -1;
		}else
			throw new IllegalArgumentException("Arguments can't be null");
	}
	
	@Override
	public int hashFunction(K key) throws Exception {
		return key.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i<table.length; i++){
			PositionList<HashEntry<K, V>> p = table[i];
			s.append((p!=null?p.toString():"--")+"\n");
		}
		return s.toString();
	}

}
