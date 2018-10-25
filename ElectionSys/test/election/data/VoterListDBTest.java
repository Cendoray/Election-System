package election.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.DawsonVoter;
import election.business.interfaces.*;
import lib.Email;
import lib.PostalCode;
import util.ListUtilities;

public class VoterListDBTest {

	public static void main(String[] args) throws DuplicateVoterException, IOException, InexistentVoterException {
		testGetVoter();
		System.out.println("------------------------------------");
		System.out.println("------------------------------------");
		testAddVoter();
		System.out.println("------------------------------------");
		System.out.println("------------------------------------");
		testDisconnect();
		System.out.println("------------------------------------");
		System.out.println("------------------------------------");
		testUpdateVoter();
		// TODO Add more method invocations to test all methods
	}

	private static void setup() {
		String[] voters = new String[2];
		voters[0] = "joe.mancini@mail.me*Joe*Mancini*H3C4B7";
		voters[1] = "raj@test.com*Raj*Wong*H3E1B4";
		// TODO add more voters if needed, but they must be in sorted order!

		String[] elecs = new String[2];
		elecs[1] = "Presidental race*2020*11*1*2020*11*1***single*2" + "\nDonald Trump" + "\nAnyone Else";
		elecs[0] = "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + "\nGame of Thrones" + "\nNarcos";
		// TODO add more elections if needed, but the must be in sorted order

		String[] tallies = new String[2];
		tallies[0] = "Presidental race*2" + "\n100*0" + "\n0*102";
		tallies[1] = "Favourite program*2" + "\n1000*0" + "\n0*560";

		// make the testfiles directory
		Path dir;
		try {
			dir = Paths.get("datafiles/testfiles");
			if (!Files.exists(dir))
				Files.createDirectory(dir);
			ListUtilities.saveListToTextFile(voters, "datafiles/testfiles/testVoters.txt");
			ListUtilities.saveListToTextFile(elecs, "datafiles/testfiles/testElections.txt");
			ListUtilities.saveListToTextFile(tallies, "datafiles/testfiles/testTally.txt");
			
			SequentialTextFileList sequentialfile1 = new SequentialTextFileList("datafiles/testfiles/testVoters.txt","datafiles/testfiles/testElections.txt","datafiles/testfiles/testTally.txt");
			ObjectSerializedList objectlist1 = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");


			List<Voter> testVoters = sequentialfile1.getVoterDatabase();
			List<Election> testElection = sequentialfile1.getElectionDatabase();
			
			objectlist1.saveVoterDatabase(testVoters);
			objectlist1.saveElectionDatabase(testElection);
			
			//System.out.println(objectlist1.getVoterDatabase());
			//System.out.println(objectlist1.getElectionDatabase());

			
			System.out.println("---------------------------------------------------------");
			
			List<Voter> testVoter1 = objectlist1.getVoterDatabase();
			List<Election> testElection1 = objectlist1.getElectionDatabase();
			
			/*File f = new File("testTally.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
	        oos.writeObject(tallies);
	        oos.flush();
	        oos.close();
			*/
	
			
		} catch (InvalidPathException e) {
			System.err.println("could not create testfiles directory " + e.getMessage());
		} catch (FileAlreadyExistsException e) {
			System.err.println("could not create testfiles directory " + e.getMessage());
		} catch (IOException e) {
			System.err.println("could not create testfiles in setup() " + e.getMessage());
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
			file = Paths.get("datafiles/database/testElections.er");
			Files.deleteIfExists(file);
		} catch (InvalidPathException e) {
			System.err.println("could not delete test files " + e.getMessage());
		} catch (NoSuchFileException e) {
			System.err.println("could not delete test files " + e.getMessage());
		} catch (DirectoryNotEmptyException e) {
			System.err.println("could not delete test files " + e.getMessage());
		} catch (IOException e) {
			System.err.println("could not delete test files " + e.getMessage());
		}

	}

