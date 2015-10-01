/**
 * 
 */
package greedy.knapsack;

import util.Util;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Item {
	
	private double benefit, weight, value;
	private double fractionOfItem;
	private String name;

	public Item(double benefit, double weight) {
		super();
		this.benefit = benefit;
		this.weight = weight;
		calculateValue();
		fractionOfItem = 0;
		this.name = "";
	}
	
	public Item(String name, double benefit, double weight) {
		super();
		this.name = name;
		this.benefit = benefit;
		this.weight = weight;
		calculateValue();
		fractionOfItem = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getFractionOfItem() {
		return fractionOfItem;
	}
	
	public void setFractionOfItem(double d) {
		this.fractionOfItem = d;
	}
	
	public double getValue() {
		return value;
	}
	
	public void calculateValue(){
		this.value = Util.trunkDouble(benefit/weight, 2);
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "["+name+": x="+fractionOfItem+", b="+benefit+", w="+weight+", value="+getValue()+"]";
	}

}
