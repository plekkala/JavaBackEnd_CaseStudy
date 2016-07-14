package com.java.prad.planner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ActivityPlanner {

	static double time = 0.0;
	static Date dateTime;
	public static String actualTime = "09:00 AM";
	static int nTeams;
	//static List<Activity> listOfActivities = new ArrayList<>();

	public static void main(String[] args) {

		List<Activity> activities	 = ReadActivities.readActivities(args[0]);
		List<Activity> listOfActivities = new ArrayList<>();
		nTeams = Integer.valueOf(args[1]);
		for (int n = 1; n <= nTeams; n++) {
			listOfActivities =activities;
			Collections.shuffle(listOfActivities);
			
			System.out.println(listOfActivities.size());
			
			
			List<Activity> teamActivities = createTimetable(0, listOfActivities);
			
			System.out.println("Team "+n+" Activities" + "\n");
			
			for (int i = 0; i < teamActivities.size(); i++) {
				
				displayActivities(teamActivities.get(i));
				
				System.out.println(teamActivities.get(i));
			}
			System.out.println( "\n");
		}
	}

	/*private static String initDate() {
		String intialTime = "09:00 AM";
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		try {
			Date start = sdf.parse(intialTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(start);
			Date intial = cal.getTime();

			intialTime = sdf.format(intial);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return intialTime;

	}
*/
	public static Activity displayActivities(Activity activity) {
		activity.setStartTime(date(String.valueOf(0)));
		activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
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

	public static ArrayList<Activity> createTimetable(double time, final List<Activity> la) {
		ArrayList<Activity> timetable = new ArrayList<Activity>();
		List<Activity> list =  new ArrayList<Activity>(la);
		

		for (int i = 0; i < list.size(); i++) {

			if (time + list.get(i).getTimeChunk() <= 180) {
				list.get(i).setStartTime(date(String.valueOf(0)));
				list.get(i).setEndTime(date(String.valueOf((list.get(i).getTimeChunk()))));
				
				timetable.add(list.get(i));
				time += list.get(i).getTimeChunk();
				// System.out.println(time);
				list.remove(list.get(i));
			}
		}
		time += 60;

		timetable.add(new Activity("Lunch", "60"));

		for (int i = 0; i < list.size(); i++) {

			if (time + list.get(i).getTimeChunk() <= 480) {
				list.get(i).setStartTime(date(String.valueOf(0)));
				list.get(i).setEndTime(date(String.valueOf((list.get(i).getTimeChunk()))));
				timetable.add(list.get(i));
				time += list.get(i).getTimeChunk();
				// System.out.println(time);
				list.remove(list.get(i));
			}
		}
		time += 60;
		timetable.add(new Activity("Staff Motivation Presentation", "60"));
		// System.out.println(time);
		actualTime="9:00 AM";
		return timetable;
	}
}