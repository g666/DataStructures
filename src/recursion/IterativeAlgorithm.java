package recursion;


/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class IterativeAlgorithm {

	public static <E> void reverseArray(E[] array){
		int i = 0;
		int j = array.length-1;
		while(i<j){
			E temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
	}

}
