package election.data.interfaces;

import java.io.IOException;
import java.util.List;

import election.business.interfaces.*;

/**
 * Interface that defines all behaviour required by a class that loads from
 * disk.
 * 
 * @author Jaya, Maja
 */
public interface ListPersistenceObject {

	/**
	 * Reads in the list of voters from disk
	 * 
	 * @return a List of Voter objects
	 */
	List<Voter> getVoterDatabase();

	/**
	 * Reads in a list of Elections from disk, along with their Tally objects
	 * 
	 * @return a List of Election
	 */
	List<Election> getElectionDatabase();

	/**
	 * Saves a List of Voters to disk
	 * 
	 * @param voters
	 *            List of Voters to save
	 * @throws IOException
	 */
	void saveVoterDatabase(List<Voter> voters) throws IOException;

	/**
	 * Saves a List of Elections to disk, along with their Tallies
	 * 
	 * @param elections
	 *            List of Elections to save
	 * @throws IOException
	 */
	void saveElectionDatabase(List<Election> elections) throws IOException;

}
