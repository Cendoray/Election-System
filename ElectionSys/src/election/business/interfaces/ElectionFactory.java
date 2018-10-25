package election.business.interfaces;

import java.io.Serializable;

import election.business.*;

/**
 * Defines the required factory methods to centralize the instantiation of business objects.
 * The goal is to ensure that all concrete objects are compatible; i.e., a DawsonElection
 * may have different rules and therefore require different implementation than a VanierElection.
 * 
 * To ensure that we program to interfaces instead of implementation, all objects should
 * be instantiated through the Factory object. Only objects in test classes should use
 * the concrete class constructors directly.
 * 
 * @author Maja, Jaya
 *
 */
public interface ElectionFactory extends Serializable {
	
	/**
	 * Instantiate a Voter object
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param postalcode
	 * @return
	 */
	Voter getVoterInstance(String firstName, String lastName, String email, String postalcode);
	
	/**
	 * Instantiate a Voter object, by copying an existing Voter
	 * @param voter
	 * @return
	 */
	Voter getVoterInstance(Voter voter);
	
	/**
	 * Instantiate an Election object
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
	 * @return
	 */
	Election getElectionInstance(String name, String type, int startYear, int startMonth, int startDay, 
			int endYear, int endMonth, int endDay, String startRange, String endRange, String... choices);
	
	/**
	 * Instantiate an Election object, by copying an existing Election
	 * @param election
	 * @return
	 */
	Election getElectionInstance(Election election);
	
	/**
	 * Instantiates a Tally object with the results-to-date and associates to an
	 * Election
	 * @param results
	 * @param election
	 */
	void setExistingTally(int[][] results, Election election);
	
	/**
	 * Instantiates a Ballot.
	 * @param choices
	 * @param type
	 * @param election
	 * @return
	 */
	Ballot getBallot(BallotItem[] choices, ElectionType type, Election election);
	
	/**
	 * Instantiates a BallotItem
	 * @param choice
	 * @param type
	 * @param totalChoices
	 * @return
	 */
	BallotItem getBallotItem (String choice, ElectionType type, int totalChoices);
	
	/**
	 * Instantiate a Tally object, by copying an existing Tally
	 * @param tally
	 * @return
	 */
	BallotItem getBallotItem(BallotItem tally);
	
	/**
	 * Instantiates an appropriate ElectionPolicy for a given election
	 * @param election
	 * @return
	 */
	 ElectionPolicy getElectionPolicy(Election election);

}
