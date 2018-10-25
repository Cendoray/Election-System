/**
 * This class will represent a person
 */
package lib;

import java.io.Serializable;

/**
 * @author Anthony Whitebean
 * @version 9/8/2017
 */
public class Person implements Serializable {
	private Name name;
	private Address address;
	private static final long serialVersionUID = 42031768871L;

	
	//Constructors
	public Person(String firstName, String lastName)
	{
		this.name = new Name(firstName, lastName);
	}
	
	public Person(String firstName, String lastName, Address address)
	{
		validateExistence(address);
		this.name = new Name(firstName, lastName);
		this.address = createDeepCopy(address);		
	}
	
	/**
	 * This will return a deep copy of the address
	 * @return an Address representing a deep copy
	 */
	public Address getAddress()
	{
		Address copy = new Address();
		if (this.address == null)
		{
			return copy;
		}
		copy = createDeepCopy(this.address);
		return copy;
	}
	
	/**
	 * This will return a deep copy of the name
	 * @return a Name representing a deep copy
	 */
	public Name getName()
	{
		Name copy = new Name(this.name.getFirstName(),this.name.getLastName());
		return copy;
	}
	
	/**
	 * This will set a new name
	 * @param firstName representing the new firstName
	 * @param lastName representing the new lastName
	 */

	public void setName(String firstName, String lastName)
	{
		//this.name.setFirstName(firstName);
		//this.name.setLastName(lastName);
	}

	/**
	 * This will set a new address
	 * @param address representing the address to be deep copyed from
	 */
	public void setAddress(Address address)
	{
		validateExistence(address);
		this.address = createDeepCopy(address);
	}
	/**
	 * This will check to see if address exists
	 * @param address that is to be checked
	 */
	private void validateExistence(Address address) {
		if (address == null)
			throw new IllegalArgumentException("Address Error - address must exist. Invalid value = " + address);
	}
	/**
	 * This is a helper method to create deep copys of addresses
	 * @param address that is wanted to be copied
	 * @return an Address representing a deep copy
	 */
	private Address createDeepCopy(Address address)
	{
		Address copy = new Address();
		copy.setCity(address.getCity());
		copy.setCivicNumber(address.getCivicNumber());
		copy.setProvince(address.getProvince());
		copy.setCode(address.getCode());
		copy.setStreetName(address.getStreetName());
		return copy;
	}
	
	@Override
	public String toString() {
		return name.toString() + "*" + (address == null ? "" : address.toString());
	}
}
