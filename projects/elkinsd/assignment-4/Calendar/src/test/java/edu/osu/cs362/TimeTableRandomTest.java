package edu.osu.cs362;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.*;

// Random test generation for TimeTable
public class TimeTableRandomTest {
	
	// Only testing public LinkedList<Appt> deleteAppt(LinkedList<Appt> appts, Appt appt)
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;
	
	// Generate random appointment to add to LinkedList<Appt>
	public static Appt generateRandomAppt(Random random) {
		int startHour = (int) ValuesGenerator.getRandomIntBetween(random, 0, 26);
		int startMinute = (int) ValuesGenerator.getRandomIntBetween(random, 0, 65);
		int startDay = (int) ValuesGenerator.getRandomIntBetween(random, 0, 35);
		int startMonth = (int) ValuesGenerator.getRandomIntBetween(random, 0, 13);
		int startYear = (int) ValuesGenerator.getRandomIntBetween(random, 2000, 2020);
		String title = "Birthday Party";
		String description = "This is my birthday party.";
			
		Appt appt = new Appt(startHour,
						startMinute ,
						startDay ,
						startMonth ,
						startYear ,
						title,
						description);
			
		return appt;
	}
	
	// Test for deleteAppt()
	@Test
	public void randomtest()  throws Throwable  {
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		TimeTable testTable = new TimeTable();
		
		System.out.println("Start testing...");
		long randomseed = 12;
		Random random = new Random(randomseed);
		
		for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			// Create new random from static random
			
			randomseed = ValuesGenerator.getRandomIntBetween(random, 0, 30);
			//System.out.println("Randomseed: "+randomseed);
			random = new Random(randomseed);
			
			LinkedList<Appt> appts;
			int apptsSize = (int) ValuesGenerator.RandInt(random);
			//System.out.println("Appts Size: " + apptsSize);
			
			if(apptsSize != 0) {
				appts = new LinkedList<Appt>();
				
				for(int i = 0; i < apptsSize; i++) {
					appts.add(generateRandomAppt(random));
				}
			}
			
			else {
				//System.out.println("Size is zero!");
				appts = null;
			}
			
			// Need to get the conditionals for the first portion working
			
			testTable.deleteAppt(appts, null);	// Catch null/null or not null/null
			//testTable.deleteAppt(appts, testAppt); // Catch not null/not null
			testTable.deleteAppt(appts, generateRandomAppt(random));
			
			// Timing 
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			if((iteration%10000)==0 && iteration!=0)
				System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		}
		
		/* Given the astronomically low odds of the code in line 82 creating the *exact same* appointment
		as earlier calls to generateRandomAppt(), I decided to do a manual check to complete coverage. */
		LinkedList<Appt> manualAppt = new LinkedList<Appt>();
		Appt testAppt = generateRandomAppt(random);
		manualAppt.add(testAppt);
		testTable.deleteAppt(manualAppt, testAppt); 
		
		System.out.println("Done testing...");
	}
	
}
