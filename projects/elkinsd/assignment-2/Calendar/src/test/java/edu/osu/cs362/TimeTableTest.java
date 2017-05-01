package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {
	TimeTable testTable = new TimeTable();
	
	/* Verify that getApptRange() works as intended. */
	@Test
	public void test01()  throws Throwable  {
		// Set up list of appointments to work with
		LinkedList<Appt> appts = new LinkedList<Appt>();
		appts.add(new Appt(15, 30, 30, 4, 2017, "Birthday", "Birthday Party!"));
		appts.add(new Appt(18, 30, 30, 4, 2017, "Work", "Gotta go to work!"));
		appts.add(new Appt(10, 30, 1, 5, 2017, "Concert", "Get there 30 minutes early!")); 
		//appts.add(new Appt(12, 0, 3, 5, 2017, "Dr. Appt", "Annual checkup.")); 
		
		// Looks like the code will return an out of bounds exception if last event is on lastDay
		
		// Check if the catching for mixed up dates works
		testTable.getApptRange(appts, new GregorianCalendar(2017, 5, 3, 18, 30, 0),
							   new GregorianCalendar(2017, 4, 30, 18, 30, 0));
		
		testTable = new TimeTable();
		appts.add(new Appt(0, 0, 0, 0, 0, "", ""));		// invalid appt for getValid() catch test 
		
		// Now run a normal set of dates
		testTable.getApptRange(appts, new GregorianCalendar(2017, 4, 30, 18, 30, 0),
							   new GregorianCalendar(2017, 5, 3, 18, 30, 0));
	}
	
	/* Verify that deleteAppt() works */
	@Test
	public void test02()	throws Throwable	{
		LinkedList<Appt> appts = new LinkedList<Appt>();
		appts.add(new Appt(15, 30, 30, 4, 2017, "Birthday", "Birthday Party!"));
		appts.add(new Appt(18, 30, 30, 4, 2017, "Work", "Gotta go to work!"));
		appts.add(new Appt(10, 30, 1, 5, 2017, "Concert", "Get there 30 minutes early!")); 
		
		// Null cases
		assertTrue(testTable.deleteAppt(appts, null) == null);
		assertTrue(testTable.deleteAppt(null, 
					new Appt(15, 30, 30, 4, 2017, "Birthday", "Birthday Party!")) == null);

		// Normal case
		assertTrue(testTable.deleteAppt(appts, 
					new Appt(15, 30, 30, 4, 2017, "Birthday", "Birthday Party!")) == null);
					
		// Invalid appt
		assertTrue(testTable.deleteAppt(appts, 
					new Appt(0, 0, 0, 0, 0, "", "")) == null);
	}

}
