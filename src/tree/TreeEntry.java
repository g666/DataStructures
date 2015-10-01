/**
 * 
 */
package tree;




/**
 * Un generico nodo di un albero.
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class TreeEntry<K, V> extends Entry<K, V>{

//	private K key;
//	private V value;
	private TreeEntry<K, V> left, right, parent;

//	public K getKey() {
//		return key;
//	}
//
//	public void setKey(K key) {
//		this.key = key;
//	}

//	public V getValue() {
//		return value;
//	}
//
//	public void setValue(V value) {
//		this.value = value;
//	}

	public TreeEntry<K, V> getLeft() {
		return left;
	}

	public void setLeft(TreeEntry<K, V> left) {
		this.left = left;
	}

	public TreeEntry<K, V> getRight() {
		return right;
	}

	public void setRight(TreeEntry<K, V> right) {
		this.right = right;
	}

	public TreeEntry<K, V> getParent() {
		return parent;
	}

	public void setParent(TreeEntry<K, V> parent) {
		this.parent = parent;
	}

	public TreeEntry(K key, V value) {
		super(key, value);
	}

	private String getspace(int num){
		String s="";
		for(int i=0; i<num; i++)
			s+=" ";
		return s;
	}

	private String print(TreeEntry<K, V> t, int level){
		String s = "";
		if(t!=null)
		{
			s+=print(t.getRight(), level+5);
			s+="\n"+getspace(level)+"-> "+t.getKey()+" ("+t.getValue()+")";
			s+=print(t.getLeft(), level+5);
		}
		return s;
	}

	@Override
	public String toString() {
		return print(this, 1);
	}

}
