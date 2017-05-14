package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

// Fun Fact: GregorianCalendar() default constructor generates off of local system time?

// Does code outside of the test functions run between each test?

public class CalDayTest {
	GregorianCalendar cal = new GregorianCalendar(2017, 4, 30, 18, 30, 0);
	CalDay testCal = new CalDay(cal);	// This fixed a bunch of null pointer errors...
	
	/* Test that ensures CalDay() constructors and set methods work. */
	@Test
	public void test01()	throws Throwable  {
		testCal = new CalDay();
		assertFalse(testCal.isValid());
		
		testCal = new CalDay(cal);
		assertTrue(testCal.isValid()); 
	}
	
	/* Test to check get methods. */
	@Test
	public void test02()	throws Throwable {
		assertEquals(0, testCal.getSizeAppts());
		assertEquals(30, testCal.getDay());
		assertEquals(4, testCal.getMonth()); 
		assertEquals(2017, testCal.getYear()); 
		assertTrue(testCal.getAppts() != null);
	}
	
	/* Test adding appointments to list. */
	@Test
	public void test03()	throws Throwable {
		// Add valid appointments
		testCal.addAppt(new Appt(19, 30, 30, 04, 2017, "Work", "Gotta go to work!"));
		assertEquals(1, testCal.getSizeAppts()); 
		testCal.addAppt(new Appt(20, 30, 30, 04, 2017, "Meeting", "Have a meeting!"));
		assertEquals(2, testCal.getSizeAppts()); 
		
		// Add bad appointment
		testCal.addAppt(new Appt(0, 0, 0, 0, 0, null, null));
		assertEquals(2, testCal.getSizeAppts()); 
		
		// Add appointment before other appointments
		testCal.addAppt(new Appt(15, 30, 30, 04, 2017, "Work", "Gotta go to work!"));
		assertEquals(3, testCal.getSizeAppts()); 
		
		// Mutation case handling var
		Appt appt1 = testCal.getAppts().get(0);
		
		// Add another appointment at the end
		testCal.addAppt(new Appt(15, 30, 30, 04, 2017, "Run", "Go for a run!"));
		assertEquals(4, testCal.getSizeAppts()); 
		
		// Second mutation case handling var
		Appt appt2 = testCal.getAppts().get(1);
		
		// If the conditional boundary is changed from '>' to '>=', appt1 == appt2 and this fails
		assertNotSame(appt1, appt2); 
		
		// Make sure that the appointments are in correct order...
		for(int i = 0; i < (testCal.getAppts().size() - 1) ; i++){
			assertTrue(testCal.getAppts().get(i+1).getStartHour() >= testCal.getAppts().get(i).getStartHour());
		}
	}
	
	/* Test if toString() functions correctly. */
	@Test
	public void test04()	throws Throwable {
		testCal = new CalDay();
		assertEquals("", testCal.toString());
		
		testCal = new CalDay(cal);
		testCal.addAppt(new Appt(19, 30, 30, 04, 2017, "Work", "Gotta go to work!"));
		testCal.addAppt(new Appt(15, 30, 30, 04, 2017, "Work", "Gotta go to work!"));
		assertTrue(testCal.toString() != "");
		
		// Mutation coverage - kills if iterator conditional is bad
		assertTrue(testCal.toString().length() > 68);
	}
	
	/* Test branching behavior of iterator(). */
	@Test
	public void test05()	throws Throwable {
		assertTrue(testCal.iterator() != null);
		
		testCal = new CalDay();
		assertTrue(testCal.iterator() == null);
	}
}
