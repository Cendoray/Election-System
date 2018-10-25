/**
 * 
 */
package election.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;

/**
 * @author Daniel
 *
 */
public class SerializedFileLoaderApp {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		SequentialTextFileList stfl = new SequentialTextFileList("datafiles/database/voters.txt","datafiles/database/elections.txt","datafiles/database/tally.txt");
		ObjectSerializedList osl = new ObjectSerializedList("datafiles/database/voters.ser", "datafiles/database/elections.ser");
		List<Voter> voter =stfl.getVoterDatabase();
		List<Election> election = stfl.getElectionDatabase();
		
		osl.saveElectionDatabase(election);
		osl.saveVoterDatabase(voter);
		
		List<Voter> voter1 = osl.getVoterDatabase();
		//System.out.println(voter1.toString());
		
		List<Election> election1 = osl.getElectionDatabase();
		//System.out.println(election1.toString());
	}

}
