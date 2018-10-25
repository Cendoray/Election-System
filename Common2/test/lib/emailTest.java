package lib;

public class emailTest {
	
	/*
	 * Author: Trevor Cole Chevrier
	 */
	
	public emailTest(){
		
	}
	public static void main(String[] args){
		
		
	//Invoking methods
		testEmailConstructor();
		testGetUserID();
		testGetHost();
		testGetAddress();
		testToString();
		testCompareTo();
		testHashCode();
		testValidateEmail();
		testEquals();
		
		
		
	}
	
	public static void testEmailConstructor() {
		System.out.println("\nTesting Email Constructor:");
		testEmailConstructor("Test 1:Checking a valid email with one domain", "Trevor1@EmailTest", true);
		testEmailConstructor("Checking a null email", null, false);
		testEmailConstructor("Test 2:Checking a valid email with multiple domains", "Trevor1@Email.Test", true);
		testEmailConstructor("Test 3:Checking when a user ID has more than 32 character", "gggggggggggggggggggggggggggggggggg@EmailTest", false);
		testEmailConstructor("Test 4:Checking when a userID contains all the valid characters", "lU5-._@EmailTest", true);
		testEmailConstructor("Test 5:Checking when a userID contains an invalid character", "Trevo?r1@EmailTest", false);
		testEmailConstructor("Test 6:Checking when the character '.' is at the beginning of the user ID", ".Trevor1@EmailTest", false);
		testEmailConstructor("Test 7:Checking when the character '.' is at the end of the user ID", "Trevor1.@EmailTest", false);
		testEmailConstructor("Test 8:Checking when there is two consecutive dots in the user ID", "Tr..vor1@EmailTest", false);
		testEmailConstructor("Test 9:Checking when there is one domain of more than 32 characters", "Trevor1@gggggggggggggggggggggggggggggggggg", false);
		testEmailConstructor("Test 10:Checking when the one domain has an illegal character", "Trevor1@Ema?ilTest", false);
		testEmailConstructor("Test 11:Checking when the one domain begins with a hyphen", "Trevor1@-EmailTest", false);
		testEmailConstructor("Test 12:Checking when a segment is more than 32 characters", "Trevor1@Email.gggggggggggggggggggggggggggggggggg", false);
		testEmailConstructor("Test 13:Checking when a segment begins with a hyphen", "Trevor1@Email.-Test", false);
		testEmailConstructor("Test 14:Checking when a segment contains an illegal character", "Trevor1@Email.Te?st", false);

	
	
	}

	public static void testEmailConstructor(String test, String email, boolean result) {
		
		System.out.println(test);
		System.out.println(email);
		
		try {
			
			Email e = new Email(email);
			
			if(!result){
				System.out.println("Do not be afraid, the address is meant to fail.");
			} else {
	        System.out.println("The address will be valid.");
			}
	        
		} catch (IllegalArgumentException iae) {
			if (!result){
				System.out.println("\t FAILED iae\t" + iae.getMessage());
			}
			if (result){
				System.out.println("The address passed the validation.");
			}
		} catch (NullPointerException npe) {
				if (!result){
					System.out.println("\t FAILED npe\t" + npe.getMessage());
				}
				if (result){
					System.out.println("The address passed the validation.");
				}
		}
	}
	
	public static void testGetUserID() {
		System.out.println("\n\nTesting getUserID");
		testGetUserID("Test 1: Checking when an email has an @ symbol", "Trevor1@Email1", true);
		testGetUserID("Test 2: Checking when an email doesn't have an @ symbol", "Trevor1Email1", false);

	}

	public static void testGetUserID(String test, String email, boolean result) {
		System.out.println(test);
		System.out.println(email);
		
		try {
			Email address = new Email(email);
			String uID = address.getUserID();
			System.out.println("\tThe USER ID is ---  " + uID + " --- TEST PASSED");

			if (!result){
				System.out.println("The USER ID is expected to be false");
			}
		} catch (StringIndexOutOfBoundsException sioobe) {
			if (!result){
				System.out.println("\tYour index is out of range, the range being:" + sioobe.getMessage());
			}
			if (result){
				System.out.println("error");
			}
		}
		}
	
	public static void testGetHost() {
		System.out.println("\n\nTesting getHost");
		testGetHost("Test 1: Checking when an email has an @ symbol", "Trevor1@Email1", true);
		testGetHost("Test 2: Checking when an email doesn't have an @ symbol", "Trevor1Email1", false);

	}

