/**
 * 
 */
package graph;



/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Edge<E, V> extends PositionAwareType<E>{

	public static final String TREE = "Tree";
	public static final String UNDEFINED = "Undefined";
	public static final String BACK = "Back";
	public static final String FORWARD = "Forward";
	public static final String CROSS = "Cross";
	
	private String type;
	private Vertex<V> source, destination;
	private double weight = 0;
	
	public Edge() {
		type = UNDEFINED;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Edge(Vertex<V> source, Vertex<V> destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	
	public Vertex<V> getDestination() {
		return destination;
	}
	
	public Vertex<V> getSource() {
		return source;
	}
	
	public void setSource(Vertex<V> source) {
		this.source = source;
	}
	
	public void setDestination(Vertex<V> destination) {
		this.destination = destination;
	}
	
	@Override
	public String toString() {
		return "{"+source.toString()+"} --> "+(info==null?
				getClass().getSimpleName():info.toString())+" [type="+type+"]"+" --> {"+
				destination.toString()+"}";
	}
	
}
