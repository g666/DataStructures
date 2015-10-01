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
public class BucketSort {

	public static double[] sort(double[] a){
		int n = a.length;
		@SuppressWarnings("unchecked")
		Pointer<Double>[] b = (Pointer<Double>[]) Array.newInstance(Pointer.class, n);
		for(int i=0; i<n; i++){
			int k = (int) (n*a[i]);
			Pointer<Double> node = b[k];
			if(node==null)
			{
				node = new Pointer<Double>(a[i]);
				b[k] = node;
			}
			else{
				Pointer<Double> last = node.getNextElementAt(node.size());
				Pointer<Double> nn = new Pointer<Double>(a[i], last);
				last.setNext(nn);
			}
		}
		int index=0;
		double[] out = new double[n];
		for(int i=0; i<n; i++){
			Pointer<Double> b_n = b[i];
			if(b_n!=null){
				InsertionSort.dynamicInsertionSort(b_n, new Comparator<Double>() {

					@Override
					public int compare(Double arg0, Double arg1) {
						return arg0.compareTo(arg1);
					}
				});
				while(b_n!=null){
					out[index] = b_n.element();
					b_n = b_n.getNext();
					index++;
				}
			}
		}
		return out;
	}

	public static void main(String[] args) {
		double[] b = BucketSort.sort(new double[]{
				0.78,0.17,0.39,0.26,0.72,0.94,0.21,0.12,0.23,0.68
		});
		for(int i=0; i<b.length; i++)
			System.out.print(b[i]+" ");
	}
}
