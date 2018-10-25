/**
 * 
 */
package election.data;

import election.business.*;
import election.business.interfaces.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import election.business.DawsonVoter;
import election.business.interfaces.Election;
import election.business.interfaces.Voter;

/**
 * @author Daniel Rafail and Ethan Wong
 * @version 20/10/2017
 */
public class ElectionFileLoader {

 // private no param constructor
 private ElectionFileLoader() {

 }
 static int eNumber=1;
 /**
  * @author Daniel Rafail and Ethan Wong
  * @version 20/10/2017
  * @param filename
  *            a string with the name of the file we are reading to get the name
  *            of the voters
  * @return voter a voter array containing all the voters
  * @throws InvalidPathException
  *             in case the path is not valid (should not happen)
  * @throws IOException
  *             in case the file does not exist (should not happen) or any other
  *             error is found
  * @throws NullPointerException
  *             in case the data is invalid (file is empty or null)
  * @throws IllegalArgumentException
  *             in case any other error is found
  */

 public static Voter[] getVoterListFromSequentialFile(String filename) throws IOException{
  Path file;
  List<String> lines = null;
  int voters = 0;
  int count = 0;
  try {
   file = Paths.get(filename);
   if (Files.exists(file))
    lines = Files.readAllLines(file);
  } catch (InvalidPathException ipe) {
   System.err.println("An error was found " + ipe.getMessage());
  } catch (IOException ioe) {
   System.err.println("An error was found " + ioe.getMessage());
  }
  
  /*
  for (String record : lines) {

   String[] fields = record.split("\\*", -1);
   for (int i = 0; i < fields.length; i++) {
    if (fields[i].isEmpty()) {
     lines.remove(i);
     throw new IllegalArgumentException("Please use valid data");
    }
    if (fields[i] == null) {
     lines.remove(i);
     throw new NullPointerException("ENTRY CANNOT BE NULL");
    }
   }
  }
  */
  
  Voter[] voter = new Voter[lines.size()];
  for (String record : lines) {
   String[] fields = record.split("\\*", -1);
   try {
    voter[voters] = new DawsonVoter(fields[1], fields[2], fields[0], fields[3]);
    voters++;
    count++;
   } catch (NullPointerException npe) {
    continue;
    //System.err.println("A null pointer exception " + npe.getMessage());
   } catch (IndexOutOfBoundsException aioobe) {
    continue;
    //System.err.println("The array was out of bounds " + aioobe.getMessage());
   } catch (IllegalArgumentException iae) {
    continue;
    //System.err.println("An error was found " + iae.getMessage());
   }
  }
  Voter[] array = new Voter[count];
  count = 0;
  for (int i = 0; i < array.length;)
  {
   if (!(voter[count] == null))
   {
    array[i] = voter[count];
    i++;
   }
   count++;
  }
  return array;
 }

