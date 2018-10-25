/**
 * 
 */
package election.business;
import java.io.Serializable;

import election.business.interfaces.*;

/**
 * @author Daniel Rafail
 * @version 9/25/2017
 */
public class DawsonBallotItem implements BallotItem, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 42031768871L;
	private String str;
	private int value=0;
	private final int maxValue;	
	/**
	 * Constructor with the optional value variable (0 by default)
	 * @param str the string used for the choice
	 * @param value the value used for the ballot
	 * @param maxValue the maximum value possible in the ballot
	 */
	public DawsonBallotItem(String str, int maxValue,  int value){
		//validating the parameters
		if (str==null || str.isEmpty())
			throw new IllegalArgumentException("Do not use a null or empty string");
		if (maxValue<1)
			throw new IllegalArgumentException("Max Value cannot be smaller than 1");
		if (value<0)
			throw new IllegalArgumentException("value cannot be smaller than 0");
		if (value>maxValue)
			throw new IllegalArgumentException("Max value cannot be smaller than value");
		this.str=str;
		this.value=value;
		this.maxValue=maxValue;
		
		
	}
	/**
	 * Constructor without the optional value variable (0 by default)
	 * @param str
	 * @param maxValue
	 */
	public DawsonBallotItem(String str, int maxValue){
		if (str==null || str.isEmpty())
			throw new IllegalArgumentException("Do not use a null or empty string");
		if (maxValue<1)
			throw new IllegalArgumentException("Max Value cannot be smaller than 1");
		this.str=str;
		this.maxValue=maxValue;
		}
	/**
	 * Copy constructor
	 * @param copy the copy of the DawsonBallotItem
	 */
	public DawsonBallotItem(BallotItem copy){
		this(copy.getChoice(), copy.getMaxValue());
		
	}
	
	
	/** 
	 * Override of the equals method to make some changes to it so it is more specific
	 * @param Object the object we use to do the comparison
	 * @return boolean returns a boolean depending on if the two objects compared are equal
	 */
	@Override
	public final boolean equals(Object obj){
		if (this == obj)
			return true;
		
		if (obj==null)
			return false;
		
		if (!(obj instanceof BallotItem))
			return false;
		DawsonBallotItem other=(DawsonBallotItem) obj;
		return this.str.equals(other.getChoice());	
	}
	
	
	/**
	 * Override of the hashCode method to make it based off get getChoice method, which is used in the equals method
	 * @return int the new hashCode value
	 */
	@Override
	public final int hashCode(){
		return this.getChoice().hashCode();
	}
	
	/**
	 * Override the compareTo method to make it based off the BallotItem 
	 * @return int an integer depending on the result of the comparison
	 * @throws IllegalArgumentException if the this is not instance of ballotitem
	 */
	@Override
	public final int compareTo(BallotItem item) throws IllegalArgumentException{
		return this.getChoice().compareTo(item.getChoice());
	}

	
	/**
	 * method used to get the choice variable
	 * @return returns a String, defensive copy of Choice string
	 */
	@Override
	public String getChoice() {
		String item=this.str;
		return item;
	}

	
	/**
	 * method used to get the maxValue variable
	 * @return returns an int, defensive copy of maxValue int
	 */
	@Override
	public int getMaxValue() {
		int item=this.maxValue;
		return item;
	}


	/**
	 * method used to get the value variable
	 * @return returns an int, defensive copy of value int
	 */
	@Override	
	public int getValue() {
		int item=this.value;
		return item;
	}

	
	/**
	 * method used to set the value variable
	 */
	@Override
	public void setValue(int value) {
		if(value< 0 || value>maxValue)
			throw new IllegalArgumentException("Please make sure your value is within the range (0 and maxValue)");
		this.value=value;
	}
	
	
	/**
	 * method used to get a string with both the choice and the value
	 * @return String a string which has both the choice variable and the value variable
	 */
	@Override
	public String toString(){
		return this.str+Integer.toString(value);
	}

}
