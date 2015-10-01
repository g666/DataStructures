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
public class RandomizedSelection<T> {

	private static Random rdm;

	public static <T> T randomizedSelect(T[] a, int i, Comparator<T> comp) throws IllegalAccessException{
		if(i<=0)
			throw new IllegalAccessException("The statistic order must be positive.");
		else
			return randomizedSelect(a, 0, a.length-1, i, comp);
	}

	private static <T> T randomizedSelect(T[] a, int p, int r, int i, Comparator<T> comp){
		if(p==r)
			return a[p];
		rdm = new Random();
		int q = randomizePartition(a, p, r, comp);
		int k = q-p+1;
		if(i==k)
			return a[q];
		if(i<k)
			return randomizedSelect(a, p, q-1, i, comp);
		else
			return randomizedSelect(a, q+1, r, i-k, comp);
	}

	private static<T> int randomizePartition(T[] a, int p, int r, Comparator<T> comp){
		int i = rdm.nextInt(r-p)+p;
		T temp = a[p];
		a[p] = a[i];
		a[i] = temp;
		return partition(a, p, r, comp);
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

	public static void main(String[] args) throws IllegalAccessException {
		Integer[] x = new Integer[]{
				15,12,5,7,3,1
		};
		for(int i=0;i<x.length; i++)
			System.out.print(x[i]+" ");
		System.out.println();
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		};
		for(int i=0; i<x.length; i++)
			System.out.print(RandomizedSelection.randomizedSelect(x, i+1, 
					comp)+" ");
		System.out.println();
		for(int i=0;i<x.length; i++)
			System.out.print(x[i]+" ");
	}
}
