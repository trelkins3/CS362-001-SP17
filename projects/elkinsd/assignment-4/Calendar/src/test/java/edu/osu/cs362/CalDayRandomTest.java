package edu.osu.cs362;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

// Random test generator for CalDay
public class CalDayRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	
	// Generate random appointment to add to CalDay
	public static Appt generateRandomAppt(Random random) {
		int startHour = (int) ValuesGenerator.getRandomIntBetween(random, -75, 75);
		int startMinute = (int) ValuesGenerator.getRandomIntBetween(random, -75, 75);
		int startDay = (int) ValuesGenerator.getRandomIntBetween(random, -75, 75);
		int startMonth = (int) ValuesGenerator.getRandomIntBetween(random, -75, 75);
		int startYear = (int) ValuesGenerator.getRandomIntBetween(random, 0, 3000);
		String title = "Birthday Party";
		String description = "This is my birthday party.";
			
		//Construct a new CalDay object with the initial data	 
		Appt appt = new Appt(startHour,
				    startMinute ,
				    startDay ,
				    startMonth ,
				    startYear ,
				    title,
				    description);
		
		return appt;
	}
	
	// Generate random Gregorian Calendar object to create for CalDay
	public static GregorianCalendar generateGregCal(Random random) {
		int year = (int) ValuesGenerator.getRandomIntBetween(random, 0, 3000);
		int month = (int) ValuesGenerator.getRandomIntBetween(random, 0, 12);
		int day = (int) ValuesGenerator.getRandomIntBetween(random, 0, 30);
		int hour = (int) ValuesGenerator.getRandomIntBetween(random, 0, 23);
		int minute = (int) ValuesGenerator.getRandomIntBetween(random, 0, 59);
		int second = (int) ValuesGenerator.getRandomIntBetween(random, 0, 69);
		
		GregorianCalendar newCal = new GregorianCalendar(year, month, day, hour, minute, second);
		
		return newCal;
	}
    
	// Test to cover addAppt()
	@Test
	public void randomtest()  throws Throwable  {
		 
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
		System.out.println("Start testing...");
		 
		for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			long randomseed = 10;
			Random random = new Random(randomseed);
			
			GregorianCalendar gregCal = generateGregCal(random);
			CalDay cal = new CalDay(gregCal);
						
			int apptsSize = (int) ValuesGenerator.getRandomIntBetween(random, 0, 5);
			
			for (int i = 0; i < NUM_TESTS; i++) {
				for (int j = 0; j < apptsSize; j++) {
					Appt tempAppt = generateRandomAppt(random);
					cal.addAppt(tempAppt);
				}
			}
			
			// Timing 
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			if((iteration%10000)==0 && iteration!=0)
				System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		}
		
		System.out.println("Done testing...");
	}
}
