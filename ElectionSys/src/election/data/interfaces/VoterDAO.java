package election.data.interfaces;

import java.io.IOException;

import election.business.interfaces.Voter;
import election.data.DuplicateVoterException;
import election.data.InexistentVoterException;
import lib.Email;
import lib.PostalCode;

/**
 * Interface that defines all behaviour required by a Data Access Object
 * that deals with Voter objects.
 * @author Jaya, Maja
 */
public interface VoterDAO {
	
	/**
	 * Adds a voter to the database. The voter is added in
	 * natural sort order to keep the database sorted.
	 * Only one customer can be inserted for an email address.
	 * 
	 * @param  voter
	 *            The voter to add to the database.
	 * @throws DuplicateVoterException;
	 *            The voter's email already exists. 
	 */
	void add(Voter voter) 
			throws DuplicateVoterException;

	/**
	 * Saves the list of voters and disconnects from the database. 
	 * 
	 * @throws IOException
	 *            Problems saving or disconnecting from database.
	 */
	void disconnect()throws IOException;

	/**			
	 * Returns the voter with the specified email address. 
	 * 
	 * @param  email The email of the requested voter.
	 *
	 * @return 	The voter with the specified email.
	 *
	 * @throws  InexistentVoterException If there is no voter
	 *			with the specified email. 
	 */
	Voter getVoter(String email) 
			throws InexistentVoterException;

	/**
	 * Modifies a voter's postal code. 
	 * 
	 * @param email
	 *            The email of the voter to be updated
	 * @param postalCode
	 *            The new postal code
	 * @throws InexistentVoterException
	 *            The voter is not in database.
	 */
	void update (Email email, PostalCode postalCode)
			throws InexistentVoterException;

}
