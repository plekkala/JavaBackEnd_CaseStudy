/*import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class s {
	 public String generateTimeTable(){
		    //2 arrays for all the months of the year and days of the week.
		    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		    String[] days = {"Mon","Tue","Wed","Thur","Fri","Sat","Sun"};



	 Date today = new Date();
	    DateTime start = today.plusDays(1).withTime(8, 0, 0, 0); // this makes it start at 8am the next day
	    String finalString = "On" + " " + days[start.getDayOfWeek()-1] + " "+ start.getDayOfMonth() + " " + months[start.getMonthOfYear() - 1] + "\n"; //This is the string for the first day.
	    // It should return something like "On Mon 13 February
	    ArrayList<Course> list = listOfCourses(); //get a sorted list of courses



	    while (start.toLocalDate().isBefore(this.startOfExams)) { // loop for everyday up until the start of exams
	        String sub1 = list.get(0).toString() + "...from " + new Interval(start, start.plusHours(3)).toString().substring(11, 16) + " to " + new Interval(start, start.plusHours(3)).toString().substring(41, 46) + "\n";
	        finalString += sub1;
	        finalString += "\n";




	        String break1 = "break...from " + new Interval(start.plusHours(3), start.plusHours(4)).toString().substring(11, 16) + " to " + new Interval(start.plusHours(3), start.plusHours(4)).toString().substring(41, 46) + "\n";
	        // String break1 = "break..." + new Interval(start.plusHours(3), start.plusHours(4)) + "\n";
	        finalString += break1;
	        finalString += "\n";


	        String sub2 = list.get(1).toString() + "..." + new Interval(start.plusHours(4), start.plusHours(7)) + "\n";
	        finalString += sub2;
	        finalString += "\n";


	        String break2 = "LunchBreak...You need to eat! " + new Interval(start.plusHours(7), start.plusHours(8)) + "\n";
	        finalString += break2;
	        finalString += "\n";



	        String sub3 = list.get(2).toString() + "..." + new Interval(start.plusHours(8), start.plusHours(10)) + "\n";
	        finalString += sub3;
	        finalString += "\n";


	        String break3 = "break..." + new Interval(start.plusHours(10), start.plusHours(11)) + "\n";
	        finalString += break3;
	        finalString += "\n";


	        String sub4 = list.get(3).toString() + "..." + new Interval(start.plusHours(11), start.plusHours(13)) + "\n";
	        finalString += sub4;
	        finalString += "\n";


	        String break4 = "break..." + new Interval(start.plusHours(13), start.plusHours(14)) + "\n";
	        finalString += break4;
	        finalString += "\n";


	        String sub5 = list.get(4).toString() + "..." + new Interval(start.plusHours(14), start.plusHours(15)) + "\n";
	        finalString += sub5;
	        finalString += "\n";


	        String sub6 = list.get(5).toString() + "..." + new Interval(start.plusHours(15), start.plusHours((int) 15.5)) + "\n";
	        //finalString += sub6;
	        //finalString += "\n";


	        start = start.plusDays(1); // Move along to the next day.

	        finalString += "\n";
	        finalString += "On" + " " + days[start.getDayOfWeek()-1] + " "+ start.getDayOfMonth() + " " + months[start.getMonthOfYear() - 1] + "\n";


	    }
	    return finalString + "EXAMM DAYYY!";


	}
public static void main(String[] args) {
    // TODO Auto-generated method stub
    LocalDate examStart = new LocalDate(2016, 3, 1);

    Course chem = new Course("Chemistry", 2, examStart);
    Course math = new Course("Mathematics", 5, examStart);
    Course phys = new Course("physics", 8, examStart);
    Course french = new Course("French", 5, examStart);
    Course Bio = new Course("Biology", 3, examStart);
    Course eng = new Course("English", 6, examStart);


    TimeTable tt = new TimeTable(2016, 3, 1);

    tt.addCourse(chem);
    tt.addCourse(math);
    tt.addCourse(phys);
    tt.addCourse(french);
    tt.addCourse(Bio);
    tt.addCourse(eng);

    //FIRST THINGS FIRST: complete testing of updating confidence.
    //tt.updateConfidence("French", 1);

    String finals = tt.generateTimeTable();

    System.out.print(finals);




}
But instead of printing out my timetable, it only prints out On Fri 1 Apr EXAMM DAYYY!

Anyone get why that may be so?
}
*/