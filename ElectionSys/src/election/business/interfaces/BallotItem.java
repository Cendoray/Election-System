package election.business.interfaces;

import java.io.Serializable;
/**
 * A Ballot Item represents a single choice on a ballot.
 * 
 * @author Jaya, Maja
 *
 */
public interface BallotItem extends Comparable<BallotItem>, Serializable {

	/**
	 * Get the choice's wording
	 * 
	 * @return choice
	 */
	String getChoice();
	
	/**
	 * Gets the maximum value that a ballot item can be given.
	 * In the case of a single-type election where only one choice
	 * is made, the maximum is 1.
	 * In the case of a ranked election where all choices must be
	 * ranked, the maximum is n-1, where n is the total number of choices.
	 * 
	 * @return maximum possible value
	 */
	int getMaxValue();
	
	/**
	 * Get the current value assigned to the ballot item
	 * 
	 * @return value
	 */
	int getValue();

	/**
	 * Sets the value of the ballot item. Validated to ensure
	 * it is less than of equal to the maxValue.
	 * 
	 * @param value
	 */
	void setValue(int value);
	
}
