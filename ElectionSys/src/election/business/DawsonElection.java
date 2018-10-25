package election.business;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.ElectionType;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import election.business.interfaces.Election;
import election.business.InvalidVoterException;
import util.ListUtilities;

/**
 *
 * @author AnthonyWhitebean
 * @version 10/16/2017
 */
public class DawsonElection implements Election, Serializable, Comparable<Election>
{
	private String name;
	private ElectionType election;
	private LocalDate start;
	private LocalDate end;
	private String postalCodeStart;
	private String postalCodeEnd;
	private BallotItem[] list;
	private Tally tally;
	private static final long serialVersionUID = 42031768871L;
	private List<Voter> gotBallot;
	private List<Voter> castBallot;
	private int counter = 0;
	
	/**
	 * Constructor for the dawson election class.
	 * 
	 * @param name representing name of election
	 * @param type representing type of election
	 * @param startYear representing starting year
	 * @param startMonth representing starting month
	 * @param startDay representing starting day
	 * @param endYear representing ending day
	 * @param endMonth representing ending month
	 * @param endDay representing ending day
	 * @param startRange representing an optional start range
	 * @param endRange representing an optional end range
	 * @param tally representing a tally.
	 * @param items representing the decisions allowed.
	 * @throws IllegalArgumentException when values sent in are incorrect
	 * @throws NullPointerException representing when a value that can't be null is null
	 * @throws DateTimeException representing a LocalDate error
	 */
	public DawsonElection(String name, String type, int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay, String startRange, String endRange, Tally tally, String... items) throws IllegalArgumentException, NullPointerException, DateTimeException
	{
		validation("Election Name field", name);
		validation("Election Type field", type);
		validation(items);
		if (tally == null)
		{
			throw new IllegalArgumentException("The tally cannot be null");
		}
		election = election.valueOf((type.toUpperCase()).trim());
		start = LocalDate.of(startYear, startMonth, startDay);
		end = LocalDate.of(endYear, endMonth, endDay);
		if (start.isAfter(end))
		{
			throw new IllegalArgumentException("The starting date is after the end date.");
		}
		list = new BallotItem[items.length];
		for (int i = 0; i < items.length; i++)
		{
			 list[i] = new DawsonBallotItem(items[i],items.length-1,i);
		}
		this.name = name;
		if (startRange == null || endRange == null)
		{
			this.postalCodeStart = null;
			this.postalCodeEnd = null;
		}
		else
		{
			validator(startRange, endRange);
			this.postalCodeStart = startRange;
			this.postalCodeEnd = endRange;
		}
		this.tally = tally;
		gotBallot = new ArrayList<Voter>();
		castBallot = new ArrayList<Voter>();
	}
	
	/**
	 * This will validate if the ranges sent in are valid
	 * @param startRange representing the start range of the election
	 * @param endRange representing the end range of the election
	 * @throws IllegalArgumentException if the start range is bigger than the end range
	 */
	private void validator(String startRange, String endRange) throws IllegalArgumentException
	{
		String upperCaseStart = startRange.toUpperCase();
		String upperCaseEnd = endRange.toUpperCase();
		if (upperCaseStart.compareTo(upperCaseEnd) > 0)
			throw new IllegalArgumentException("The start range is bigger than the end range");	
	}

	/**
	 * This returns the starting date of the election
	 * 
	 * @return LocalDate representing the starting day of the election.
	 */
	@Override
	public LocalDate getStartDate()
	{
		return start;
	}
	
	/**
	 * This returns the ending date of the election
	 * 
	 * @return LocalDate representing the ending date of the election.
	 */
	@Override
	public LocalDate getEndDate()
	{
		return end;
	}

