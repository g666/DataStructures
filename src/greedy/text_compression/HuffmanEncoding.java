/**
 * 
 */
package greedy.text_compression;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;

import recursion.Heap;
import tree.TreeEntry;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class HuffmanEncoding {

	private static TreeEntry<Integer, Character>[] computeFrequency(String str){
		char[] str_c = str.toCharArray();
		Hashtable<Character, Integer> hash = new Hashtable<Character, Integer>();
		for(int i=0; i<str_c.length; i++){
			char c = str_c[i];
			if(hash.containsKey(c))
				hash.put(c, hash.get(c)+1);
			else
				hash.put(c, 1);
		}
		@SuppressWarnings("unchecked")
		TreeEntry<Integer, Character>[] tree = (TreeEntry<Integer, Character>[]) Array.newInstance(TreeEntry.class, hash.size());
		Enumeration<Character> enu = hash.keys();
		int i = 0;
		while(enu.hasMoreElements()){
			char c = enu.nextElement();
			tree[i] = new TreeEntry<Integer, Character>(hash.get(c), c);
			i++;
		}
		
		return tree;
	}
	
	public static TreeEntry<Integer, Character> encode(String str){
		TreeEntry<Integer, Character>[] fr = computeFrequency(str);
		Heap<TreeEntry<Integer, Character>> heap = new Heap<TreeEntry<Integer,Character>>();
		Comparator<TreeEntry<Integer, Character>> comp = 
				new Comparator<TreeEntry<Integer,Character>>() {
					@Override
					public int compare(TreeEntry<Integer, Character> o1,
							TreeEntry<Integer, Character> o2) {
						return o1.getKey().compareTo(o2.getKey());
					}
				};
		heap.setMaxHeap(true);
		heap.heapSort(fr, comp);
		while(heap.getHeap().length>1){
			TreeEntry<Integer, Character> z = new TreeEntry<Integer, Character>(0, null);
			TreeEntry<Integer, Character> x = heap.extractMin(comp);
			TreeEntry<Integer, Character> y = heap.extractMin(comp);
			z.setLeft(x);
			z.setRight(y);
			z.setKey(x.getKey()+y.getKey());
			heap.insert(z, comp);
			heap.heapSort(comp);
		}
		return heap.extractMin(comp);
	}
	
	public static void main(String[] args) {
		String s = "a fast runner need never be afraid of the dark";
//		String s = "ciao peppe";
		System.out.println(HuffmanEncoding.encode(s));
	}
}
