/**
 * 
 */
package recursion;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class MatrixAlgorithm {

	public static int[][] kFolding(int k, int[][] a, int a_colLength){
		if(a_colLength == a.length){
			if(k>1){
				int[][] c = prod(a, a_colLength, a, a_colLength);
				for(int i=2; i<k; i++){
					c = prod(a, a_colLength, c, a_colLength);
				}
				return c;
			}else
				return a;
		}else
			return null;
	}

	public static int[][] chiusuraTransitiva(int[][] m, int col){
		int[][] c = new int[m.length][col];
		for(int i=0; i<m.length; i++)
			for(int j=0; j<col; j++){
				if((i==j) || m[i][j]==1)
					c[i][j]=m[i][j];
				else
					c[i][j]=0;
			}
		for(int k=0; k<m.length; k++)
			for(int i=0; i<m.length; i++)
				for(int j=0; j<m.length; j++)
					c[i][j] = c[i][j]==1 || (c[i][k]==1 && c[k][j]==1)?1:0;

		return c;
	}

	private static int[][] prod(int[][] a, int a_colLength, int[][] b, int b_colLength){
		int a_length = a.length;
		int b_length = b.length;
		if(a_colLength==b_length){
			int[][] c = new int[a_length][b_colLength];

			for(int i=0; i<a_length; i++){
				for(int j=0; j<b_colLength; j++){
					int sum = 0;
					for(int k=0; k<a_colLength; k++){
						sum+=a[i][k]*b[k][j];
					}
					c[i][j]=sum;
				}
			}			
			return c;
		}else
			return null;
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
