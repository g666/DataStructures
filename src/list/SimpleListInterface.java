/**
 * 
 */
package list;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public interface SimpleListInterface<E> extends DynamicList<E> {

	/**
	 * Aggiunge un elemento in testa alla lista.
	 * 
	 * @param t
	 * 		L'elemento da aggiungere alla lista.
	 * @throws Exception 
	 */
	public void add(E t) throws Exception;

	/**
	 * Rimuove l'elemento in testa alla lista.
	 * 
	 * @throws NullPointerException
	 * 		Non vi sono elementi nella lista.
	 * @throws Exception 
	 */
	public void delete() throws NullPointerException, Exception;
}
