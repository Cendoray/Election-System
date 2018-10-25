package election.business;

import election.business.interfaces.*;

/** Tracks and counts ballot selections.
  * @author Jaya, Maja
  * */

public class DawsonTally implements Tally{
 
 private static final long serialVersionUID = 42031768871L;
 private int[][] results;
 private String myElection;
 
 public DawsonTally(int choices, String election) {
  myElection = election;
  //each row (1st offset) for the candidate, column (2nd offset) for ranking 0 to n-1
  //for example, a ballot with two candidates would have a column for 0 and a column for 1 (selected)
  results = new int[choices][choices];
 }
 
 public DawsonTally(String election, int[][] results){
  this(results.length, election);
  if ( results.length != this.results.length)
   throw new IllegalArgumentException("Tally has been given an incorrect starting results.");
  for(int i = 0; i < results.length; i++) {
   if (results[i].length != this.results[i].length) 
    throw new IllegalArgumentException("Tally has been given an incorrect starting results.");
  }
  //deep copy the results
   for(int i = 0; i < results.length; i++)
    for (int j=0; j<results[0].length; j++)
     this.results[i][j] = results[i][j];
 }

 public void update(Ballot ballot) {
	  BallotItem[] choices = ballot.getBallotItems();
	  if ( choices.length != results.length)
	   throw new IllegalArgumentException("Tally expeced "+results.length+"choices, ballot had "+choices.length+" choices.");
	  
	  //check if only 1 choice
	  int sum = 0;
	  for(int i = 0; i < choices.length; i++) {
	   if (choices[i].getValue() > results[i].length) //ranking of n candidates from 0 to n-1
	    throw new IllegalArgumentException("Ballot has an invalid value." + choices[i].getValue() );
	   sum += choices[i].getValue();
	  }
	  if (sum == 1) {
	   //single ballot
	   for(int i = 0; i < choices.length; i++) {
	    if (choices[i].getValue() > 0)
	     results[i][i] ++ ;
	   }
	  } else {
	   //ranked ballot
	   for(int i = 0; i < choices.length; i++) {
	    results[i][choices[i].getValue()] ++ ;
	   }
	  }
	 }
 
 public int[][] getVoteBreakdown() {
  int[][] copy = new int[results.length][results[0].length];
  for(int i = 0; i < results.length; i++)
   for (int j=0; j<results[0].length; j++)
    copy[i][j] = results[i][j];
  
  return copy;
 }
 
 @Override
 public String getElectionName() {
  return myElection;
 }
 
 public String toString() {
  String str = myElection;
  str += "*" +results.length +"\n";
  String values = "";
  int j;
  for(int i = 0; i < results.length; i++) {
   for (j=0; j<results[0].length-1; j++)
    values += results[i][j]+"*";
   values += results[i][j] + "\n";
  }
  //remove the last "\n"
  values = values.substring(0, values.length()-"\n".length());
  return str+values;
 }
 
}
