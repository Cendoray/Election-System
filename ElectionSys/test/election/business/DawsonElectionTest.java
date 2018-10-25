/**
 * 
 */
package election.business;

import java.time.DateTimeException;

import election.business.DawsonElection;
import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.Election;
import election.business.interfaces.StubBallot;
import election.business.DawsonTally;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import lib.Name;

/**
 * This will make sure the election is in tip top shape.
 * 
 * @author AnthonyWhiebean
 * @version 2017/09/27
 */
//Fix code on DawsonTally.
public class DawsonElectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] choices = {"One", "Two", "Three"};
		Election newFunctions = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("Test", "SINGLE", 2016, 06, 25, 2017, 12, 25, "A1A1A1", "C3C3C3", choices);
		System.out.println("=====");
		testConstructor();
		System.out.println("=====");
		testIsLimitedTo();
		System.out.println("=====");
		testSetTally();
		System.out.println("=====");
		testGetBallot(newFunctions);
		System.out.println("=====");
		testEquals();
		System.out.println("=====");
	}

	private static void testGetBallot(Election election) {
		System.out.println("Testing the getBallot method");
		Voter voter = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("antbean@hotmail.com","anthony", "whitebean", "A1A1A1");
		Voter voter2 = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("antbean@hotmail.com","anthony", "whitebean", "A1A1A1");
		Voter voter3 = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("justcause@hotmail.com","dude", "why", "E4E4E4");
		testGetBallot("Test 1: Testing with correct data", election, voter, true);
		testGetBallot("Test 2: Testing with voter that is not valid for election", election, voter3, false);
		System.out.println("(Next part will test cast, as this step is needed to test same voter");
		System.out.println("!!!!!");
		testCastBallot(election);
		System.out.println("!!!!!");
		testGetBallot("Test 3 (AfterCaseBallot): Testing with voter who already case", election, voter2, false);
		
	}
	
	private static void testGetBallot(String string, Election election, Voter voter, Boolean check)
	{
		try
		{
			System.out.println(string);
			election.getBallot(voter);
			if (check == false)
			{
				System.err.println("Was expected to fail");
			}
			System.out.println(" ");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			if (check == true)
			{
				System.err.println("Expected to work");
			}
			System.out.println(" ");
		}
	}

	private static void testEquals() {
		System.out.println("Testing the equals method");
		System.out.println("-------------------------");
		String[] goodString = new String[3];
		goodString[0] = "One";
		goodString[1] = "Two";
		goodString[2] = "Three";
		Tally tally = new DawsonTally(1,"NAME");
		Tally badTally = new DawsonTally(1,"DAVEVEBSG");
		Name name = new Name("Anthony", "Whitebean");
		DawsonElection original = new DawsonElection("NAME", "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, tally,
				goodString);
		DawsonElection good = new DawsonElection("NAME", "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, tally,
				goodString);
		DawsonElection bad = new DawsonElection("DAVEVEBSG", "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, badTally,
				goodString);
		testEquals("Test 1: Testing the original election with itself", original, original);
		testEquals("Test 2: Testing the original election with another election with the same name", original, good);
		testEquals("Test 3: Testing the original election with another election with a different name", original, bad);
		testEquals("Test 4: Testing the original election with a null election", original, null);
		testEquals("Test 5: Testing the original election with another object", original, name);

	}

	private static void testEquals(String string, DawsonElection original, Object copy)
			throws NullPointerException, IllegalArgumentException {
		try {
			System.out.println(string);
			System.out.println(original.equals(copy));
			System.out.println();
		} catch (NullPointerException e) {
			System.err.println("There was no NullPointerExpection expected");
			System.out.println();
		} catch (IllegalArgumentException e) {
			System.err.println("There was no IllegalArgumentExpected expected");
			System.out.println();
		}
	}

	private static void testSetTally() {
		System.out.println("Testing the set tally");
		System.out.println("---------------------");
		Tally tally = new DawsonTally(1,"NAME");
		String[] goodString = new String[3];
		goodString[0] = "One";
		goodString[1] = "Two";
		goodString[2] = "Three";
		DawsonElection original = new DawsonElection("NAME", "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, tally,
				goodString);
		testSetTally("Test 1: Testing the set tally with an election with the same name", original, original, true);
		testSetTally("Test 2: Testing the set tally with a null", original, null, false);
	}

	private static void testSetTally(String string, DawsonElection original, DawsonElection copy, boolean expected)
			throws NullPointerException, IllegalArgumentException {
		try {
			System.out.println(string);
			System.out.println(original.getTally().getElectionName());
			System.out.println(copy.getTally().getElectionName());
			original.setTally(copy.getTally());
			if (expected == false) {
				System.err.println("This was expected to fail.");
			}
			System.out.println();
		} catch (NullPointerException e) {
			System.out.println("There was a null argument exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("This was expected to work.");
			}
			System.out.println();
		} catch (IllegalArgumentException e) {
			System.out.println("There was an illegal argument exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("This was expected to work.");
			}
			System.out.println();
		}
	}

	private static void testIsLimitedTo() {
		String[] goodString = new String[3];
		goodString[0] = "One";
		goodString[1] = "Two";
		goodString[2] = "Three";
		Tally tally = new DawsonTally(1,"Name");
		System.out.println("Testing the isLimitedToMethod");
		System.out.println("-----------------------------");
		testIsLimitedTo("Test 1: Sending in correct data with null ranges", "Name", "SINGLE", 2016, 06, 25, 2017, 07,
				06, null, null, tally, goodString, true);
		testIsLimitedTo("Test 2: Sending in correct data with range", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06,
				"A1", "C3", tally, goodString, true);

	}

	private static void testIsLimitedTo(String string, String name, String type, int startYear, int startMonth,
			int startDay, int endYear, int endMonth, int endDay, String startRange, String endRange, Tally tally,
			String[] items, boolean expected) throws NullPointerException, IllegalArgumentException {
		try {
			System.out.println(string);
			DawsonElection test = new DawsonElection(name, type, startYear, startMonth, startDay, endYear, endMonth,
					endDay, startRange, endRange, tally, items);
			System.out.println(test.isLimitedToPostalRange());
			System.out.println();
		} catch (NullPointerException e) {
			System.err.println("There is not suppose to be a null error.");
			System.out.println();
		} catch (IllegalArgumentException e) {
			System.err.println("There is not suppose to be an IllegalArgumentException.");
			System.out.println();
		}
	}

	private static void testConstructor() {
		String[] goodString = new String[3];
		goodString[0] = "One";
		goodString[1] = "Two";
		goodString[2] = "Three";
		String[] badString = new String[1];
		badString[0] = "Bad";
		Tally tally = new DawsonTally(1,"Name");
		System.out.println("Testing the constructor");
		System.out.println("-----------------------");
		testEquals("Test 1: Sending in correct data with null ranges", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06,
				null, null, tally, goodString, true);
		testEquals("Test 2: Sending in correct data with 2 good ranges", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06,
				"A1", "C3", tally, goodString, true);
		testEquals("Test 3: Sending in correct data with 1 good range", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06,
				"A1", null, tally, goodString, true);
		testEquals("Test 4: Sending in a bad string array", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06, "A1", null,
				tally, badString, false);
		testEquals("Test 5: Sending in a bad startRange/endRange", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06, "C1",
				"A1", tally, goodString, false);
		testEquals("Test 6: Sending in a null tally", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, null,
				goodString, false);
		testEquals("Test 7: Sending in a null name", null, "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, tally,
				goodString, false);
		testEquals("Test 8: Sending in a blank name", "", "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, tally,
				goodString, false);
		testEquals("Test 9: Sending in a null electiontype", "Name", null, 2016, 06, 25, 2017, 07, 06, null, null,
				tally, goodString, false);
		testEquals("Test 10: Sending in a blank electiontype", "Name", "", 2016, 06, 25, 2017, 07, 06, null, null,
				tally, goodString, false);
		testEquals("Test 11: Sending in a bad electiontype", "Name", "Many", 2016, 06, 25, 2017, 07, 06, null, null,
				tally, goodString, false);
		testEquals("Test 12: Sending in a bad startyear", "Name", "SINGLE", 2018, 06, 25, 2017, 07, 06, null, null,
				tally, goodString, false);
		testEquals("Test 13: Sending in a bad startday when both the year and month is same", "Name", "SINGLE", 2017,
				07, 25, 2017, 07, 06, null, null, tally, goodString, false);
		testEquals("Test 14: Sending in a day out of range (MORE THAN)", "Name", "SINGLE", 2016, 06, 40, 2017, 07, 06,
				null, null, tally, goodString, false);
		testEquals("Test 15: Sending in a month out of range (MORE THAN)", "Name", "SINGLE", 2016, 15, 25, 2017, 07, 06,
				null, null, tally, goodString, false);
		testEquals("Test 16: Sending in a day out of range (LESS THAN)", "Name", "SINGLE", 2016, 06, 25, 2017, 0, 06,
				null, null, tally, goodString, false);
		testEquals("Test 17: Sending in a month out of range (LESS THAN)", "Name", "SINGLE", 2016, 06, 25, 2017, 07, 0,
				null, null, tally, goodString, false);
	}

	private static void testEquals(String string, String name, String type, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay, String startRange, String endRange, Tally tally, String[] items,
			boolean expected) throws NullPointerException, IllegalArgumentException, DateTimeException {
		try {
			System.out.println(string);
			DawsonElection test = new DawsonElection(name, type, startYear, startMonth, startDay, endYear, endMonth,
					endDay, startRange, endRange, tally, items);
			System.out.println("It contructed.");
			System.out.println(test);
			if (expected == false) {
				System.err.println("Was expected to fail.");
			}
			System.out.println();
		} catch (NullPointerException e) {
			System.out.println("The test caught a null:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		} catch (IllegalArgumentException e) {
			System.out.println("The test caught an illegal argument exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		} catch (DateTimeException e) {
			System.out.println("The test caught a datetime exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		}
	}

	private static void testCastBallot(Election election) {
		System.out.println("=====");
		System.out.println("Testing the cast ballot");
		System.out.println("-----------------------");
		String[] goodString = new String[3];
		goodString[0] = "One";
		goodString[1] = "Two";
		goodString[2] = "Three";
		BallotItem[] list = new BallotItem[3];
		for (int i = 0; i < goodString.length; i++) {
			list[i] = new DawsonBallotItem(goodString[i], goodString.length - 1, i);
		}
		Voter test = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("antbean@hotmail.com","anthony", "whitebean", "B2B2B2");
		Voter voter = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("antbean@hotmail.com","anthony", "whitebean", "A1A1A1");
		Voter voter2 = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("antbean@hotmail.com","anthony", "whitebean", "A1A1A1");
		Voter voter3 = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("justcause@hotmail.com","dude", "why", "E4E4E4");
		testCastBallot("Test 1: Sending in null list", election, null, test, false);
		testCastBallot("Test 2: Sending in null voter", election, list, null, false);
		testCastBallot("Test 3: Sending voter with correct data", election, voter, true);
		testCastBallot("Test 4: Sending voter who already cast", election, voter2, false);
		testCastBallot("Test 5: Sending voter who never got ballot", election, voter3, false);
	}

	private static void testCastBallot(String string, Election election, BallotItem[] list, Voter voter, boolean expected) {
		try {
			Ballot ballot = DawsonElectionFactory.DAWSON_ELECTION.getBallot(list, election.getElectionType(), election);
			System.out.println(string);
			election.castBallot(ballot, voter);
			System.out.println("It worked");
			if (expected == false) {
				System.err.println("This was suppose to fail");
			}
			System.out.println();

		} catch (IllegalArgumentException e) {
			System.out.println("The test caught an illegal argument exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		} catch (NullPointerException e) {
			System.out.println("The test caught a null exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (expected == true) {
				System.err.println("This was expected to work");
			}
			System.out.println();
		}
	}

	private static void testCastBallot(String string, Election election, Voter voter, boolean expected)
			throws NullPointerException, IllegalArgumentException {
		try {
			BallotItem[] choices = new BallotItem[election.getElectionChoices().length];
			for (int i = 0; i < choices.length; i++)
			{
				choices[i] = DawsonElectionFactory.DAWSON_ELECTION.getBallotItem(election.getElectionChoices()[i], election.getElectionType(),election.getElectionChoices().length);
			}
			Ballot ballot = DawsonElectionFactory.DAWSON_ELECTION.getBallot(choices, election.getElectionType(), election);
			System.out.println(string);
			election.castBallot(ballot, voter);
			System.out.println("It worked");
			if (expected == false) {
				System.err.println("This was suppose to fail");
			}
			System.out.println();

		} catch (IllegalArgumentException e) {
			System.out.println("The test caught an illegal argument exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		} catch (NullPointerException e) {
			System.out.println("The test caught a null exception:" + e.getMessage());
			if (expected == true) {
				System.err.println("Was expected to work.");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (expected == true) {
				System.err.println("This was expected to work");
			}
			System.out.println();
		}
	}
}

