/**
 * This code will load sort and merge the 9 original voter files.
 */
package election.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import election.business.VoterNameComparator;
import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import util.ListUtilities;

/**
 * @author 1640463 and Daniel Rafail
 * @version 26/10/2017
 */
public class SortMergeApp {
	public static void main(String[] args) throws IOException
	{
		Voter[][] voter = new Voter[9][4];
		for (int i = 1; i < 9; i++) {
			voter[i] = (Voter[]) ElectionFileLoader.getVoterListFromSequentialFile("datafiles/testfiles/unsorted/unsorted/voters" + i + ".txt");
			ListUtilities.sort(voter[i]);
		File sorted = new File("datafiles/sorted");
		if (!(sorted.exists()))
			sorted.mkdir();
		sorted = new File("datafiles/database");
		if (!(sorted.exists()))
			sorted.mkdir();
		try
		{
			ListUtilities.saveListToTextFile(voter[i], "datafiles/sorted/sortedVoters"+ i + ".txt");
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		try
		{
			if (i!=1){
			voter[1] = (Voter[])ListUtilities.merge(voter[1],voter[i],"datafiles/database/duplicateVoters.txt");
			ListUtilities.saveListToTextFile(voter[1], "datafiles/database/voters.txt");
			}
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		} 
		Election[][] election= new Election[9][12];
		for(int i = 1; i < 9; i++) {
		election[i] = (Election[])ElectionFileLoader.getElectionListFromSequentialFile("datafiles/testfiles/unsorted/unsorted/elections" + i + ".txt");
		ListUtilities.sort(election[i]);
		try
		{
			ListUtilities.saveListToTextFile(election[i], "datafiles/sorted/sortedElections" + i + ".txt");
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		try
		{
			if (i!=1){
			election[1] = (Election[])ListUtilities.merge(election[1],election[i],"datafiles/database/duplicateElections.txt");

			ListUtilities.saveListToTextFile(election[1], "datafiles/database/elections.txt");
			}
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		}
			ElectionFileLoader.setExistingTallyFromSequentialFile("datafiles/testfiles/unsorted/unsorted/tally.txt", election[1]);
		}
		
	}
