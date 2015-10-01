/**
 * 
 */
package util;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Random;

import recursion.Heap;
import util.Pair;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class ImpiccatoSorting {

	private Pair<Character, Integer>[] pair_word;
	private String word;
	
	@SuppressWarnings("unchecked")
	public ImpiccatoSorting(String word) {
		this.word = word;
		char[] w = word.toCharArray();
		pair_word = (Pair<Character, Integer>[]) Array.newInstance(Pair.class, w.length);
		for(int i=0; i<w.length; i++){
			pair_word[i] = new Pair<Character, Integer>(w[i], i);
		}
		pair_word = new Heap<Pair<Character, Integer>>().heapSort(pair_word, new Comparator<Pair<Character,Integer>>() {
			
			@Override
			public int compare(Pair<Character, Integer> arg0,
					Pair<Character, Integer> arg1) {
				return arg0.getFirst().compareTo(arg1.getFirst());
			}
		});
	}
	
	public void automaticPlay(int times, int k){
		Random r = new Random();
		for(int t=0; t<times; t++){
			System.out.println("Game: "+(t+1));
			char[] cs = new char[k];
			int found = 0;
			for(int i=0; i<k; i++){
				char c = (char)(r.nextInt(100)<50?
						r.nextInt(90-65)+65:r.nextInt(122-97)+97);
				cs[i] = c;
				for(int j=0; j<pair_word.length; j++){
					if(pair_word[j].getFirst()==c)
						found++;
				}
			}
			System.out.println("\tword: "+word);
			System.out.println("\tInserted char: "+new String(cs));
			System.out.println("\t"+(word.length()==found?"win":"lose"));
		}
	}
	
	public static void main(String[] args) {
		ImpiccatoSorting i = new ImpiccatoSorting("shellcode");
		long start = System.currentTimeMillis();
		i.automaticPlay(2000000, 7);
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
	}
}
