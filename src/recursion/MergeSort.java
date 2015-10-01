/**
 * 
 */
package recursion;

import java.lang.reflect.Array;
import java.util.Comparator;

import list.Pointer;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class MergeSort {

	public static <T> void sort(Class<T> c, T[] t, Comparator<T> comp){
		sort(c, t, 0, t.length-1, comp);
	}

	private static <T> void sort(Class<T> c, T[] t, int p, int r, Comparator<T> comp){
		if(p<r){
			int q=(p+r)/2;
			sort(c, t, p, q, comp);
			sort(c, t, q+1, r, comp);
			merge(c, t, p, q, r, comp);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> void merge(Class<T> c, T[] t, int p, int q, int r, Comparator<T> comp){
		int n1=q-p;
		int n2=r-q;
		T[] t1 = (T[]) Array.newInstance(c, n1);
		T[] t2 = (T[]) Array.newInstance(c, n2);
		for(int i=0; i<n1; i++)
			t1[i]=t[p+i];
		for(int i=0; i<n2; i++)
			t2[i]=t[q+i];
		int i=0;
		int j=0;
		for(int k=p; k<r; k++){
			try {
				if(comp.compare(t1[i],t2[j])<=0)
				{
					t[k]=t1[i];
					t[k+1]=t2[j];
					i++;

				}
				else{
					t[k]=t2[j];
					t[k+1]=t1[i];
					j++;
				}		
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}

	public static <T> void dynamicSort(Pointer<T> t, Comparator<T> comp){
		dynamicSort(t, 0, t.size()-1, comp);
	}

	private static <T> void dynamicSort(Pointer<T> t, int p, int r, Comparator<T> comp){
		if(p<r){
			int q=(p+r)/2;
			dynamicSort(t, p, q, comp);
			dynamicSort(t, q+1, r, comp);
			dynamicMerge(t, p, q, r, comp);
		}
	}

	private static <T> void dynamicMerge(Pointer<T> t, int p, int q, int r, Comparator<T> comp){
		int n1=q-p+1;
		int n2=r-q;
		Pointer<T> t1 = t.copyNextElementAt(p);
		Pointer<T> t2 = t.copyNextElementAt(q+1);
		for(int i=1; i<n1; i++){
			t1.setNext(t.copyNextElementAt(p+i));
		}
		for(int i=1; i<n2; i++){
			t2.setNext(t.copyNextElementAt(q+i+1));
		}
		for(int k=p; k<r; k++){
			if(comp.compare(t1.element(), t2.element())<=0)
			{
				t.getNextElementAt(k).setElement(t1.element());
				t.getNextElementAt(k).getNext().setElement(t2.element());
				t1 = t1.getNext();
			}
			else{
				t.getNextElementAt(k).setElement(t2.element());
				t.getNextElementAt(k).getNext().setElement(t1.element());
				t2 = t2.getNext();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Integer[] i = new Integer[]{6,5,4,8,9,3,10,2};
		System.out.println(i.length);
		MergeSort.sort(Integer.class, i,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		for(int k=0; k<i.length; k++)
			System.out.print(i[k]+" ");
		
	}
	
}
