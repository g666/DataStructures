/**
 * 
 */
package tree;

import java.util.Comparator;


/**
 * Albero avl.
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class AVLTree<K, V> extends AugmentedBinaryTree<K, V> {

	public AVLTree(Comparator<K> comp) {
		super(comp);
	}

	@Override
	public void insert(K key, V value) {
		TreeEntryTagged<K, V> w = new TreeEntryTagged<K, V>(key, value);
		w.setStatus(1);
		super.insert(w);
		TreeEntryTagged<K, V> x = w.getParent();
		TreeEntryTagged<K, V> y = x!=null?x.getParent():null;
		TreeEntryTagged<K, V> z = y!=null?y.getParent():null;

		if(isViolatedAVLProperty(w))
			repairAVLProperty(x, y, z);

		x=z;
		y = x!=null?x.getParent():null;
		z = y!=null?y.getParent():null;

		// propagazione del ripristino della proprietà sulle altezze dei nodi
		while(isViolatedAVLProperty(x) && x!=null && comparator.compare(x.getKey(), root.getKey())!=0)
		{
			repairAVLProperty(x, y, z);
			x=z;
			y = x!=null?x.getParent():null;
			z = y!=null?y.getParent():null;
		}
	}

	private boolean isViolatedAVLProperty(TreeEntryTagged<K, V> alias){
		boolean rebuild = false;
		while(alias!=null && !rebuild){
			int hg = alias.getStatus()+1;
			alias = alias.getParent();
			if(alias!=null){
				alias.setStatus(Math.max(hg, 
						(alias.getLeft()!=null && alias.getRight()!=null)?
								Math.max(alias.getLeft().getStatus(), alias.getRight().getStatus()):
									alias.getLeft()!=null?alias.getLeft().getStatus():
										alias.getRight()!=null?alias.getRight().getStatus():0));
				if(alias.getLeft()!=null && alias.getRight()!=null)
				{
					rebuild = Math.abs(alias.getLeft().getStatus()-alias.getRight().getStatus())
							>1;
				}
			}
		}
		return rebuild;
	}

	private void repairAVLProperty(TreeEntryTagged<K, V> x, TreeEntryTagged<K, V> y,
			TreeEntryTagged<K, V> z){
		if(y!=null && z==null){
			if(isLeftChild(x, y))
				leftRotate(y);
			else
				rightRotate(y);
		}
		else if(y!=null && z!=null){
			if(isLeftChild(y, z) && isLeftChild(x, z))
				rightRotate(z);
			else if(isRightChild(y, z) && isRightChild(x, y))
				leftRotate(z);
			else if(isLeftChild(y, z) && isRightChild(x, y)){
				leftRotate(y);
				rightRotate(z);
			}
			else if(isRightChild(y, z) && isLeftChild(x, y)){
				rightRotate(y);
				leftRotate(z);
			}
		}
	}

	public void rightRotate(TreeEntryTagged<K, V> y){
		super.rightRotate(y);
		if(y.getLeft()!=null && y.getRight()!=null)
			y.setStatus(Math.max(y.getLeft().getStatus(), y.getRight().getStatus()) +1);
		else if(y.getLeft()!=null)
			y.setStatus(y.getLeft().getStatus() +1);
		else if(y.getRight()!=null)
			y.setStatus(y.getRight().getStatus() +1);
		else
			y.setStatus(1);

		TreeEntryTagged<K, V> x = y.getParent();
		if(x!=null){
			if(x.getLeft()!=null)
				x.setStatus(Math.max(y.getStatus(), x.getLeft().getStatus())+1);
			else
				x.setStatus(y.getStatus() +1);
		}
		x = x.getParent();
		while(x!=null){
			if(x.getLeft()!=null && x.getRight()!=null)
				x.setStatus(Math.max(x.getLeft().getStatus(), x.getRight().getStatus()) +1);
			else if(x.getLeft()!=null)
				x.setStatus(x.getLeft().getStatus() +1);
			else if(x.getRight()!=null)
				x.setStatus(x.getRight().getStatus() +1);
			else
				x.setStatus(1);
			x = x.getParent();
		}
	}

	public void leftRotate(TreeEntryTagged<K, V> x){
		super.leftRotate(x);
		if(x.getLeft()!=null && x.getRight()!=null)
			x.setStatus(Math.max(x.getLeft().getStatus(), x.getRight().getStatus()) +1);
		else if(x.getLeft()!=null)
			x.setStatus(x.getLeft().getStatus() +1);
		else if(x.getRight()!=null)
			x.setStatus(x.getRight().getStatus() +1);
		else
			x.setStatus(1);

		TreeEntryTagged<K, V> y = x.getParent();
		if(y!=null){
			if(y.getRight()!=null)
				y.setStatus(Math.max(x.getStatus(), y.getRight().getStatus())+1);
			else
				y.setStatus(x.getStatus() +1);
		}
		y = y.getParent();
		while(y!=null){
			if(y.getLeft()!=null && y.getRight()!=null)
				y.setStatus(Math.max(y.getLeft().getStatus(), y.getRight().getStatus()) +1);
			else if(y.getLeft()!=null)
				y.setStatus(y.getLeft().getStatus() +1);
			else if(y.getRight()!=null)
				y.setStatus(y.getRight().getStatus() +1);
			else
				y.setStatus(1);
			y = y.getParent();
		}
	}
	
	private void repairRoot(TreeEntryTagged<K, V> w){
		TreeEntryTagged<K, V> x, y, z;
		if(isViolatedAVLProperty(w.getLeft()))
		{
			x = w.getLeft();
			y = w;
			z = w.getRight();
			while(isViolatedAVLProperty(x) && x!=null && comparator.compare(x.getKey(), root.getKey())!=0)
			{
				repairAVLProperty(x, y, z);
				x=z;
				y = x!=null?x.getParent():null;
				z = y!=null?y.getParent():null;
			}
		}else if(isViolatedAVLProperty(w.getRight())){
			x = w.getRight();
			y = w;
			z = w.getLeft();
			while(isViolatedAVLProperty(x) && x!=null && comparator.compare(x.getKey(), root.getKey())!=0)
			{
				repairAVLProperty(x, y, z);
				x=z;
				y = x!=null?x.getParent():null;
				z = y!=null?y.getParent():null;
			}
		}
	}

	@Override
	public void delete(K key) {
		TreeEntryTagged<K, V> del = (TreeEntryTagged<K, V>) treeSearch(key);
		TreeEntryTagged<K, V> w = del.getParent();
		super.delete(del);
		TreeEntryTagged<K, V> z = null;
		while(w!=null){
			if(w.getLeft()!=null && w.getRight()!=null)
			{
				if(Math.abs(w.getLeft().getStatus()-w.getRight().getStatus())>1)
					z = w;
				w.setStatus(Math.max(w.getLeft().getStatus(), w.getRight().getStatus()) +1);
			}
			else if(w.getLeft()!=null)
				w.setStatus(w.getLeft().getStatus() +1);
			else if(w.getRight()!=null)
				w.setStatus(w.getRight().getStatus() +1);
			else
				w.setStatus(1);
			w = w.getParent();
		}
		w = del.getParent();
		z = z==null?w:z;
		TreeEntryTagged<K, V> y=null, x;
		if(z!=null){
			if(z.getLeft()!=null && z.getRight()!=null){
				if(comparator.compare(z.getLeft().getKey(), z.getRight().getKey())>=0)
					y = z.getLeft();
				else
					y = z.getRight();
			}else{
				if(z.getLeft()!=null)
					y = z.getLeft();
				else
					y = z.getRight();
			}
		}

		if(y!=null){
			if(y.getLeft()!=null && y.getRight()!=null){
				if(comparator.compare(y.getLeft().getKey(), y.getRight().getKey())>=0)
					x = y.getLeft();
				else
					x = y.getRight();
			}else{
				if(y.getLeft()!=null)
					x = y.getLeft();
				else
					x = y.getRight();
			}

			repairAVLProperty(x, y, z);

			// propagazione del ripristino della proprietà sulle altezze dei nodi
			while(isViolatedAVLProperty(x) && x!=null && comparator.compare(x.getKey(), root.getKey())!=0)
			{
				repairAVLProperty(x, y, z);
				x=z;
				y = x!=null?x.getParent():null;
				z = y!=null?y.getParent():null;
			}
		}

		if(w==null)
			repairRoot((TreeEntryTagged<K, V>) root);
	}
	
}
