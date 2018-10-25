/**
 * 
 */
package election.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.ElectionOffice;
import election.business.interfaces.ElectionType;
import election.business.interfaces.Voter;
import election.data.DuplicateElectionException;
import election.data.DuplicateVoterException;
import election.data.InexistentElectionException;
import election.data.InexistentVoterException;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.VoterDAO;
import util.ListUtilities;

/**
 * Implements the ElectionOffice interface
 * 
 * @author Ethan Wong
 * @version 11/26/2017 Class that creates a election office that has methods
 *          that creates an election, getters, and finds winner of
 *          election,voter,election
 */
public class DawsonElectionOffice extends java.util.Observable implements ElectionOffice {

	/**
	 * 
	 */
	private final ElectionFactory factory;
	private final ElectionDAO elections;
	private final VoterDAO voters;
	private static final long serialVersionUID = 42031768871L;

	public DawsonElectionOffice(ElectionFactory factory, ElectionDAO elections, VoterDAO voters) {

		this.factory = factory;
		this.elections = elections;
		this.voters = voters;
	}

	// TODO Auto-generated constructor stub
	@Override
	public Ballot getBallot(Voter voter, Election election) throws InvalidVoterException {
		// get the voter from the election
		// then get the ballot from the voter
		// delete the voter from the list
		// return the ballot
		// DawsonBallotItem bi = new DawsonBallotItem
		Election e = factory.getElectionInstance(election);

		Voter v = factory.getVoterInstance(voter);

		if (!v.isEligible(e))
			throw new InvalidVoterException("FAILED -- VOTER CANNOT VOTE");

		Ballot b = e.getBallot(v);

		return b;
	}

	@Override

	public void castBallot(Voter voter, Ballot b) {
		b.cast(voter);

	}

	@Override
	public void closeOffice() throws IOException {
		// saving the state of the election and voters
		try {
			voters.disconnect();
			elections.disconnect();
		} catch (IOException ioe) {
			throw new IOException("Error-- Cannot close the file");
		}

	}

	@Override
	public Election createElection(String name, String type, int startYear, int startMonth, int startDay, int endYear,
			int endMonth, int endDay, String startRange, String endRange, String... choices)
			throws DuplicateElectionException {

		// create the database
		// List<Election> database=;
		// create a new election
		Election newElection = factory.getElectionInstance(name, type, startYear, startMonth, startDay, endYear,
				endMonth, endDay, startRange, endRange, choices);
		// check if the new election is within the database
		try {
			elections.add(newElection);
		} catch (DuplicateElectionException dee) {
			throw new DuplicateElectionException("FAILED-Election is already in database");
		}
		// int check = ListUtilities.binarySearch(database, newElection, 0,
		// database.size() - 1);
		// if (check >= 0)

		// throw new DuplicateElectionException("The election is already in the
		// database.");

		return newElection;

	}

	@Override
	// UPDATED METHOD NOW ITS OBSERVABLE
	public List<String> getWinner(Election election) {
		setChanged();
		notifyObservers(factory.getElectionPolicy(election).getWinner());
		return factory.getElectionPolicy(election).getWinner();
	}

	// UPDATED METHOD NOW ITS OBSERVABLE
	@Override
	public Voter registerVoter(String firstName, String lastName, String email, String postalcode)
			throws DuplicateVoterException {
		// creates a new voter object
		Voter newVoter = factory.getVoterInstance(firstName, lastName, email, postalcode);
		// check if the voter is already added to the database
		try {
			voters.add(newVoter);
		} catch (DuplicateVoterException dve) {
			throw new DuplicateVoterException("FAILED-Voter is already in database");
		}
		setChanged();
		notifyObservers(newVoter);
		return newVoter;
	}
	//UPDATED METHOD NOW ITS OBSERVABLE
	@Override
	public Election findElection(String name) throws InexistentElectionException {
		// create a try-catch to try find the election object
		try {
			elections.getElection(name);
		} catch (InexistentElectionException iee) {
			throw new InexistentElectionException("FAILED -- No Election has that name");
		}
		// if no error is caught then it will return the found object
		Election found = factory.getElectionInstance(elections.getElection(name));
		setChanged();
		notifyObservers(found);

		return found;
	}

	// UPDATED METHOD NOW ITS OBSERVABLE
	@Override
	public Voter findVoter(String email) throws InexistentVoterException {
		try {
			// create a try-catch to try find the voter object

			voters.getVoter(email);
		} catch (InexistentVoterException ive) {
			throw new InexistentVoterException("FAILED -- No Election has that name");
		}
		// if no error is caught then it will return the found object
		Voter found = factory.getVoterInstance(voters.getVoter(email));
		setChanged();
		notifyObservers(found);

		return found;
	}
	// UPDATED METHOD NOW ITS OBSERVABLE
	/**
	 * Finds a voter with a given email
	 * 
	 * @param email
	 *            of the voter
	 * @param if
	 *            notify is true, notify observers
	 * @return voter if found
	 * @throws InexistentVoterException
	 * @author Trevor Chevrier
	 */
	public Voter findVoter(String email, boolean notify) throws InexistentVoterException {
		Voter voter;
		try {
			voter = findVoter(email);

			if (notify == true)
				notifyObservers(voter);

		} catch (InexistentVoterException ive) {
			throw new InexistentVoterException("FAILED --- THERE IS NO VOTER");
		}
	
		return voter;

	}
}