	public static void testGetHost(String test, String email, boolean result) {
		System.out.println(test);
		System.out.println(email);
		
		try {
			Email address = new Email(email);
			String host = address.getHost();
			System.out.println("\tThe host is ---  " + host + " --- TEST PASSED");

			if (!result){
				System.out.println("The host is expected to be false");
			}
		} catch (StringIndexOutOfBoundsException sioobe) {
			if (!result){
				System.out.println("\tYour index is out of range, the range being:" + sioobe.getMessage());
			}
			if (result){
				System.out.println("error");
			}
		}
		
	}
	
	public static void testGetAddress() {
		System.out.println("\n\nTesting getAddress");
		testGetAddress("Test 1: Checking a full email", "Trevor1@Email1", true);
		testGetAddress("Test 2: Checking when email is null", null, false);

	}

	public static void testGetAddress(String test, String email, boolean result) {
		System.out.println(test);
		System.out.println(email);
		try {
			Email address = new Email(email);
			String a = address.getAddress();
			System.out.println("\tThe address is ---  " + a + " --- TEST PASSED");

			if (!result){
				System.out.println("The address is expected to be false");
			}
		} catch (NullPointerException npe) {
			if (!result){
				System.out.println("\t" + npe.getMessage());
			}
			if (result){
				System.out.println("error");
			}
		}
	}
	
	public static void testToString() {

		System.out.println("\n\nTesting the overridden toString method");
		testToString("Test 1: Email is valid because its complete", "Trevor1@Email1", true);
		testToString("Test 2: Email is false because its null", null, false);

	}
	
	public static void testToString(String test, String email, boolean result) {
		System.out.println(test);
		System.out.println(email);
		
		try {

			Email e = new Email(email);
			System.out.println("\tReturned string: " + e.toString() + " PASSED");

			if (result == false){
				System.out.println("Test expected to fail.");
			}
		} catch (IllegalArgumentException iae) {
			if (result == false)
				System.out.println("\t" + iae.getMessage());
			if (result == true)
				System.out.println("error");
		} catch (NullPointerException npe) {
			if (result == false)
				System.out.println("\t" + npe.getMessage());
			if (result == true)
				System.out.println("error");

		}
	}
	
	public static void testCompareTo() {
		System.out.println("\n\nTesting overridden compareTo method");
		testCompareTo("Test 1: When two emails are the same - VALID", "Trevor1@Email1", "Trevor1@Email1", true);
		testCompareTo("Test 1: When two emails are both null - INVALID", null, null, false);
		testCompareTo("Test 1: When the first email is greater than the second - RETURN 1 - VALID", "Zrevor1@Zmail1", "Trevor1@Tmail1", true);
		testCompareTo("Test 1: When the second email is greater than the first - RETURN -1 - VALID", "Trevor1@Tmail1", "Zrevor1@Zmail1", true);
	}
	
	public static void testCompareTo(String test, String email1, String email2, boolean result){
		System.out.println(test);
		System.out.println(email1 + " ----- " + email2);
		try {
		Email e1 = new Email(email1);
		Email e2 = new Email(email2);
		
		System.out.println("The result is: " + e1.compareTo(e2) + " PASSED");
		
		if(!result){
			System.out.println("Excepted to be false");
		} 
		
		} catch (IllegalArgumentException iae) {
			if (!result){
			System.out.println("\t" + iae.getMessage());
			}
			if(result){
				System.out.println("Error");
			}
			
			
		} catch (NullPointerException npe) {
			if (!result){
			System.out.println("\t" + npe.getMessage());
			}
			if(result){
				System.out.println("Error");
			}
			
			
		}
		
		
	}
	
	public static void testHashCode() {

		System.out.println("\n\nTesting overridden hashCode method");
		testHashCode("Test 1: Will return a valid hashcode", "Trevor1@Email1", true);
		testHashCode("Test 2: Will not be able to return hashcode because the param is null", null, false);

	}

	public static void testHashCode(String test, String email, boolean result) {
		System.out.println(test);
		System.out.println(email);
		
		try {

			Email e = new Email(email);
			System.out.println("\t returned hashCode is " + e.hashCode() + " PASSED");

			if (!result) {
				System.out.println("Error");
			}
		} catch (IllegalArgumentException iae) {
			if (!result) {
				System.out.println("\t" + iae.getMessage());
			}
			if (result) {
				System.out.println("Error");
			}
		} catch (NullPointerException npe) {
			if (!result) {
				System.out.println("\t" + npe.getMessage());
			}
			if (result) {
				System.out.println("Error");
			}

		}
	}
	
