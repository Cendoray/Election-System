package election.ui.gui;


//import java.io.FileNotFoundException;
import java.io.IOException;


import election.business.DawsonElectionFactory;
import election.business.DawsonElectionOffice;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.data.ElectionListDB;
import election.data.InexistentElectionException;
import election.data.ObjectSerializedList;
import election.data.VoterListDB;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.VoterDAO;

public class StubGUIApp {

  public static void main(String[] args) throws InexistentElectionException, IOException { 
    ElectionFactory factory = 
        DawsonElectionFactory.DAWSON_ELECTION;
    VoterDAO voterDb = new VoterListDB(new ObjectSerializedList("datafiles/database/voters.ser",
        "datafiles/database/elections.ser"));
    ElectionDAO electionDb =
        new ElectionListDB(new ObjectSerializedList("datafiles/database/voters.ser",
            "datafiles/database/elections.ser"));
    Election e = electionDb.getElection("Brittany independence referendum");
    DawsonElectionOffice model = new DawsonElectionOffice(factory, electionDb, voterDb);

    election.ui.gui.MainApp.initContext(model, e);
    javafx.application.Application.launch(election.ui.gui.MainApp.class);
  }
}
