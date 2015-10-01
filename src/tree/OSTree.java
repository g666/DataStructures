/**
 * 
 */
package tree;

import java.util.Comparator;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class OSTree<K, V> extends RedBlackTree<K, V> {

	public OSTree(Comparator<K> comp) {
		super(comp);
	}

	private OSTreeEntry<K, V> osSelect(OSTreeEntry<K, V> x, int i){
		OSTreeEntry<K, V> left = x.getLeft();
		OSTreeEntry<K, V> right = x.getRight();
		try {
			int r = left.getSize()+1;
			if(i==r)
				return x;
			else if(i<r)
				return osSelect(left, i);
			else 
				return osSelect(right, i-r);
		} catch (Exception e) {
			if(right!=null)
			{
				if(right.getSize()==(i-1))
					return right;
				else return x;
			}
			else if(x!=null && x.getSize()==i)
				return x;
			else return null;
		}
	}

	@Override
	public void insert(K key, V value) {
		OSTreeEntry<K, V> entry = new OSTreeEntry<K, V>(key, value);
		if(root==null)
		{
			root = entry;
		}
		else{
			boolean doneForEqualKey = false;
			OSTreeEntry<K, V> x = (OSTreeEntry<K, V>) root;
			OSTreeEntry<K, V> y = null;
			while(x!=null && !doneForEqualKey ){
				y = x;
				if(comparator.compare(key, x.getKey())>0)
				{
					x.setSize(x.getSize()+1);
					x = x.getRight();
				}
				else if(comparator.compare(key, x.getKey())<0)
				{
					x.setSize(x.getSize()+1);
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
					y = (OSTreeEntry<K, V>) root;
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
	public void delete(K key) {
		// TODO Auto-generated method stub
		super.delete(key);
	}

	@Override
	public void leftRotate(TreeEntry<K, V> t) {
		OSTreeEntry<K, V> x = (OSTreeEntry<K, V>) t;
		OSTreeEntry<K, V> y = (OSTreeEntry<K, V>) x.getRight();
		if(y!=null)
			y.setSize(x.getSize());
		super.leftRotate(t);
		int r_size=0;
		try {
			r_size = x.getRight().getSize();
		} catch (Exception e) {
			r_size = 0;
		}
		int l_size=0;
		try {
			l_size = x.getLeft().getSize();
		} catch (Exception e) {
			l_size = 0;
		}

		x.setSize(l_size + r_size + 1);
	}

	@Override
	public void rightRotate(TreeEntry<K, V> t) {
		OSTreeEntry<K, V> y = (OSTreeEntry<K, V>) t;
		OSTreeEntry<K, V> x = (OSTreeEntry<K, V>) y.getLeft();
		if(x!=null)
			x.setSize(y.getSize());
		super.rightRotate(t);
		int r_size=0;
		try {
			r_size = y.getRight().getSize();
		} catch (Exception e) {
			r_size = 0;
		}
		int l_size=0;
		try {
			l_size = y.getLeft().getSize();
		} catch (Exception e) {
			l_size = 0;
		}

		y.setSize(l_size + r_size + 1);
	}

	public OSTreeEntry<K, V> osSelect(int i){
		if(i<=0 || i>size)
			return null;
		else
			return osSelect((OSTreeEntry<K, V>) root, i);
	}

	public int osRank(OSTreeEntry<K, V> x){
		OSTreeEntry<K, V> left = x.getLeft();
		if(left!=null){
			try {
				int r = left.getSize()+1;
				OSTreeEntry<K, V> y = x;
				while(comparator.compare(y.getKey(), root.getKey())!=0){
					OSTreeEntry<K, V> p = y.getParent().getRight();
					if(p!=null && comparator.compare(y.getKey(), p.getKey())==0)
						r = r + y.getParent().getLeft().getSize()+1;
					y = y.getParent();
				}
				return r;
			} catch (Exception e) {
				return -1;
			}
		}else
		{
			if(isLeftChild(x, x.getParent()))
				return osRank(x.getParent())-1;
			else
				return osRank(x.getParent())+1;
		}
	}

	public int osRank(K key){
		OSTreeEntry<K, V> e = (OSTreeEntry<K, V>) treeSearch(key);
		return e!=null?osRank(e):-1;
	}

}
