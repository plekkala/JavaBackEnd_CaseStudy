/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.java.prad.planner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Main class for running the Activity Planner with user provided List of activities data.
 * The resultant output will schedule activities according to the start time and amount of time for each task
 * The Input file path is passed as argument  
 * @param   args
 * 
 *
 * @author Pradeep Lekkala
 */
public class ActivityPlanner {

	static double time = 0.0;
	static Date dateTime;
	public static String actualTime = "09:00 AM";
	public static final String startLunchTime= "12:00 PM";
	public static final String LUNCH_DESCRIPTION="Lunch Break";
	public static final String PRESENTATION_DESCRIPTION="Staff Motivation Presentation";
	public static final String LUNCH_TIME="60min";
	static List<Activity> listOfActivities = new ArrayList<>();
	/**
	 * Main Method to run the application
	 *
	 * @param args Input File location
	 */
	public static void main(String[] args) {
		int nTeams;
		String filePath= args[0];
		nTeams = Integer.valueOf(args[1]);

		ActivityPlanner planner = new ActivityPlanner();

		for (int n = 1; n <= nTeams; n++) {

			planner.initActivities(filePath);

			List<Activity> teamActivities = planner.generateActivites();


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

	/**
	 *Method to load the activites into the List Object and reload if the activities if the size decreases<br>
	 * also shuffle the activities to randomize the pick
	 * @param filePath Input File path
	 */
	public void initActivities(String filePath) {

		List<Activity> activitiesFromFile	 = ReadActivities.readActivities(filePath);

		if(listOfActivities.size()<6){
			listOfActivities = new ArrayList<>(activitiesFromFile);
		}	

		Collections.shuffle(listOfActivities);
	}
	/**
	 *Method to load the activites into the List Object and reload if the activities if the size decreases<br>
	 * also shuffle the activities to randomize the pick
	 * @param filePath Input File path
	 */
	public ArrayList<Activity> generateActivites() {
		double time =0;
		ArrayList<Activity> timetable = new ArrayList<Activity>();
		//Reset time for each loop
		actualTime="9:00 AM";

		for (Iterator<Activity> iterator = listOfActivities.iterator(); iterator.hasNext();) {
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

		time += 60;
		//aroundLunchActivities(list,timetable);

		Activity lunch = new Activity(LUNCH_DESCRIPTION, LUNCH_TIME);
		/*
		lunch.setStartTime(date(String.valueOf(0)));
		lunch.setEndTime(date(String.valueOf((lunch.getTimeChunk()))));

		 */
		timetable.add(setStartAndEndTime(lunch));
		for (Iterator<Activity> iterator = listOfActivities.iterator(); iterator.hasNext();) {
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
		Activity presentation = new Activity(PRESENTATION_DESCRIPTION, "");

		/*presentation.setStartTime(date(String.valueOf(0)));
		presentation.setEndTime(date(String.valueOf((lunch.getTimeChunk()))));
		 */timetable.add(setStartAndEndTime(presentation));
		 actualTime="9:00 AM";
		 return timetable;
	}

	public Activity displayActivities(Activity activity) {
		/*activity.setStartTime(date(String.valueOf(0)));
		activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
		 */System.out.println(activity.getStartTime()+" : "+activity.getActivity()+" "+activity.getTimeTaken());

		 return activity;
	}

	public Activity setStartAndEndTime(Activity activity) {
		activity.setStartTime(date(String.valueOf(0)));
		activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
		return activity;
	}

	public static String date(String amt) {

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