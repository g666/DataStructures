/**
 * 
 */
package tree;

import java.util.Comparator;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public abstract class AugmentedBinaryTree<K, V> extends BinarySearchTree<K, V> {

	public AugmentedBinaryTree(Comparator<K> comp) {
		super(comp);
	}
	
	public void swapChildren(K key){
		TreeEntry<K, V> t = treeSearch(key);
		if(t!=null && t.getLeft()!=null && t.getRight()!=null){
			TreeEntry<K, V> l  =t.getLeft();
			TreeEntry<K, V> r = t.getRight();
			t.setLeft(r);
			t.setRight(l);
		}
	}
	
	public void leftRotate(TreeEntry<K, V> t){
		TreeEntry<K, V> p = t.getParent();
		TreeEntry<K, V> l = t.getRight();
		if(l!=null){
			TreeEntry<K, V> l_l = null;
			if(l!=null)
				l_l = l.getLeft();
			t.setRight(l_l);
			l.setLeft(t);
			l.setParent(p);
			t.setParent(l);
			if(p!=null){
				if(comparator.compare(p.getLeft().getKey(), t.getKey())==0)
					p.setLeft(l);
				else
					p.setRight(l);
			}else
				root=l;
		}
	}
	
	public void rightRotate(TreeEntry<K, V> t){
		TreeEntry<K, V> p = t.getParent();
		TreeEntry<K, V> l = t.getLeft();
		if(l!=null){
			TreeEntry<K, V> l_r = null;
			if(l!=null)
				l_r = l.getRight();
			t.setLeft(l_r);
			l.setRight(t);
			l.setParent(p);
			t.setParent(l);
			if(p!=null){
				if(p.getLeft()!=null && t!=null && comparator.compare(p.getLeft().getKey(), t.getKey())==0)
					p.setLeft(l);
				else
					p.setRight(l);
			}else
				root = l;
		}
	}
	
	public void leftRotate(K key){
		leftRotate(treeSearch(key));
	}

	public void rightRotate(K key){
		rightRotate(treeSearch(key));
	}

}
