/**
 * This code will keep a database of a list of elections/
 * 
 * @author Anthony Whitebean
 * @version 11/13/2017
 */
package election.data;


import java.io.IOException;
import java.util.*;

import util.ListUtilities;
import election.business.DawsonElectionFactory;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.ListPersistenceObject;

public class ElectionListDB implements ElectionDAO{
	private List<Election> database;
	private final ObjectSerializedList objectSerializedList;
	private final ElectionFactory factory;
	
	public ElectionListDB(ObjectSerializedList objectSerializedList, ElectionFactory factory)
	{
		this.objectSerializedList = objectSerializedList;
		this.factory = factory;
		this.database = this.objectSerializedList.getElectionDatabase();
	}
	
	public ElectionListDB(ObjectSerializedList objectSerializedList)
	{
		this(objectSerializedList, DawsonElectionFactory.DAWSON_ELECTION);
	}

	/**
	 * This code will add an election to the data base
	 * 
	 * @DuplicateElectionException if the election is already in the database
	 */
	@Override
	public void add(Election election) throws DuplicateElectionException {
		int check = ListUtilities.binarySearch(this.database, election, 0, this.database.size() - 1);
		if (check >= 0)
		{
			throw new DuplicateElectionException("The election is already in the database.");
		}
		check = check + 1;
		check = check * -1;
		Election copy = factory.getElectionInstance(election);
		database.add(check, copy);
	}


	/**
	 * This will disconnect the instance of the database, after saving the database
	 * 
	 * @IOException if an error occurs during saving
	 */
	@Override
	public void disconnect() throws IOException {
		objectSerializedList.saveElectionDatabase(this.database);
		this.database = null;
	}

	/**
	 * This code will return the election of a given name
	 * 
	 * @return an election that is represented by the string sent in
	 * 
	 * @InexistentElectionException if the name sent in is not in database
	 */
	@Override
	public Election getElection(String name) throws InexistentElectionException {
		String[] choices = {"One", "Two", "Three"};
		Election test = factory.getElectionInstance(name, "SINGLE", 2016, 06, 25, 2017, 07, 06, null, null, choices);
		int check = ListUtilities.binarySearch(this.database, test, 0, this.database.size() - 1);
		if (check < 0)
		{
			throw new InexistentElectionException("The election called for does not exist in the database.");
		}
		return this.database.get(check);
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		string.append("Number of reservations in database: ");
		string.append(database.size() + "\n");
		for (int i = 0; i < database.size(); i++)
		{
			string.append(database.get(i) + "\n");
		}
		return string.toString();
	}
}
