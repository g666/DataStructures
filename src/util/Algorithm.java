/**
 * 
 */
package util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Algorithm <T extends Comparable<T>> {

	private T[][] matrix;
	private static int row, col;

	@SuppressWarnings("unchecked")
	public Algorithm(Class<T> clazz,
			T[]... rowObject) {
		if(rowObject.length==0)
			throw new IllegalStateException("The matrix must has any rows.");
		else{
			col = rowObject[0].length;
			matrix = (T[][]) Array.newInstance(clazz, rowObject.length, col);	
			row = rowObject.length;
			for(int i=0; i<rowObject.length; i++){
				T[] colObject = rowObject[i];
				if(colObject.length==col){
					for(int k=0; k<colObject.length; k++){
						matrix[i][k] = colObject[k];
					}
				}else
					throw new IllegalStateException("The numer of column is not equal for all columns.");
			}
		}

	}

	public List<MatEl<T>> getMaximun(){
		List<MatEl<T>> maxEl = new ArrayList<MatEl<T>>();
		for(int j=0; j<row; j++){
			T maxRow = matrix[j][0];
			for(int i=1; i<col; i++){
				T m = matrix[j][i];
				if(m.compareTo(maxRow)>0)
					maxRow = m;
			}
			
			for(int t=0; t<col; t++){
				T maxCol = matrix[0][t];
				for(int i=1; i<row; i++){
					T m = matrix[i][t];
					if(m.compareTo(maxCol)>0)
						maxCol = m;
				}
				if(maxCol.compareTo(maxRow)==0)
					maxEl.add(new MatEl<T>(maxCol, j, t));
			}
		}
		return maxEl;
		// deve restituire un array e un iteratore
	}

	public static void main(String[] args) {
		Algorithm<Integer> a = new Algorithm<Integer>(Integer.class,
				new Integer[]{4,5,8,6,74},
				new Integer[]{1,5,2,3,4},
				new Integer[]{5,7,8,9,6}
		);
		List<MatEl<Integer>> max = a.getMaximun();
		for(int i=0; i<max.size(); i++){
			MatEl<Integer> m = max.get(i);
			System.out.println(m.getElement()+" --> "+m.getRow()+" : "+m.getCol());
		}
	}
}
