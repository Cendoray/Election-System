package election.business;

import election.business.interfaces.*;

/**
 * Represents a Ballot where each BallotItem must be ranked
 * in order of preference.
 * @author Maja, Jaya
 * */

public class DawsonRankedBallot implements Ballot {
 private static final long serialVersionUID = 42031768871L;
 private BallotItem[] choices;
 private Election election;


 public DawsonRankedBallot (BallotItem[] items, Election election) {
  this.choices = validate(items);
  if (election == null)
   throw new IllegalArgumentException("Election cannot be null");
  this.election = election;
  
 }
 @Override
 public int compareTo(Ballot o) {
  BallotItem[] other = o.getBallotItems();
  int compare = this.choices.length - other.length;
  int i = 0;
  while (compare != 0 && i < this.choices.length) {
   compare = this.choices[i].compareTo(other[i]);
  }
  return compare;
 }

 @Override
 public boolean validateSelections() {
  int count = 0;
  for (int i=0; i<choices.length; i++) {
   count = 0;
   for (BallotItem b : choices) {
    if (b.getValue() == i)
     count++;
   }
   if (count > 1)
    return false;
  }
  return true;
 }
 
 @Override
 public Election getElection() {
  return this.election;
 }
 
 @Override
 public void cast(Voter v) {
  this.election.castBallot(this, v);
 }
 

 @Override
 public void selectBallotItem(int position, int value) {
  //validate
  if (position <0 || position >= choices.length)
   throw new IllegalArgumentException("Requested position is invalid");

   if (value <0 || value >= choices.length)
    throw new IllegalArgumentException("Requested value is invalid with a ranked choice election");

   choices[position].setValue(value);
 }

 @Override
 public BallotItem[] getBallotItems() {
  BallotItem[] copy = new BallotItem[choices.length];
  for (int i = 0; i < choices.length; i++)
   copy[i] = DawsonElectionFactory.DAWSON_ELECTION.getBallotItem(choices[i]);
  return copy;
 }

 
 private BallotItem[] validate(BallotItem[] items) {
  if (items == null)
   throw new IllegalArgumentException("BallotItem array cannot be null");
  for (BallotItem b : items) {
   if (b==null)
    throw new IllegalArgumentException("BallotItem in the array cannot be null");    
  }
  if (items.length <2)
   throw new IllegalArgumentException("need at least 2 BallotItems");
  return items;
 }


}
