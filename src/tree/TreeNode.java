/**
 * 
 */
package tree;

import java.util.ArrayList;
import java.util.List;




/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class TreeNode<V> {

	private V value;
	private TreeNode<V> parent;
	private List<TreeNode<V>> sons;

	public TreeNode(V value, TreeNode<V> parent) {
		super();
		this.value = value;
		this.parent = parent;
		sons = new ArrayList<TreeNode<V>>();
	}

	public TreeNode<V> addSon(V v){
		if(!v.equals(value)){
			TreeNode<V> node = new TreeNode<V>(v, this);
			sons.add(node);
			return node;
		}else
			return this;
	}
	
	public TreeNode<V> getRoot(){
		TreeNode<V> t = this;
		while(t.getParent()!=null)
			t = t.getParent();
		
		return t;
	}

	public TreeNode<V> getParent() {
		return parent;
	}

	public void setParent(TreeNode<V> parent) {
		this.parent = parent;
	}

	public List<TreeNode<V>> getSons() {
		return sons;
	}

	public void setSons(List<TreeNode<V>> sons) {
		this.sons = sons;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	private String getspace(int num){
		String s="";
		for(int i=0; i<num; i++)
			s+=" ";
		return s;
	}

	private String print(TreeNode<V> t, int level){
		String s = "";
		if(t!=null)
		{
			s+="\n"+getspace(level)+"-> "+t.getValue();
			List<TreeNode<V>> sns = t.getSons();
			for(int i=0; i<sns.size(); i++)
				s+=print(sns.get(i), level+5);
		}
		return s;
	}

	@Override
	public String toString() {
		return print(this, 1);
	}
	
}
