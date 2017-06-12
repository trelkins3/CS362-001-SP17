/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package finalprojectB;

import junit.framework.TestCase;
import java.util.Random;
import java.util.Calendar;

/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */

public class UrlValidatorTest extends TestCase {

   private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
   private static final int NUM_TESTS=100;

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   String[] validSchemes = new String[] {"http://", "ftp://", "https://"};
   String[] invalidSchemes = new String[] {"3ht://", "http:/", "http/", "://", "", "h3t://"};
   String[] validAuthorities = new String[] {"www.amazon.com", "amazon.au", "255.255.255.255"};
   String[] invalidAuthorities = new String[] {"amazon.ckaso", "", "aaa", "1.2.3.4.5"};
   String[] validPorts = new String[] {":80", ":65555", ":0", ""};
   String[] invalidPorts = new String[] {":alkj", "80aewt", ":-5"};
   String[] validPaths = new String[] {"/test", "/564543", "/test/", "", "/test/file"};
   String[] invalidPaths = new String[] {"//test", "/test//file", "/.../"};
   String[] queries = new String[] {"", "?action=view"};

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   // Pick a random element from a given string array
   public static String selectMember(Random random, String[] array) {
      int n = random.nextInt(array.length);
      return array[n];
   }

   public static boolean getRandomBool(Random random) {
      random = new Random();
      return random.nextBoolean();
   }

   // Standard options test
   public void testIsValid01() {
      // Check standard options
      UrlValidator urlVal = new UrlValidator();
      assertTrue(urlVal.isValid("http://www.amazon.com"));
      assertTrue(urlVal.isValid("http://www.amazon.com/index.html"));
      assertTrue(urlVal.isValid("http://www.amazon.com:80/test/index.html"));
      assertFalse(urlVal.isValid("www.amazon.com"));	// Should fail because no scheme
      assertFalse(urlVal.isValid(null));
      assertFalse(urlVal.isValid("汉"));	// Should fail because Non-ASCII
      assertFalse(urlVal.isValid("http://amazon.com///test1"));
   }

   // Scheme testing
   public void testIsValid02() {
      String[] schemes = {"http","https"};
      UrlValidator urlVal = new UrlValidator(schemes);
      // Valid URL
      assertTrue(urlVal.isValid("http://www.amazon.com/pathQuery#Fragment"));
      // Invalid Schemes
      assertFalse(urlVal.isValid("htt://www.amazon.com/pathQuery#Fragment"));
      assertFalse(urlVal.isValid("httpss://www.amazon.com/pathQuery#Fragment"));
      // Invalid Host Name
      assertFalse(urlVal.isValid("http:///pathQuery#Fragment"));
      // Invalid path, "//"" not allowed
      assertFalse(urlVal.isValid("http://www.amazon.com//pathQuery#Fragment"));
      // Valid Query, null query returns true.
      assertTrue(urlVal.isValid("http://www.amazon.com/path"));
      // BUG, the beginning of the Query should be denoted by "?", but it is disallowed.
      assertTrue(urlVal.isValid("http://www.amazon.com/path?Query#Fragment"));

      urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
      // Valid Schemes, all properly formatted schemes should be valid.
      assertTrue(urlVal.isValid("http://www.amazon.com/path.htmlQuery#Fragment"));
      assertTrue(urlVal.isValid("ht://www.amazon.com/path.htmlQuery#Fragment"));
      assertTrue(urlVal.isValid("htttps://www.amazon.com/path.htmlQuery#Fragment"));
      assertTrue(urlVal.isValid("httttttttsssssssss://www.amazon.com/path.htmlQuery#Fragment"));
      // Invalid scheme, numbers are not proper format?
      assertFalse(urlVal.isValid("12345://www.amazon.com/path.htmlQuery#Fragment"));
      // Invalid scheme, scheme cannot be null
      assertFalse(urlVal.isValid("://www.amazon.com/path.htmlQuery#Fragment"));
   }

