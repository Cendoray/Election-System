package election.business;

import election.business.VoterPostalCodeComparator;
import election.business.DawsonVoter;
/**
 * Testing the voterpostalcodecomparator
 * 
 * @author Anthony Whitebean
 * @version 2017-10-22
 */
public class VoterPostalCodeComparatorTest {
	public static void main(String[] args)
	{
		testVoterPostalComparator();
	}
	
	public static void testVoterPostalComparator()
	{
		DawsonVoter voter1 = new DawsonVoter("Bob", "Jones","BobJones@gmail.com",  "A1B2C3");
		DawsonVoter voter2 = voter1;
		DawsonVoter voter3 = new DawsonVoter( "Dave", "Jake", "DaveJake@gmail.com","A1B2C3");
		DawsonVoter voter4 = new DawsonVoter("Carl", "Mitton","DaBeast@hotmail.com",  "A2C3B2");
		testVoterPostalComparator("Test 1: Testing equal voters", voter1, voter2, true);
		testVoterPostalComparator("Test 2: Testing voters with same postal code but different names", voter1, voter3, true);
		testVoterPostalComparator("Test 3: Testing voters with different postal code and names", voter1, voter4, true);
	}

	private static void testVoterPostalComparator(String string, DawsonVoter voter1, DawsonVoter voter2, boolean check) throws IllegalArgumentException, NullPointerException{
		try {
			System.out.println(string);
			System.out.println(voter1);
			System.out.println(voter2);
			VoterPostalCodeComparator test = new VoterPostalCodeComparator();
			System.out.println(test.compare(voter1,voter2));
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Failed");
			if (check == false)
			{
				System.err.println("Expected to work:" + e.getMessage());
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("Failed");
			if (check == false)
			{
				System.err.println("Expected to work:" + e.getMessage());
			}
		}
		catch (Exception e)
		{
			System.out.println("Failed");
			if (check == false)
			{
				System.err.println("Expected to work:" + e.getMessage());
			}			
		}
		
	}
}
