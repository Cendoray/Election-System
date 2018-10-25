package election.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import election.data.interfaces.ListPersistenceObject;
import util.ListUtilities;

/**
 * Class that implements ListPersistenceObject for text files populated with
 * toString() representation of the objects.
 * @author Maja, Jaya
 **/
public class SequentialTextFileList implements ListPersistenceObject {

 private final String voterFilename;
 private final String electionFilename;
 private final String tallyFilename;
 
 /**
  * Constructor requires the filenames of the files containing the sorted
  * string representations of the Voter, Elections, and Tallies.
  * @param voterFilename Text file with sorted Voters
  * @param electionFilename Text file with sorted Elections
  * @param tallyFilename Text file with sorted Tallies
  */
 public SequentialTextFileList (String voterFilename,
   String electionFilename, String tallyFilename) {
  this.voterFilename = voterFilename;
  this.electionFilename = electionFilename;
  this.tallyFilename = tallyFilename;
 }
 
 /** 
  * Returns a reference to an arraylist containing the voters. If an
  * IOException occurs an ArrayList of size zero will be returned.
  */
 @Override
 public List<Voter> getVoterDatabase() {
  Voter[] voters;
  try{
   voters = 
   ElectionFileLoader.getVoterListFromSequentialFile(this.voterFilename);
  }
  catch (IOException e) {
   return new ArrayList<Voter>();
  }

  // Create the adapter object that will be used as an argument to 
  // instantiate an ArrayList instance.

  List<Voter> listAdapter = java.util.Arrays.asList(voters);

  // return a reference to an ArrayList instance.
  return new ArrayList<Voter>(listAdapter);
 }
 
 /** 
  * Returns a reference to an arraylist containing the elections. If an
  * IOException occurs an ArrayList of size zero will be returned.Once the Election
  * objects are created, the Tallies needs to be associated
  */
 @Override
 public List<Election> getElectionDatabase() {
  Election[] elections;
  
  try{
   elections = 
   ElectionFileLoader.getElectionListFromSequentialFile(this.electionFilename);
   ElectionFileLoader.setExistingTallyFromSequentialFile(this.tallyFilename, elections);
  }
  catch (IOException e) {
   return new ArrayList<Election>();
  }

  // Create the adapter object that will be used as an argument to 
  // instantiate an ArrayList instance.

  List<Election> listAdapter = java.util.Arrays.asList(elections);

  // return a reference to an ArrayList instance.
  return new ArrayList<Election>(listAdapter);
 }

 /**
  * Saves the list of voters to the text file
  */
 @Override
 public void saveVoterDatabase(List<Voter> voters) throws IOException {
  //For now we'll use the existing saveListToTextFile utility method.
  Voter[] voterArray = 
    voters.toArray(new Voter[voters.size()]);
  ListUtilities.saveListToTextFile(voterArray, this.voterFilename);

 }

 /**
  * Saves the list of elections and their tallies to the text files
  */
 @Override
 public void saveElectionDatabase(List<Election> elections) throws IOException {
  //For now we'll use the existing saveListToTextFile utility method.
  Election[] electionArray = 
    elections.toArray(new Election[elections.size()]);
  ListUtilities.saveListToTextFile(electionArray, this.electionFilename);
  
  Tally[] tallies = new Tally[electionArray.length];
  for (int i = 0; i < tallies.length; i++) {
   tallies[i] = electionArray[i].getTally();
  }
  ListUtilities.saveListToTextFile(tallies, this.tallyFilename);
 }


}

