package com.java.prad.planner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.java.prad.planner.Activity;
/**
 * Utility class used to read the file with activities
 * 
 * @author Pradeep Lekkala
 */
public class ReadActivities {
	
	 
	/**
	 *Method to read the sample activities file and storing data into List
	 *
	 * @param   path Input file path
	 * @return List of activities 
	 */
	public static List<Activity>  readActivities(String path){
		List<Activity> listOfActivities = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get(path))) {
				Activity activity = new Activity(line.substring(0, line.lastIndexOf(" ")), line.substring(line.lastIndexOf(" ") + 1));
				listOfActivities.add(activity);
			    }	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfActivities;
	}

	
}