	private static void testGetVoter() throws FileNotFoundException  {
		setup();
		ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");
		VoterListDB db = new VoterListDB(file);

		System.out.println("\n** test getVoter ** ");
		System.out.println("\nTest case 1: Get Voter:");
		try {
			Voter voter = db.getVoter("joe.mancini@mail.me");
			System.out.println(voter.toString());

			System.out.println("SUCCESS: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: voter should be found");
		}

		System.out.println("\nTest case 2: Get Voter which doesn't exist in database:");
		try {
			Voter voter2 = db.getVoter("jar@test.ru");
			System.out.println("FAILING TEST CASE: Voter found " + voter2.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILURE: voter not found");
		}

		teardown();
	}
	private static void testUpdateVoter() throws FileNotFoundException {
		setup();
		ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");	
		VoterListDB db = new VoterListDB(file);

		System.out.println("\n** test updateVoter ** ");
		System.out.println("\nTest case 1: Update Voter in database from H3C4B7 to H3C5B8:");
		try {
			Email email = new Email("joe.mancini@mail.me");
			PostalCode postalcode = new PostalCode("H3C5B8");

			db.update(email,postalcode);
			System.out.println("SUCCESS: Voter found " + db.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: voter should have been changed");
		}

		System.out.println("\nTest case 2: Update Voter which doesn't exist in database:");
		try {
			Email email1 = new Email("joe.mancini@live.me");
			PostalCode postalcode1 = new PostalCode("H3C5B8");
			db.update(email1,postalcode1);
			System.out.println("FAILING TEST CASE: Voter found " + db.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILURE: voter not found");
		}
		try {
			db.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teardown();
	}
	
	
	private static void testAddVoter() throws DuplicateVoterException, InexistentVoterException, FileNotFoundException {
		setup();
		ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");


				VoterListDB db = new VoterListDB(file);
		
		System.out.println("\n** test addVoter ** ");
		System.out.println("\nTest case 1: Add Voter to database:");
		
		Voter v1= DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Billy", "Bobo","billybobo@gmail.com","H8b1p7");
		db.add(v1);
		System.out.println("SUCCESS: Voter found " + db.toString());
		
		System.out.println("\nTest case 2: Add Duplicate Voter in database:");
		try {
		Voter v2= DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Raj","Wong","raj@test.com","H3E1B4");
		db.add(v2);
		System.out.println("WARNING User was added to database even though it was a duplicate " + db.toString());
		}
		catch(DuplicateVoterException dve) {
		System.out.println("FAILURE: Duplicate Voter found, WILL NOT BE ADDED TO DATABASE " + db.toString() + dve.getMessage());	
		}
		teardown();
	}

	private static void testDisconnect() throws DuplicateVoterException, IOException, InexistentVoterException {
		setup();
		ObjectSerializedList file = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");

		VoterListDB db = new VoterListDB(file);
		System.out.println("\n** test disconnectVoter ** ");
		System.out.println("\nTest case 1: Adding voters to database, disconnecting, and then printing toString:");
		
		Voter v1= DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Billy", "Bob","billybob@gmail.com","H8b1p7");
		Voter v2=  DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Joe", "Dylan","joedyl@gmail.com","H8b1p7");
		Voter v3=  DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Master", "Roshi","masRoshStar@gmail.com","H8b1p7");
		db.add(v2);
		db.add(v1);
		db.add(v3);
		db.disconnect();
		ObjectSerializedList file2 = new ObjectSerializedList("datafiles/database/testVoters.ser", "datafiles/database/testElections.ser");

		VoterListDB db2 = new VoterListDB(file2);		
		
		if (file2.getVoterDatabase().size() ==5)
			System.out.println("SUCCESS: Voter found " + db2.toString());
		else
			System.out.println("FAILED VOTER NOT FOUND" + db2.toString());
		
		System.out.println("\nTest case 2: Adding duplicate voters to database, disconnecting, and then printing toString:");
		try{
		Voter v4= DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Raj","Wong","raj@test.com","H3E1B4");
		db2.add(v4);
		}
		catch(DuplicateVoterException dve){
		System.out.println("FAILURE: Duplicate Voter found, WILL NOT BE ADDED TO DATABASE " + db2.toString() + dve.getMessage());	
		}
		teardown();
	}
	
}