/**
 * 
 */
package tree;

import java.util.Comparator;

/**
 * Albero binario di ricerca.
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class BinarySearchTree <K, V> {

	protected TreeEntry<K, V> root;
	protected int size;
	protected Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comp) {
		this.comparator = comp;
		root = null;
		size = 0;
	}
	
	public final boolean isLeftChild(TreeEntry<K, V> c, TreeEntry<K, V> p){
		return p.getLeft()!=null && comparator.compare(p.getLeft().getKey(), c.getKey())==0;
	}

	public final boolean isRightChild(TreeEntry<K, V> c, TreeEntry<K, V> p){
		return p.getRight()!=null && comparator.compare(p.getRight().getKey(), c.getKey())==0;
	}

	public void insert(K key, V value){
		insert(new TreeEntry<K, V>(key, value));
	}

	public void insert(TreeEntry<K, V> entry){
		if(root==null)
		{
			root = entry;
		}
		else{
			K key = entry.getKey();
			V value = entry.getValue();
			boolean doneForEqualKey = false;
			TreeEntry<K, V> x = root;
			TreeEntry<K, V> y = null;
			while(x!=null && !doneForEqualKey ){
				y = x;
				if(comparator.compare(key, x.getKey())>0)
					x = x.getRight();
				else if(comparator.compare(key, x.getKey())<0)
					x = x.getLeft();
				else
				{
					doneForEqualKey = true;
					x.setValue(value);
				}
			}
			if(!doneForEqualKey)
			{
				if(y == null){
					y = root;
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
	}

	public void inorderTreeWalk(TreeEntry<K, V> t){
		if(t!=null)
		{
			inorderTreeWalk(t.getLeft());
			System.out.print(t.getKey()+" ");
			inorderTreeWalk(t.getRight());
		}
	}

	public void postOrderTreeWalk(TreeEntry<K, V> t){
		if(t!=null)
		{
			inorderTreeWalk(t.getLeft());
			inorderTreeWalk(t.getRight());
			System.out.print(t.getKey()+" ");
		}
	}

	public void preOrderTreeWalk(TreeEntry<K, V> t){
		if(t!=null)
		{
			System.out.print(t.getKey()+" ");
			inorderTreeWalk(t.getLeft());
			inorderTreeWalk(t.getRight());
		}
	}

	public TreeEntry<K, V> treeSearch(K key){
		TreeEntry<K, V> x = root;
		while(x!=null && comparator.compare(key, x.getKey())!=0){
			if(comparator.compare(key, x.getKey())<0)
				x = x.getLeft();
			else
				x = x.getRight();
		}
		return x;
	}

	public TreeEntry<K, V> minimum(TreeEntry<K, V> x){
		while(x.getLeft()!=null){
			x = x.getLeft();
		}
		return x;
	}

	public TreeEntry<K, V> maximum(TreeEntry<K, V> x){
		while(x.getRight()!=null){
			x = x.getRight();
		}
		return x;
	}

	public TreeEntry<K, V> successor(TreeEntry<K, V> x){
		if(x.getRight()!=null)
			return minimum(x.getRight());
		TreeEntry<K, V> y = x.getParent();
		while(y!=null && comparator.compare(x.getKey(), y.getRight().getKey())==0){
			x = y;
			y = y.getParent();
		}
		return y;
	}

	public TreeEntry<K, V> predecessor(TreeEntry<K, V> x){
		if(x.getLeft()!=null)
			return maximum(x.getLeft());
		TreeEntry<K, V> y = x.getParent();
		while(y!=null && x.equals(y.getLeft())){
			x = y;
			y = y.getParent();
		}
		return y;
	}

	public void delete(K key){
		TreeEntry<K, V> s = treeSearch(key);
		delete(s);
		size--;
	}

	public void delete(TreeEntry<K, V> s){
		TreeEntry<K, V> p = s.getParent();
		if(s!=null){
			if(s.getLeft()==null && s.getRight()==null){
				if(p!=null)
				{
					if(p.getLeft()!=null && comparator.compare(p.getLeft().getKey(), s.getKey())==0)
						p.setLeft(null);
					else
						p.setRight(null);
				}else if(comparator.compare(s.getKey(), root.getKey())==0)
					root = null;
			}else if(s.getLeft()!=null && s.getRight()==null)
			{
//				s = s.getLeft();
				s.setKey(s.getLeft().getKey());
				s.setValue(s.getLeft().getValue());
				s.setLeft(null);
			}
			else if(s.getRight()!=null && s.getLeft()==null)
			{
//				s = s.getRight();
				s.setKey(s.getRight().getKey());
				s.setValue(s.getRight().getValue());
				s.setRight(null);
			}
			else if(s.getLeft()!=null && s.getRight()!=null){
				TreeEntry<K, V> suc = successor(s);
				K temp = suc.getKey();
				V value = suc.getValue();
				suc.setKey(s.getKey());
				suc.setValue(s.getValue());
				s.setKey(temp);
				s.setValue(value);
				delete(suc);
			}
		}
	}

	public TreeEntry<K, V> getRoot() {
		return root;
	}

	public int getSize() {
		return size;
	}

	public void print(){
		System.out.println(root.toString());
	}
	
}
