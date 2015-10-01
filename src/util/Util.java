package util;

import java.util.Random;
/**
 * 
 */

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Util {

	public static double trunkDouble(double d, int rightnumbers){
		return (double)(
				(long) (d*Math.pow(10, rightnumbers))
				)/Math.pow(10, rightnumbers);
	}
	
	public static void reverse(Object[] a){
		for(int i=0; i<a.length/2; i++)
		{
			int j=(a.length-i)-1;
			Object temp = a[i];
			a[i] = a[j];
			a[j]=temp;
		}
	}
	
	public static String generateRandomString(int length){
		Random r = new Random();
		StringBuilder s = new StringBuilder();
		for(int i=0; i<length; i++){
			s.append((char)(r.nextInt(100)<50?
					r.nextInt(90-65)+65:r.nextInt(122-97)+97));
		}
		return s.toString();
	}
	
}
