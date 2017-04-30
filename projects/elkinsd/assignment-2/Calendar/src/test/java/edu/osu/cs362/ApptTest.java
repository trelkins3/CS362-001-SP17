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
		
}
