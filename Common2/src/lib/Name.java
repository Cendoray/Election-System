/**
 * This class will represent a name for a person
 */
package lib;

import java.io.Serializable;

/**
 * @author Ethan_Wong
 * @version 9/21/2017
 */
public class Name implements Serializable, Comparable<Name> {
	private final String firstName;
	private final String lastName;
	private static final long serialVersionUID = 4203172017L;

	// Constructor
	public Name(String first, String last) {
		this.firstName = validate(first);
		this.lastName = validate(last);
	}

	/**
	 * This will return the full name
	 * 
	 * @return String representing full name
	 */
	public String getFullName() {
		String string = firstName + " " + lastName;
		return string;
	}

	/**
	 * This will return the first name
	 * 
	 * @return String representing the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This will return the last name
	 * 
	 * @return String representing the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This will return a string of the full name
	 * 
	 * @return String representation of the full name
	 */
	@Override
	public String toString() {
		return (firstName + "*" + lastName);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;
		Name name = (Name) object;
		return this.getFullName().equalsIgnoreCase(name.getFullName());
	}

	/**
	 * This will check and return a string of the name 
	 * 
	 * @return String representation of the name
	 */

	public String validate(String name) throws NullPointerException, IllegalArgumentException {

		if (name == null)
			throw new NullPointerException("Name Error - " + name + " must exist.");

		String nameTrim = name.trim();

		if (nameTrim.length() < 2) {
			throw new IllegalArgumentException("Name error: " + nameTrim + " must contain minumum 2 letters.");

		}
		for (int i = 0;  i < nameTrim.length(); i++) {
			if (nameTrim.charAt(i) == '\'') {
				continue;
			}
			if (nameTrim.charAt(i) == '-') {
				continue;
			}
			if (nameTrim.charAt(i) == ' ') {
				continue;
			}
			if ((nameTrim.charAt(i) >= 'a' && nameTrim.charAt(i) <= 'z') || (nameTrim.charAt(i)>='A' && nameTrim.charAt(i) <= 'Z')){
				continue;
			} else {
				
				throw new IllegalArgumentException("Name Error: " + nameTrim + " Illegal Characters");
				
			}

		}
		return nameTrim;
	}
	/**
	 * This will return a 0 if strings are equal, -1 if n1 is less than obj, 1 if n1 is greater than obj
	 * 
	 * @return String representation of the name
	 */
	@Override
	public int compareTo(Name obj) {


		//int checkOrder = this.getLastName().compareToIgnoreCase(obj.getLastName());
		//if (checkOrder == 0) //{
			//checkOrder = this.getFirstName().compareToIgnoreCase(obj.getFirstName());
		//return checkOrder;
		/*}
		else if (checkOrder > 0) {
			checkOrder = this.getFirstName().compareToIgnoreCase(this.getFirstName());
		return 1;
		}
		*/
		if (this.getLastName().compareToIgnoreCase(obj.getLastName()) == 0) {
			if(this.getFirstName().compareToIgnoreCase(obj.getFirstName())> 0)
			{
				return 1;
			}else if(this.getFirstName().compareToIgnoreCase(obj.getFirstName())< 0){
				return -1;
			}else {
				return 0;
			}
		}
		if (this.getLastName().compareToIgnoreCase(obj.getLastName()) < 0 ) 
			return -1;
		if (this.getLastName().compareToIgnoreCase(obj.getLastName()) > 0 )
			return 1;
		
		return 0;

	}
}
