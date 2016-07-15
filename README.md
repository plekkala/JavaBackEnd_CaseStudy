# README #

This README would normally document whatever steps are necessary to get your application up and running.

### Quick summary ###
* Application to run Activity Planner. The resultant output will be on the console containing the schedule of activities with the start time and amount of time for each activity

### Assumptions Made ###
* Introduced a new parameter to generate team activities based on the required number of teams
* In The current solution some times the Lunch time activity can start before 12:00PM
* Inorder to eliminate such scenario, introduced a commented code which will filter out activities
* The order of activities displayed will be different for each run 
* Assumed that a activity can be repeated if the expected team size is more than 2
* Assumed that if the expected team size is 2, then all the current sample of 20 activities will be grouped into two teams.
* The solution can be scalable to increase into more teams and more activities as well.
* Also a small piece of code is in deprecated state which can be improved to make it more effective.

### Summary of Setup ###
* To run this Application, user has to provide two input arguments to main method of ActivityPlanner.java  (Path of Input file location) and (No of teams to which activities are to be associated)

### Configuration ###
* Path of Input file location Example :(/Users/pradeep/Documents/workspace/CoreJava/JavaTest/activities.txt)
* No of teams to which activities are to be associated Example:(10)

###Deployment instructions###
* Java 1.8
* Import the project into eclipse and run from there or
* Download the jar file 
* cd to location of jar file and run the below command
* java -cp JavaTest.jar com.java.prad.planner.ActivityPlanner "/Users/Radhika/Documents/workspace/CoreJava/Deloitte/activities.txt" "10"

### Who do I talk to? ###

* plekkala.ibm@gmail.com