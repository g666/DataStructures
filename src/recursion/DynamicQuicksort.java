/**
 * 
 */
package recursion;

import java.util.Comparator;
import java.util.Random;

import list.Pointer;
import list.SimpleList;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class DynamicQuicksort {

	private static Random rdm;
	
	public static <T> void sort(Pointer<T> a, Comparator<T> comp, boolean randomize) {
		if(randomize){
			rdm = new Random();
			randomizeSort(a, 0, a.size(), comp);
		}
		else sort(a, 0, a.size(), comp);
	}

	private static <T> void sort(Pointer<T> a, int p, int r, Comparator<T> comp){
		if(p<r){
			int q = partition(a, p, r, comp);
			sort(a, p, q, comp);
			sort(a, q+1, r, comp);
		}
	}
	
	private static <T> void randomizeSort(Pointer<T> a, int p, int r, Comparator<T> comp){
		if(p<r){
			int i = rdm.nextInt(r-p)+p;
			T temp = a.getNextElementAt(i).element();
			a.getNextElementAt(i).setElement(a.getNextElementAt(p).element());
			a.getNextElementAt(p).setElement(temp);
			int q = partition(a, p, r, comp);
			sort(a, p, q, comp);
			sort(a, q+1, r, comp);
		}
	}
	
	private static <T> int partition(Pointer<T> a, int p, int r, Comparator<T> comp) {
		T x = a.getNextElementAt(p).element();
		r++;
		p--;
		boolean exit=false;
		while(!exit){
			do{
				r--;
			}while(comp.compare(a.getNextElementAt(r).element(), x)>0);
			do{
				p++;
			}while(comp.compare(a.getNextElementAt(p).element(), x)<0);
			if(p<r){
				T temp = a.getNextElementAt(p).element();
				a.getNextElementAt(p).setElement(a.getNextElementAt(r).element());
				a.getNextElementAt(r).setElement(temp);
			}
			else exit=true;
		}
		return r;
	}
	
	public static void main(String[] args) throws Exception {
		SimpleList<Integer> sm = new SimpleList<Integer>();
		sm.add(5);
		sm.add(6);
		sm.add(4);
		sm.add(9);
		sm.add(2);
		sm.add(3);
		System.out.println(sm.toString());
		DynamicQuicksort.sort(sm.getHead(), new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		}, true);
		System.out.println(sm.toString());
	}
}
