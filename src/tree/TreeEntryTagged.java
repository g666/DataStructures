/**
 * 
 */
package tree;



/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class TreeEntryTagged<K, V> extends TreeEntry<K, V>{

	/**
	 * Nel {@link RedBlackTree} indica se un nodo è rosso o nero, mentre
	 * nel {@link AVLTree} indica l'altezza del nodo.
	 */
	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int color) {
		this.status = color;
	}

	public TreeEntryTagged(K key, V value) {
		super(key, value);
	}

	private String getspace(int num){
		String s="";
		for(int i=0; i<num; i++)
			s+=" ";
		return s;
	}

	public TreeEntryTagged<K, V> getLeft() {
		return (TreeEntryTagged<K, V>) super.getLeft();
	}

	public void setLeft(TreeEntry<K, V> left) {
		super.setLeft(left);
	}

	public TreeEntryTagged<K, V> getRight() {
		return (TreeEntryTagged<K, V>) super.getRight();
	}

	public void setRight(TreeEntry<K, V> right) {
		super.setRight(right);
	}

	public TreeEntryTagged<K, V> getParent() {
		return (TreeEntryTagged<K, V>) super.getParent();
	}

	public void setParent(TreeEntry<K, V> parent) {
		super.setParent(parent);
	}

	private String print(TreeEntryTagged<K, V> t, int level){
		String s = "";
		if(t!=null)
		{
			s+=print((TreeEntryTagged<K, V>) t.getRight(), level+5);
			s+="\n"+getspace(level)+"-> "+t.getKey()+" ("+t.getStatus()+") {"+t.getValue()+"}";
			s+=print((TreeEntryTagged<K, V>) t.getLeft(), level+5);
		}
		return s;
	}

	@Override
	public String toString() {
		return print(this, 1);
	}

}
