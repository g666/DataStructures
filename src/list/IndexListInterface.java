/**
 * 
 */
package list;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public interface IndexListInterface<E> extends SimpleListInterface<E> {

	/**
	 * Aggiunge un elemento in posizione <code>index</code>.</br>
	 * L'elemento che precedentemente occupava tale posizione viene spostato in index+1;
	 * 
	 * @param t
	 * 		L'elemento da inserire.
	 * @param index
	 * 		<code>[1, n]</code>. Se <code>index>n</code> l'elemento viene inserito in fondo alla lista.
	 * @throws IndexOutOfBoundsException
	 * 		<code>index<1</code>.
	 * @throws Exception 
	 */
	public void add(E t, int index) throws IndexOutOfBoundsException, Exception;

	/**
	 * Rimuove un elemento in posizione <code>index</code>.
	 * 
	 * @param index
	 * 		<code>[1, n]</code>. Se <code>index>n</code> viene eliminato l'elemento in fondo alla lista.
	 * @throws IndexOutOfBoundsException
	 *  	<code>index<1</code>.
	 * @throws Exception 
	 */
	public void delete(int index) throws IndexOutOfBoundsException, Exception;

	/**
	 * @param index
	 * 		<code>[1, n]</code>.
	 * @throws IndexOutOfBoundsException
	 *  	<code>index<1 </code>.
	 * @return
	 * 		L'elemento in posizione <code>index</code> nella lista.
	 */
	public E get(int index) throws IndexOutOfBoundsException;
}
