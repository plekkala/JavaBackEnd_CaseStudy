package com.java.prad.planner;

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

	public void setTimeChunk(Double timeChunk) {
		this.timeChunk = timeChunk;
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
		Double time;
		//System.out.println(this.timeTaken);
		String taskTime = this.timeTaken;
		if(taskTime.equals("sprint")){
			 time = 15D;
		}
		else
			time = Double.parseDouble(taskTime.replace("min", ""));
		
		//System.out.println("time:"+time.intValue());
		return time.intValue();
	}
	
}
