/**
 * 
 */
package greedy.activity_selection;

/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class Activity {

	private int startTime, finishTime;

	public Activity(int startTime, int finishTime) {
		super();
		this.startTime = startTime;
		this.finishTime = finishTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	
	@Override
	public String toString() {
		return "["+startTime+", "+finishTime+"]";
	}

}
