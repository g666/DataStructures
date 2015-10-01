/**
 * 
 */
package tree;



/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class OSTreeEntry<K, V> extends TreeEntryTagged<K, V>{

	private int size = 1;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public OSTreeEntry(K key, V value) {
		super(key, value);
	}

	private String getspace(int num){
		String s="";
		for(int i=0; i<num; i++)
			s+=" ";
		return s;
	}

	public OSTreeEntry<K, V> getLeft() {
		return (OSTreeEntry<K, V>) super.getLeft();
	}

	public OSTreeEntry<K, V> getRight() {
		return (OSTreeEntry<K, V>) super.getRight();
	}

	public OSTreeEntry<K, V> getParent() {
		return (OSTreeEntry<K, V>) super.getParent();
	}

	private String print(OSTreeEntry<K, V> t, int level){
		String s = "";
		if(t!=null)
		{
			s+=print((OSTreeEntry<K, V>) t.getRight(), level+5);
			s+="\n"+getspace(level)+"-> "+t.getKey()+" (size: "+t.getSize()
//					+", status: "+t.getStatus()
					+")";
			s+=print((OSTreeEntry<K, V>) t.getLeft(), level+5);
		}
		return s;
	}

	@Override
	public String toString() {
		return print(this, 1);
	}

}
