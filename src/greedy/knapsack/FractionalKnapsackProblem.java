/**
 * 
 */
package greedy.knapsack;

import java.util.Comparator;

import list.AbstractList;
import list.Pointer;
import list.SimpleList;
import recursion.DynamicQuicksort;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class FractionalKnapsackProblem {

	public static void fractionalKnapsack(AbstractList<Item> items, int totalWeight){
		DynamicQuicksort.sort(items.getHead(), new Comparator<Item>() {
			@Override
			public int compare(Item arg0, Item arg1) {
				return Double.compare(arg1.getValue(), arg0.getValue());
			}
		}, true);
		Pointer<Item> p = items.getHead();
		int w = 0;
		while(p!=null && w<totalWeight){
			Item it = p.element();
			it.setFractionOfItem(Math.min(it.getWeight(), totalWeight-w));
			w += Math.min(it.getWeight(), totalWeight-w);
			p = p.getNext();
		}
	}
	
	public static void main(String[] args) throws Exception {
		SimpleList<Item> sl = new SimpleList<Item>();
		sl.add(new Item("item 2", 32, 8));
		sl.add(new Item("item 3", 40, 2));
		sl.add(new Item("item 5", 50, 1));
		sl.add(new Item("item 4", 30, 6));
		sl.add(new Item("item 1", 12, 4));
		FractionalKnapsackProblem.fractionalKnapsack(sl, 10);
		System.out.println(sl);
	}
	
}
