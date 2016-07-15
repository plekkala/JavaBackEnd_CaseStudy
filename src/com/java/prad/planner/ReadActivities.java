package com.java.prad.planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
	 * @throws FileNotFoundException 
	 */
	public static List<Activity>  readActivities(String path) throws FileNotFoundException{
		List<Activity> listOfActivities = new ArrayList<>();
		if(fileExists(path)){
			try {
				for (String line : Files.readAllLines(Paths.get(path))) {
					Activity activity = new Activity(line.substring(0, line.lastIndexOf(" ")), line.substring(line.lastIndexOf(" ") + 1));
					listOfActivities.add(activity);
				    }	
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Error in Loading the file");
			}
		}
		
		return listOfActivities;
	}
	
	private static boolean fileExists(String file) throws FileNotFoundException{
		if (!new File(file).exists())
		{
		   throw new FileNotFoundException("File Not Found!");
		}
		return true;
	}

	
}
