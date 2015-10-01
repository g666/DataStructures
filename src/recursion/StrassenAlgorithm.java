/**
 * 
 */
package recursion;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class StrassenAlgorithm {

	public static int[][] strassenAlgorithm(int[][] a, int[][] b){
		int n = a.length;
		int[][] c = new int[n][n];
		if(n==1)
			c[0][0] = a[0][0]*b[0][0];
		else{
			int[][] a_11 = new int[n/2][n/2];
			int[][] a_12 = new int[n/2][n/2];
			int[][] a_21 = new int[n/2][n/2];
			int[][] a_22 = new int[n/2][n/2];

			int[][] b_11 = new int[n/2][n/2];
			int[][] b_12 = new int[n/2][n/2];
			int[][] b_21 = new int[n/2][n/2];
			int[][] b_22 = new int[n/2][n/2];

			divideMatrix(a, a_11, 0, 0);
			divideMatrix(a, a_12, 0, n/2);
			divideMatrix(a, a_21, n/2, 0);
			divideMatrix(a, a_22, n/2, n/2);

			divideMatrix(b, b_11, 0, 0);
			divideMatrix(b, b_12, 0, n/2);
			divideMatrix(b, b_21, n/2, 0);
			divideMatrix(b, b_22, n/2, n/2);

			int[][] p1 = strassenAlgorithm(a_11, subMatrix(b_12, b_22));
			int[][] p2 = strassenAlgorithm(sumMatrix(a_11, a_12), b_22);
			int[][] p3 = strassenAlgorithm(sumMatrix(a_21, a_22), b_11);
			int[][] p4 = strassenAlgorithm(a_22, subMatrix(b_21, b_11));
			int[][] p5 = strassenAlgorithm(sumMatrix(a_11, a_22), sumMatrix(b_11, b_22));
			int[][] p6 = strassenAlgorithm(subMatrix(a_12, a_22), sumMatrix(b_21, b_22));
			int[][] p7 = strassenAlgorithm(subMatrix(a_11, a_21), sumMatrix(b_11, b_12));

			int[][] c_11 = sumMatrix(subMatrix(sumMatrix(p5, p4), p2), p6);
			int[][] c_12 = sumMatrix(p1, p2);
			int[][] c_21 = sumMatrix(p3, p4);
			int[][] c_22 = subMatrix(subMatrix(sumMatrix(p5, p1), p3), p7);

			copyMatrix(c_11, c, 0 , 0);
			copyMatrix(c_12, c, 0 , n/2);
			copyMatrix(c_21, c, n/2, 0);
			copyMatrix(c_22, c, n/2, n/2);
		}
		return c;
	}

	private static void divideMatrix(int[][] parent, int[][] child, int iB, int jB) {
		for(int i1 = 0, i2=iB; i1<child.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<child.length; j1++, j2++)
				child[i1][j1] = parent[i2][j2];
	}

	private static int[][] subMatrix(int[][] a, int[][] b){
		int n = a.length;
		int[][] c = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				c[i][j] = a[i][j]-b[i][j];
		return c;
	}

	private static void copyMatrix(int[][] child, int[][] parent, int iB, int jB) {
		for(int i1 = 0, i2=iB; i1<child.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<child.length; j1++, j2++)
				parent[i2][j2] = child[i1][j1];
	}

	private static int[][] sumMatrix(int[][] a, int[][] b){
		int n = a.length;
		int[][] c = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				c[i][j] = a[i][j]+b[i][j];
		return c;
	}

	public static void printMatrix(int[][] a, int col){
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<col; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}
