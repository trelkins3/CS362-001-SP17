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
	LinkedList<Appt> appts;
	
	/* Verify that getApptRange() works as intended. */
	@Test
	public void test01()  throws Throwable  {
		// Set up list of appointments to work with
		appts = new LinkedList<Appt>();
		appts.add(new Appt(15, 30, 30, 4, 2017, "Birthday", "Birthday Party!"));
		appts.add(new Appt(18, 30, 30, 4, 2017, "Work", "Gotta go to work!"));
		appts.add(new Appt(10, 30, 1, 5, 2017, "Concert", "Get there 30 minutes early!")); 
		//appts.add(new Appt(12, 0, 3, 5, 2017, "Dr. Appt", "Annual checkup.")); 
		
		// Looks like the code will return an out of bounds exception if last event is on lastDay
		
		// Now run a normal set of dates
		testTable.getApptRange(appts, new GregorianCalendar(2017, 4, 30, 18, 30, 0),
							   new GregorianCalendar(2017, 5, 3, 18, 30, 0));

		//testTable = new TimeTable();
		appts.add(new Appt(0, 0, 0, 0, 0, "", ""));		// invalid appt for getValid() catch test 
		testTable.getApptRange(appts, new GregorianCalendar(2017, 4, 30, 18, 30, 0),
							   new GregorianCalendar(2017, 5, 3, 18, 30, 0));
	}
	
	/* Verify that deleteAppt() works */
	@Test
	public void test02()	throws Throwable	{
		appts = new LinkedList<Appt>();
		Appt appointment = new Appt(15, 30, 30, 4, 2017, "Birthday", "Birthday Party!");
		
		appts.add(appointment);	// Using the actual object here fixed the problems I was having...
								// Something to do with pointers and linked lists, I'm guessing.					
		appts.add(new Appt(18, 30, 30, 4, 2017, "Work", "Gotta go to work!"));
		appts.add(new Appt(10, 30, 1, 5, 2017, "Concert", "Get there 30 minutes early!")); 

		// Normal case
		assertNotNull(testTable.deleteAppt(appts, appointment));
		
		// Null cases
		assertTrue(testTable.deleteAppt(appts, null) == null);
		assertTrue(testTable.deleteAppt(null, 
					new Appt(10, 30, 30, 4, 2017, "Birthday", "Birthday Party!")) == null);
					
		// Invalid appt
		assertTrue(testTable.deleteAppt(appts, 
					new Appt(0, 0, 0, 0, 0, "", "")) == null);
	}

	/* Test to handle mutation cases in getApptRange() */
	@Test
	public void test03()	throws Throwable	{
		appts = new LinkedList<Appt>();
		appts.add(new Appt(15, 30, 29, 4, 2017, "Birthday", "Birthday Party!"));
		appts.add(new Appt(18, 30, 30, 4, 2017, "Work", "Gotta go to work!"));
		
		LinkedList<CalDay> calDayTest;
		calDayTest = testTable.getApptRange(appts, new GregorianCalendar(2017, 4, 28, 14, 30, 0),
						new GregorianCalendar(2017, 4, 31, 19, 30, 0));
						
		assertTrue(calDayTest.get(0).getSizeAppts() == 0);
		assertTrue(calDayTest.get(1).getSizeAppts() == 1);
		assertTrue(calDayTest.get(2).getSizeAppts() == 1);
		
		/* Attempts at testing getApptOccurences() mutations... nothing is working...
		appts = new LinkedList<Appt>();
		calDayTest = new LinkedList<CalDay>();
		appts.add(new Appt(15, 30, 5, 4, 2017, "Test", "Test appt"));
		calDayTest = testTable.getApptRange(appts, new GregorianCalendar(2017, 4, 1, 14, 30, 0),
						new GregorianCalendar(2017, 4, 10, 19, 30, 0));
		
		assertNotNull(calDayTest.get(0).getAppts());
		assertTrue(calDayTest.get(0).getSizeAppts() == 0);
		assertTrue(calDayTest.get(1).getSizeAppts() == 0);
		assertTrue(calDayTest.get(2).getSizeAppts() == 0);
		assertTrue(calDayTest.get(3).getSizeAppts() == 0);	
		assertTrue(calDayTest.get(4).getSizeAppts() == 1);*/
	}
	
	/* Test to handle deleteAppt() mutations */
	@Test
	public void test04()	throws Throwable	{
		appts = new LinkedList<Appt>();
		Appt delAppt1 = new Appt(15, 30, 29, 4, 2017, "Birthday", "Birthday Party!");
		Appt delAppt2 = new Appt(18, 30, 30, 4, 2017, "Work", "Gotta go to work!");
		Appt fakeAppt = new Appt(12, 30, 30, 5, 2017, "Fake", "Not a real appt!");
		
		appts.add(delAppt1);
		appts.add(delAppt2);
		
		assertNotNull(testTable.deleteAppt(appts, delAppt1));
		assertNull(testTable.deleteAppt(appts, fakeAppt));
	}
}
