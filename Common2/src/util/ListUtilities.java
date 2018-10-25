package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Anthony Whitebean AND Trevor Cole Chevrier and Daniel Rafail
 * @version 11-03-2017
 */

public class ListUtilities {
 private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

 private ListUtilities() {

 }

 /**
  * Takes in a list of objects and writes them to a given file. This method
  * overwrites data in file and uses UTF8 character set.
  * 
  * @param objects
  *            Array of items to be written to file.
  * @param filename
  *            filename and location of the file where list of items will be
  *            written.
  * @throws IOException
  *             if an I/O error occurs writing to or creating the file
  */
 public static void saveListToTextFile(Object[] objects, String filename) throws IOException {
  saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
 }

 /**
  * Takes in a list of objects and writes them to a given file. This method uses
  * UTF8 character set.
  * 
  * @param objects
  *            Array of items to be written to file.
  * @param filename
  *            filename and location of the file where list of items will be
  *            written.
  * @param append
  *            boolean indicating if the file is overwritten or if the items are
  *            written to the end of the file.
  * @throws IOException
  *             if an I/O error occurs writing to or creating the file
  */
 public static void saveListToTextFile(Object[] objects, String filename, boolean append) throws IOException {
  saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
 }

 /**
  * Takes in a list of objects and writes them to a given file.
  * 
  * @param objects
  *            Array of items to be written to file.
  * @param filename
  *            filename and location of the file where list of items will be
  *            written.
  * @param append
  *            boolean indicating if the file is overwritten or if the items are
  *            written to the end of the file.
  * @param characterEncoding
  *            the Charset to be used when encoding
  * @throws IOException
  *             if an I/O error occurs writing to or creating the file
  */
 public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
   throws IOException {

  Path path = Paths.get(filename);
  OpenOption option;
  if (append)
   option = StandardOpenOption.APPEND;
  else
   option = StandardOpenOption.CREATE;

  // create list of strings
  List<String> toWrite = new ArrayList<String>();
  for (Object obj : objects)
   if (obj != null)
    toWrite.add(obj.toString());

  // write list to file
  Files.write(path, toWrite, characterEncoding, StandardOpenOption.WRITE, option);
 }

