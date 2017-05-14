package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class ApptTest {
	
	// Variables for all testing
	int startHour=13;
	int startMinute=30;
	int startDay=10;
	int startMonth=4;
	int startYear=2017;
	String title="Birthday Party";
	String description="This is my birthday party.";
	//Construct a new Appointment object with the initial data	 
	Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
		       description);
			   
    
    /* Test that the gets methods work as expected. */
	@Test
	public void test01()  throws Throwable  {
		// assertions
		assertTrue(appt.getValid());
		assertEquals(13, appt.getStartHour());
		assertEquals(30, appt.getStartMinute());
		assertEquals(10, appt.getStartDay());
		assertEquals(04, appt.getStartMonth());
		assertEquals(2017, appt.getStartYear());
		assertEquals("Birthday Party", appt.getTitle());
		assertEquals("This is my birthday party.", appt.getDescription());         		
	}
	 
	/* Test that the set methods work as expected */
	@Test
	public void test02()	throws Throwable	{
		// assertions
		assertTrue(appt.getValid());
		appt.setStartHour(15);
		assertTrue(appt.getValid());
		appt.setStartMinute(29);
		assertTrue(appt.getValid());
		appt.setStartDay(9);
		assertTrue(appt.getValid());
		appt.setStartMonth(05);
		assertTrue(appt.getValid());
		appt.setStartYear(2016);
		assertTrue(appt.getValid());
		
		appt.setTitle(null);
		assertEquals("", appt.getTitle());
		
		appt.setTitle("Work");
		assertEquals("Work", appt.getTitle());
		
		appt.setDescription(null);
		assertEquals("", appt.getDescription());
		
		appt.setDescription("Gotta go to work!");
		assertEquals("Gotta go to work!", appt.getDescription());
	}
	  
	/* Test that isValid() catches bad input correctly */
	@Test
	public void test03()	throws Throwable	{
		assertTrue(appt.getValid());
		  
		appt.setStartMonth(13);
		assertFalse(appt.getValid());
		appt.setStartMonth(0);
		assertFalse(appt.getValid());
		  
		appt.setStartDay(32);
		assertFalse(appt.getValid());
		appt.setStartDay(0);
		assertFalse(appt.getValid());
		  
		appt.setStartMinute(60);
		assertFalse(appt.getValid());
		appt.setStartMinute(-1);
		assertFalse(appt.getValid());
		  
		appt.setStartHour(24);
		assertFalse(appt.getValid());
		appt.setStartHour(-1);
		assertFalse(appt.getValid());
	}
		
	/* Test that toString() outputs correctly */
	@Test
	public void test04()	throws Throwable	{
		startHour=13;
		startMinute=30;
		startDay=10;
		startMonth=4;
		startYear=2017;
		title="Birthday Party";
		description="This is my birthday party.";
		// Reset appointment object with the initial data	
		appt = new Appt(startHour,
			   startMinute ,
		       startDay ,
		       startMonth ,
		       startYear ,
		       title,
		       description);
				 
		// assertions
		assertTrue(appt.getValid());
		assertEquals(appt.toString(), ("\t" + appt.getStartMonth()+"/"
					+appt.getStartDay()+"/"+appt.getStartYear() + " at " 
					+ 1 + ":" + appt.getStartMinute() + "pm" + " ," +
					appt.getTitle() + ", " + appt.getDescription() + "\n"));
	}
	
	/* Test to catch mutations that fail to call isValid() in set methods */
	@Test
	public void test05()	throws Throwable	{
		Appt testAppt = appt;
		Appt newAppt; 
		
		// setStartHour() mutation check
		testAppt.setStartHour(24);
		if(testAppt.getValid() == true) {
			newAppt = new Appt(testAppt.getStartHour(), 30, 10, 4,
						2017, testAppt.getTitle(), testAppt.getDescription());
			assertTrue(newAppt.getValid());
		}

		// setStartMinute() mutation check
		testAppt.setStartHour(13);
		testAppt.setStartMinute(61);
		if(testAppt.getValid() == true){
			newAppt = new Appt(13, testAppt.getStartMinute(), 10, 4,
							2017, testAppt.getTitle(), testAppt.getDescription());
			assertTrue(newAppt.getValid());
		}
							
		// setStartDay() mutation check
		testAppt.setStartMinute(30);
		testAppt.setStartDay(32);
		if(testAppt.getValid() == true) {
			newAppt = new Appt(13, 30, testAppt.getStartDay(), 4,
							2017, testAppt.getTitle(), testAppt.getDescription());
			assertTrue(newAppt.getValid());
		}
	}
	
	/* Test to catch all mutation cases for isValid() */
	@Test
	public void test06()	throws Throwable	{
		// Negation of startHour < 0
		appt.setStartHour(-1); 
		assertFalse(appt.getValid()); 
		
		// Boundary change to startHour <= 0
		appt.setStartHour(0);
		assertTrue(appt.getValid()); 
		
		// Negation of startHour > 23
		appt.setStartHour(24);
		assertFalse(appt.getValid());
		
		// Boundary change to startHour >= 23
		appt.setStartHour(23);
		assertTrue(appt.getValid());

		// Negation of startMinute < 0
		appt.setStartMinute(-1); 
		assertFalse(appt.getValid());
		
		// Boundary change to startMinute <= 0
		appt.setStartMinute(0);
		assertTrue(appt.getValid()); 
		
		// Negation of startMinute > 59
		appt.setStartMinute(60);
		assertFalse(appt.getValid());
		
		// Boundary change to startMinute >= 59
		appt.setStartMinute(59);
		assertTrue(appt.getValid());
		
		// Negation of startDay < 1
		appt.setStartDay(0); 
		assertFalse(appt.getValid());
		
		// Boundary change to startDay <= 1
		appt.setStartDay(1);
		assertTrue(appt.getValid()); 
		
		// Negation of startDay > 31
		appt.setStartDay(32);
		assertFalse(appt.getValid());
		
		// Boundary change to startDay >= 31
		appt.setStartDay(31);
		assertTrue(appt.getValid());
		
		// Negation of startMonth < 1
		appt.setStartMonth(0); 
		assertFalse(appt.getValid());
		
		// Boundary change to startMonth <= 1
		appt.setStartMonth(1);
		assertTrue(appt.getValid()); 
		
		// Negation of startMonth > 12
		appt.setStartMonth(13);
		assertFalse(appt.getValid());
		
		// Boundary change to startMonth >= 12
		appt.setStartMonth(12);
		assertTrue(appt.getValid());
	}
	
	/* Test to catch toString() mutations */
	@Test
	public void test07()	throws Throwable {
		appt.setStartMonth(13);
		assertNull(appt.toString()); 
		
		appt.setStartMonth(12); 
		appt.setStartHour(11); 
		assertFalse(appt.toString().contains("pm"));	// Seems more likely to catch than .contains("am")
		assertFalse(appt.toString().contains("-1"));	// 11 - 12 = -1
	}
}