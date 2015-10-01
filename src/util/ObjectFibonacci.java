/**
 * 
 */
package util;

/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class ObjectFibonacci {

	private int n;

	public ObjectFibonacci(int n) {
		this.n = n;
	}

	public int getFib(){
		if(n<=1)return 1;
		else return new ObjectFibonacci(n-1).getFib()+new ObjectFibonacci(n-2).getFib();
	}

}
