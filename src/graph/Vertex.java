/**
 * 
 */
package graph;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Vertex<E> extends PositionAwareType<E>{

	public static final int WHITE = 0;
	public static final int GREY = 1;
	public static final int BLACK = 2;
	
	private int distance, color, finishingTime;
	private double key;
	private double weight = 0;
	
	public Vertex() {
		init();
	}
	
	public void init(){
		color = WHITE;
		distance = 0;
		finishingTime = 0;
		key = 0;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getKey() {
		return key;
	}
	
	public void setKey(double key) {
		this.key = key;
	}

	public int getFinishingTime() {
		return finishingTime;
	}

	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}

	public void setInfo(E info) {
		this.info = info;
		distance = 0;
		color = WHITE;
	}
	
	public int getColor() {
		return color;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return info.toString()+"[distance="+
				distance+"; "+"finishingTime="+finishingTime+"; "+"key="+key+"; "+
				"color="+(color==WHITE?"WHITE":color==BLACK?"BLACK":GREY)+"]";
	}
	
}
