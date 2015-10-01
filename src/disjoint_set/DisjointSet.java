/**
 * 
 */
package disjoint_set;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public interface DisjointSet<E> {
	
	public abstract PointerSet<E> makeSet(E element) throws Exception;
	
	public abstract void union(PointerSet<E> x, PointerSet<E> y) throws Exception;
	
	public abstract DisjointSet<E> findSet(PointerSet<E> p);

}
