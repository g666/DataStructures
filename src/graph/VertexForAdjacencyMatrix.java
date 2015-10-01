/**
 * 
 */
package graph;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class VertexForAdjacencyMatrix<E> extends Vertex<E>{
	
	private int matrixIndex;
	
	public VertexForAdjacencyMatrix() {
		super();
	}
	
	public int getMatrixIndex() {
		return matrixIndex;
	}
	
	public void setMatrixIndex(int matrixIndex) {
		this.matrixIndex = matrixIndex;
	}
		
}