 /**
  * Sorts a list of objects in ascending natural order using selection sort.
  * 
  * Precondition: Assumes that the list is not null and that the list's capacity
  * is equal to the list's size.
  * 
  *
  * @param list
  *            A list of objects. Assumes that the list's capacity is equal to
  *            the list's size.
  * 
  * @throws IllegalArgumentException
  *             if the parameter is * not full to capacity.
  *
  * @throws NullPointerException
  *             if the list is null.
  * 
  * @author Anthony Whitebean
  */
 @SuppressWarnings({ "rawtypes", "unchecked" })
 public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
  if (list == null) {
   throw new NullPointerException("The list sent in is null.");
  }
  for (int i = 0; i < list.length; i++) {
   if (list[i] == null) {
    throw new IllegalArgumentException("There is an object in the list that was null.");
   }
  }
  for (int i = 0; i < list.length; i++) {
   int smallest = i;
   for (int j = i + 1; j < list.length; j++) {
    if (list[j].compareTo(list[smallest]) < 0) {
     smallest = j;
    }
   }
   Comparable temp = list[smallest];
   list[smallest] = list[i];
   list[i] = temp;
  }
 }

 /**
  * Sorts a list of objects in the given order.
  * 
  * Precondition: Assumes that the list is not null and that the list's capacity
  * is equal to the list's size.
  * 
  *
  * @param list
  *            A list of objects. Assumes that the list's capacity is equal to
  *            the list's size.
  * @param sortOrder
  *            A Comparator object that defines the sort order
  * 
  * @throws IllegalArgumentException
  *             if the parameter is * not full to capacity.
  *
  * @throws NullPointerException
  *             if the list or sortOrder * are null.
  * 
  * @author Anthony Whitebean
  */
 @SuppressWarnings({ "rawtypes", "unchecked" })
 public static void sort(Comparable[] list, Comparator sortOrder)
   throws IllegalArgumentException, NullPointerException {
  if (list == null) {
   throw new NullPointerException("The list sent in is null.");
  }
  for (int i = 0; i < list.length; i++) {
   if (list[i] == null) {
    throw new IllegalArgumentException("There is an object in the list that was null.");
   }
  }
  Arrays.sort(list, sortOrder);
 }

 /**
  * Merges two naturally sorted lists in ascending order.
  * 
  * Precondition: The two lists being passed through the parameter are not null,
  * full to capacity and contains objects that can be compared to each other.
  * 
  *
  * @param list1
  *            A naturally sorted list of objects. Assumes that the list contains
  *            no duplicates and that its capacity is equal to its size.
  * @param list2
  *            A naturally sorted list of objects. Assumes that the list contains
  *            no duplicates and that its capacity is equal to its size.
  * @param duplicateFileName
  *            The name of the file in datafiles\duplicates where the duplicates
  *            in list 1 and 2 are appended to
  * 
  * @throws IllegalArgumentException
  *             if either parameter is not full to capacity.
  *
  * @throws NullPointerException
  *             if the either list is null.
  * @throws IOException
  *             if the duplicates file is invalid
  */
 @SuppressWarnings({ "rawtypes", "unchecked" })
 public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName)
   throws NullPointerException, IllegalArgumentException, IOException {

  if (list1 == null || list2 == null) {
   throw new NullPointerException("The lists being merged cannot be null.");
  }

  for (int i = 0; i < list1.length; i++) {
   if (list1[i] == null) {
    throw new IllegalArgumentException("The first list must be full to capacity");
   }
  }

  for (int i = 0; i < list2.length; i++) {
   if (list2[i] == null) {
    throw new IllegalArgumentException("The second list must be full to capacity");
   }
  }

  Comparable[] mergedlist = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
    list1.length + list2.length);

  Object[] duplicatelist = new Object[list1.length + list2.length];

  int size = 0;
  int index1 = 0;
  int index2 = 0;
  int duplicateSize = 0;

  while (!(index1 > list1.length - 1 && index2 > list2.length - 1)) {
   if (index1 > list1.length - 1 && index2 <= list2.length - 1) {
    mergedlist[size] = list2[index2];

    index2++;
    size = size + 1;
    continue;
   }
   else if (index2 > list2.length - 1 && index1 <= list1.length - 1) {
    mergedlist[size] = list1[index1];

    index1++;
    size = size + 1;
    continue;
   }

   else if (list1[index1].compareTo(list2[index2]) < 0) {
    mergedlist[size] = list1[index1];

    index1++;
    size = size + 1;
    continue;

   }

   else if (list1[index1].compareTo(list2[index2]) > 0) {
    mergedlist[size] = list2[index2];

    index2++;
    size = size + 1;
    continue;
   }

   else if (list1[index1].compareTo(list2[index2]) == 0) {
    mergedlist[size] = list1[index1];

    duplicatelist[duplicateSize] = list1[index1] + "\t(merged)";
    duplicatelist[duplicateSize + 1] = list2[index2];

    index1++;
    index2++;
    size = size + 1;
    duplicateSize = duplicateSize + 2;
    continue;

   }

  }

  Path duplicate = Paths.get(duplicateFileName);
  if (Files.exists(duplicate)) {
   if (duplicatelist != null) {
    saveListToTextFile(duplicatelist, duplicateFileName, false);

   }
  } else {
   throw new IOException("The file does not exist");
  }

  if (size < mergedlist.length) {
   Comparable[] resizedmerge = Arrays.copyOf(mergedlist, size);
   return resizedmerge;
  }

  return mergedlist;
 }

 /**
  * 
  * @param database
  * @param key
  * @return the correct value if found, -1 if nothing found
  */
 @SuppressWarnings({ "unchecked", "rawtypes" })
 public static int binarySearch(Comparable[] database, Comparable key) {
  int low = 0;
  int high = database.length - 1;
  int mid = (high + low) / 2;
  while (high >= low) {
   mid = (high + low) / 2;
   if (database[mid].compareTo(key) > 0)
    high = mid - 1;
   if (database[mid].compareTo(key) < 0)
    low = mid + 1;
   if (database[mid].compareTo(key) == 0)
    return mid;
  }
  return -low-1;
 }

 /**
  * 
  * @param database
  * @param key
  * @param low
  * @param high
  * @return the correct value if found, -1 if nothing found
  */
 public static <T extends Comparable<? super T>> int binarySearch(List<T> database, T key, int low, int high) {
  if (high < low)
   return -low - 1;
  int mid = (low + high) / 2;
  if (database.get(mid).compareTo(key) == 0)
   return mid;
  if (database.get(mid).compareTo(key) < 0)
   return binarySearch(database, key, mid + 1, high);
  if (database.get(mid).compareTo(key) > 0)
   return binarySearch(database, key, low, mid - 1);
  return -1;
 }

}