   // Fragment testing
   public void testIsValid03() {
      UrlValidator urlVal = new UrlValidator(UrlValidator.NO_FRAGMENTS);
      // Valid URL, no fragments
      assertTrue(urlVal.isValid("http://www.amazon.com/path.htmlQuery"));
      // Invalid URL, fragments present
      assertFalse(urlVal.isValid("http://www.amazon.com/path.html#Fragment"));
   }

   // Double Slash test
   public void testIsValid04() {
      UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);
      // Valid URL
      assertTrue(urlVal.isValid("http://www.amazon.com/pathQuery#Fragment"));
      // Valid Path, "//" allowed
      assertTrue(urlVal.isValid("http://www.amazon.com//pathQuery#Fragment"));
      // BUG. Invalid Path, ALLOW_2_SLASHES states that it allows 2 slashes in the path, adding more should be invalid but is not.
      assertFalse(urlVal.isValid("http://www.amazon.com////////pathQuery#Fragment"));
      // Invalid Path, zero path slashes 
      assertFalse(urlVal.isValid("http://www.amazon.compathQuery#Fragment"));
   }

   // Local URL test
   public void testIsValid05() {
      UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
      // BUG, Valid Local URL, does not accept.
      assertTrue(urlVal.isValid("http://amazon/"));
      // Valid Local Url
      assertTrue(urlVal.isValid("http://amazon/path.html"));
      /* FOUND A FAIL CASE HERE */
      assertTrue(urlVal.isValid("http://localhost/home/index.html"));	// Should be true
      assertTrue(urlVal.isValid("http://localhost/index.html"));	// Should be true
      assertFalse(urlVal.isValid("localhost/test/index.html"));	// No scheme
   }

   // Test valid partitions
   public void testValidRandomIsValid()	throws Throwable {

      long startTime = Calendar.getInstance().getTimeInMillis();
      long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

      System.out.println("Start testing...");

      for(int iteration = 0; elapsed < TestTimeout; iteration++) {
	 long randomseed = 7;	// Change this for different values
	 Random random = new Random(randomseed);
	 UrlValidator urlVal = new UrlValidator();

	 for (int i = 0; i < NUM_TESTS; i++) {
	    String Scheme = selectMember(random, validSchemes);
	    String Authority = selectMember(random, validAuthorities);
	    /* BUG - Ports seem to sometimes cause URL validity to fail. */
	    String Ports = selectMember(random, validPorts);
	    String Paths = selectMember(random, validPaths);
	    /* BUG, all non-null queries throw an exception */
	    String Query = selectMember(random, queries); 
	    String URL = Scheme + Authority + Ports + Paths + Query;

	    assertTrue(URL, urlVal.isValid(URL)); 
	 }

	 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	 if((iteration%10000)==0 && iteration!=0)
	    System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
      }

      System.out.println("Done testing...");
   }

   // Test invalid partitions
   public void testInvalidRandomIsValid() throws Throwable {

      long startTime = Calendar.getInstance().getTimeInMillis();
      long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

      System.out.println("Start testing...");

      for(int iteration = 0; elapsed < TestTimeout; iteration++) {
	 long randomseed = 7;	// Change this for different values
	 Random random = new Random(randomseed);
	 UrlValidator urlVal = new UrlValidator();

	 for (int i = 0; i < NUM_TESTS; i++) {
	    String Scheme = selectMember(random, invalidSchemes);
	    String Authority = selectMember(random, invalidAuthorities);
	    String Ports = selectMember(random, invalidPorts);
	    String Paths = selectMember(random, invalidPaths);
	    String Query = selectMember(random, queries); 
	    String URL = Scheme + Authority + Ports + Paths + Query;

	    assertFalse(URL, urlVal.isValid(URL)); 
	 }

	 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	 if((iteration%10000)==0 && iteration!=0)
	    System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
      }

      System.out.println("Done testing...");
   }
}
