package election.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.Voter;
import util.ListUtilities;
import util.Utilities;


public class ElectionListDBTest {
	/*
	 * Author: Trevor Chevrier 164405
	 * Date: 09/11/17
	 * Version: 1.0
	 */

	
	public static void main(String[] args) throws IOException {
		String test1 = "Presidential race";
		String message1 = "Test case 1: Testing an existing Election";
		getElectionTest(test1,message1);
		String test2 = "This is not an election";
		String message2 = "Test case 2: Testing a non-existent Election (SHOULD FAIL)";
		getElectionTest(test2,message2);
		
		
		String [] choices1 = {"\nBob Ross","\nAnyone Else"}; 
		Election election1 = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("Best Singer", "single", 2020, 11, 1, 
		2020, 11, 1, "H4G", "H4G", choices1);
		String message3 = "Test case 1: Testing a non-duplicate Election.";
		
		
		String [] choices2 = {"\nGame of Thrones","\nNarcos"}; 
		Election election2 = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("Favourite program", "single", 2018, 5, 1, 
		2019, 5, 31, "H4G", "H4G", choices2);
		String message4 = "Test case 2: Testing a duplicate Election. (SHOULD FAIL)";
		addTest(election1, message3);
		addTest(election2, message4);
	
		disconnectTest();
	}
	
	private static void setup()
	{
		String[] voters = new String[2];
		voters[0] = "joe.mancini@mail.me*Joe*Mancini*H3C4B7";
		voters[1] = "raj@test.ru*Raj*Wong*H3E1B4";
		//TODO add more voters if needed, but they must be in sorted order!

		String[] elecs = new String[2];
		elecs [1] = "Presidential race*2020*11*1*2020*11*1*H40G*H4G*single*2" + 
				"\nDonald Trump" + 
				"\nAnyone Else";
		elecs [0] = "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + 
				"\nGame of Thrones" + 
				"\nNarcos";
		//TODO add more elections if needed, but the must be in sorted order

		String[] tallies = new String[2];
		tallies [0] = "Presidential race*2" + 
				"\n100*0" + 
				"\n0*102";
		tallies [1] = "Favourite program*2" + 
				"\n1000*0" + 
				"\n0*560";
		
		// make the testfiles directory
		Path dir;
		try {
			dir= Paths.get("datafiles/testfiles");
			if( !Files.exists(dir))
				Files.createDirectory(dir);
			
			String votersPath = "datafiles/testfiles/testVoters.txt";
			String electionPath = "datafiles/testfiles/testElections.txt";
			String tallyPath = "datafiles/testfiles/testTally.txt";
			
			ListUtilities.saveListToTextFile(voters,votersPath 
					);
			ListUtilities.saveListToTextFile(elecs, electionPath)
					;
			ListUtilities.saveListToTextFile(tallies, tallyPath
					);
			
		

			SequentialTextFileList sequentialfile1 = new SequentialTextFileList("datafiles/testfiles/testVoters.txt","datafiles/testfiles/testElections.txt","datafiles/testfiles/testTally.txt");
			ObjectSerializedList objectlist1 = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");


			List<Voter> testVoters = sequentialfile1.getVoterDatabase();
			List<Election> testElection = sequentialfile1.getElectionDatabase();
			
			objectlist1.saveVoterDatabase(testVoters);
			objectlist1.saveElectionDatabase(testElection);
			
			//System.out.println(objectlist1.getElectionDatabase());
			
			//System.out.println(objectlist1.getVoterDatabase());


	
			
			
		} catch (InvalidPathException e) {
			System.err.println("could not create testfiles directory: " + e.getMessage());
		} catch (FileAlreadyExistsException e) {
			System.err.println("could not create testfiles directory: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("could not create testfiles in setup(): " + e.getMessage());
		} 
	

	}	
	
	private static void teardown() {
		Path file;
		try {
			file = Paths.get("datafiles/testfiles/testVoters.txt");
			Files.deleteIfExists(file); 
			file = Paths.get("datafiles/testfiles/testElections.txt");
			Files.deleteIfExists(file); 
			file = Paths.get("datafiles/testfiles/testTally.txt");
			Files.deleteIfExists(file); 
			file = Paths.get("datafiles/database/testVoters.ser");
			Files.deleteIfExists(file); 
			file = Paths.get("datafiles/database/testElections.ser");
			Files.deleteIfExists(file); 
		} catch (InvalidPathException e) {
			System.err.println("could not delete test files: " + e.getMessage());
		} catch (NoSuchFileException e) { 
			System.err.println("could not delete test files: " + e.getMessage());
		} catch (DirectoryNotEmptyException e) {
			System.err.println("could not delete test files: " + e.getMessage());
		 } catch (IOException e) { 
		    System.err.println("could not delete test files: " + e.getMessage());
		} 
		
	}
	
	private static void addTest(Election election, String message) {
		System.out.println("\n** Testing ElectionListDB add **\n");
		
		setup();
	
		
		
		try {
			System.out.println(message);
			
			ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");
			ElectionListDB db = new ElectionListDB(file);
			
			db.add(election);
			System.out.println("The test case passed!");
			
		} catch(DuplicateElectionException dee) {
			System.out.println("TEST FAILED: The election should not be a duplicate: " + dee.getMessage());
		} catch(FileNotFoundException fnfe) {
			System.out.println("TEST FAILED: File not found exception thrown: " + fnfe.getMessage());
		}
		
		
		
		teardown();
		
	}
	
	private static void disconnectTest() {
		System.out.println("\n** Testing ElectionListDB disconnect **\n");
		
		setup();
		
	
		String [] choices = {"Yes, I want classes on friday","No, I don't want classes on friday"}; 
		Election election = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("DSU Referendum", "SINGLE", 2017, 9, 10, 
				2018, 9, 14, "H4G", "H4G", choices);
		
		try {
			System.out.println("Test case 1");
			
			ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");
			ElectionListDB db = new ElectionListDB(file);
			
			System.out.println(db);
			
			db.add(election);
			db.disconnect();
			ElectionListDB db2 = new ElectionListDB(file);
			System.out.println(db2);
			System.out.println("The test case passed!");
		} catch (IOException ioe) {
			System.out.println("Test failed: Could not disconnect from the instance of ElectionListDB ");		
		}
		  catch (Exception e) {
			System.out.println("Test failed: " + e.getMessage());  
		} 
		
		teardown();
		
		
		
	}
	
	private static void getElectionTest(String name, String message) {
		setup();
		System.out.println("\n** test getElection ** ");
		System.out.println(message);
		try {
			ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");
			ElectionListDB db = new ElectionListDB(file);
			Election election = db.getElection(name);
			System.out.println("SUCCESS: Election found");
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: election not found ");
		} catch(FileNotFoundException fnfe) {
			System.out.println("TEST FAILED: File not found exception thrown :" + fnfe.getMessage());
		}
		teardown();
		
	}

}
