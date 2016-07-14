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
	static int nTeams;
	static List<Activity> listOfActivities = new ArrayList<>();

	public static void main(String[] args) {

		String filePath= args[0];
		nTeams = Integer.valueOf(args[1]);
		
		for (int n = 1; n <= nTeams; n++) {
			
			initActivities(filePath);
			
			List<Activity> teamActivities = createTimetable(0, listOfActivities);
			
			System.out.println("Team "+n+":");
			
			for (int i = 0; i < teamActivities.size(); i++) {
				
				displayActivities(teamActivities.get(i));
				
				}
			System.out.println( "\n");
		}
	}
	
	private static void initActivities(String filePath) {
		
		List<Activity> activitiesFromFile	 = ReadActivities.readActivities(filePath);
		
		if(listOfActivities.size()<6){
			listOfActivities = new ArrayList<>(activitiesFromFile);
		}	
		
		Collections.shuffle(listOfActivities);
	}

	public static Activity displayActivities(Activity activity) {
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

	public static ArrayList<Activity> createTimetable(double time, final List<Activity> list) {
		ArrayList<Activity> timetable = new ArrayList<Activity>();	
		for (int i = 0; i < list.size(); i++) {
			for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
				Activity activity = iterator.next();
				if (time + activity.getTimeChunk() <= 180) {
					activity.setStartTime(date(String.valueOf(0)));
					activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
					timetable.add(activity);
					time += activity.getTimeChunk();
			        iterator.remove();
			    }
			}
		}
		time += 60;
		timetable.add(new Activity("Lunch Break", "60min"));

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
}