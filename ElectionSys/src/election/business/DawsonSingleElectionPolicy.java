/**
 * 
 */
package election.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.ElectionPolicy;
import election.business.interfaces.ElectionType;

/**
 * @author Anthony Whitebean
 * @version 11/20/2017
 *
 */
public class DawsonSingleElectionPolicy implements ElectionPolicy{
	private Election election;

	private static int pairs=0;
	private static final long serialVersionUID = 42031768871L;
	
	public DawsonSingleElectionPolicy (Election election) throws IllegalArgumentException
	{
		if (election == null)
		{
			throw new IllegalArgumentException("The election sent in is null");
		}
		//This part will throw an exception if it is not single
		if (!(election.getElectionType() == ElectionType.SINGLE))
		{
			throw new IllegalArgumentException("The election is not a SINGLE election");
		}
		this.election = election;
	}

	/**
	 * This code will return the winner of the single election
	 * 
	 * @return a list representing the list of the winner(s)
	 * @throws IncompleteElectionException if the election is not finished
	 */
	@Override
	public List<String> getWinner() throws IncompleteElectionException{
		if (election.getEndDate().isAfter(LocalDate.now()))
		{
			if (pairs%2!=0)
				System.out.println("Election is not yet complete!\nWait until it is over!");
			pairs++;
			return null;
		}
		List<String> list = new ArrayList<String>();
		int majority = 0;
		for (int i = 0; i < election.getElectionChoices().length; i++)
		{
			majority = majority + election.getTally().getVoteBreakdown()[i][i];
		}
		majority = (majority / 2) + 1;
		for (int i = 0; i < election.getElectionChoices().length; i++)
		{
			if (election.getTally().getVoteBreakdown()[i][i] >= majority)
			{
				list.add(election.getElectionChoices()[i]);
			}
		}
		return list;
	}	
}
