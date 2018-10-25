package election.ui.tui;

import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TextView implements Observer {

	public TextView(Observable item) {
		item.addObserver(this);
	}

	public void update(Observable o, Object arg) {
		if (arg instanceof Election) {
			printElection(arg);
			}

		if (arg instanceof Voter) {
			printVoter(arg);		
		}
		
		if (arg instanceof List<?>) {
			printElectionWinner(arg);
		}
	}
	
	private void printVoter(Object arg) {
		Voter voter = (Voter) arg;
		System.out.println("\nVoter Information: ");
		System.out.println("Name: " + voter.getName().getFullName());
		System.out.println("Email: " + voter.getEmail().getAddress());
		System.out.println("Postal Code: " + voter.getPostalCode().getCode());
	}
	
	private void printElection(Object arg) {
		Election election = (Election) arg;
		System.out.println("\nElection Information");
		System.out.println("Name: " + election.getName());
		System.out.println("Start Date: " + election.getStartDate().toString());
		System.out.println("End Date: " + election.getEndDate().toString());
		
	}
	
	@SuppressWarnings("unchecked")
	private void printElectionWinner(Object arg) {
		List<String> getWinners = (List<String>) arg;
		for (int i = 0; i < getWinners.size(); i++)
		{
			System.out.println(getWinners.get(i).toString());
		}
		//System.out.println("\nElection Information");
		//System.out.println(getWinners.getName().toString());
		//System.out.println(getWinners.getStartDate().toString());
		//System.out.println(getWinners.getEndDate().toString());
	}
}
