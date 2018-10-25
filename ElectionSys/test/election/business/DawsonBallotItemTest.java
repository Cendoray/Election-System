/**
 * 
 */
package election.business;

import election.business.interfaces.BallotItem;
import lib.Name;

/**
 * @author Daniel Rafail
 * @version 9/25/2017
 */
public class DawsonBallotItemTest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8047068248808259392L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testConstructor();
		System.out.println("\n\n ===========================================================================");
		testEquals();
		System.out.println("\n\n ===========================================================================");
		testCompareTo();
		System.out.println("\n\n ===========================================================================");



	}

	public static void testConstructor(){
		System.out.println("TESTING THE CONSTRUCTOR");
		DawsonBallotItem item = new DawsonBallotItem("Yes", 10, 4);
		testConstructor("Test 1: Checking a valid DawsonBallotItem object \"Yes\" \"10\" \"1\"", "Yes", 10, 1, true);
		testConstructor("Test 2: Checking with maxValue smaller than value \"Yes\" \"10\" \"40\"", "Yes", 10, 40, false);
		testConstructor("Test 3: Checking with value being negative \"Yes\" \"10\" \"-5\"", "Yes", 10, -5, false);
		testConstructor("Test 4: Checking with choice being null \"\" \"10\" \"5\"", null, 10, 5, false);
		testConstructor("Test 5: Checking with choice being empty \"\" \"5\" \"10\"", "", 10, 5, false);
		testConstructor("Test 6: Checking with maxValue being negative \"yes\" \"-5\" \"5\"", "Yes", -5, 5, false);		
		testConstructor("Test 7: Checking with value and maxValue being negative \"Yes\" \"-5\" \"-6\"", "Yes", -5, -6, false);	
		testConstructor("Test 8: Checking with value and maxValue being negative, and value being bigger than maxValue \"Yes\" \"-5\" \"-1\"", "Yes", -5, -1, false);	
		testConstructor("Test 9: Checking with copy of testConstructor1 \"item\"", item, true);	
		testConstructor("Test 10: Checking without a value and an invalid maxValue \"Yes\" \"-5\"", "Yes", -5, false);			
		testConstructor("Test 11: Checking without a value  \"Yes\" \"5\"", "Yes", 5, true);	

		

	}
	
	public static void testConstructor(String str, String choice, int maxValue, int value, boolean expected){
		System.out.println(str);
		try{
			DawsonBallotItem item = new DawsonBallotItem(choice, maxValue, value);
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		}	
		catch (IllegalArgumentException e){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage() + "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+ "\n");
		}
		catch (Exception aie){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+ "\n");
		}
	}
	
	public static void testConstructor(String str, String choice,int maxValue, boolean expected){
		System.out.println(str);
		try{
			DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL === \n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK === \n");
		}	
		catch (IllegalArgumentException e){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+ "\n");
		}
		catch (Exception aie){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+ "\n");
		}
	}
	
	public static void testConstructor(String str, DawsonBallotItem item, boolean expected){
		System.out.println(str);
		try{
			DawsonBallotItem item2 = new DawsonBallotItem(item);
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		}	
		catch (IllegalArgumentException e){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+ "\n");
		}
		catch (Exception aie){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+ "\n");
		}
	}

	public static void testEquals(){
		System.out.println("Testing the equals method on DawsonBallotItem object \"Yes\" \"4\" \"10\"");
		DawsonBallotItem item = new DawsonBallotItem("Yes", 10, 1);
		testEquals("Test 1: Sending in a null object.", null, true);
		DawsonBallotItem test = new DawsonBallotItem("yes", 10, 4);
		testEquals("Test 2: Sending in same DawsonBallotItem object with lowercased parameters.", test, true);
		DawsonBallotItem test2 = new DawsonBallotItem("Yes", 10, 3);
		testEquals("Test 3: Sending in same DawsonBallotItem object with different value.", test2, true);
		DawsonBallotItem test3 = new DawsonBallotItem("Yes", 100, 4);
		testEquals("Test 4: Sending in same DawsonBallotItem object with different maxValue.", test3, true);
		DawsonBallotItem test4 = new DawsonBallotItem("Yes", 100, 40);
		testEquals("Test 5: Sending in same DawsonBallotItem object with different maxValue and value.", test4, true);
		DawsonBallotItem test5 = new DawsonBallotItem("Yes", 100);
		testEquals("Test 6: Sending in same DawsonBallotItem object without a value.", test5, true);
		DawsonBallotItem test6 = new DawsonBallotItem(item);
		DawsonBallotItem item2 = new DawsonBallotItem("Yes", 100, 40);
		testEquals("Test 7: Sending in same DawsonBallotItem object with a copy.", test6, true);
		testEquals("Test 8: Sending in same DawsonBallotItem object with a copy.", item2, true);
		Name testname = new Name("Daan iel","Raf ail");
		testEquals("Test 9: Sending in a different object not inheriting DawsonBallotItem", testname, false);
	}

	public static void testEquals(String str, Object obj, boolean expected){
		System.out.println(str);
		try{
			DawsonBallotItem item = new DawsonBallotItem("Yes", 10, 4);
			DawsonBallotItem item2 = new DawsonBallotItem(item);
			System.out.println(item.equals(obj));
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL === \n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		}	
		catch (IllegalArgumentException e){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+ "\n");
		}
		catch (Exception aie){
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+ "\n");
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+ "\n");
		}
	}
	
	public static void testCompareTo(){
		System.out.println("Testing the CompareTo method on DawsonBallotItem object \"Yes\" \"10\" \"4\"");
		DawsonBallotItem item = new DawsonBallotItem("Yes", 10, 4);
		testCompareTo("Test 1: Sending in a null object.", item, null, true);
		DawsonBallotItem test = new DawsonBallotItem("yes", 10, 4);
		testCompareTo("Test 2: Sending in same DawsonBallotItem object with lowercased parameters.", item, test, true);
		DawsonBallotItem test2 = new DawsonBallotItem("Yes", 10);
		testCompareTo("Test 4: Sending in same DawsonBallotItem object with no value.", item, test2, true);
		DawsonBallotItem test3 = new DawsonBallotItem("Hi", 10, 4);
		testCompareTo("Test 3: Sending in same DawsonBallotItem object with different choice(smaller).", item, test3, true);
		DawsonBallotItem test4 = new DawsonBallotItem("Hey", 10, 4);
		testCompareTo("Test 4: Sending in same DawsonBallotItem object with different choice(same length).", item, test4, true);
		DawsonBallotItem test5 = new DawsonBallotItem("Hello", 10, 4);
		testCompareTo("Test 5: Sending in same DawsonBallotItem object with different choice(bigger).", item, test5, true);
		DawsonBallotItem test6 = new DawsonBallotItem("Yes", 10, 6);
		testCompareTo("Test 6: Sending in same DawsonBallotItem object with different value.", item, test6, true);
		DawsonBallotItem test7 = new DawsonBallotItem("Yes", 100, 3);
		testCompareTo("Test 7: Sending in same DawsonBallotItem object with different maxValue.", item, test7, true);
		DawsonBallotItem test8 = new DawsonBallotItem("Yes", 100, 40);
		testCompareTo("Test 8: Sending in same DawsonBallotItem object with different maxValue and value.", item, test8, true);
	}
	
	public static void testCompareTo(String str, DawsonBallotItem obj, DawsonBallotItem item, boolean expected){
		System.out.println(str);
		try
		{
			DawsonBallotItem item1 = new DawsonBallotItem("Yes", 10, 4);
			System.out.println(item.compareTo(obj));
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if(expected)
			System.out.println("=== EXPECTED TO WORK ===\n");
		}
		catch (NullPointerException aie)
		{
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+ "\n");
		    if (!expected)
			System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+ "\n");
		}
		catch (IllegalArgumentException e)
		{
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+ "\n");
		    if (!expected)
			System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+ "\n");
		}
		
	}
}
