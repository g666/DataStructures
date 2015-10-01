/**
 * 
 */
package tree;




/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class IntervalTreeEntry<V> extends TreeEntryTagged<Entry<Integer, Integer>, V>{

	private int maximumEndpoint;
	
	public IntervalTreeEntry(Entry<Integer, Integer> key, V value) {
		super(key, value);
	}
	
	
	public int getMaximumEndpoint() {
		return maximumEndpoint;
	}
	
	public void setMaximumEndpoint(int maximumEndpoint) {
		this.maximumEndpoint = maximumEndpoint;
	}
	
	@Override
	public IntervalTreeEntry<V> getLeft() {
		return (IntervalTreeEntry<V>) super.getLeft();
	}
	
	@Override
	public IntervalTreeEntry<V> getParent() {
		return (IntervalTreeEntry<V>) super.getParent();
	}
	
	@Override
	public IntervalTreeEntry< V> getRight() {
		return (IntervalTreeEntry< V>) super.getRight();
	}

	private String getspace(int num){
		String s="";
		for(int i=0; i<num; i++)
			s+=" ";
		return s;
	}

	private String print(IntervalTreeEntry<V> t, int level){
		String s = "";
		if(t!=null)
		{
			s+=print((IntervalTreeEntry<V>) t.getRight(), level+5);
			s+="\n"+getspace(level)+"-> "+t.getStatus()+
					"[low: "+t.getKey().getKey()+", high: "+t.getKey().getValue()+", max: "+t.getMaximumEndpoint()+"]";
			s+=print((IntervalTreeEntry<V>) t.getLeft(), level+5);
		}
		return s;
	}

	@Override
	public String toString() {
		return print(this, 1);
	}

}
