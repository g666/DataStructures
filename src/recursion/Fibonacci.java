/**
 * 
 */
package recursion;

import util.Pair;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Fibonacci {

	public static int binaryFibonacci(int k){
		if(k<=1)
			return 1;
		else
			return binaryFibonacci(k-1) + binaryFibonacci(k-2);
	}

	private static Pair<Integer, Integer> linearFib(int n){
		if(n<=1) return new Pair<Integer, Integer>(1, 0);
		else {
			Pair<Integer, Integer> tmp = linearFib(n-1);
			return new Pair<Integer, Integer>(tmp.getFirst()+tmp.getSecond(), tmp.getFirst());
		}
	}

	// O(n)
	public static int linearFibonacci(int n){
		return linearFib(n).getFirst();
	}
	
}
