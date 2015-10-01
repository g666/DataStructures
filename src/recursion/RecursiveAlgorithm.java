package recursion;


/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class RecursiveAlgorithm {

	public static int recursiveFactorial(int n){
		if(n<2)
			return 1;
		else
			return n*recursiveFactorial(n-1);
	}

	public static void drawOneTick(int tickLength) { 
		drawOneTick(tickLength, - 1); 
	}

	private static void drawOneTick(int tickLength, int tickLabel) {
		for (int i = 0; i < tickLength; i++)
			System.out.print("-");
		if (tickLabel >= 0) System.out.print(" " + tickLabel);
		System.out.print("\n");
	}

	private static void drawTicks(int tickLength) {
		if (tickLength > 0) {
			drawTicks(tickLength- 1);
			drawOneTick(tickLength);
			drawTicks(tickLength- 1);
		}
	}

	public static void drawRuler(int nInches, int majorLength) {
		drawOneTick(majorLength, 0);
		for (int i = 1; i <= nInches; i++)
		{
			drawTicks(majorLength- 1);
			drawOneTick(majorLength, i);
		}
	}

	public static int linearSum(int[] a, int n){
		if(a==null || a.length==0)
			throw new IllegalArgumentException("The parameters can't be null.");
		else if(n<1)
			throw new IllegalArgumentException("n must be grater than 0.");
		else if(n>a.length)
			throw new IllegalArgumentException("n must be less than legth of array.");
		else if(n==1)
			return a[0];
		else
			return linearSum(a, n-1)+a[n-1];
	}

	// non funziona
	public static int binarySum(int[] a, int i, int n){
		if(a==null || a.length==0)
			throw new IllegalArgumentException("The parameters can't be null.");
		else if(n<1)
			throw new IllegalArgumentException("n must be grater than 0.");
		else if(n>a.length)
			throw new IllegalArgumentException("n must be less than legth of array.");
		else if(i>a.length || i<0)
			throw new IllegalArgumentException();
		else if(n==1)
			return a[i];
		else{
			//			int y = (int) Math.floor(n/2);
			return binarySum(a, i, n/2) + binarySum(a, i+(n/2), n/2);
		}
	}

	public static <E> void reverseArray(E[] array){
		reverseArray(array, 0, array.length-1);
	}

	private static <E> void reverseArray(E[] array, int i, int j){
		E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		if(i<j)
			reverseArray(array, i+1, j-1);
	}

	// O(n)
	public static int powerFunction(int x, int n){
		if(n==0)
			return 1;
		else
			return x*powerFunction(x, n-1);
	}

	// O(log(n))
	public static int betterPowerFunction(int x, int n){
		if(n==0)
			return 1;
		else if(n%2==0)
		{
			int y = betterPowerFunction(x, n/2);
			return y * y;			
		}
		else
		{
			int y = betterPowerFunction(x, (n-1)/2);
			return x * y * y;
		}
	}

	public static void summationPuzzles(){

	}

	public static void main(String[] args) {
		int[] a = new int[]{
				4,5,7,8,2,5,6
		};
		System.out.println(a.length/2);
		System.out.println(binarySum(a, 0, a.length));
		//		System.out.println(binaryFibonacci(5));
	}
}