	/**
	 * This code will return the name of the election
	 * 
	 * @return a String representing the name of the election
	 */
	@Override
	public String getName()
	{
		return name;
	}
	/**
	 * This will tell if the election is limited by a postal range
	 * 
	 * @return boolean representing if the code is limited by postal range
	 */
	@Override
	public boolean isLimitedToPostalRange()
	{
		if ((postalCodeStart != null) && (postalCodeEnd != null))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This code will return  ballot for this election
	 * 
	 * @return a ballot about the election.
	 */
	public Ballot getBallot()
	{
		Ballot voting;
		BallotItem[] newList = new BallotItem[list.length];
		for (int i = 0 ; i < list.length; i++)
		{
			newList[i] = DawsonElectionFactory.DAWSON_ELECTION.getBallotItem(this.list[i]);
		}
		voting = DawsonElectionFactory.DAWSON_ELECTION.getBallot(newList, getElectionType(), this);
		return voting;
	}

	
	/**
	 * This class will set the tally to a new tally
	 * 
	 * @param newTally a tally that this election's tally is gonna change into
	 * @throw IllegalArgumentException when the new Tally does not equal the tally elections name
	 * @throw NullPointerException when the new Tally is null
	 */
	@Override
	public void setTally(Tally newTally) throws IllegalArgumentException, NullPointerException
	{
		if (newTally == null)
		{
			throw new NullPointerException("The tally that is being sent in is null.");
		}
		if (!(newTally.getElectionName() == this.getName()))
		{
			throw new IllegalArgumentException("The tally election name is not the same as this name.");
		}
		tally = newTally;
	}
	
	/**
	 * This method will return the tally, not deep copy
	 * 
	 * @return a Tally representing this elections tally.
	 */
	@Override
	public Tally getTally()
	{
		return tally;
	}
	
	/**
	 * This will allow a voter to cast into a ballot
	 * 
	 * @param a Ballot representing the ballot to be cast into
	 * @param a Voter representing the voter to cast
	 * 
	 * @IllegalArgumentException when ballot is not valid, or when voter is null
	 * @InvalidVoterException when the voter is not valid to send in to the ballot
	 */
	@Override
	public void castBallot(Ballot ballot, Voter voter)throws IllegalArgumentException, InvalidVoterException
	{
		if (validateSelection(ballot) == false)
		{
			throw new IllegalArgumentException("The ballot sent in is not valid");
		}
		if (voter == null)
		{
			throw new IllegalArgumentException("The voter sent in is null.");
		}
		if (voter.isEligible(this) == false)
		{
			counter++;
			throw new InvalidVoterException("The voter is not eligible.");
		}
		if (ListUtilities.binarySearch(gotBallot, voter, 0, gotBallot.size()-1) < 0)
		{
			counter++;
			throw new InvalidVoterException("The voter has not requested a ballot.");
		}
		int check = ListUtilities.binarySearch(castBallot, voter, 0, castBallot.size()-1);
		if (check > 0)
		{
			counter++;
			throw new InvalidVoterException("The voter has already cast in the ballot.");
		}
		check = check + 1;
		check = check * -1;
		castBallot.add(check, voter);
		if (ballot.validateSelections() == true)
		{
			tally.update(ballot);
		}
		else
		{
			counter++;
			throw new InvalidVoterException("The ballot is not valid for selection");
		}
	}
	
	/**
	 * This will validate if a ballot is valid or not
	 * @param ballot representing ballot to be tested
	 * @return a boolean representing if it is valid
	 */
	private boolean validateSelection(Ballot ballot){
		if (ballot == null)
		{
			return false;
		}
		BallotItem[] test = ballot.getBallotItems();
		for (int i = 0; i < test.length; i++)
		{
			if (test[i] == null)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * This will return the election choices
	 * 
	 * @return A string array representing the election choices
	 */
	@Override
	public String[] getElectionChoices()
	{
		String[] array = new String[list.length];
		for (int i = 0; i < list.length;i++)
		{
			array[i] = list[i].getChoice();
		}
		return array;
	}
		
	/**
	 * This code will check if a string is empty or null
	 * @param field name of the string being tested
	 * @param string the string being tested
	 * @throws IllegalArgumentException if the string is empty
	 * @throws NullPointerException if string is null
	 */
	private void validation(String field, String string) throws IllegalArgumentException, NullPointerException
	{
		if (string == null)
		{
			throw new NullPointerException("The " + field + " is null.");
		}
		if (string == "")
		{
			throw new IllegalArgumentException("The " + field + " is empty");
		}
	}
	
	/**
	 * This code will check to see if a string list is valid
	 * @param list representing list being compared
	 * @throws IllegalArgumentException when invalid data is sent.
	 */
	private void validation(String... items) throws IllegalArgumentException
	{
		if (items == null)
		{
			throw new NullPointerException("The ballot list is null.");
		}
		if (items.length < 2)
		{
			throw new IllegalArgumentException("The election has to have at least two choices.");
		}
		for (int i = 0; i < items.length; i++)
		{
			validation("item from ballot list (Index "+i+")", items[i]);
		}
	}

	@Override
	public String toString()
	{
		String representation = "";
		representation = this.name + "*" + this.start.getYear() + "*" + this.start.getMonthValue() + "*"+ this.start.getDayOfMonth() + "*" + this.end.getYear() + "*" + this.end.getMonthValue() + "*"+ this.end.getDayOfMonth();
		if (postalCodeStart == null)
		{
			representation = representation + "**";
		}
		else
		{
			representation = representation + "*" + postalCodeStart + "*" + postalCodeEnd;
		}
		representation = representation + "*" + election + "*" + (list[0].getMaxValue()+1);
		for (int i = 0; i <= list[0].getMaxValue(); i++)
		{
			representation = representation + "\r\n" + list[i].getChoice();
		}
		return representation;
	}
	
	@Override
	public final boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (object == null)
		{
			return false;
		}
		if (!(object instanceof DawsonElection))
		{
			return false;
		}
		DawsonElection testedObject = (DawsonElection) object;
		if (this.getName() == testedObject.getName())
		{
			return true;
		}
		return false;		
	}
	
	@Override
	public final int hashCode()
	{
		return this.name.hashCode();
	}

	@Override
	public int compareTo(Election o) {
		return (this.getName().compareTo(o.getName()));
	}

	/**
	 * This code will return the election type of the election
	 * 
	 * @return an ElectionType represetning the election type of the election
	 */
	@Override
	public ElectionType getElectionType() {
		return (this.election);
	}

	/**
	 * This code will return the end range of the election
	 * 
	 * @return A string representing the end range of the election
	 */
	@Override
	public String getPostalRangeEnd() {
		return this.postalCodeEnd;
	}

	/**
	 * This code will return the start range of the election
	 * 
	 * @return A string representing the start of the range
	 */
	@Override
	public String getPostalRangeStart() {
		return this.postalCodeStart;
	}

	/**
	 * THis method will get the ballot of the election for a voter
	 * 
	 * @param a Voter representing voter getting the ballot
	 * @return a Ballot representing the ballot of the Election
	 * 
	 * @IllegalArgumentException when voter sent in is null
	 * @InvalidVoterException when the voter is not valid to get ballot, or when he has already casted
	 */
	@Override
	public Ballot getBallot(Voter v) throws IllegalArgumentException, InvalidVoterException {
		if (v == null)
		{
			throw new IllegalArgumentException("The voter is null.");
		}
		if (v.isEligible(this) == false)
		{
			throw new InvalidVoterException("The voter is not valid to get the ballot");
		}
		int check = ListUtilities.binarySearch(gotBallot, v, 0, gotBallot.size() - 1);
		if (check < 0)
		{
			check = check + 1;
			check = check * -1;
			gotBallot.add(check,v);
		}
		else
		{
			if (ListUtilities.binarySearch(castBallot,v,0,castBallot.size() - 1) >= 0)
			{
				throw new InvalidVoterException("The voter has already cast a ballot");
			}
		}
		Ballot ballot =DawsonElectionFactory.DAWSON_ELECTION.getBallot(this.list, this.election, this);
		return ballot;
	}

	/**
	 * This will return the total casts in the ballot
	 * 
	 * @return an Int representing number
	 */
	@Override
	public int getTotalVotesCast(){
		return castBallot.size();
	}

	/**
	 * This will return the number of invalid attempts
	 * 
	 * @return an Int representing failed attempts
	 */
	@Override
	public int getInvalidVoteAttempts(){
		return counter;
	}

}

