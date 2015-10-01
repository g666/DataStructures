/**
 * 
 */
package tree;

import java.util.Comparator;

/**
 * Albero rosso-nero.
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class RedBlackTree<K, V> extends AugmentedBinaryTree<K, V>{

	private static final int RED = 0;
	private static final int BLACK = 1;

	public RedBlackTree(Comparator<K> comp) {
		super(comp);
	}

	public final void repairRBpropertyOnInsert(TreeEntryTagged<K, V> x){
		if(comparator.compare(x.getKey(), root.getKey())==0)
			((TreeEntryTagged<K, V>) root).setStatus(BLACK);
		else
		{
			x.setStatus(RED);
			TreeEntryTagged<K, V> n = x.getParent()!=null?x.getParent().getParent():null;
			while(comparator.compare(x.getKey(), root.getKey())!=0 
					&& x.getParent()!=null && x.getParent().getStatus()==RED 
					&& n!=null){
				if(n.getLeft()!=null && comparator.compare(x.getParent().getKey(), n.getLeft().getKey())==0){
					TreeEntryTagged<K, V> y = x.getParent().getParent().getRight();
					if(y!=null && y.getStatus()==RED){
						x.getParent().setStatus(BLACK);
						y.setStatus(BLACK);
						n.setStatus(RED);
						x = n;
						n = x.getParent()!=null?x.getParent().getParent():null;
					}else{
						if(x.getParent().getRight()!=null &&
								comparator.compare(x.getKey(), x.getParent().getRight().getKey())==0){
							x = x.getParent();
							leftRotate(x);
						}
						x.getParent().setStatus(BLACK);
						n.setStatus(RED);
						rightRotate(n);
					}
				}else{
					TreeEntryTagged<K, V> y = n.getLeft();
					if(y!=null && y.getStatus()==RED){
						x.getParent().setStatus(BLACK);
						y.setStatus(BLACK);
						n.setStatus(RED);
						x = x.getParent().getParent();
						n = x.getParent()!=null?x.getParent().getParent():null;
					}else{
						if(x.getParent().getLeft()!=null && 
								comparator.compare(x.getKey(), x.getParent().getLeft().getKey())==0){
							x = x.getParent();
							rightRotate(x);
						}
						x.getParent().setStatus(BLACK);
						n.setStatus(RED);
						leftRotate(n);
					}
				}
			}
			((TreeEntryTagged<K, V>)root).setStatus(BLACK);
		}
	}

	@Override
	public void insert(K key, V value) {
		TreeEntryTagged<K, V> x = new TreeEntryTagged<K, V>(key, value);
		super.insert(x);
		repairRBpropertyOnInsert(x);
	}

	public void print(){
		if(root!=null)
			System.out.println(root.toString());
		else
			System.out.println("rbtree null");
	}

	@Override
	public void delete(K key) {
		TreeEntryTagged<K, V> z = (TreeEntryTagged<K, V>) treeSearch(key);
		TreeEntryTagged<K, V> y = (TreeEntryTagged<K, V>) ((z.getLeft()==null || z.getRight()==null)?z:
			successor(z));
		TreeEntryTagged<K, V> x = (y.getLeft()!=null)?y.getLeft():(y.getRight()!=null)?y.getRight():y;
		x.setParent(y.getParent());
		if(y.getParent()==null)
			root = x;
		else if(comparator.compare(y.getKey(), y.getParent().getLeft().getKey())==0)
			y.getParent().setLeft(x);
		else
			y.getParent().setRight(x);
		if(comparator.compare(y.getKey(), z.getKey())!=0)
			z.setKey(y.getKey());
		//super.delete(z);
		if(root!=null && y.getStatus()==BLACK)
			repairRBpropertyOnDelete(x);
		
		((TreeEntryTagged<K, V>)root).setStatus(BLACK);
	}

	private void repairRBpropertyOnDelete(TreeEntryTagged<K, V> x) {
		while(comparator.compare(x.getKey(), root.getKey())!=0 
				&& x.getStatus()==BLACK ){
			if(comparator.compare(x.getKey(), x.getParent().getLeft().getKey())==0){
				TreeEntryTagged<K, V> w = x.getParent().getRight();
				if(w.getStatus() == RED){
					w.setStatus(BLACK);
					x.getParent().setStatus(RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().getStatus()==BLACK && w.getRight().getStatus()==BLACK){
					w.setStatus(RED);
					x = x.getParent();
				}
				else if(w.getRight().getStatus()==BLACK){
					w.getLeft().setStatus(BLACK);
					w.setStatus(RED);
					rightRotate(w);
					w = x.getParent().getRight();
				}

				w.setStatus(x.getParent().getStatus());
				x.getParent().setStatus(BLACK);
				w.getRight().setStatus(BLACK);
				leftRotate(x.getParent());
				x = (TreeEntryTagged<K, V>) root;
			}else{
				TreeEntryTagged<K, V> w = x.getParent().getLeft();
				if(w.getStatus() == RED){
					w.setStatus(BLACK);
					x.getParent().setStatus(RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if(w.getRight().getStatus()==BLACK && w.getLeft().getStatus()==BLACK){
					w.setStatus(RED);
					x = x.getParent();
				}
				else if(w.getLeft().getStatus()==BLACK){
					w.getRight().setStatus(BLACK);
					w.setStatus(RED);
					leftRotate(w);
					w = x.getParent().getLeft();
				}

				w.setStatus(x.getParent().getStatus());
				x.getParent().setStatus(BLACK);
				w.getLeft().setStatus(BLACK);
				rightRotate(x.getParent());
				x = (TreeEntryTagged<K, V>) root;
			}
			x.setStatus(BLACK);
		}
	}

	public static void main(String[] args) {
		RedBlackTree<Integer, Integer> r = new RedBlackTree<Integer, Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		});
		r.insert(15, 4);
		r.insert(7, 4);
		r.insert(10, 4);
		r.insert(30, 4);
		r.insert(20, 4);
		r.insert(35, 4);
		r.insert(45, 4);
		r.insert(70, 4);
		r.delete(30);
		r.print();
	}

}
