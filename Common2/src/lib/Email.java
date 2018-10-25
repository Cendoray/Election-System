package lib;

import java.io.Serializable;
import java.util.Scanner;

public class Email implements Serializable, Comparable<Email> {
	/*
	 * Author: Trevor Cole Chevrier Election Project: Phase 1
	 */
	private final String address;
	private static final long serialVersionUID = 4203172017L;

	/**
	 * Constructor invokes the validateEmail method
	 * 
	 * @param An
	 *            address as a string
	 * 
	 * @return (n/a)
	 **/
	public Email(String address) {
		this.address = validateEmail(address);
		
	}

	/**
	 * A getter that returns the entire address
	 * 
	 * @param n/a
	 * 
	 * @return address as a string
	 **/
	public String getAddress() {
		return address;

	}

	/**
	 * A getter that returns the user ID (everything before the @ symbol)
	 * 
	 * @param n/a
	 * 
	 * @return user ID as a string
	 **/
	public String getUserID() {
	
		String userID = getAddress().substring(0, (getAddress().indexOf('@')));
		return userID;

	}

	/**
	 * A getter that returns the host name (everything after the @ symbol)
	 * 
	 * @param n/a
	 * 
	 * @return host name as a string
	 **/
	public String getHost() {
		String hostName = getAddress().substring(getAddress().indexOf('@') + 1);
		return hostName;

	}

	/**
	 * The overridden equals method takes an object and the address, and
	 * determines whether or not they are equal based on if they are instances
	 * of the same class, or equal (ignoring case).
	 * 
	 * @param an
	 *            object of type Object.
	 * 
	 * @return true if the conditions are met, else false.
	 **/
	@Override
	public final boolean equals(Object object) {

		if(this == object){
			return true;
		}
		// Checking if the object param is null
		if (object == null) {
			return false;
		}

		// Checking if the two objects are instances of the same class
		if (!(object instanceof Email)) {
			return false;
		}

		Email email = (Email) object;
		// Checking if their addresses are the same (case insensitive)
		if(this.getUserID().equalsIgnoreCase(email.getUserID()) && (this.getHost().equalsIgnoreCase(email.getHost()))){
			return true;
		}
		return false;
		
	}

	/**
	 * Converts the address to upper case in order to convert it into hash.
	 * 
	 * @param n/a
	 * 
	 * @return an int that represents the hash code (used to store/locate an
	 *         object quickly).
	 **/
	@Override
	public final int hashCode() {

		// Converting the object to uppercase
		return getAddress().toUpperCase().hashCode();

	}

	/**
	 * An overridden toString method
	 * 
	 * @param n/a
	 * 
	 * @return the string address
	 **/
	@Override
	public String toString() {
		// Returns the address
		return getAddress();
	}

	/**
	 * An overridden compareTo method that compares the object passed through
	 * the parameter and the address. Determines if the order of the
	 * case-insensitive host name is followed by the case-insensitive user ID.
	 * 
	 * @param An
	 *            object of type Email
	 * 
	 * @return an int based on the order of contents in the email.
	 **/
	@Override
	public int compareTo(Email object) {
		Email email1 = (Email) this;
		Email email2 = object;

		if (email1.getHost().compareToIgnoreCase(email2.getHost()) > 0) {
			return 1;
		}

		if (email1.getHost().compareToIgnoreCase(email2.getHost()) < 0) {
			return -1;
		}
	   
		if(email1.getHost().compareToIgnoreCase(email2.getHost()) == 0) {
			if(email1.getUserID().compareToIgnoreCase(email2.getUserID()) > 0) {
				return 1;
			}
			if(email1.getUserID().compareToIgnoreCase(email2.getUserID()) < 0) {
				return -1;
			}
		}
		return 0;

	}

