package com.java.prad.planner;
/**
 * Model class used as Object 
 * 
 * @author Pradeep Lekkala
 */
public class Activity {

	String activity;
	
	String timeTaken;
	
	Double timeChunk;
	
	String startTime;
	
	String endTime;
	
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Activity(String activity, String timeTaken) {
		super();
		this.activity = activity;
		this.timeTaken = timeTaken;
	}

	

	

	
	
	
	/*@Override
	public boolean equals (Object obj) {
		Activity a = (Activity) obj;
        if (a.startTime==(startTime))
        	return true;
        return false;
    }*/

	

	
	public void setTimeChunk(Double timeChunk) {
		this.timeChunk = timeChunk;
	}

	public Activity(String activity, String timeTaken, Double timeChunk, String startTime, String endTime) {
		
		this.activity = activity;
		this.timeTaken = timeTaken;
		this.timeChunk = timeChunk;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	@Override
	public String toString() {
		return "Activity [activity=" + activity + ", timeTaken=" + timeTaken + ", timeChunk=" + getTimeChunk()
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	public  int getTimeChunk(){
		Double time =0D;
		String taskTime = this.timeTaken;
		if(taskTime!=null && taskTime.length()>0){
			if(taskTime.equals("sprint")){
				 time = 15D;
			}
			else
				time = Double.parseDouble(taskTime.replace("min", ""));
			
		}
		
		//System.out.println("time:"+time.intValue());
		return time.intValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((timeTaken == null) ? 0 : timeTaken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (timeTaken == null) {
			if (other.timeTaken != null)
				return false;
		} else if (!timeTaken.equals(other.timeTaken))
			return false;
		return true;
	}

	

	
}
