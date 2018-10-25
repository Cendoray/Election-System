package election.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.ElectionPolicy;
import election.business.interfaces.ElectionType;

/**
 * This code is to determine the winner of a ranked dawson election
 * @author Anthony Whitebean
 * @Version 11/22/2017
 *
 */
public class DawsonRankedElectionPolicy implements ElectionPolicy{
	private Election election;
	
	private static final long serialVersionUID = 42031768871L;
	
	public DawsonRankedElectionPolicy (Election election) throws IllegalArgumentException
	{
		if (election == null)
		{
			throw new IllegalArgumentException("The election sent in is null");
		}
		//This part will throw exception if it is not ranked
		if (!(election.getElectionType() == ElectionType.RANKED))
		{
			throw new IllegalArgumentException("The election sent in is not ranked");
		}
		this.election = election;
	}
	
	/**
	 * This code will return the winner of the ranked election
	 * 
	 * @return a list representing the list of the winner(s)
	 * @throws IncompleteElectionException if the election is not finished
	 */
	@Override
	public List<String> getWinner() throws IncompleteElectionException{
		if (election.getEndDate().isAfter(LocalDate.now()))
		{
			throw new IncompleteElectionException("The election is not over");
		}
		List<String> list = new ArrayList<String>();
		int highest = 0;
		for (int i = 0; i < election.getElectionChoices().length; i++)
		{
			int sum = 0;
			sum = sum + (election.getTally().getVoteBreakdown()[i][0] * 5);
			sum = sum + (election.getTally().getVoteBreakdown()[i][1] * 2);
			if (sum > highest)
			{
				highest = sum;
			}
		}
		for (int i = 0; i < election.getElectionChoices().length; i++)
		{
			int sum = 0;
			sum = sum + (election.getTally().getVoteBreakdown()[i][0] * 5);
			sum = sum + (election.getTally().getVoteBreakdown()[i][1] * 2);
			if (sum == highest)
			{
				list.add(election.getElectionChoices()[i]);
			}
		}
		return list;
	}
}
