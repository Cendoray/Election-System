/**
 * 
 */
package election.data;

import java.io.IOException;

import election.business.DawsonElection;
import election.business.DawsonElectionFactory;
import election.business.DawsonTally;
import election.business.DawsonVoter;
import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import election.data.ElectionFileLoader;
import lib.Name;

/**
 * @author 1633028
 *
 */
public class TestApplication {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		testGetVoterListFromSequentialFile();
		System.out.println("\n\n ===========================================================================");
		System.out.println("\n\n ===========================================================================");
		testGetElectionListFromSequentialFile();
		System.out.println("\n\n ===========================================================================");
		System.out.println("\n\n ===========================================================================");
		testSetExistingTallyFromSequentialFile();

	}

	public static void testGetVoterListFromSequentialFile() throws IOException {
		System.out.println("\nTesting the getVoterListFromSequentialFile method");
		testGetVoterListFromSequentialFile("Test Case 1: Testing a valid text file", "datafiles/testfiles/unsorted/unsorted/voters2.txt",
				true);
		testGetVoterListFromSequentialFile("Test Case 2: Testing a invalid text file",
				"datafiles/testfiles/unsorted/unsorted/elections02.txt", false);
		testGetVoterListFromSequentialFile("Test Case 3: Testing without a text file", null, false);
		testGetVoterListFromSequentialFile("Test Case 4: Testing with a text file that does not exist", "", false);
	}

	public static void testGetVoterListFromSequentialFile(String testCase, String filename, boolean expected) throws IOException {

		System.out.println(" " + testCase);
		try {
			Voter[] voter = ElectionFileLoader.getVoterListFromSequentialFile(filename);
			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		} catch (IllegalArgumentException e) {
			if (expected)
				System.out.println(
						"=== EXPECTED TO WORK CORRECTLY === Illegal Argument Exception\n" + e.getMessage() + "\n");
			if (!expected)
				System.out.println(
						"=== EXPECTED TO FAIL MISERABLY === Illegal Argument Exception\n" + e.getMessage() + "\n");
		} catch (NullPointerException aie) {
			if (expected)
				System.out.println(
						"=== EXPECTED TO WORK CORRECTLY === Null Pointer Exception\n" + aie.getMessage() + "\n");
			if (!expected)
				System.out.println(
						"=== EXPECTED TO FAIL MISERABLY === Null Pointer Exception\n" + aie.getMessage() + "\n");
		}
	}

	public static void testGetElectionListFromSequentialFile() throws IOException {
		System.out.println("\nTesting the GetElectionListFromSequentialFile method ");
		testGetElectionListFromSequentialFile("Test Case 1: Testing a valid text file",
				"datafiles/testfiles/unsorted/unsorted/election2.txt", true);
		testGetVoterListFromSequentialFile("Test Case 2: Testing a invalid text file",
				"datafiles/testfiles/unsorted/unsorted/voters02.txt", false);
		testGetElectionListFromSequentialFile("Test Case 3: Testing without a text file", null, false);
		testGetElectionListFromSequentialFile("Test Case 4: Testing with a text file that does not exist", "", false);
	}

	public static void testGetElectionListFromSequentialFile(String testCase, String filename, boolean expected) {

		System.out.println(" " + testCase);
		try {
			System.out.println("\tThe textfile " + filename + " was read:");

			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		} catch (IllegalArgumentException e) {
			if (expected)
				System.out.println(
						"=== EXPECTED TO WORK CORRECTLY === Illegal Argument Exception\n" + e.getMessage() + "\n");
			if (!expected)
				System.out.println(
						"=== EXPECTED TO FAIL MISERABLY === Illegal Argument Exception\n" + e.getMessage() + "\n");
		} catch (NullPointerException aie) {
			if (expected)
				System.out.println(
						"=== EXPECTED TO WORK CORRECTLY === Null Pointer Exception\n" + aie.getMessage() + "\n");
			if (!expected)
				System.out.println(
						"=== EXPECTED TO FAIL MISERABLY === Null Pointer Exception\n" + aie.getMessage() + "\n");
		}
	}

	public static void testSetExistingTallyFromSequentialFile() throws IOException {
		int [][] array = new int[4][4];
		int [][] array2= new int[2][2];
		String electionS= "election";
		String[] arrayB= {"Purple", "Yellow"};
		String [] arrayC = {"Red","Green","Orange","Blue","Pink","Cyan"};
		DawsonTally tally = new DawsonTally(electionS,array);
		DawsonTally tally2 = new DawsonTally(electionS,array2);
		DawsonElection election1= new DawsonElection("Dawson DSU president","SINGLE",2017,8,23,2017,12,23,"B6","C1",tally,arrayC);
		DawsonElection good = new DawsonElection("NAME", "SINGLE", 2016, 06, 25, 2017, 07, 06,"A2", "B2", tally,arrayC);
		DawsonElection election2= new DawsonElection("Name", "SINGLE", 2016, 06, 25, 2017, 07, 06,"A1", "C3", tally,arrayC);
		DawsonElection election55= new DawsonElection("Dawson Color Election", "Single",  2020, 12, 20, 2022, 1, 20, null, null, tally, arrayC);
		DawsonElection election66= new DawsonElection("Brittany independence referendum", "Single", 2020, 12, 20, 2022, 1, 20, null, null, tally2, arrayB);
		Election [] electionArray= {election1, election2, election55};
		Election [] electionArray2= {election66, election55};


		Election [] election = {election1,good,election2};
		Election[] election420= {election55};
		System.out.println("\nTesting the SetExistingTallyFromSequentialFile method ");
		testSetExistingTallyFromSequentialFile("Test Case 1: Testing a valid text file", "datafiles/testfiles/unsorted/unsorted/tally.txt",
				election420, true);
		testSetExistingTallyFromSequentialFile("Test Case 2: Testing a invalid text file", "datafiles/testfiles/unsorted/unsorted/voters2.txt",
				election420, false);
		testSetExistingTallyFromSequentialFile("Test Case 3: Testing without a text file", null, election, false);
		testSetExistingTallyFromSequentialFile("Test Case 4: Testing with a text file that does not exist", "", election, false);
		testSetExistingTallyFromSequentialFile("Test Case 5: Testing with with a election array different from tally's", "datafiles/testfiles/unsorted/unsorted/tally.txt", electionArray, true);
		testSetExistingTallyFromSequentialFile("Test Case 6: Testing with with 2 different elections which have 2 different tallies", "datafiles/testfiles/unsorted/unsorted/tally.txt", electionArray2, true);

	}

	public static void testSetExistingTallyFromSequentialFile(String testCase, String filename, Election[] election,
			boolean expected) throws IOException {

		System.out.println(" " + testCase);
		try {
			ElectionFileLoader.setExistingTallyFromSequentialFile(filename, election);
			System.out.println("\tThe textfile " + filename + " was read:");

			if (!expected)
				System.out.println("=== EXPECTED TO FAIL ===\n");
			if (expected)
				System.out.println("=== EXPECTED TO WORK ===\n");
		} catch (IllegalArgumentException e) {
			if (expected)
				System.out.println(
						"=== EXPECTED TO WORK CORRECTLY === Illegal Argument Exception\n" + e.getMessage() + "\n");
			if (!expected)
				System.out.println(
						"=== EXPECTED TO FAIL MISERABLY === Illegal Argument Exception\n" + e.getMessage() + "\n");
		} catch (NullPointerException aie) {
			if (expected)
				System.out.println(
						"=== EXPECTED TO WORK CORRECTLY === Null Pointer Exception\n" + aie.getMessage() + "\n");
			if (!expected)
				System.out.println(
						"=== EXPECTED TO FAIL MISERABLY === Null Pointer Exception\n" + aie.getMessage() + "\n");
		}
	}
}
