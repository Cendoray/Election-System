package election.business.interfaces;

import java.io.Serializable;

/**
 * Defines an election ballot.
 * @author Jaya, Maja
 *
 */
public interface Ballot extends Serializable, Comparable<Ballot>{
	
	/**
	 * Ballot can be cast by a voter. The voter parameter is required since eventually
	 * we need to verify that the voter is not fraudulent (i.e., not eligible to vote,
	 * or has already voted.
	 * @param voter
	 */
	void cast(Voter voter);
	
	/**
	 * Returns a deep copy of the BallotItems that make up the Ballot. Used to
	 * Tally the vote.
	 * @return array of BallotItems
	 */
	BallotItem[] getBallotItems();
	
	/**
	 * Returns the Election that is associated with teh Ballot
	 * @return election
	 */
	Election getElection();
	
	/**
	 * Sets a BallotItem's value.
	 * @param position The 0-indexed position of the ballot choice
	 * @param value The value to set
	 */
	void selectBallotItem(int position, int value);
	
	/**
	 * Validates in the BallotItems selected are valid
	 * @return true if the Ballot is valaid and ready to cast
	 */
	boolean validateSelections(); 

}
