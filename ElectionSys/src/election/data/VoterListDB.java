/**
 * 
 */
package election.data;
import election.data.interfaces.ListPersistenceObject;
import election.data.interfaces.VoterDAO;
import lib.Email;
import lib.PostalCode;
import util.ListUtilities;

import java.io.IOException;
import java.util.List;
import election.business.DawsonElectionFactory;
import election.business.interfaces.*;

/**
 * @author Daniel
 * @version 12/11/17
 * Class which takes care of the database holding the voters
 */
public class VoterListDB implements VoterDAO{

	
	private List<Voter> database;
	private final ListPersistenceObject ListPersistenceObject;
	private final ElectionFactory factory;

	/**
	 * Constructor who sets values to the variables using only one listPersistenceObject parameter
	 * @param listPersistenceObject a field used to assign a reference to the database
	 */
	public VoterListDB(ObjectSerializedList objectSerializedList){
		this.ListPersistenceObject=objectSerializedList;
		this.factory=DawsonElectionFactory.DAWSON_ELECTION;	
		this.database = this.ListPersistenceObject.getVoterDatabase();
	}
/**
	 * Constructor who sets values to the variables using only one listPersistenceObject parameter and a factory parameter
 * @param listPersistenceObject a field used to assign a reference to the database
 * @param factory a field used to assign a reference to the factory
 */
	public VoterListDB (ObjectSerializedList objectSerializedList,ElectionFactory factory){
		this.ListPersistenceObject=objectSerializedList;
		this.factory=factory;	
		this.database = this.ListPersistenceObject.getVoterDatabase();
	}
	/**
	 * Overridden toString method which returns the number of voters in the database, followed by all those voters
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n Number of voters in Database: " + database.size());
		for(Voter value: database){
			sb.append("\n" + value.toString());
		}
		return sb.toString();
	}

	
	/**
	 * method which adds a voter to the database, unless it is a duplicate
	 * @param voter the voter which will be added to the database
	 * @throws DuplicateVoterException throws this exception if the voter already resides within the database
	 */
	public void add(Voter voter) throws DuplicateVoterException{
		
		int check = ListUtilities.binarySearch(database, voter, 0, database.size() - 1);
		if (check>= 0)
			throw new DuplicateVoterException("You already possess this voter in your database");
		else{
			check = check + 1;
			check = check * -1;
			Voter copyVoter = factory.getVoterInstance(voter);
			database.add(check, copyVoter);
		}
	}
	/**
	 * method which disconnects the user from the database ( saves all the voters to a file and turns the database null)
	 * @throws IOException throws this exception if the file where the voters should be saved on is not found
	 */
	public void disconnect() throws IOException{
		this.ListPersistenceObject.saveVoterDatabase(this.database); //.saveListToTextFile(database.toArray(), "datafiles/database/voters", true);
		this.database=null;
	}
	/**
	 * method which returns a voter given he has the same email address as the parameter
	 * @param email the email address which will used to look for the voter
	 * @return the voter if he is found
	 * @throws InexistentVoterException throws this exception if no voter is found with the given email address
	 */
	public Voter getVoter(String email) throws InexistentVoterException{
		Voter voter = factory.getVoterInstance("FirstName", "Lastname",email, "A1B2C3");
		int check = ListUtilities.binarySearch(database, voter, 0, database.size() - 1);
		if (check < 0)
			throw new InexistentVoterException("The email address used was not attached to any of the voters");
		return database.get(check);
	}
	/**
	 * method which updates a voter's postal code with a new one
	 * @param email the email address which will be used to look for the voter
	 * @param postalCode the new postal code which will the voter will have 
	 * @throws InexistentVoterException throws this exception if no voter with the given email address was found
	 */
	public void update (Email email, PostalCode postalCode) throws InexistentVoterException{
		/*if (ListUtilities.binarySearch((Comparable[])database.toArray(), email) ==-1)
			throw new InexistentVoterException("The email address used was not attached to any of the voters");
		database.get(ListUtilities.binarySearch((Comparable[])database.toArray(), email)).setPostalCode(postalCode);
		 */
		Voter voter = factory.getVoterInstance("Firstname", "Lastname", email.getAddress(), postalCode.getCode());
		int check = ListUtilities.binarySearch(database, voter, 0, database.size()-1);
		if (check < 0)
			throw new InexistentVoterException("The email address used was not attached to any of the voters");
		else
			database.get(check).setPostalCode(postalCode);
	}
}
