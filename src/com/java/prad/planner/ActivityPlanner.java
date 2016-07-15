package com.java.prad.planner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ActivityPlanner {

	static double time = 0.0;
	static Date dateTime;
	public static String actualTime = "09:00 AM";
	public static final String startLunchTime= "12:00 PM";
	
	static List<Activity> listOfActivities = new ArrayList<>();

	public static void main(String[] args) {
		int nTeams;
		String filePath= args[0];
		nTeams = Integer.valueOf(args[1]);
		
		ActivityPlanner planner = new ActivityPlanner();
		
		for (int n = 1; n <= nTeams; n++) {
			
			planner.initActivities(filePath);
			
			List<Activity> teamActivities = planner.createTimetable(0, listOfActivities);
			
			
			boolean matchLunchTime = teamActivities.contains(new Activity("Lunch Break","60min",null,"12:00 PM","01:00 PM"));
			
			
			if(matchLunchTime){
				System.out.println("Team "+n+":");
			for (int i = 0; i < teamActivities.size(); i++) {
				
				
				//System.out.println(teamActivities);
				//		endTime=12:00 PM], Activity [activity=Lunch Break, timeTaken=60min, timeChunk=60, startTime=null, endTime=null], A

				planner.displayActivities(teamActivities.get(i));
				
				}
			
			System.out.println( "\n");
			}
		}
	}
	
	private void initActivities(String filePath) {
		
		List<Activity> activitiesFromFile	 = ReadActivities.readActivities(filePath);
		
		if(listOfActivities.size()<6){
			listOfActivities = new ArrayList<>(activitiesFromFile);
		}	
		
		Collections.shuffle(listOfActivities);
	}
	
	private ArrayList<Activity> createTimetable(double time, final List<Activity> list) {
		ArrayList<Activity> timetable = new ArrayList<Activity>();
		//Reset time for each loop
		actualTime="9:00 AM";
		System.out.println("CurrentTime at start of new loop:"+actualTime);
		for (int i = 0; i < list.size(); i++) {
			for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
				Activity activity = iterator.next();
				if(!activity.getTimeTaken().equals("sprint")){
					if (time + activity.getTimeChunk() <= 180) {
						activity.setStartTime(date(String.valueOf(0)));
						activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
						timetable.add(activity);
						time += activity.getTimeChunk();
				        iterator.remove();
				    }
				}
				
			}
		}
		time += 60;
		//aroundLunchActivities(list,timetable);
		
		Activity lunch = new Activity("Lunch Break", "60min");
		
		lunch.setStartTime(date(String.valueOf(0)));
		lunch.setEndTime(date(String.valueOf((lunch.getTimeChunk()))));
		
		
		//timetable.add(new Activity("Lunch Break", "60min"));
		timetable.add(lunch);
		for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
			Activity activity = iterator.next();
			if (time + activity.getTimeChunk() <= 480) {
				activity.setStartTime(date(String.valueOf(0)));
				activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
				timetable.add(activity);
				time += activity.getTimeChunk();
		        iterator.remove();
		    }
		}
		time += 60;
		timetable.add(new Activity("Staff Motivation Presentation", "60min"));
		actualTime="9:00 AM";
		return timetable;
	}

	private Activity displayActivities(Activity activity) {
		activity.setStartTime(date(String.valueOf(0)));
		activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
		System.out.println(activity.getStartTime()+" : "+activity.getActivity()+" "+activity.getTimeTaken());
		
		return activity;
	}

	private static String date(String amt) {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		try {
			Date start = sdf.parse(actualTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(start);
			cal.add(Calendar.MINUTE, Integer.valueOf(amt));
			Date end = cal.getTime();
			actualTime = sdf.format(end);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return actualTime;
	}
	private static long difference() {
		long diffMinutes = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		try {
			Date endTime = sdf.parse(actualTime);
			Date lunchTime = sdf.parse(startLunchTime);
			
			long diff = lunchTime.getTime() - endTime.getTime();
			diffMinutes = diff / (60 * 1000) % 60;
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return diffMinutes;
		
	}

	public String aroundLunchActivities(List<Activity> list, ArrayList<Activity> timetable){
		System.out.println("aroundLunchActivities start");
		long timeGap = difference();
		System.out.println("difference:"+timeGap);
		if(timeGap>0){
			for (int i = 0; i < list.size(); i++) {
				for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
					Activity activity = iterator.next();
					if(activity.getTimeTaken().equals("sprint")){
						activity.setStartTime(date(String.valueOf(0)));
						activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
						timetable.add(activity);
						time += activity.getTimeChunk();
						iterator.remove();
						break;	
					}
					break;
				}
			}
		}
		
		System.out.println("aroundLunchActivities end");
		return null;
		
	}
	
}