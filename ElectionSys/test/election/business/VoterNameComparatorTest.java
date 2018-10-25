package election.business;

import election.business.VoterNameComparator;
import election.business.DawsonVoter;

public class VoterNameComparatorTest {
	public static void main (String[] args)
	{
		testNameComparator();
	}
		
	public static void testNameComparator()
	{
		DawsonVoter voter1 = new DawsonVoter("Bob", "Jones", "BobJones@gmail.com", "A1B2C3");
		DawsonVoter voter2 = voter1;
		DawsonVoter voter3 = new DawsonVoter( "Dave", "Jake", "DaveJake@gmail.com","A1B2C3");
		DawsonVoter voter4 = new DawsonVoter("Bob", "Jones", "FaralMan@hotmail.com", "A2C3B2");
		testNameComparator("Test 1: Testing equal voters", voter1, voter2, true);
		testNameComparator("Test 2: Testing voters with different name", voter1, voter3, true);
		testNameComparator("Test 3: Testing voters with same name but different postal and email", voter1, voter4, true);
	}

	private static void testNameComparator(String string, DawsonVoter voter1, DawsonVoter voter2, boolean check) throws IllegalArgumentException, NullPointerException{
		try 
		{
			System.out.println(string);
			System.out.println(voter1);
			System.out.println(voter2);
			VoterNameComparator test = new VoterNameComparator();
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

