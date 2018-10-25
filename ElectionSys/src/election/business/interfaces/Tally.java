package election.business.interfaces;

import java.io.Serializable;
/**
 * Tally object is used by every election to track the cast
 * ballot results.
 * 
 * @author Maja, Jaya
 *
 */
public interface Tally extends Serializable {

	/**
	 * Returns the name of the Election to which this Tally is associated
	 * @return election name
	 */
	String getElectionName();

	/**
	 * Returns a 2d array where the rows represent each Ballot Item
	 * and the columns represent the ranking (0 to number of
	 * Ballot Items -1). Note that in the case of a Single election, 
	 * only the first two columns will be used.
	 * @return
	 */
	int[][] getVoteBreakdown();
	
	/**
	 * Provides a Ballot to update the vote results
	 * @param ballot
	 */
	void update(Ballot ballot);
	
	//the following method cannot be implemented until Phase 4

	//int getWinner(ElectionPolicy policy);


}
