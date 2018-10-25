/**
 * 
 */
package election.business.interfaces;

import java.io.Serializable;

import lib.Email;
import lib.Name;
import lib.PostalCode;

/**
 * Represents an individual Voter
 * @author Maja, Jaya
 *
 */
public interface Voter extends Serializable, Comparable<Voter> {

	/**
	 * Returns the voter's email
	 * @return
	 */
	Email getEmail();
	
	/**
	 * Returns the voter's name
	 * @return
	 */
	Name getName();
	
	/**
	 * Returns the voter's postal code
	 * @return
	 */
	PostalCode getPostalCode();
	
	/**
	 * Determines if a voter is eligible for a given election
	 * @param election
	 * @return true if eligible
	 */
	boolean isEligible(Election election);
	
	/**
	 * Allow the voter to change postal codes
	 * @param newCode
	 */
	void setPostalCode(PostalCode newCode);

}
