/**
 *  This code will is made to keep information about a address
 *  @version 9/8/2017
 */

package lib;
import java.io.Serializable;

/**
 * @author Anthony Whitebean
 */

public class Address implements Serializable {
	private String city = "";
	private String civicNumber = "";
	private String province = "";
	private String code = "";
	private String streetName = "";


	public Address() {
	}

	public Address(String civicNumber, String streetName, String city) {
		this.civicNumber = validateExistence("civic number", civicNumber);
		this.streetName = validateExistence("street name", streetName);
		this.city = validateExistence("city", city);
	}

	/**
	 * Returns a String representation of the address.
	 * 
	 * @return address a formatted address.
	 */
	public String getAddress() {
		String address = civicNumber + " " + streetName + "\n" + city;
		address += (province.equals("")?"": (", " + province)) + (code.equals("")?"":("\n" + code));

		return address;
	}

	/**
	 * Return a String representation of the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Return a String representation of the civic number.
	 * @return the civicNumber
	 */
	public String getCivicNumber() {
		return civicNumber;
	}

	/**
	 * Return a String representation of the province.
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Return a String representation of the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Return a String representation of the street name.
	 * @return the streetName
	 */
	public String getStreetName() {
		
		return streetName;
	}

	/**
	 * Changes the city variable to a requested string.
	 * 
	 * @param city the city to set
	 */
	public void setCity(String city) {
		if (city == null)
			throw new IllegalArgumentException("City being changed to is null.");
		this.city = city;
	}

	/**
	 * Changes the civic number variable to a requested string.
	 * 
	 * @param civicNumber the civicNumber to set
	 */
	public void setCivicNumber(String civicNumber) {
		if (civicNumber == null)
			throw new IllegalArgumentException("Civic number being changed to is null.");
		this.civicNumber = civicNumber;
	}

	/**
	 * Changes the province variable to a requested string.
	 * 
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Changes the province variable to a requested string.
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Changes the street name variable to a requested string.
	 * 
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		if (streetName == null)
			throw new IllegalArgumentException("Street name being changed to is null.");
		this.streetName = streetName;
	}
	
	private String validateExistence(String fieldName, String fieldValue) {
		if (fieldValue == null)
			throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + fieldValue);
		String trimmedString = fieldValue.trim();
		if (trimmedString.isEmpty())
			throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + fieldValue);
		return trimmedString;
	}
	
	@Override
	public String toString() {
		return (civicNumber + "*" + streetName + "*" + city + "*" + province + "*" + code);	}


}
