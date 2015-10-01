/**
 * 
 */
package tree;

import java.util.Comparator;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 * @param <I>
 * @param <H>
 *
 */
public class IntervalTree<V> extends RedBlackTree<Entry<Integer, Integer>, V>{

	public IntervalTree() {
		super(new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> arg0,
					Entry<Integer, Integer> arg1) {
				return arg0.getKey().compareTo(arg1.getKey());
			}
		});
	}

	private void calculateMax(IntervalTreeEntry<V> i){
		if(i!=null){
			IntervalTreeEntry<V> l = i.getLeft();
			IntervalTreeEntry<V> r = i.getRight();
			int l_int = l!=null?l.getMaximumEndpoint():0;
			int r_int = r!=null?r.getMaximumEndpoint():0;
			i.setMaximumEndpoint(Math.max(i.getKey().getValue(), Math.max(l_int, r_int)));
		}
	}

	@Override
	public void leftRotate(TreeEntry<Entry<Integer, Integer>, V> t) {
		super.leftRotate(t);
		calculateMax((IntervalTreeEntry<V>) t);
		calculateMax((IntervalTreeEntry<V>) t.getParent());
	}

	@Override
	public void rightRotate(TreeEntry<Entry<Integer, Integer>, V> t) {
		super.rightRotate(t);
		calculateMax((IntervalTreeEntry<V>) t);
		calculateMax((IntervalTreeEntry<V>) t.getParent());
	}


	@Override
	public void insert(Entry<Integer, Integer> key, V value) {
		IntervalTreeEntry<V> entry = new IntervalTreeEntry<V>(key, value);
		calculateMax(entry);
		if(root==null)
		{
			root = entry;
		}
		else{
			boolean doneForEqualKey = false;
			IntervalTreeEntry<V> x = (IntervalTreeEntry<V>) root;
			IntervalTreeEntry<V> y = null;
			while(x!=null && !doneForEqualKey ){
				y = x;
				if(comparator.compare(key, x.getKey())>0)
				{
					IntervalTreeEntry<V> l = x.getLeft();
					int l_int = l!=null?l.getMaximumEndpoint():0;
					int r_int = entry!=null?entry.getMaximumEndpoint():0;
					x.setMaximumEndpoint(Math.max(x.getKey().getValue(), Math.max(l_int, r_int)));
					x = x.getRight();
				}
				else if(comparator.compare(key, x.getKey())<0)
				{
					IntervalTreeEntry<V> l = x.getRight();
					int l_int = l!=null?l.getMaximumEndpoint():0;
					int r_int = entry!=null?entry.getMaximumEndpoint():0;
					x.setMaximumEndpoint(Math.max(x.getKey().getValue(), Math.max(l_int, r_int)));
					x = x.getLeft();
				}
				else
				{
					doneForEqualKey = true;
					x.setValue(value);
				}
			}
			if(!doneForEqualKey)
			{
				if(y == null){
					y = (IntervalTreeEntry<V>) root;
				}
				entry.setParent(y);
				if(comparator.compare(key, y.getKey())>0){
					y.setRight(entry);
				}else{
					y.setLeft(entry);
				}
			}
		}
		size++;

		super.repairRBpropertyOnInsert(entry);
	}

	@Override
	public IntervalTreeEntry<V> treeSearch(Entry<Integer, Integer> i) {
		IntervalTreeEntry<V> x = (IntervalTreeEntry<V>) root;
		while(x!=null && !overlap(i, x.getKey())){
			if(x.getLeft()!=null && x.getLeft().getMaximumEndpoint()>=i.getKey())
				x = x.getLeft();
			else
				x = x.getRight();
		}
		return x;
	}

	public boolean overlap(Entry<Integer, Integer> i, Entry<Integer, Integer> x){
		return i.getKey()<=x.getValue() || x.getKey()>=i.getValue();
	}

}
