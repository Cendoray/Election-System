package election.business.interfaces;

import election.business.interfaces.Ballot;
import election.business.interfaces.Tally;

public class StubTally implements Tally {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4839151081071992150L;

	public StubTally() {

	}

	@Override
	public String getElectionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getVoteBreakdown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ballot ballot) {
		// TODO Auto-generated method stub

	}

}
