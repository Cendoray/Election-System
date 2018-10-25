package election.business.interfaces;

import java.io.Serializable;
import java.util.List;
/**
 * Strategy to determine the winner of an election.
 * @author Jaya, Maja
 *
 */
public interface ElectionPolicy extends Serializable {
	/**
	 * Determines the winner(s).
	 * @return the list of the winning choice names, or an empty list
	 */
	List<String> getWinner();

}




