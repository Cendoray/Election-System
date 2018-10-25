package election.business.interfaces;

import java.io.IOException;
import java.util.List;

import election.business.InvalidVoterException;
import election.data.DuplicateElectionException;
import election.data.DuplicateVoterException;
import election.data.InexistentElectionException;
import election.data.InexistentVoterException;

public interface ElectionOffice {
 
 /**
  * Gets a Ballot for a given Election for a given Voter.
  * 
  * @param voter who wants to vote
  * @param election to vote in
  * @throws InvalidVoterException if voter cannot vote in the election
  */
  Ballot getBallot(Voter voter, Election election)
  throws InvalidVoterException;
 
  /**
   * Enables a given voter to cast a given ballot.
   * 
   * @param voter who want to cast a ballot
   * @param ballot to cast
   */
 void castBallot(Voter voter, Ballot b);
 
 /**
  * Saves all data and closes the admin office and voting booth.
  * 
  * @throws IOException
  *             If there is a problem closing the files.
  */
 void closeOffice() throws IOException;

 /**
  * Create a new Election
  * @param name
  * @param type
  * @param startYear
  * @param startMonth
  * @param startDay
  * @param endYear
  * @param endMonth
  * @param endDay
  * @param startRange
  * @param endRange
  * @param choices
  * @return the new election
  * @throws DuplicateElectionException if an election with same name exists
  */
 Election createElection(String name, String type, int startYear, int startMonth, int startDay, 
   int endYear, int endMonth, int endDay, String startRange, String endRange, String... choices)
   throws DuplicateElectionException;

 /**
  * Deternine the winner of an election
  * @param election
  * @throws IncompleteElectionException if the election is ongoing
  */
 List<String> getWinner(Election election);
  
 /**
  * Registers a new Voter
  * @param firstName
  * @param lastName
  * @param email
  * @param postalcode
  * @return The Voter object
  * @throws DuplicateVoterException if a voter with same e-mail address exists
  */
 Voter registerVoter(String firstName, String lastName, String email, String postalcode)
   throws DuplicateVoterException;
 
 /**
 * Finds and returns the Election with the given name
 * @param name of the election
 * @return Election object
 * @throws InexistentElectionException
 *          if an election with that name cannot be found
 */
 Election findElection(String name)
  throws InexistentElectionException;
 
  /**
 * Finds and returns the Voter with the given email address.
 * @param email address
 * @return Voter object
 * @throws InexistentVoterException
 *          if a voter with that email address cannot be found
 */
 Voter findVoter (String email)
  throws InexistentVoterException;
 
 /**
	 * Finds a voter with a given email
	 * @param email of the voter
	 * @param notify observers if true
	 * @return
	 * @throws InexistentVoterException
	 */
	Voter findVoter (String email, boolean notify)
		throws InexistentVoterException;
 
}

