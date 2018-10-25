package lib;

public class PersonTest {

	public static void main(String[] args) {
		testTheThreeParameterConstructor();
		testTheTwoParameterConstructor();
		testSetName();
		testSetAddress();
	}

	private static void testTheThreeParameterConstructor() {
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		Address blankAddress = new Address();
		System.out.println("\nTesting the three parameter constructor.");
		testTheThreeParameterConstructor(
			"Case 1 - Valid data (Anthony Whitebean 3040 Sherbrooke Westmount)", "Anthony",
				"Whitebean", theAddress, true);
		testTheThreeParameterConstructor(
				"Case 2 - Valid data - Empty address sent in(Anthony Whitebean)", "Anthony",
					"Whitebean", blankAddress, true);
		testTheThreeParameterConstructor(
				"Case 3 - Invalid data - Empty first name ( Whitebean 3040 Sherbrooke Westmount)", "",
					"Whitebean", theAddress, false);
		testTheThreeParameterConstructor(
				"Case 4 - Invalid data -Empty last name(Anthony 3040 Sherbrooke Westmount)", "Anthony",
					"", theAddress, false);
		testTheThreeParameterConstructor(
				"Case 5 - Invalid data - null last name(Anthony null 3040 Sherbrooke Westmount)", "Anthony",
					null, theAddress, false);
		testTheThreeParameterConstructor(
				"Case 6 - Invalid data -null first name(null Whitebean 3040 Sherbrooke Westmount)", null,
					"Whitebean", theAddress, false);
		testTheThreeParameterConstructor(
				"Case 7 - Invalid data -null address(Anthony Whitebean)", "Anthony",
					"Whitebean", null, false);
	}
	
	private static void testTheThreeParameterConstructor(String testCase,
			String firstName, String lastName, Address address, boolean expectValid) {
		System.out.println("   " + testCase);
		try {
			Person thePerson = new Person(firstName, lastName, address);
			System.out.print("\tThe Person instance was created: " + thePerson);
		
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
	
	private static void testTheTwoParameterConstructor() {
		System.out.println("\nTesting the two parameter constructor.");
		testTheTwoParameterConstructor(
				"Case 1 - Valid data (Anthony Whitebean)", "Anthony",
					"Whitebean", true);
			testTheTwoParameterConstructor(
					"Case 2 - Invalid data - null first name(null Whitebean)", null,
						"Whitebean", false);
			testTheTwoParameterConstructor(
					"Case 3 - Invalid data - Empty first name ( Whitebean )", "",
						"Whitebean", false);
			testTheTwoParameterConstructor(
					"Case 4 - Invalid data -Empty last name(Anthony )", "Anthony",
						"", false);
			testTheTwoParameterConstructor(
					"Case 5 - Invalid data - null last name(Anthony null )", "Anthony",
						null, false);

	}
	
		private static void testTheTwoParameterConstructor(String testCase,
			String firstName, String lastName, boolean expectValid) {
		System.out.println("   " + testCase);
		try {
			Person thePerson = new Person(firstName, lastName);
			System.out.print("\tThe Person instance was created: " + thePerson);
		
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

	private static void testSetName() {
		System.out.println("\n\nTesting the setName method.");
		testSetName("Case 1: Valid - Chernoff without leading/trailing spaces",
				"Brandon", "Chernoff","Brandon Chernoff",true);
		testSetName("Case 2: Invalid null firstName",
				null, "Chernoff","  Chernoff", false);
		testSetName("Case 3: Invalid null lastName",
				"Brandon", null,"Brandon  ",false);
		System.out.println("   Case 4: Checking if changes to copy carry over");
		testDeepNameCopy();
	}
	
	private static void testSetName(String testCase, 
			String firstName, String lastName, String expectedFullName,boolean expectValid){
		System.out.println("   " + testCase);
		Person thePerson = 
				new Person("Anthony", "Whitebean");
		try {
			thePerson.setName(firstName, lastName);
			Name personName = thePerson.getName();
			System.out.print("\tThe Person instance was created: " + thePerson);
			
			if (!personName.getFullName().equals(expectedFullName))
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
	
	public static void testDeepNameCopy()
	{
		Person thePerson = new Person("Anthony", "Whitebean");
		Name copy = thePerson.getName();
		//copy.setFirstName("Brandon");
		System.out.println("	Original: " + thePerson );
		System.out.println("	Copy: " + copy.getFullName());
	}
	
  	private static void testSetAddress() {
		System.out.println("\n\nTesting the setAddress method.");
		Address copy = new Address("1", "Copy", "Person");
		testSetAddress("Case 1: Valid", copy,"Copy",true);
		testSetAddress("Case 2: Invalid null Address", null,"",false);
		System.out.println("   Case 3: Checking if changes to copy carry over");
		testDeepAddressTest(copy);
	}
	
	private static void testSetAddress(String testCase, 
			Address copy, String expectedStreetName,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = new Address("3000", "Original", "Guy");
		Person thePerson = new Person("Anthony","Whitebean",theAddress);
		try {
			thePerson.setAddress(copy);
			System.out.print("\tThe Person instance was created: " + thePerson);
			
			Address test = thePerson.getAddress();
			if (!test.getStreetName().equals(expectedStreetName))
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
	
	public static void testDeepAddressTest(Address copy)
	{		
		Person thePerson = new Person("Anthony", "Whitebean", copy);
		Address test = thePerson.getAddress();
		test.setStreetName("This is gonna be new street");
		Address original = thePerson.getAddress();
		System.out.println("	Original: " + original );
		System.out.println("	Copy: " + test);
	}
}
