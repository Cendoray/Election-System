package election.business.interfaces;

import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.Election;
import election.business.interfaces.Voter;

public class StubBallot implements Ballot {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1103085460961824604L;

	public StubBallot (BallotItem[] items, Election election) {
				
	}

	@Override
	public int compareTo(Ballot o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cast(Voter voter) {
		// TODO Auto-generated method stub

	}

	@Override
	public BallotItem[] getBallotItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Election getElection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectBallotItem(int position, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateSelections() {
		// TODO Auto-generated method stub
		return false;
	}

}
