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

public class CalDayTest {
	GregorianCalendar cal = new GregorianCalendar(2017, 4, 30, 18, 30, 0);
	Appt testAppt;
	CalDay testCal;
	
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
		// First try to add an appointment that would've already happened
		testAppt = new Appt(15, 30, 30, 04, 2017, "Work", "Gotta go to work!");
		testCal.addAppt(testAppt);
		assertEquals(0, testCal.getSizeAppts()); 
		
		// Add valid appointment
		testAppt = new Appt(19, 30, 30, 04, 2017, "Work", "Gotta go to work!");
		testCal.addAppt(testAppt);
		assertEquals(1, testCal.getSizeAppts()); 
		
		// Add bad appointment
		testAppt = new Appt(0, 0, 0, 0, 0, null, null);
		testCal.addAppt(testAppt);
		assertEquals(1, testCal.getSizeAppts()); 
	}
}
