/**
 * 
 */
package recursion;

import java.util.Comparator;

import list.Pointer;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class InsertionSort {

	public static <T> void insertionSort(T[] t, Comparator<T> comp){
		for(int i=1; i<t.length; i++){
			T temp = t[i];
			int j=i-1;
			while(j>=0 && comp.compare(t[j], temp)>=0){
				t[j+1]=t[j];
				t[j]=temp;
				j--;
			}
		}
	}
	
	public static <T> void dynamicInsertionSort(Pointer<T> t, Comparator<T> comp){
		for(Pointer<T> i=t.getNext(); i!=null; i=i.getNext()){
			T temp = i.element();
			Pointer<T> j=i.getPrevious();
			while(j!=null && comp.compare(j.element(),temp)>=0){
				j.getNext().setElement(j.element());
				j.setElement(temp);
				j=j.getPrevious();
			}
		}
	}
	
}
