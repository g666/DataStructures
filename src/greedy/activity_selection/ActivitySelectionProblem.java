/**
 * 
 */
package greedy.activity_selection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import list.AbstractList;
import list.Pointer;
import list.SimpleList;
import recursion.DynamicQuicksort;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class ActivitySelectionProblem {

	public static Scheduler taskSchedule(AbstractList<Activity> activities) throws Exception{
		DynamicQuicksort.sort(activities.getHead(), new Comparator<Activity>() {

			@Override
			public int compare(Activity arg0, Activity arg1) {
				return arg0.getFinishTime()-arg1.getFinishTime();
			}
		}, true);
		Pointer<Activity> rif = activities.getHead();
		Pointer<Activity> p = rif.getNext();
		Scheduler s = new Scheduler();
		s.addActivity(rif.element());
		while(p!=null){
			Activity arif = rif.element();
			Activity a = p.element();
			if(a.getStartTime()>=arif.getFinishTime())
			{
				s.addActivity(a);
				rif = p;
				p = p.getNext();
			}
			else
				p = p.getNext();
		}
		return s;

	}

	public static List<Scheduler> taskScheduleUsingMinimumNumberOfMachines(AbstractList<Activity> activities) throws Exception{
		DynamicQuicksort.sort(activities.getHead(), new Comparator<Activity>() {

			@Override
			public int compare(Activity arg0, Activity arg1) {
				return arg0.getFinishTime()-arg1.getFinishTime();
			}
		}, true);
		Pointer<Activity> rif = activities.getHead();
		Pointer<Activity> p = rif.getNext();
		Scheduler s = new Scheduler();
		s.addActivity(rif.element());
		List<Scheduler> list = new ArrayList<Scheduler>();
		list.add(s);
		while(p!=null){
			Activity arif = rif.element();
			Activity a = p.element();
			if(a.getStartTime()>=arif.getFinishTime())
			{
				s.addActivity(a);
				rif = p;
				p = p.getNext();
			}
			else
			{
				boolean found = false;
				for(int i=0; i<list.size() && !found; i++)
				{
					Scheduler sc = list.get(i);
					Activity lastAct = sc.getActivities().get(sc.getActivities().size());
					if(a.getStartTime()>=lastAct.getFinishTime())
					{
						sc.addActivity(a);
						rif = p;
						p = p.getNext();
						found = true;
						s = sc;
					}		
				}
				if(!found){
					Scheduler sc = new Scheduler();
					sc.addActivity(a);
					list.add(sc);
					p = p.getNext();
				}
			}
		}
		return list;

	}

	public static void main(String[] args) throws Exception {
		SimpleList<Activity> sl = new SimpleList<Activity>();
				sl.add(new Activity(3, 7));
				sl.add(new Activity(2, 5));
				sl.add(new Activity(1, 3));
				sl.add(new Activity(6, 9));
				sl.add(new Activity(4, 7));
				sl.add(new Activity(1, 4));
				sl.add(new Activity(7, 8));
		//		Scheduler s = ActivitySelectionProblem.taskSchedule(sl);
		//		System.out.println(s);
		List<Scheduler> l = ActivitySelectionProblem.taskScheduleUsingMinimumNumberOfMachines(sl);
		for(int i=0; i<l.size(); i++){
			System.out.println(l.get(i));
		}
	}
}
