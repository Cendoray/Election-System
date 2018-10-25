package election.business.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

public interface Election extends Serializable, Comparable<Election> {
	
	/**
	 * Returns the election type (either SINGLE or RANKED)
	 * 
	 * @return election type
	 */
	ElectionType getElectionType();
	
	/**
	 * Returns an array with all the ballot item choices
	 * 
	 * @return array of ballot item choice wording
	 */
	String[] getElectionChoices();
	
	/**
	 * Gets the last date of the election
	 * 
	 * @return end date
	 */
	LocalDate getEndDate();
	
	/**
	 * Gets the first date of teh election
	 * 
	 * @return start date
	 */
	LocalDate getStartDate();
	
	/**
	 * Gets the postal code start range, if applicable. Returns null if
	 * not applicable.
	 * 
	 * @return postal code start range, or null
	 */
	String getPostalRangeEnd();	
	
	/**
	 * Gets the postal code end range, if applicable. Returns null if
	 * not applicable.
	 * 
	 * @return postal code end range, or null
	 */
	String getPostalRangeStart();
	
	/**
	 * Checks if the election is limited to voters residing in
	 * a postal code range.
	 * 
	 * @return true if limited to a postal code range
	 */
	boolean isLimitedToPostalRange();
	
	/**
	 * Returns election name
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * Returns the Tally object. Used to determine the winner.
	 * 
	 * @return
	 */
	Tally getTally();
	
	/**
	 * Sets the Tally object. Used when reading data that is persisted
	 * to file, for example when the voting takes place over multiple
	 * days.
	 * 
	 * @param tally to associate with election.
	 */
	void setTally(Tally tally);
	
	/**
	 * Returns a Ballot for this Election. To prevent fraud, the
	 * voter is validated.
	 * 
	 * @param voter who wants the ballot
	 * @return Ballot if the voter is eligible
	 */
	Ballot getBallot(Voter v);
	
	/**
	 * Casts a Ballot with the BallotItem values set as needed. If
	 * valid, the Tally is incremented.
	 * @param b
	 * @param v
	 */
	void castBallot(Ballot b, Voter v);

	//the following methods cannot be implemented in Phase 1
	//throw a new UnsupportedOperationException() if they are invoked

	int getTotalVotesCast();
	int getInvalidVoteAttempts();

}
