package com.java.prad.planner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActivityPlannerTest {
	static ActivityPlanner planner;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 planner = new ActivityPlanner();
		 
	}

	@Before
	public void setUp() throws Exception {
		String filepath="/Users/Radhika/Documents/workspace/CoreJava/Deloitte/activities.txt";
		planner.initActivities(filepath);
	}

	@Test
	public void testInitActivities() {
		String filepath="/Uers";
		planner.initActivities(filepath);
	}

	@Test
	public void testGenerateActivites() {
	ArrayList<Activity> list = planner.generateActivites();
	System.out.println(list);
	}

	@Test
	public void testDisplayActivities() {
		Activity activity = new Activity("Demo Activity", "60min", null, "", "");
		planner.displayActivities(activity);
	}

	@Test
	public void testSetStartAndEndTime() {
		Activity activity = new Activity("Demo Activity", "60min", null, "", "");
		planner.setStartAndEndTime(activity);
	}

	@Test
	public void testDate() {
		ActivityPlanner.date("2");
	}

	/* To do Implementation
	 * @Test
	public void testAroundLunchActivities() {
		planner.aroundLunchActivities(list, table)
	}
*/
}
