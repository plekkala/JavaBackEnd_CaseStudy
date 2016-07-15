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

import java.io.FileNotFoundException;
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
 * The Input file path is passed as argument and number of teams to be split is passed as argument
 * @param   args
 * 
 *
 * @author Pradeep Lekkala
 */
public class ActivityPlanner {

	public static Date dateTime;
	public static String actualTime = "09:00 AM";
	public static final String LUNCH_DESCRIPTION="Lunch Break";
	public static final String PRESENTATION_DESCRIPTION="Staff Motivation Presentation";
	public static final String STARTLUNCHTIME= "12:00 PM";
	public static final String ENDLUNCHTIME= "01:00 PM";
	public static final String LUNCH_TIME="60min";
	static List<Activity> listOfActivities = new ArrayList<>();
	
	
	/**
	 * Main Method to run the application
	 *
	 * @param args Input File location
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args)  {
		int nTeams;
		if(args.length!=2){
			System.out.println("Input Arguments are wrong. Please pass two arguments to the application");
			System.out.println("args[0]-- Path of the Activities File");
			System.out.println("args[1]-- No of teams to be generated and value should be > 1");
			System.exit(1);
			
		}
		if(Integer.valueOf(args[1])<=1){
			System.out.println("args[1]-- No of teams to be generated and value should be > 1");
			System.exit(1);	
		}

		String filePath= args[0];
		nTeams = Integer.valueOf(args[1]);
		ActivityPlanner planner = new ActivityPlanner();

		for (int n = 1; n <= nTeams; n++) {

			planner.initActivities(filePath);

			List<Activity> teamActivities = planner.generateActivites();
			
			
			// Display only the activities for which the Lunch Time starts at 12:00
			
			boolean matchLunchTime = teamActivities
					.contains(new Activity(LUNCH_DESCRIPTION, LUNCH_TIME, null, STARTLUNCHTIME, ENDLUNCHTIME));

			if (matchLunchTime) {
				System.out.println("Team " + n + ":");
				for (int i = 0; i < teamActivities.size(); i++) {
					planner.displayActivities(teamActivities.get(i));
				}
				System.out.println("\n");
			}
			
			// Display All the activities including the activities  with lunch time before 12:00
			
			/*	System.out.println("Team "+n+":");
				for (int i = 0; i < teamActivities.size(); i++) {
					planner.displayActivities(teamActivities.get(i));
				}
				System.out.println( "\n");*/
			
		}
	}

	/**
	 *Method to load the activites into the List Object and reload if the activities if the size decreases<br>
	 * also shuffle the activities to randomize the pick
	 * @param filePath Input File path
	 * @throws FileNotFoundException 
	 */
	public void initActivities(String filePath) {

		List<Activity> activitiesFromFile = null;
		try {
			activitiesFromFile = ReadActivities.readActivities(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Reading file");
			System.exit(-1);
		}
		
		if(activitiesFromFile.isEmpty()){
			System.out.println("Erorr in loading file. Please Check the file location or the contents inside the file");
			System.exit(-1);
		}

		if(listOfActivities.size()<6){
			listOfActivities = new ArrayList<>(activitiesFromFile);
		}	

		Collections.shuffle(listOfActivities);
	}
	/**
	 * Method to generate the activites based in the amount of time taken for each ativity<br>
	 * also it does generate events for lunch time and presentation time 
	 * @return ArrayList<Activity> of Activities
	 */
	public ArrayList<Activity> generateActivites() {
		double time =0;
		ArrayList<Activity> activityTable = new ArrayList<Activity>();
		//Reset time for each loop
		actualTime="9:00 AM";
		//Morning Activities
		for (Iterator<Activity> iterator = listOfActivities.iterator(); iterator.hasNext();) {
			Activity activity = iterator.next();
				if (time + activity.getTimeChunk() <= 180) {
					activityTable.add(setStartAndEndTime(activity));
					time += activity.getTimeChunk();
					iterator.remove();
				}
		}
		//Lunch Time Addition
		time += 60;
		Activity lunch = new Activity(LUNCH_DESCRIPTION, LUNCH_TIME);
		activityTable.add(setStartAndEndTime(lunch));
		//Post Lunch Activites
		for (Iterator<Activity> iterator = listOfActivities.iterator(); iterator.hasNext();) {
			Activity activity = iterator.next();
			if (time + activity.getTimeChunk() <= 480) {
				activityTable.add(setStartAndEndTime(activity));
				time += activity.getTimeChunk();
				iterator.remove();
			}
		}
		//Presentation Time Addition
		time += 60;
		Activity presentation = new Activity(PRESENTATION_DESCRIPTION, "");
		activityTable.add(setStartAndEndTime(presentation));
		 actualTime="9:00 AM";
		 return activityTable;
	}
	
	/**
	 * Method to display the generated activites with time slots into console
	 * @param activity  activity to be displayed
	 */
	public Activity displayActivities(Activity activity) {
		System.out.println(activity.getStartTime()+" : "+activity.getActivity()+" "+activity.getTimeTaken());
		 return activity;
	}

	/**
	 * Method to set startTime and endTime of each activity
	 * @param activity  activity to be set
	 */
	public Activity setStartAndEndTime(Activity activity) {
		activity.setStartTime(date(String.valueOf(0)));
		activity.setEndTime(date(String.valueOf((activity.getTimeChunk()))));
		return activity;
	}
	
	/**
	 * Method to generate the Time based on the amount of time taken for a activity
	 * @param amt  amount of time for taken for a activity
	 */
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
	
	/**
	 * @deprecated
	 * To be Developed
	 * Method to generate difference amount of time during lunchTime and previous activity
	 */
	private static long difference() {
		long diffMinutes = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		try {
			Date endTime = sdf.parse(actualTime);
			Date lunchTime = sdf.parse(STARTLUNCHTIME);

			long diff = lunchTime.getTime() - endTime.getTime();
			diffMinutes = diff / (60 * 1000) % 60;

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return diffMinutes;

	}
	
	/**
	 * @deprecated
	 * To be Developed
	 * Method to generate difference amount of time during lunchTime and previous activity
	 */
	public String aroundLunchActivities(List<Activity> list, ArrayList<Activity> table){
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
						table.add(activity);
						int time = activity.getTimeChunk();
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