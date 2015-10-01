package list;


/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public interface PositionListInterface<E, P extends Position<E>>  {

	public P first() throws Exception;

	public P last() throws Exception;

	public P next(P p) throws Exception ;

	public P prev(P p) throws Exception ;

	public  P addFirst(E e) throws Exception;

	public  P addLast(E e);

	public  P addAfter(P p, E e) throws Exception ;

	public  P addBefore(P p, E e) throws Exception ;

	public E remove(P p) throws Exception ;

	public E set(P p, E e) throws Exception ;
	
	public boolean isFirst(P p) throws Exception ;

	public boolean isLast(P p) throws Exception ;

	public void swapElements(P a, P b) throws Exception ;

}