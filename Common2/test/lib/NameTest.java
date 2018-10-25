/**
 * 
 */
package lib;

/**
 * @author Ethan_Wong
 *
 */
public class NameTest {

	/**
	 * 
	 */
	public NameTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testGetFirstName();
		testGetLastName();
		testGetFullName();
		testToString();
		testEquals();
		testHashCode();
		testConstructor();
		testValidate();
		testToCompare();

	}

	public static void testConstructor() {
		System.out.println("\nTesting the Name Instructor");
		testConstructor("Test 1:Checking full name- ", "Ethan", "Wong", true);
		testConstructor("Test 2:checking firstname null- ", null, "Wong", false);
		testConstructor("Test 3:checking lastname null- ", "Ethan", null, false);
		testConstructor("Test 4:Checking firstname 2 characters", "et", "Wong", true);
		testConstructor("Test 5:Checking lastname 2 characters", "Ethan", "Wo", true);
		testConstructor("Test 6:Checking firstname less than 2 characters", "e", "Wong", false);
		testConstructor("Test 7:Checking lastname less than 1 characters", "Ethan", "w", false);
		testConstructor("Test 8:Checking Special character space", "Ethan", "Wo ng ", true);
		testConstructor("Test 9:Checking Special character asterix", "Ethan", "*Wong", true);
		testConstructor("Test 10:Checking Special character apostrophe", "Ethan's", "Wong", true);
		testConstructor("Test 11:Checking Special character anything else", "Ethan?", "Wong", false);
	}

	public static void testConstructor(String testCase, String fname, String lname, boolean expectValid) {
		System.out.println(" " + testCase);
		try {
			Name n = new Name(fname, lname);
			System.out.println("\tThe Name instance was created: " + n + " -WORKED");

			if (!expectValid)
				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Both names must have 2 or more characters");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");
		}
	}

	public static void testGetFirstName() {
		System.out.println("\n\nTesting the getFirstName method.");
		testGetFirstName("Test 1: Valid - Ethan without leading/trailing spaces", "Ethan", "Ethan Wong", true);
		testGetFirstName("Test 2: Invalid null firstName", null, "  Wong", false);

	}

	public static void testGetFirstName(String testCase, String fname, String lname, boolean expectValid) {
		System.out.println(" " + testCase);
		try {
			Name name = new Name(fname, lname);
			name.getFirstName();
			System.out.println("\tFeteched First Name: " + name + " -WORKED");

			if (!expectValid)
				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");
		}
	}

	public static void testGetLastName() {
		System.out.println("\n\nTesting the getLastName method.");
		testGetLastName("Test 1: Valid - Wong without leading/trailing spaces", "Wong", "Ethan Wong", true);
		testGetLastName("Test 2: Invalid null LastName", null, " Ethan ", false);
	}

	public static void testGetLastName(String testCase, String lname, String fname, boolean expectValid) {
		System.out.println(" " + testCase);
		try {
			Name name = new Name(fname, lname);
			name.getLastName();
			System.out.println("\tFeteched Last Name: " + name + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");
		}
	}

	// needs to be edited
	public static void testGetFullName() {
		System.out.println("\n\nTesting the getFullName method.");
		testGetFullName("Test 1: Valid - full name without leading/trailing spaces", "Wong", "Ethan Wong", true);
		testGetFullName("Test 2: Invalid null fullName", null, null, false);

	}

	// needs to be edited
	public static void testGetFullName(String testCase, String fname, String lname, boolean expectValid) {

		try {
			System.out.println(" " + testCase);
			Name name = new Name(fname, lname);
			name.getFullName();
			System.out.println("\tFeteched Full Name: " + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");

		}
	}

	public static void testToString() {

		System.out.println("\n\nTesting the overrided hashchode method.");
		testToString("Test 1: Valid -full name string without leading/trailing spaces", "Ethan", "Wong", true);
		testToString("Test 2: Invalid null fullName", null, null, false);

	}

	public static void testToString(String testCase, String fname, String lname, boolean expectValid) {
		System.out.println(" " + testCase);
		try {

			Name name = new Name(fname, lname);
			System.out.println("\tFetched to String: " + name.toString() + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");

		}
	}

	public static void testEquals() {

		System.out.println("\n\nTesting the overrided testEquals method.");
		testEquals("Test 1: Valid - shows true ", "Ethan", "Wong", "Ethan", "Wong", true);
		testEquals("Test 2: Invalid null fullName", null, null, null, null, false);
		testEquals("Test 3: Invalid -does not match ", "Ethan", "Wong", "Julian", "Wong", false);
		testEquals("Test 4: Invalid - shows false firstname cannot be null", null, "Wong", "Ethan", "Wong", false);
		testEquals("Test 5: Invalid - shows false lastname cannot be null", "Ethan", null, "Ethan", "Wong", false);
		testEquals("Test 6: Invalid - false firstname2 cannot be null", "Ethan", "Wong", null, "Wong", false);
		testEquals("Test 7: Invalid - shows false lastname2cannot be null", "Ethan", "Wong", "EThan", null, false);

	}

	public static void testEquals(String testCase, String fname, String lname, String fname2, String lname2,
			boolean expectValid) {
		System.out.println(" " + testCase);
		try {

			Name name = new Name(fname, lname);
			Name name2 = new Name(fname2, lname2);

			System.out.println("\tChecked with equals: " + name.equals(name2) + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");

		}
	}

	public static void testHashCode() {

		System.out.println("\n\nTesting the overrided hashCode method.");
		testHashCode("Test 1: Valid -haschode of the name", "Ethan", "Wong", true);
		testHashCode("Test 2: Invalid cannot be null", "Ethan", null, false);

	}

	public static void testHashCode(String testCase, String fname, String lname, boolean expectValid) {
		System.out.println(" " + testCase);
		try {

			Name name = new Name(fname, lname);
			System.out.println("\tFetched hashCode: " + name.hashCode() + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");

		}
	}

	public static void testValidate() {
		System.out.println("\n\nTesting the overrided validate method.");
		testValidate("Test 1: Valid - validates name", "Ethan", "Wong", true);
		testValidate("Test 2: Invalid - cannot be Null", "Ethan", null, false);
		testValidate("Test 3: Invalid - cannot be less than 2 characters", "E", "Wong", false);
		testValidate("Test 8:Checking Special character space", "Ethan", "Wo ng ", true);
		testValidate("Test 9:Checking Special character dash", "Ethan", "-Wong", true);
		testValidate("Test 10:Checking Special character apostrophe", "Ethan's", "Wong", true);
		testValidate("Test 11:Checking Special character anything else", "Ethan9", "Wong", false);
		testValidate("Test 12:Checking Special character anything else", "Ethan%", "Wong", false);
		testValidate("Test 13:Checking Special character anything else", "Eth9an", "Wong", false);
		testValidate("Test 14:Checking Special character anything else", "9Ethan", "Wong", false);


	}

	public static void testValidate(String testCase, String fname, String lname, boolean expectValid) {
		System.out.println(" " + testCase);
		try {

			Name name = new Name(fname, lname);
			System.out.println("\tFetched validate: " + name.validate(fname) + "," + name.validate(lname) + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");

		}
	}

	public static void testToCompare() {

		System.out.println("\n\nTesting the overrided toCompare method.");
		testToCompare("Test 1: Valid - shows true ", "Ethan", "Wong", "Ethan", "Wong", true);
		testToCompare("Test 2: Invalid null fullName", null, null, null, null, false);
		testToCompare("Test 3: Valid - when name 2 is greater then name 1 ", "Ethan", "Wong", "Marc", "Yannick", true);
		testToCompare("Test 4: Valid - when name 1 is greater then name 2", "Marc", "Yannick", "Ethan", "Wong", true);
		testToCompare("Test 5: Valid - when name 1 is greater then name 2 ", "Ethan", "Wong", "Yannick", "Marc", true);
		testToCompare("Test 6: Valid - when name 2 is greater then name 1", "Yannick", "Marc", "Ethan", "Wong", true);
	}

	public static void testToCompare(String testCase, String fname, String lname, String fname2, String lname2,
			boolean expectValid) {
		System.out.println(" " + testCase);
		try {

			Name name = new Name(fname, lname);
			Name name2 = new Name(fname2, lname2);
			System.out.println("\tFetched validate: " + name.compareTo(name2) + " -WORKED");

			if (!expectValid)

				System.out.println(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			if (!expectValid)
				System.out.println("\t" + iae.getMessage());
			if (expectValid)
				System.out.println(" Error! Cannot find name");
		} catch (NullPointerException npe) {
			if (!expectValid)
				System.out.println("\t" + npe.getMessage());
			if (expectValid)
				System.out.println("Error! Expected Valid. ==== Failed Test ====");

		}
	}
}
