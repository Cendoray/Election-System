/**
 * This code will test the postal code in order to make it tip top shape!
 */
package lib;
import lib.PostalCode;
import lib.Name;
/**
 * @author Anthony Whitebean
 * @version 9/22/2017
 *
 */
public class PostalCodeTest {
	public static void main(String[] args)
	{
		testConstructor();
		testEqual();
		testInRange();
	}
	
	public static void testConstructor()
	{
		System.out.println("Testing constructor:");
		testConstructor("Test 1: Checking a valid code H1B2C3","H1B2C3",true);
		testConstructor("Test 2: Checking a valid code H1B 2C3","H1B 2C3",true);
		testConstructor("Test 3: Checking a space code", "      ", false);
		testConstructor("Test 4: Checking a lowercase code h1b2c3", "h1b2c3", true);
		testConstructor("Test 5: Checking a code with number in wrong place 11b2c3", "1hb2c3", false);
		testConstructor("Test 6: Checking a code with letter in wrong place hhb2c3", "1hb2c3", false);
		testConstructor("Test 7: Code starts with illegal letter W W1b2c3", "W1b2c3", false);
		testConstructor("Test 8: Code starts with illegal letter Z Z1b2c3", "Z1b2c3", false);
		testConstructor("Test 9: Checking a valid code containing illegal start letters Z W H1B2C3","H1z2w3",true);
		testConstructor("Test 10: Code contains illegal letter D a1b2D3", "a1b2D3", false);
		testConstructor("Test 11: Code contains illegal letter F a1F 2F3", "a1F 2F3", false);
		testConstructor("Test 12: Code contains illegal letter I a1b2I3", "a1b2I3", false);
		testConstructor("Test 13: Code contains illegal letter O a1O 2c3", "a1O 2c3", false);
		testConstructor("Test 14: Code contains illegal letter Q a1b 2Q3", "a1b 2Q3", false);
		testConstructor("Test 15: Code contains illegal letter U a1b2U3", "a1b2U3", false);
		testConstructor("Test 16: Code is null", null, false);
		testConstructor("Test 17: Code is not correct length Abcde", "Abcde", false);
	}
	
	public static void testConstructor(String string, String code, boolean expected)
	{
		System.out.println(string);
		try {
			PostalCode attempt = new PostalCode(code);
			if (expected == false)
			{
				System.err.println("Expected to fail");
			}
			System.out.println("Worked");
		}
		catch (IllegalArgumentException e)
		{
			if (expected == true)
			{
				System.err.println("Expected to be able to work:" + e.getMessage());
		    }
			System.out.println("Failed");
		}
		catch (NullPointerException e)
		{
			if (expected == true)
			{
				System.err.println("Expected to be able to work" + e.getMessage());
		    }
			System.out.println("Failed");
		}
	}	
	public static void testEqual()
	{
		System.out.println("Testing the equals method on string A1B2C3:");
		testEqual("Test 1: Sending in a null object.", null, true);
		PostalCode test = new PostalCode("a1b2c3");
		testEqual("Test 2: Sending in same postalcode lowercased.", test, true);
		PostalCode test2 = new PostalCode("A1B 2C3");
		testEqual("Test 3: Sending in same postalcode with space.", test2, true);
		PostalCode test3 = new PostalCode("A1C 2C3");
		testEqual("Test 4: Sending in a different postalcode", test3, true);
		Name testname = new Name("Anthony","Whitebean");
		testEqual("Test 5: Sending in a different object not inheriting postalcode", testname, true);	
	}
	
	public static void testEqual(String string, Object object, Boolean expected)
	{
		System.out.println(string);
		PostalCode code = new PostalCode("A1B2C3");
		try
		{
			System.out.println(code.equals(object));
			if (expected == false)
			{
				System.err.println("Expected to fail");
			}
			System.out.println("Worked");
		}
		catch (IllegalArgumentException e)
		{
			if (expected == true)
			{
				System.err.println("Expected to be able to work" + e.getMessage());
		    }
			System.out.println("Failed");
		}
		catch (NullPointerException e)
		{
			if (expected == true)
			{
				System.err.println("Expected to be able to work" + e.getMessage());
		    }
			System.out.println("Failed");
		}
		
	}
	
	public static void testInRange()
	{
		System.out.println("Testing the in range with B2B2B2:");
		testInRange("Test 1:Sending in null strings", null, null, false);
		testInRange("Test 2:Within range of A1 and C3", "A1", "C3", true);
		testInRange("Test 3:Within range of A1 and C3A", "A1", "C3A", true);
		testInRange("Test 4:Overmaximum of A1 and B1", "A1", "B1", true);
		testInRange("Test 5:Underminimum of C1 and C3", "C1", "C3", true);
		testInRange("Test 6:Sending in a string with spaces", "   C1   ", "C3", true);
		testInRange("Test 7:Sending in a string with a space in middle", "A1A 1A1", "C3C3C3", true);
		testInRange("Test 8:Sending in a large string without a space", "A1A1A1","C3C3C3",true);
	}
	
	public static void testInRange(String string, String start, String end, Boolean expected)
	{
		System.out.println(string);
		PostalCode code = new PostalCode("B2B2B2");
		try
		{
			System.out.println(code.inRange(start,end));
			if (expected == false)
			{
				System.err.println("Expected to fail");
			}
			System.out.println("Worked");
		}
		catch (IllegalArgumentException e)
		{
			if (expected == true)
			{
				System.err.println("Expected to be able to work" + e.getMessage());
		    }
			System.out.println("Failed");
		}
		catch (NullPointerException e)
		{
			if (expected == true)
			{
				System.err.println("Expected to be able to work" + e.getMessage());
		    }
			System.out.println("Failed");
		}
		
	}
}
