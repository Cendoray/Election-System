package election.ui.tui;


import java.io.FileNotFoundException;

import election.business.*;
import election.business.interfaces.ElectionFactory;
import election.data.*;
import election.data.interfaces.*;

/** Main application for the Text-based user interface
  * @author Jaya, Maja
  */
public class TextApp {

 public static void main(String[] args) throws FileNotFoundException, DuplicateVoterException {
  ElectionFactory factory = 
    DawsonElectionFactory.DAWSON_ELECTION;
  VoterDAO voterDb =
    new VoterListDB(new ObjectSerializedList
      ("datafiles/database/voters.ser" ,
       "datafiles/database/elections.ser"));
  ElectionDAO electionDb =
    new ElectionListDB(new ObjectSerializedList
      ("datafiles/database/voters.ser" ,
      "datafiles/database/elections.ser"));

  DawsonElectionOffice model = new DawsonElectionOffice(factory, electionDb, voterDb);
  TextView view = new TextView(model);
  TextController controller = new TextController(model);
  controller.run();
 }
}
