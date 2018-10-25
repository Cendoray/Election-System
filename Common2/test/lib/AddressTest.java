/**
 * 
 */
package lib;

public class AddressTest {

	public static void main(String[] args) {
		testTheThreeParameterConstructor();
		testGetCivicNumber();
		testSetCivicNumber();
		testSetStreetName();
		testSetCity();
	}

	private static void testTheThreeParameterConstructor() {
		System.out.println("\nTesting the three parameter constructor.");
		testTheThreeParameterConstructor(
			"Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040",
				"Sherbrooke", "Westmount", true);
		testTheThreeParameterConstructor(
				"Case 2 - Invalid data – empty civicNumber ( Sherbrooke Westmount)","",
					"Sherbrooke","Westmount", false);	
  		testTheThreeParameterConstructor(
				"Case 3 - Invalid data – space civicNumber (       Sherbrooke Westmount)","     ",
				"Sherbrooke","Westmount", false);
		testTheThreeParameterConstructor(
				"Case 4 - Invalid data – empty streetname( 3040 Westmount)","3040",
				"","Westmount", false);
		testTheThreeParameterConstructor(
				"Case 5 - Invalid data – space streetname ( 3040       Westmount)","3040",
				"     ","Westmount", false);
		testTheThreeParameterConstructor(
				"Case 6 - Invalid data – empty city ( 3040 Sherbrooke)","3040",
				"Sherbrooke","", false);
		testTheThreeParameterConstructor(
				"Case 7 - Invalid data – space city ( 3040 Sherbrooke      )","3040",
				"Sherbrooke","     ", false);
		testTheThreeParameterConstructor(
				"Case 8 - Invalid data - null civicNumber (null Sherbrooke Westmount)", null,
					"Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 9 - Invalid data - null streetname (3040 null Westmount)", "3040",
					null, "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 10 - Invalid data - null city (3040 Sherbrooke null)", "3040",
					"Sherbrooke", null, false);

	}

	
	private static void testTheThreeParameterConstructor(String testCase,
			String civicNumber, String streetName, String city,
			boolean expectValid) {
		System.out.println("   " + testCase);
		try {
			Address theAddress = new Address(civicNumber, streetName, city);
			System.out.print("\tThe Address instance was created: " + theAddress);
		
			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print(" Error! Expected Valid. ==== Failed Test ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");			
		}
		System.out.println("\n");
		
	}
	private static void testGetCivicNumber()
	{
		System.out.println("\n\nTesting the getCivicNumber method.");
		testGetCivicNumber("Case 1: 3040 without leading/trailing spaces",
				"3040","3040");
		testGetCivicNumber("Case 2: 3040 with leading/trailing spaces",
				"    3040    ","3040");
	}
	
	private static void testGetCivicNumber(String testCase, 
			String civicNumber, String expectedCivicNumber)
	{
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address (civicNumber, "Sherbrooke","Westmount");
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}
	
	private static void testSetCivicNumber() {
		System.out.println("\n\nTesting the setCivicNumber method.");
		testSetCivicNumber("Case 1: Valid - 2086 without leading/trailing spaces",
				"2086","2086",true);
		testSetCivicNumber("Case 2: Invalid null civic number",
				null,"",false);
	}
	
	private static void testSetCivicNumber(String testCase, 
			String civicNumber, String expectedCivicNumber,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setCivicNumber(civicNumber);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testSetStreetName() {
		System.out.println("\n\nTesting the setStreetName method.");
		testSetStreetName("Case 1: Valid - Center Street without leading/trailing spaces",
				"Center Street","Center Street",true);
		testSetStreetName("Case 2: Invalid null streetName.",
				null,"",false);
	}	
	private static void testSetStreetName(String testCase, 
			String streetName, String expectedStreetName,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setStreetName(streetName);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getStreetName().equals(expectedStreetName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}
	
	private static void testSetCity() {
		System.out.println("\n\nTesting the setCity method.");
		testSetCity("Case 1: Valid - 2086 without leading/trailing spaces",
				"Lance","Lance",true);
		testSetCity("Case 2: Invalid null city",
				null,"",false);
	}
	
	private static void testSetCity(String testCase, 
			String city, String expectedCity,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setCity(city);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getCity().equals(expectedCity))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}



}
