/**
 * 
 */
package util;


import java.io.IOException;


import util.ListUtilities;
/**
 * @author Anthony Whitebean and Trevor Cole Chevrier
 *
 */
public class ListUtilitiesTest {
	public static void main (String[] args)
	{
		testSortMethod();
		testMerge();
		testSaveListToTextFile();
	}
	
	/**
	 * This code will test the sort method.
	 * 
	 * @author Anthony Whitebean
	 */
	public static void testSortMethod()
	{
		String[] words1 = {"Dave", "Bob", "Joe", "Tim", "John"};
		String[] words2 = null;
		String[] words3 = {"Dave", null, "Joe", "Tim", "John"};
		testSortMethod("Test 1: Testing to see if it actually works Dave Bob Joe Tim John", words1, true);
		testSortMethod("Test 2: Testing to see if it actually catches null arrays", words2, false);
		testSortMethod("Test 3: Testing to see if it actually catches nulls in arrays", words3, false);
	}

	/**
	 * This code will test the sort method
	 * @param string Representing the name of the test
	 * @param words representing the array being used
	 * @param check representing if it was expected to pass or fail.
	 * 
	 * @author Anthony Whitebean
	 */
	private static void testSortMethod(String string, String[] words, boolean check) {
		System.out.println(string);
		try
		{
			ListUtilities.sort(words);
			if (check == false)
			{
				System.err.println("Expected to fail");
			}
			for (int i = 0; i < words.length; i++)
			{
				System.out.println(words[i]);
			}
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Failed");
			if (check == true)
			{
				System.err.println("Expected to work");
				System.err.println(e.getMessage());
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("Failed");
			if (check == true)
			{
				System.err.println("Expected to work");
				System.err.println(e.getMessage());
			}
		}
	}
	
		@SuppressWarnings("rawtypes")
	public static void testMerge()
	{
		Comparable[] list1 = {"alpha", "beta", "charlie", "delta", "zebra"};
		Comparable[] list2 = null;
		Comparable[] list3 = {"alpha", null, "charlie", "delta", "zebra"};
		Comparable[] list4 = {"alpha", "beta", "charlie", "delta", "zebra"};
		Comparable[] list5 = {"imagine", "what", "the", "toe", "said"};
		Comparable[] list6 = {"there","are","less","objects"};
		Comparable[] list7 = {"alpha", "beta", "different","list"};
		
		testMerge("Test 1: Testing with null param", list1, list2, "src/util/duplicateElections.txt", false);
		testMerge("Test 2: Testing when a list is not full to capacity", list1, list3, "src/util/duplicateElections.txt", false);
		testMerge("Test 3: Testing two identical lists", list1, list4, "src/util/duplicateElections.txt", true);
		testMerge("Test 4: Testing two different lists", list1, list5, "src/util/duplicateElections.txt", true);
		testMerge("Test 5: Testing two lists of different lengths", list1, list6, "src/util/duplicateElections.txt", true);
		testMerge("Test 6: Testing two lists that have: identical objects, different lengths", list1, list7, "src/util/duplicateElections.txt", true);
		testMerge("Test 7: Testing two different lists with an invalid file", list1, list5, "src/util/notafile.txt", false);
	}

	@SuppressWarnings("rawtypes")
	private static void testMerge(String message, Comparable[] list1, Comparable[] list2, String fileName, boolean expect) {
		System.out.println("\n"+ message + "\n");
		System.out.println(fileName + "\n");
		try
		{
			
			if (expect == false)
			{
				System.out.println("\nThis test is expected to fail\n");
			}
			for (int i = 0; i < list1.length; i++)
			{
				System.out.println(list1[i]);
			}
			System.out.println("");
			
			for (int i = 0; i < list2.length; i++)
			{
				System.out.println(list2[i]);
			}
			
			ListUtilities.merge(list1,list2, fileName);
			System.out.println("\nThe test was successful\n");
			Comparable [] test = ListUtilities.merge(list1,list2, fileName);
					for(int i=0;i<test.length;i++) {
						System.out.println(test[i]);
					}
			
		}
		catch (NullPointerException npe)
		{
			System.err.println("\n" + npe.getMessage());
			System.out.println("\nFailed");
			
			
			
			if (expect == true)
			{
				System.out.println("\nINVALID - TEST WAS MEANT TO WORK\n");
				
			}
		}
		catch (IllegalArgumentException iae)
		{
			System.err.println("\n" + iae.getMessage());
			System.out.println("Failed");
			
			
			
			if (expect == true)
			{
				System.out.println("\nINVALID - TEST WAS MEANT TO WORK\n");
				
			}
		}
		catch (IOException ioe)
		{
			System.err.println("\n" + ioe.getMessage());
			System.out.println("\nFailed\n");
			
			
			
			if (expect == true)
			{
				System.out.println("\nINVALID - TEST WAS MEANT TO WORK\n");
				
			}
		}
		
	}
	
	
	public static void testSaveListToTextFile() {
		String [] test1 = {"please", "save", "me"};
		
		testSaveListToTextFile("Test 1: Testing a full string array and a proper file path with append", test1, "src/util/duplicateElections.txt", true, true);
		testSaveListToTextFile("Test 2: Testing a full string array and a proper file path without append", test1, "src/util/duplicateElections.txt", false, true);
		testSaveListToTextFile("Test 3: Testing a full string array and an improper file path with append", test1, "src/util/whatisgoingon.txt", true, false);
	}
	
	
	private static void testSaveListToTextFile(String message, String [] list, String fileName, boolean append, boolean expect) {
		System.out.println("\n"+ message + "\n");
		System.out.println(fileName + "\n");
		try
		{
			
			if (expect == false)
			{
				System.out.println("\nThis test is expected to fail\n");
			}
			for (int i = 0; i < list.length; i++)
			{
				System.out.println(list[i]);
			}
			
			ListUtilities.saveListToTextFile(list,fileName,append);
			System.out.println("\nThe test was successful\n");
		}
		catch (IOException ioe)
		{
			System.err.println("\n" + ioe.getMessage());
			System.out.println("\nFailed\n");
			
			
			
			if (expect == true)
			{
				System.out.println("\nINVALID - TEST WAS MEANT TO WORK\n");
				
			}
		}
		
	}
	
	
	
	
	
	
	
}

