/**
 * 
 */
package recursion;

import java.util.Comparator;
import java.util.Random;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Quicksort {

	private static Random rdm;
	
	public static<T> void sort(T[] a, Comparator<T> comp, boolean randomize) {
		if(randomize)
			{
			rdm = new Random();
			randomizeSort(a, 0, a.length-1, comp);
			}
		else
			sort(a, 0, a.length-1, comp);
	}

	private static<T> void sort(T[] a, int p, int r, Comparator<T> comp){
		if(p<r){
			int q = partition(a, p, r, comp);
			sort(a, p, q, comp);
			sort(a, q+1, r, comp);
		}
	}
	
	private static<T> void randomizeSort(T[] a, int p, int r, Comparator<T> comp){
		if(p<r){
			int i = rdm.nextInt(r-p)+p;
			T temp = a[p];
			a[p] = a[i];
			a[i] = temp;
			int q = partition(a, p, r, comp);
			sort(a, p, q, comp);
			sort(a, q+1, r, comp);
		}
	}
	
	private static<T> int partition(T[] a, int p, int r, Comparator<T> comp) {
		T x = a[p];
		r++;
		p--;
		boolean exit = false;
		while(!exit){
			do{
				r--;
			}while(comp.compare(a[r], x)>0);
			do{
				p++;
			}while(comp.compare(a[p], x)<0);
			if(p<r){
				T temp = a[p];
				a[p] = a[r];
				a[r] = temp;
			}
			else exit = true;
		}
		return r;
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{
				15,12,5,7,3,1
		};
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
		System.out.println();
		Quicksort.sort(a, new Comparator<Integer>() {
			
			@Override
			public int compare(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				return arg0-arg1;
			}
		}, true);
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
	}
}
