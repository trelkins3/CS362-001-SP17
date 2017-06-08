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

/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
 
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }


   public void testManualTest()
   {
	   // Not sure what's broken and what isn't, so we're gonna check all of the standard options
	   
	   // Check standard options
	   UrlValidator urlVal = new UrlValidator();
	   assertTrue(urlVal.isValid("http://www.amazon.com"));
	   assertFalse(urlVal.isValid("www.amazon.com"));	// Should fail because no scheme
	   assertFalse(urlVal.isValid(null));
	   assertFalse(urlVal.isValid("汉"));	// Should fail because Non-ASCII
	   assertFalse(urlVal.isValid("http://amazon.com///test1"));
	   
	   // Check all scheme option
	   urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertTrue(urlVal.isValid("htt://www.amazon.com"));	
	   assertTrue(urlVal.isValid("ftp://www.amazon.com"));	
	   assertTrue(urlVal.isValid("h://www.amazon.com"));	
	   assertFalse(urlVal.isValid("www.amazon.com"));				// Should fail because no scheme
	   assertFalse(urlVal.isValid("http://localhost/home/index.html"));	// Should be false
	   
	   // Check local URL option
	   urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_LOCAL_URLS);
	   /* FOUND A FAIL CASE HERE - Both lines 54 and 55 fail. */
	   //assertTrue(urlVal.isValid("http://localhost/home/index.html"));	// Should be true
	   //assertTrue(urlVal.isValid("http://localhost/index.html"));	// Should be true
	   assertFalse(urlVal.isValid("localhost/test/index.html"));	// No scheme
	   
	   // Check double slashes option
	   urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_2_SLASHES);
	   assertTrue(urlVal.isValid("http://www.amazon.com/test1//test2"));
	   /* EITHER A BUG OR AN OVERSIGHT? - More than 2 slashes can be added when the option is on */
	   //assertFalse(urlVal.isValid("http://www.amazon.com/test1///test2"));
   }
   
   
   public void testYourFirstPartition()
   {
	   
   }
   
   public void testYourSecondPartition(){
	   
   }
   
   
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
	*/

}