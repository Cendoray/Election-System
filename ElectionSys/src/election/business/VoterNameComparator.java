/**
 * 
 */
package election.business;

import java.util.Comparator;

import election.business.interfaces.Voter;
/**
 * @author 1640463
 *
 */
public class VoterNameComparator implements Comparator<Voter>{
	@Override
	public int compare(Voter voter1, Voter voter2)
	{
		if (voter1 == voter2)
		{
			return 0;
		}
		if (!voter1.getName().equals(voter2.getName()))
		{
			return voter1.getName().compareTo(voter2.getName());
		}
		return voter1.compareTo(voter2);
	}
}
