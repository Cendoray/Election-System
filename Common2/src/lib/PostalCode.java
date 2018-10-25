/*
 * 
 */
package lib;

import java.io.Serializable;

/**
 * This code will keep track of a given postal code.
 * 
 * @author Anthony Whitebean
 * @version 10/16/2017
 */
public class PostalCode implements Serializable, Comparable<PostalCode> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4203172017L;
	private final String code;
	
	public PostalCode(String code)throws IllegalArgumentException
	{
		this.code = validate(code);
	}
	
	/**
	 * This code will return an int representing the difference between two strings
	 * 
	 * @param a code representing the other code being compared
	 * @return an int representing the difference (Ignoring case)
	 */
	@Override
	public int compareTo(PostalCode code)
	{
		String code1 = this.getCode();
		String code2 = code.getCode();
		return code1.compareToIgnoreCase(code2);
	}
	
	/**
	 * This code will check to see if an object is equal to this postalcode
	 * 
	 * @param an object being compared
	 * @return a boolean representing if they are
	 */
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
		if (!(object instanceof PostalCode))
		{
			return false;
		}		
		PostalCode newCode = (PostalCode) object;
		if (this.compareTo(newCode) != 0)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * This code will return the hashcode of the uppercase version of the code
	 * 
	 * @return an int representing the uppercase hashcode of the code variable
	 */
	@Override
	public final int hashCode()
	{
		return code.toUpperCase().hashCode();
	}
	
	/**
	 * This will return the uppercase version of the code
	 * @return a string representing the uppercase version of the code
	 */
	public String getCode()
	{
		return code.toString();
	}
	
	/**
	 * This code will check to see if the code is withing the range given
	 * @param start, a string representing the minimum
	 * @param end, a string representing the maximum
	 * @return a boolean representing if it is in range
	 */
	public boolean inRange(String start, String end)throws NullPointerException
	{
		if (start == null || end == null)
		{
			throw new NullPointerException("A string sent in is null.");
		}
		String newStart = start.trim();
		String newEnd = end.trim();
		if (this.code.compareTo(newStart) < 0)
		{
			return false;
		}
		if (this.code.compareTo(newEnd) > 0)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * This code will return the string representation of the code in upper case
	 * 
	 * @return a string representing the upper case version of the code.
	 */
	@Override
	public String toString()
	{
		String upperCode = (this.code.toUpperCase());
		return upperCode;
	}
	
	/**
	 * This will validate if the code is able to 
	 * @param code representing code being verified
	 * @return the code that is verified and fixed
	 * @throws IllegalArgumentException when a code is incorrect
	 */
	private String validate(String code) throws IllegalArgumentException, NullPointerException
	{
		if (code == null)
		{
			throw new NullPointerException("The string sent in is null");
		}
		String upperCode = (code.toUpperCase());
		upperCode = upperCode.trim();
		upperCode = upperCode.replaceAll("\\s+", "");
		if (upperCode.length() == 0)
		{
			throw new IllegalArgumentException("The code that was sent in has nothing in it or spaces only.");
		}
		if (upperCode.length() != 6)
		{
			throw new IllegalArgumentException("The code sent in was not the right length (6 or 7)");
		}
		if (upperCode.charAt(0) == 'W' || upperCode.charAt(0) == 'Z')
		{
			throw new IllegalArgumentException("The beginning of the code cannot have W or Z");
		}
		for (int i = 0; i < upperCode.length(); i++)
		{

			if ((Character.isDigit(upperCode.charAt(i))) && (i%2 == 0))
			{
				throw new IllegalArgumentException("There is a digit that is in a wrong index of the string, which is not correct.");
			}
			if ((Character.isLetter(upperCode.charAt(i))) && (i%2 == 1))
			{
				throw new IllegalArgumentException("There is a letter that is in a wrong index of the string, which is not correct.");
			}
			if (upperCode.charAt(i) == 'D' || upperCode.charAt(i) == 'F' || upperCode.charAt(i) == 'I' || upperCode.charAt(i) == 'O' || upperCode.charAt(i) == 'Q' || upperCode.charAt(i) == 'U')
			{
				throw new IllegalArgumentException("There is a letter(D,F,I,O,Q,U) in the string, which is wrong.");
			}
		}
		return upperCode;
	}

}

