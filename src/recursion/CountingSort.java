/**
 * 
 */
package recursion;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class CountingSort {

	public static int[] sort(int k, int...a){
		if(a.length>0){
			int[] b = new int[a.length];
			int[] c = new int[k];
			for(int i=0; i<k; i++){
				c[i] = 0;
			}
			for(int i=0; i<a.length; i++){
				c[a[i]-1]+=1;
			}
			for(int i=1; i<k; i++){
				c[i] = c[i] + c[i-1];
			}
			for(int i=a.length-1; i>=0; i--){
				b[c[a[i]-1]-1] = a[i];
				c[a[i]-1] -= 1;
			}
			return b;
		}else
			throw new IllegalArgumentException("The input array is empty.");
	}

	public static void main(String[] args) {
		int[] a = CountingSort.sort(8, new int[]{4,1,2,5,8});
		System.out.println(a.length);
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
	}
}
