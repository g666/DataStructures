/**
 * 
 */
package util;

/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {

	private X x;

	private Y y;
	
	public Pair() {
	}

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getFirst() {
		return x;
	}

	public Y getSecond() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> other = (Pair<?, ?>) o;
			return (x.equals(other.x)) && (y.equals(other.y));
		}
		return false;
	}
}
