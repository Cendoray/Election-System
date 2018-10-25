package election.business;

import java.time.LocalDateTime;
import election.business.interfaces.Election;
import election.business.interfaces.StubTally;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import lib.Name;

public abstract  class DawsonVoterTest implements Voter {


	private static final long serialVersionUID = -1342803706730004830L;

	public static void main(String[] args) {
		
		testConstructor();
		System.out.println("\n\n ===========================================================================");
		testEquals();
		System.out.println("\n\n ===========================================================================");
		System.out.println("\n\n ===========================================================================");
		testIsEligible();
		System.out.println("\n\n ===========================================================================");
		testToString();
		System.out.println("\n\n ===========================================================================");	
		testCompareTo();
		System.out.println("\n\n ===========================================================================");

	}

	public static void testConstructor(){
		System.out.println("TESTING THE CONSTRUCTOR");
		testConstructor("Test 1: Checking a valid voter object \"Daniel\" \"Rafail\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", "Daniel", "Rafail", "daniel.rafail@gmail.com", "H3W 1N9", true);
		testConstructor("Test 2: Checking a valid voter object \"Daniel\" \"Rafail\" \"daniel.rafail@gmail.com\" \"H3W1N9\"", "Daniel", "Rafail", "daniel.rafail@gmail.com", "H3W1N9", true);
		testConstructor("Test 3: Checking a valid voter object \"Bob\" \"Joe\" \"Bob.Joe@gmail.com\" \"H3H 1H9\"", "Bob", "Joe", "Bob.Joe@gmail.com", "H3H 1H9", true);
		testConstructor("Test 4: Checking a empty first name \"\" \"Rafail\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", "", "Rafail", "daniel.rafail@gmail.com", "H3W 1N9", false);
		testConstructor("Test 5: Checking a empty last name \"Daniel\" \"\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", "Daniel", "", "daniel.rafail@gmail.com", "H3W 1N9", false);
		testConstructor("Test 6: Checking a empty first and last name \"\" \"\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", "", "", "daniel.rafail@gmail.com", "H3W 1N9", false);
		testConstructor("Test 7: Checking a empty email \"Daniel\" \"Rafail\" \"\" \"H3W 1N9\"", "Daniel", "Rafail", "", "H3W 1N9", false);
		testConstructor("Test 8: Checking a empty postal Code \"Daniel\" \"Rafail\" \"daniel.rafail@gmail.com\" \"\"", "Daniel", "Rafail", "daniel.rafail@gmail.com", "", false);
		testConstructor("Test 9: Checking with everything empty \"\" \"\" \"\" \"\"", "", "", "", "", false);
		testConstructor("Test 10: Checking a null first name \"\" \"Rafail\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", null, "Rafail", "daniel.rafail@gmail.com", "H3W 1N9", false);
		testConstructor("Test 11: Checking a null last name \"Daniel\" \"\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", "Daniel", null, "daniel.rafail@gmail.com", "H3W 1N9", false);
		testConstructor("Test 12: Checking a null first and last name \"\" \"\" \"daniel.rafail@gmail.com\" \"H3W 1N9\"", null, null, "daniel.rafail@gmail.com", "H3W 1N9", false);
		testConstructor("Test 13: Checking a null email \"Daniel\" \"Rafail\" \"\" \"H3W 1N9\"", "Daniel", "Rafail", null, "H3W 1N9", false);
		testConstructor("Test 14: Checking a empty postal Code \"Daniel\" \"Rafail\" \"daniel.rafail@gmail.com\" \"\"", "Daniel", "Rafail", "daniel.rafail@gmail.com", null, false);
		testConstructor("Test 15: Checking with everything null \"\" \"\" \"\" \"\"", null, null, null, null, false);
		testConstructor("Test 16: Checking with strings in the wrong order \"Daniel\" \"daniel.rafail@gmail.com\"  \"H3W 1N9\" \"Rafail\"", "Daniel","daniel.rafail@gmail.com", "H3W 1N9", "Rafail",false);

	}
	
