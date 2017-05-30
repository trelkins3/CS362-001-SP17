package edu.osu.cs362;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

// Random test generator for Appt Class
// Original code from Ali Aburas (?)
public class ApptRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	// Randomly select and return method to be tested from given methods
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setTitle","setDescription","isValid"}; // List of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length); // get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
    }
		
	// Randomly select and return data member to be tested from given members
	public static String RandomSelectDataMember(Random random){
		String[] dataMemberArray = new String[] {"startHour","startMinute","startDay","startMonth"}; // List of members to be tested in the Appt class
		
		int n = random.nextInt(dataMemberArray.length);
		
		return dataMemberArray[n]; // return the member name 
	}
	
	// Generate random tests for Appt
	@Test
	public void randomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
		System.out.println("Start testing...");
		 
		 
		for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			long randomseed = 10;//System.currentTimeMillis();
	//		System.out.println(" Seed:"+randomseed );
			Random random = new Random(randomseed);
				
			int startHour = 13;
			int startMinute = 30;
			int startDay = 10;
			int startMonth = 4;
			int startYear = 2017;
			String title = "Birthday Party";
			String description = "This is my birthday party.";
			//Construct a new Appointment object with the initial data	 
			Appt appt = new Appt(startHour,
				        startMinute ,
				        startDay ,
				        startMonth ,
				        startYear ,
				        title,
				        description);
						
			for (int i = 0; i < NUM_TESTS; i++) {
				String methodName = ApptRandomTest.RandomSelectMethod(random);
					if (methodName.equals("setTitle")){
						String newTitle=(String) ValuesGenerator.getString(random);
						appt.setTitle(newTitle);						   
					}
					
					else if (methodName.equals("setDescription")){
						String newDescription=(String) ValuesGenerator.getString(random);
						appt.setDescription(newDescription);						   
					}
						
					else if (methodName.equals("isValid")){
						String memberName = ApptRandomTest.RandomSelectDataMember(random);
						int newVal = (int) ValuesGenerator.getRandomIntBetween(random, -75, 75);
						if(memberName.equals("startHour")){
							appt.setStartHour(newVal);
						}
						else if(memberName.equals("startMinute")){
							appt.setStartMinute(newVal);
						}
						else if(memberName.equals("startDay")){
							appt.setStartDay(newVal);
						}
						else if(memberName.equals("startMonth")){
							appt.setStartMonth(newVal);
						}
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
