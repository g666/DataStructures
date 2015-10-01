/**
 * 
 */
package util;

import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class ImpiccatoHash {
	
	private Hashtable<Integer, List<Integer>> map;
	private char[] wd;
	
	public ImpiccatoHash(String word) {
		this.wd = word.toCharArray();
		map = new Hashtable<Integer, List<Integer>>();
		for(int i=0; i<wd.length; i++){
			if(!map.containsKey((int)wd[i])){
				List<Integer> lis = new Vector<Integer>();
				lis.add(new Integer(i));
				map.put((int)wd[i], lis);
			}else{
				List<Integer> lis = map.get((int)wd[i]);
				if(lis==null)
					lis = new Vector<Integer>();
				lis.add(new Integer(i));
			}
		}
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
				try {
					found+=map.get((int)c).size();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.println("\tword: "+new String(wd));
			System.out.println("\tInserted char: "+new String(cs));
			System.out.println("\t"+(wd.length==found?"win":"lose"));
		}
	}

	public static void main(String[] args) {
		ImpiccatoHash i = new ImpiccatoHash("shellcode");
		long start = System.currentTimeMillis();
		i.automaticPlay(2000000, 7);
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
	}
}