	public static void testConstructor(String string,  String email, String firstName, String lastName, String postalCode, boolean expected){
		System.out.println(string);
		try{
			DawsonVoter voter = new DawsonVoter( email, firstName, lastName,postalCode);
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		}	
		catch (IllegalArgumentException e){
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage() +"\n");
		}
		catch (Exception aie){
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+"\n");
		}
		
	}
	public static void testEquals()
	{
		System.out.println("Testing the equals method on voter object \"daniel.rafail@gmail.com\" \"Daniel\" \"Rafail\" \"H3W 1N9\"");
		testEquals("Test 1: Sending in a null object.", null, true);
		DawsonVoter test = new DawsonVoter("daniel", "rafail", "daniel.rafail@gmail.com","h3w 1n9");
		testEquals("Test 2: Sending in same voter object with lowercased parameters.", test, true);
		DawsonVoter test2 = new DawsonVoter( "Daniel", "Rafail", "daniel.rafail@gmail.com","H3W1N9");
		testEquals("Test 3: Sending in DawsonVoter object but without space in postal code.", test2, true);
		DawsonVoter test3 = new DawsonVoter("Billy", "Joe","billy.joe@gmail.com", "K3K 3K3");
		testEquals("Test 4: Sending in a different dawsonVoter", test3, true);
		Name testname = new Name("Daan iel","Raf ail");
		testEquals("Test 5: Sending in a different object not inheriting postalcode", testname, true);	
		
		}
	public static void testEquals(String string, Object obj, Boolean expected)
	{
		System.out.println(string);
		DawsonVoter voter1 = new DawsonVoter("Daniel", "Rafail", "daniel.rafail@gmail.com","H3W 1N9");
		try
		{
			DawsonVoter voter = new DawsonVoter("Daniel", "Rafail","daniel.rafail@gmail.com","H3W 1N9");
			System.out.println(voter1.equals(obj));
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if(expected)
			System.out.println("=== EXPECTED TO WORK ===\n");
		}
		catch (IllegalArgumentException e)
		{
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+"\n");
		    if (!expected)
			System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+"\n");
		}
		catch (NullPointerException aie)
		{
			if (expected)
				System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+"\n");
		    if (!expected)
			System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+"\n");
		}
	}
		public static void testIsEligible(){
			Tally tally = new StubTally();
			DawsonVoter voter = new DawsonVoter(  "Daniel", "Rafail","daniel.rafail@gmail.com","H3W 1N9");
			System.out.println("Testing isEligible method on the voter object with a postal code of \"H3W 1N8\" and the election object with a postal code range of \"H1V 3V1\" until \"K3K 3K3\"");
			DawsonElection test = new DawsonElection("test", "single", 2017, 8, 20, 2017, 12, 30, "B1B 3B1", "C3C 3C1",  tally, "Yes, Free Gym", "No, Free Gym");
			testIsEligible("Test 1: Sending in same voter object and election object with postal code out of range (lower).", test, voter, true);
			DawsonElection test2 = new DawsonElection("test", "single", 2017,8, 20, 2017, 12, 30, "P1P 3P1", "R3R 3R1",  tally, "Yes, Free Gym", "No, Free Gym");
			testIsEligible("Test 2: Sending in same voter object and election object with postal code out of range (higher).", test2, voter, true);
			DawsonElection test3 = new DawsonElection("test", "single", 2016, 8, 20, 2016,  12, 30, "H1V 3V1", "K3K 3K3", tally, "Yes, Free Gym", "No, Free Gym");
			testIsEligible("Test 3: Sending in same voter object and election object with a good postal code but a date out of range(lower) ", test3, voter, true);
			DawsonElection test4 = new DawsonElection("test", "single", 2018, 8, 20,2018, 12, 30, "H1V 3V1", "K3K 3K3", tally, "Yes, Free Gym", "No, Free Gym");
			testIsEligible("Test 4: Sending in same voter object and election object with a good postal code but a date out of range(higher) ", test4, voter,true);
			DawsonElection test5 = new DawsonElection("test", "single", 2017,8, 20,2017,  12, 30, "H1V 3V1", "K3K 3K3", tally, "Yes, Free Gym", "No, Free Gym");
			testIsEligible("Test 5: Sending in same voter object and valid election object ", test5, voter,true);
			
		}
		public static void testIsEligible(String string, Election election, Voter voter, Boolean expected){
			System.out.println(string);
			
			try
			{
				Tally tally = new StubTally();
				LocalDateTime now = LocalDateTime.now();
				DawsonVoter voter1 = new DawsonVoter( "Daniel", "Rafail", "daniel.rafail@gmail.com","H3W 1N9");
				DawsonElection election1 = new DawsonElection("test", "single", 2017,8, 20,2017,  12, 30, "H1V 3V1", "K3K 3K3", tally, "Yes, Free Gym", "No, Free Gym");
				System.out.println(voter1.isEligible(election));
				if (!expected)
					System.err.println("=== EXPECTED TO FAIL ===\n");
				if(expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
			}
			catch (IllegalArgumentException e)
			{
				if (expected)
					System.err.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+"\n");
			    if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+"\n");
			}
			catch (NullPointerException aie)
			{
				if (expected)
					System.err.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+"\n");
			    if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+"\n");
			}
			
		}	
		public static void testCompareTo(){
			System.out.println("Testing the compareTo on voter object \"daniel.rafail@gmail.com\" \"Daniel\" \"Rafail\" \"H3W 1N9\"");
			DawsonVoter voter1 = new DawsonVoter("Daniel", "Rafail","daniel.rafail@gmail.com",  "H3W 1N9");
			DawsonVoter test = new DawsonVoter( "daniel", "rafail","DANIEL.RAFAIL@GMAIL.COM", "H3W 1N9");
			testCompareTo("Test 1: Sending in same voter object with upper cased email.", voter1, test, true);
			DawsonVoter test2 = new DawsonVoter( "Daniel", "Rafail","daniel.rafail@GMAIL.com","H3W1N9");
			testCompareTo("Test 2: Sending in DawsonVoter object with only gmail capital.", voter1, test2, true);
			DawsonVoter test3 = new DawsonVoter("Daniel", "Rafail", "daniel.rafail@gmail.COM", "H3W1N9");
			testCompareTo("Test 3: Sending in DawsonVoter object with only .com capital.", voter1, test3, true);
			DawsonVoter test4 = new DawsonVoter("Daniel", "Rafail", "DANIEL.RAFAIL@gmail.com", "H3W1N9");
			testCompareTo("Test 4: Sending in DawsonVoter object with first half in capital.", voter1, test4, true);
			DawsonVoter test5 = new DawsonVoter( "Billy", "Joe", "billy.joe@gmail.com","K3K 3K3");
			testCompareTo("Test 5: Sending in a different dawsonVoter", voter1, test5, true);
			DawsonVoter test6 = new DawsonVoter( "Jonathon", "Joe", "Jonathon.joe@gmail.com","K3K 3K3");
			testCompareTo("Test 6: Sending in a different dawsonVoter", voter1, test6, true);
		}
		
		public static void testCompareTo(String string, Voter obj, Voter voter, Boolean expected){
			System.out.println(string);
			try
			{
				DawsonVoter voter1 = new DawsonVoter("Daniel", "Rafail", "daniel.rafail@gmail.com","H3W 1N9");
				System.out.println(voter.compareTo(obj));
				if (!expected)
					System.out.println("=== EXPECTED TO FAIL ===\n");
				if(expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
			}
			catch (IllegalArgumentException e)
			{
				if (expected)
					System.out.println("=== EXPECTED TO WORK CORRECTLY === " + e.getMessage()+"\n");
			    if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + e.getMessage()+"\n");
			}
			catch (NullPointerException aie)
			{
				if (expected)
					System.out.println("=== EXPECTED TO WORK CORRECTLY === " + aie.getMessage()+"\n");
			    if (!expected)
				System.out.println("=== EXPECTED TO FAIL MISERABLY === " + aie.getMessage()+"\n");
			}
		}	
		
		public static void testToString(){
			DawsonVoter voter = new DawsonVoter( "Daniel", "Rafail","daniel.rafail@gmail.com","H3W 1N9");
			System.out.println(voter.toString());
		}
}