 /**
  * @author Daniel Rafail and Ethan Wong
  * @version 20/10/2017
  * @param filename
  *            a string with the name of the file we are reading to get the name
  *            of the voters
  * @return election a election array containing all the election information
  * @throws InvalidPathException
  *             in case the path is not valid (should not happen)
  * @throws IOException
  *             in case the file does not exist (should not happen) or any other
  *             error is found
  * @throws NullPointerException
  *             in case the data is invalid (file is empty or null)
  * @throws IllegalArgumentException
  *             in case any other error is found
  */
 public static Election[] getElectionListFromSequentialFile(String filename) throws IOException{
  Path file;
  List<String> lines = null;
  String[] fields = new String[filename.length()];
  String[] arrayElections;
  Election[] election = new Election[filename.length()];
  String completeLine = "";
  int count = 0;
  int voters = 0;
  try {
   file = Paths.get(filename);
   if (Files.exists(file))
    lines = Files.readAllLines(file);

  } catch (InvalidPathException ipe) {
   System.err.println("An error was found " + ipe.getMessage());
  } catch (IOException ioe) {
   System.err.println("An error was found " + ioe.getMessage());
  }
  

  for (String record : lines) {
   String[] text = record.split("\\*", -1);
   for (int i = 0; i < text.length; i++) {
    if (text[i].isEmpty()) {
     //lines.remove(i);
     System.err.println("One of the parameters in election " + eNumber + " is empty, election will be ignored");
     break;
     //throw new IllegalArgumentException("Please use valid data");
    }
    if (text[i] == null) {
     //lines.remove(i);
     System.err.println("One of the parameters in election " + eNumber + " is empty, election will be ignored");
     break;
     //throw new NullPointerException("ENTRY CANNOT BE NULL");
    }
   }
  }
  eNumber++;
  
  for (String record: lines)
  {
   completeLine = completeLine + record + "*";
  }

  
  arrayElections = completeLine.split("\\*", -1);

  voters = 0;
  for (int i = 0; i < (arrayElections.length - 10); i++)
  {
   if (!(isNumber(arrayElections[i+10])))
   {
    continue;
   }
   try
   {
    int choiceLength = 0;
    int[] arrayInt = { Integer.parseInt(arrayElections[i+1]), Integer.parseInt(arrayElections[i+2]), Integer.parseInt(arrayElections[i+3]),
     Integer.parseInt(arrayElections[i+4]), Integer.parseInt(arrayElections[i+5]), Integer.parseInt(arrayElections[i+6]), Integer.parseInt(arrayElections[i+10])};
   
    String[] choices = new String[arrayInt[6]];
    for (int j = i+10+1; j < (i+10+1+arrayInt[6]); j++)
    {
     choices[choiceLength] = arrayElections[j];
     choiceLength++;
    }
    //String text = arrayElections[i+9].toUpperCase();
    //String[] str = new String[fields[11].length()];
    //int value=0;
    /**
    for (int k=0; k<Integer.parseInt(fields[11]);k++){
     int value2=fields[11].indexOf(',') + value + 1;
     str[k]=(str[k]+fields[11].substring(value, value2));
     value=value2;
    }
    **/
    election[voters] = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance(arrayElections[i], arrayElections[i+9], arrayInt[0], arrayInt[1], arrayInt[2], arrayInt[3], arrayInt[4], arrayInt[5], arrayElections[i+7], arrayElections[i+8], choices);
    voters++;
    count++;  
   } catch (NullPointerException npe) {
    continue;
    //System.err.println("A null pointer exception" + npe.getMessage());
   } catch (IndexOutOfBoundsException aioobe) {
    continue;
    //System.err.println("The array was out of bounds " + aioobe.getMessage());
   } catch (IllegalArgumentException iae) {
    continue;
    //System.err.println("An error was found " + iae.getMessage());
   }
     catch (DateTimeException t)
   {
    continue;
   }
  }
  Election[] array = new Election[count];
  count = 0;
  for (int i = 0; i < array.length;)
  {
   if (!(election == null))
   {
    array[i] = election[count];
    i++;
   }
   count++;
  }
  return array;
 }

 /**
  * @author Daniel Rafail and Ethan Wong and Anthony WhiteBean
  * @version 25/10/2017
  * @param filename
  *            a string with the name of the file we are reading to get the name
  *            of the voters
  * @return election a election array containing all the election information
  * @throws InvalidPathException
  *             in case the path is not valid (should not happen)
  * @throws IOException
  *             in case the file does not exist (should not happen) or any other
  *             error is found
  * @throws NullPointerException
  *             in case the data is invalid (file is empty or null)
  * @throws IllegalArgumentException
  *             in case any other error is found
  */
 public static void setExistingTallyFromSequentialFile(String filename, Election[] elections) throws IOException {
  Path file;
  List<String> lines = null;
  String[] arrayElections;
  String completeLine = "";
  try {
   file = Paths.get(filename);
   if (Files.exists(file))
    lines = Files.readAllLines(file);
  } catch (InvalidPathException ipe) {
   System.err.println("An error was found " + ipe.getMessage());
  } catch (IOException ioe) {
   System.err.println("An error was found " + ioe.getMessage());
  }
  
  for (String record: lines)
  {
   completeLine = completeLine + record + "*";
  }
  // splitting the text into an array of strings

  //for (String record : lines) {
   arrayElections = completeLine.split("\\*", 0);//record was here
   for (int i = 0; i < arrayElections.length; i++) {
    if (arrayElections[i].isEmpty()) {
     //lines.remove(i);
     throw new IllegalArgumentException("Please use valid data");
    }
    if (arrayElections[i] == null) {
     //lines.remove(i);
     throw new NullPointerException("ENTRY CANNOT BE NULL");
    }
   }

  //}
  // using the indexs of the array elections now search if the name matches if it
  // does then add it to int [][] result
   for (int i = 0; i < (arrayElections.length - 2); i++) {
    if (!(isNumber(arrayElections[i+1])))
    {
     continue;
    }
    int fieldParse = Integer.parseInt(arrayElections[i + 1]);
    for (int e = 0; e < elections.length; e++) {
     if (arrayElections[i].equals(elections[e].getName())) { 
      int[][] result = new int[fieldParse][fieldParse];
      for (int k = 0; k < fieldParse; k++) {
       for (int j = 0; j < fieldParse; j++) {
        result[k][j] = Integer.parseInt(arrayElections[i + 2]);
        i++;
       }
      }
      DawsonElectionFactory.DAWSON_ELECTION.setExistingTally(result, elections[e]);
      i++;
     }
    }
   }
  }
 
 public static boolean isNumber(String string)
 {
  for (int i = 0; i < string.length(); i++)
  {
   if (!(Character.isDigit(string.charAt(i))))
   {
    return false;
   }
  }
  return true;
 }
}