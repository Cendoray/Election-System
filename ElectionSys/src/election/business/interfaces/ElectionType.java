package election.business.interfaces;

/**
 * Enum for the different types of elections supported by
 * the system. A SINGLE election implies that only one choice
 * can be selected on the ballot, whereas RANKED means all 
 * choices are given a ranking
 * @author Jaya, Maja
 *
 */
public enum ElectionType {
	SINGLE, RANKED;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}

}