	/**
	 * Takes an email and determines whether it is valid. If there are
	 * characters that are not allowed, they are removed and a proper email is
	 * returned. If they don't meet requisites, an exception is thrown.
	 * 
	 * @param the
	 *            email as a string.
	 * 
	 * @return a fixed string email if no IllegalArgumentExceptions are thrown.
	 **/
	public String validateEmail(String email) throws IllegalArgumentException {
        
		if(email == null){
			throw new NullPointerException("Please enter an email address which is not empty");
		}
		String uID = email.substring(0, email.indexOf('@'));
		
	
		char c;
		
		
		// USER ID VALIDATION

		// Validating that there is at least 1 character and more than 32
		// characters.
		if (uID.length() < 1 || uID.length() > 32) {
			throw new IllegalArgumentException("The email must be between 1 and 32 characters. ");
		}
		
		

		// For loop to sort through the user ID and determine if its
		// using an illegal character.
		
		for (int i = 0; i < uID.length(); i++) {
			c = uID.charAt(i);
			if (c >= 'A' && c <= 'z'){
				continue;				
			}
			if (c >= '0' && c <= '9'){
				continue;
			}
			if (c == '-'){
				continue;
			}
			if (c == '_'){
				continue;
			}
			if (c == '.'){
				continue;
			}
			
			throw new IllegalArgumentException("The User ID cannot contain anything other than lower case, upper case, digits, - , _ or .");


		}

		int dotIndex = uID.indexOf(".");

			
			// Validating that the dot is not at the beginning or end of the user
			// ID.
			if ((dotIndex == 0) || (dotIndex == uID.length() - 1)) {
				throw new IllegalArgumentException("The dot cannot be at the beginning or end of the User ID.");
			}
			
			// Validating that there are not two consecutive dots in the User ID.
			if (uID.charAt(dotIndex + 1) == '.') {
				throw new IllegalArgumentException("There cannot be two consecutive dots in the User ID.");
			}
		
		

		// HOST NAME VALIDATION

		String host = email.substring(email.indexOf('@')+1);
		int dot = host.indexOf('.');
		
		String segment = host.substring(0, dot + 1);

		// Validating that there is at least one domain name and it is at least
		// 1 character or at most 32 characters.
		if (dot == -1) {

			if (host.length() < 1 || host.length() > 32) {
				throw new IllegalArgumentException("The domain name must be more at least 1 character and at most 32");
			}

			// Validate if the segment begins or ends with a hyphen when there
			// is only one domain name
			if (host.indexOf("-") == 0 || host.indexOf("-") ==  host.length() - 1) {
				throw new IllegalArgumentException("The domain name cannot begin with or end with a hyphen");

			}

			// For loop to sort through the one domain and determine if its
			// using an illegal character.
			for (int i = 0; i < host.length(); i++) {
				c = host.charAt(i);
				if (c >= 'A' && c <= 'z'){
					continue;				
				}
				if (c >= '0' && c <= '9'){
					continue;
				}
				if (c == '-'){
					continue;
				}
				
				throw new IllegalArgumentException("The one domain cannot contain anything other than lower case, upper case, digits or a hyphen.");


			}

		}

		while (dot != -1) {

			// Validating that the segment is at least 1 character and at most
			// 32 characters
			if (segment.length() - 1 < 1 || segment.length() - 1 > 32) {
				throw new IllegalArgumentException("Each segment must be at least 1 character or at most 32.");
			}

			// Validate if the segment begins or ends with a hyphen when there
			// is a segment that follows
			if (segment.indexOf("-") == 0 || segment.indexOf("-") == dot - 1) {
				throw new IllegalArgumentException("The segment cannot begin with or end with a hyphen");

			}

			// For loop to sort through the segment and determine if its using
			// an illegal character.
			for (int i = 0; i < segment.length() - 1; i++) {
				c = segment.charAt(i);
				if (c >= 'A' && c <= 'z'){
					continue;				
				}
				if (c >= '0' && c <= '9'){
					continue;
				}
				if (c == '-'){
					continue;
				}
				
				
				throw new IllegalArgumentException("The segment cannot contain anything other than lower case, upper case, digits or a hyphen");


			}
			

			// make the substring everything after the dot
			segment = host.substring(dot + 1);
			// search for another dot
			dot = segment.indexOf(".");

			// if there are no more dots, check the final segment's length
			if (dot == -1) {
				if (segment.length() < 1 || segment.length() > 32) {
					throw new IllegalArgumentException("Each segment must be at least 1 character or at most 32.");
				}

				// Validate if the segment begins or ends with a hyphen when it
				// is the last segment of the host.
				if (segment.indexOf("-") == 0 || segment.indexOf("-") == segment.length() + 1) {
					throw new IllegalArgumentException("The segment cannot begin with or end with a hyphen");

				}

				// For loop to sort through the last segment and determine if
				// its using an illegal character.
				for (int i = 0; i < segment.length() - 1; i++) {
					c = segment.charAt(i);
					if (c >= 'A' && c <= 'z'){
						continue;				
					}
					if (c >= '0' && c <= '9'){
						continue;
					}
					if (c == '-'){
						continue;
					}
					
					
					throw new IllegalArgumentException("The last segment cannot contain anything other than lower case, upper case, digits or a hyphen");


				}

			}

		}
		return email.trim();
		
	}
}
