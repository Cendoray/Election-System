package election.business;

import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.ElectionPolicy;
import election.business.interfaces.ElectionType;
import election.business.interfaces.Voter;

/**
 * Enum that implements the ElectionFactory interface. Since it is an
 * enum type, there will only be as many objects as there are enum
 * constants - in this case only one. As such, the factory is a
 * singleton. The factory provides methods top centralize the 
 * instantiation of all concrete classes related to the Dawson 
 * system. This facilitates the rest of the code being programmed
 * to interfaces instead of concrete implementations
 * @author Maja, Jaya
 **/
public enum DawsonElectionFactory implements ElectionFactory {
 DAWSON_ELECTION;

 @Override
 public Voter getVoterInstance(String firstName, String lastName, String email, String postalcode) {
  return new DawsonVoter(firstName, lastName, email, postalcode);
 }
 
 @Override
 public Voter getVoterInstance(Voter v) {
  return new DawsonVoter(v.getName().getFirstName(), v.getName().getLastName(), 
    v.getEmail().getAddress(), v.getPostalCode().getCode());
 }


 @Override
 public Election getElectionInstance(String name, String type, int startYear, int startMonth, int startDay, 
   int endYear, int endMonth, int endDay, String startRange, String endRange, String... choices) {
  return new DawsonElection( name,  type,  startYear,  startMonth,  startDay, 
     endYear,  endMonth,  endDay,  startRange,  endRange, new DawsonTally(choices.length, name), choices);
 }

 @Override
 public Election getElectionInstance(Election e) {
  return new DawsonElection( e.getName(),  e.getElectionType().name(),  e.getStartDate().getYear(),  e.getStartDate().getMonthValue(),  e.getStartDate().getDayOfMonth(), 
    e.getEndDate().getYear(),  e.getEndDate().getMonthValue(),  e.getEndDate().getDayOfMonth(),
    e.getPostalRangeStart(),  e.getPostalRangeEnd(), new DawsonTally(e.getElectionChoices().length, e.getName()), e.getElectionChoices());
 }
 
 
 @Override
 public void setExistingTally(int[][] results, Election e) {
  DawsonTally t = new DawsonTally(e.getName(), results);
  e.setTally(t);  
 }

 @Override
 public Ballot getBallot(BallotItem[] choices, ElectionType type, Election election) {
  switch (type) {
  case SINGLE:
   return new DawsonSingleBallot(choices, election);
  case RANKED:
   return new DawsonRankedBallot(choices, election);
  default:
   throw new IllegalArgumentException("Added more constants to ElectionType enum?");
  }

 }

 @Override
 public BallotItem getBallotItem(String choice, ElectionType type, int totalChoices) {
  switch (type) {
  case SINGLE:
   return new DawsonBallotItem(choice, 1);
  default:
   return new DawsonBallotItem(choice, totalChoices-1);
  }

 }

 @Override
 public BallotItem getBallotItem(BallotItem copy) {
  return new DawsonBallotItem(copy);
 }
 
 @Override
 public ElectionPolicy getElectionPolicy(Election e) {
  switch (e.getElectionType()) {
   case SINGLE:
    return new DawsonSingleElectionPolicy(e);
   case RANKED:
    return new DawsonRankedElectionPolicy (e);
   default:
    throw new RuntimeException("Added more constants to ElectionType enum?");    
  }
 }


}
