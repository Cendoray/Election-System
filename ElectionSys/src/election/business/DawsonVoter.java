package election.business;

import java.io.Serializable;

import java.time.LocalDate;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import lib.PostalCode; 
import lib.Name;
import lib.Email;

/**
 * @author Daniel Rafail
 * @version 9/24/2017
 */

public class DawsonVoter implements Serializable, Voter {

	//all the variables
	private Name name;
	private Email email;
	private PostalCode postalCode;
	private static final long serialVersionUID = 42031768871L;
	
/**
 * Constructor, takes in a first name string, last name string, email string and postCode string
 * @param firstname the first name of the voter object
 * @param lastname the last name of the voter object
 * @param email the email address of the voter object
 * @param postCode the postal code of the voter object
 */
	public DawsonVoter( String firstname, String lastname, String email, String postCode) {
		this.name= new Name(firstname, lastname);
		this.email=new Email(email);
		this.postalCode= new PostalCode(postCode);
	}
	
	/**
	 * This code will get the name object ( in a defensive way)
	 * 
	 * @return name name object from name class
	 */
	public Name getName() {
		return new Name(name.getFirstName(), name.getLastName());
	}
	/**
	 * This code will get the email object ( in a defensive way)
	 * 
	 * @return name email object from Email class
	 */
	public Email getEmail() {
		return new Email(email.getAddress());
	}
	/**
	 * This code will get the PostalCode object( in a defensive way)
	 * 
	 * @return name postalCode object from PostalCode class
	 */
	public PostalCode getPostalCode() {
		return new PostalCode(postalCode.getCode());
	}
	
	/**
	 * This code will verify if the election is eligible (if it's within the time zone and within the postal Code Range)
	 * 
	 * @return boolean boolean which tells if it is eligible or not
	 */
	public boolean isEligible(Election election) {
		LocalDate now = LocalDate.now();
		if (election.getStartDate().isBefore(now) && election.getEndDate().isAfter(now)) {
		 
			if(this.postalCode.inRange(election.getPostalRangeStart(), election.getPostalRangeEnd())) {
				return true;
			}	
		}
		return false;
	}	
	
	/**
	 * Over riding the equals method
	 * 
	 * @return true if it is equals, but making it more specific
	 */
		@Override
		public final boolean equals (Object obj) {
			if (this==obj)
				return true;
			if (obj==null)
				return false;
			if (!(obj instanceof Voter))
				return false;
			Voter other=(Voter)obj;
			return this.getEmail().equals(other.getEmail());
		}
		
		/**
		 * This code return the new hashCode
		 * 
		 * @return hashCode hashCode from the email object (because we use it in the equals method)
		 */
		@Override
		public final int hashCode() {
			return email.hashCode();
		}
		
		/**
		 * This code will return a String with the first name, last name ,email and postal code, all delimited by an asterisk (*)
		 * 
		 * @return String String containing first name, last name ,email and postal Code
		 */
		@Override
		public String toString() {
			return email.toString()+"*"+name.toString()+"*"+postalCode.toString();
		}
			
		/**
		 * Over riding the compareTo method
		 * 
		 * @return int int depending on the result of the compareTo
		 */
		
	@Override
	public final int compareTo(Voter voter) {
		if (this.getEmail().compareTo(voter.getEmail()) > 0) 
			return 1;
		if (this.getEmail().compareTo(voter.getEmail()) < 0)
			return -1;   
		return 0;
	}

		
		public void setPostalCode(PostalCode newCode) {
			this.postalCode= newCode;
			
		}
}
