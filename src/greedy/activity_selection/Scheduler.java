/**
 * 
 */
package greedy.activity_selection;

import list.IndexList;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Scheduler {

	private IndexList<Activity> act;
	
	public Scheduler() {
		act = new IndexList<Activity>();
	}
	
	public void addActivity(Activity a) throws Exception{
		act.add(a, act.size()+1);
	}
	
	public void removeActivity(Activity a) throws Exception{
		act.remove(a);
	}
	
	public IndexList<Activity> getActivities() {
		return act;
	}
	
	@Override
	public String toString() {
		String s = "";
		int i=1;
		while(i<=act.size()){
			s=s+act.get(i).toString();
			i++;
		}
		return s;
	}
}