	public static void testValidateEmail() {
		System.out.println("\nTesting Email Constructor:");
		testValidateEmail("Test 1:Checking a valid email with one domain", "Trevor1@EmailTest", true);
		testValidateEmail("Checking a null email", null, false);
		testValidateEmail("Test 2:Checking a valid email with multiple domains", "Trevor1@Email.Test", true);
		testValidateEmail("Test 3:Checking when a user ID has more than 32 character", "gggggggggggggggggggggggggggggggggg@EmailTest", false);
		testValidateEmail("Test 4:Checking when a user ID has less than 1 character", "@EmailTest", false);
		testValidateEmail("Test 5:Checking when a userID contains all the valid characters", "lU5-._@EmailTest", true);
		testValidateEmail("Test 6:Checking when a userID contains an invalid character", "Trevo?r1@EmailTest", false);
		testValidateEmail("Test 7:Checking when the character '.' is at the beginning of the user ID", ".Trevor1@EmailTest", false);
		testValidateEmail("Test 8:Checking when the character '.' is at the end of the user ID", "Trevor1.@EmailTest", false);
		testValidateEmail("Test 9:Checking when there is two consecutive dots in the user ID", "Tr..vor1@EmailTest", false);
		testValidateEmail("Test 10:Checking when there is one domain of more than 32 characters", "Trevor1@gggggggggggggggggggggggggggggggggg", false);
		testValidateEmail("Test 11:Checking when the one domain has an illegal character", "Trevor1@Ema?ilTest", false);
		testValidateEmail("Test 12:Checking when the one domain begins with a hyphen", "Trevor1@-EmailTest", false);
		testValidateEmail("Test 13:Checking when a segment is more than 32 characters", "Trevor1@Email.gggggggggggggggggggggggggggggggggg", false);
		testValidateEmail("Test 14:Checking when a segment begins with a hyphen", "Trevor1@Email.-Test", false);
		testValidateEmail("Test 15:Checking when a segment contains an illegal character", "Trevor1@Email.Te?st", false);

	}

	public static void testValidateEmail(String test, String email, boolean result) {
		System.out.println(test);
		System.out.println(email);
		
		try {

			Email e = new Email(email);
			System.out.println("\tThe trimmed email is: " + e.validateEmail(email) + " PASSED");

			if (!result)
				System.out.println("Error");
		} catch (IllegalArgumentException iae) {
			if (!result)
				System.out.println("\t" + iae.getMessage());
			if (result)
				System.out.println("Error");
		} catch (NullPointerException npe) {
			if (!result)
				System.out.println("\t" + npe.getMessage());
			if (result)
				System.out.println("Error");

		}
	}
	
	public static void testEquals() {

		System.out.println("\n\nTesting overridden equals method");
		testEquals("Test 1: VALID --- Emails are equivalent ", "Trevor1@Email1", "Trevor1@Email1", true);
		testEquals("Test 2: FAILS --- THE EMAILS CANNOT BE NULL", null, null, false);
		testEquals("Test 3: FAILS --- THE EMAILS DO NOT MATCH ", "Trevo1@Email1", "Trevor1@Gmail1", true);
		testEquals("Test 4: FAILS --- SECOND EMAIL CANNOT BE NULL", "Trevor1@Email1", null, false);
		testEquals("Test 5: FAILS --- FIRST EMAIL CANNOT BE NULL", null, "Trevor1@Email1", false);

	}

	public static void testEquals(String test, String email1, String email2,
			boolean result) {
		System.out.println(test);
		System.out.println(email1 + " ----- " + email2);
		try {

			Email e1 = new Email(email1);
			Email e2 = new Email(email2);

			System.out.println("\tDoes the first email equal the second email? " + e1.equals(e2));

			if (!result)

				System.out.println("Error");
		} catch (IllegalArgumentException iae) {
			if (!result)
				System.out.println("\t" + iae.getMessage());
			if (result)
				System.out.println("Error");
		} catch (NullPointerException npe) {
			if (!result)
				System.out.println("\t" + npe.getMessage());
			if (result)
				System.out.println("Error");

		}
	}

	
	

		
		
				
		
		
	
}

