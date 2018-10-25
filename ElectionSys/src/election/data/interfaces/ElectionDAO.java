package election.data.interfaces;

import java.io.IOException;

import election.business.interfaces.Election;
import election.data.DuplicateElectionException;
import election.data.InexistentElectionException;


/**
 * Interface that defines all behaviour required by a Data Access Object
 * that deals with Election objects.
 * @author Jaya, Maja
 */
public interface ElectionDAO {
	
	/**
	 * Adds an election to the database. The election is added in
	 * natural sort order to keep the database sorted.
	 * Only one election can be inserted for a given name string.
	 * 
	 * @param  election
	 *            The election to add to the database.
	 * @throws DuplicateElectionException;
	 *            The election's name already exists. 
	 */
	void add(Election election) 
			throws DuplicateElectionException;

	/**
	 * Saves the list of elections and disconnects from the database. 
	 * 
	 * @throws IOException
	 *            Problems saving or disconnecting from database.
	 */
	void disconnect()throws IOException;

	/**			
	 * Returns the election with the specified name. 
	 * 
	 * @param  name The name of the requested election.
	 *
	 * @return 	The election with the specified name.
	 *
	 * @throws  InexistentElectionException If there is no election
	 *			with the specified name. 
	 */
	Election getElection(String name) 
			throws InexistentElectionException;

}
