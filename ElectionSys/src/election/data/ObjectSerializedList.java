package election.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import election.data.interfaces.ListPersistenceObject;
import util.ListUtilities;
import util.Utilities;

public class ObjectSerializedList implements ListPersistenceObject {

	private String fileVoter;
	private String fileElection;
	
	public ObjectSerializedList(String voter, String election ) throws FileNotFoundException {

		this.fileVoter = voter;		
		this.fileElection = election;
	}
	
	
	
	@Override
	public List<Voter> getVoterDatabase() {
		List<Voter> voterlist = new ArrayList<>();
		try {
			voterlist=(List<Voter>) Utilities.deserializeObject(fileVoter);
		} catch (ClassNotFoundException e) {
			System.out.println("An error was found, classNotFoundException, returning an empty list : \n" + e.getMessage());
			return voterlist;
		} catch (IOException e) {
			System.out.println("An error was found, IOException, returning an empty list: \n" + e.getMessage());
			return voterlist;
		}
		return voterlist;
	}

	@Override
	public List<Election> getElectionDatabase() {
		List<Election> electionlist = new ArrayList<>();
		try {
			electionlist=(List<Election>)Utilities.deserializeObject(fileElection);
		} catch (ClassNotFoundException e) {
			System.out.println("An error was found, classNotFoundException, returning an empty list: \n" + e.getMessage());
			return electionlist;
		} catch (IOException e) {
			System.out.println("An error was found, IOException, returning an empty list: \n" + e.getMessage());
			return electionlist;
		}
		return electionlist;
	}

	@Override
	public void saveVoterDatabase(List<Voter> voters) throws IOException {
		Utilities.serializeObject(voters, fileVoter);
	}

	@Override
	public void saveElectionDatabase(List<Election> elections) throws IOException {
		Utilities.serializeObject(elections, fileElection);

	}
}
