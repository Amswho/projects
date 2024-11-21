//Name: AMY HU 
//Student Number: 501258348

import java.util.Arrays;
import java.util.Scanner;

// The city consists of a grid of 9 X 9 City Blocks

// Streets are east-west (1st street to 9th street)
// Avenues are north-south (1st avenue to 9th avenue)

// Example 1 of Interpreting an address:  "34 4th Street"
// A valid address *always* has 3 parts.
// Part 1: Street/Avenue residence numbers are always 2 digits (e.g. 34).
// Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
// Part 3: Must be "Street" or "Avenue" (case insensitive)

// Use the first digit of the residence number (e.g. 3 of the number 34) to determine the avenue.
// For distance calculation you need to identify the the specific city block - in this example 
// it is city block (3, 4) (3rd avenue and 4th street)

// Example 2 of Interpreting an address:  "51 7th Avenue"
// Use the first digit of the residence number (i.e. 5 of the number 51) to determine street.
// For distance calculation you need to identify the the specific city block - 
// in this example it is city block (7, 5) (7th avenue and 5th street)
//
// Distance in city blocks between (3, 4) and (7, 5) is then == 5 city blocks
// i.e. (7 - 3) + (5 - 4) 

public class CityMap
{
  // Checks for string consisting of all digits
  // An easier solution would use String method matches()
  private static boolean allDigits(String s) //given
  {
    for (int i = 0; i < s.length(); i++)
      if (!Character.isDigit(s.charAt(i)))
        return false;
    return  true;
  }

  // Get all parts of address string
  // An easier solution would use String method split()
  // Other solutions are possible - you may replace this code if you wish
  private static String[] getParts(String address)
  {  //rewritten code
     String[] parts = address.split("\\s+"); //stores string
     //returns empty string[]
     if (parts.length == 0 || (parts.length == 1 && parts[0].isEmpty())) {
         return new String[0];
     //returns proper string[]
     }else if (parts.length >= 3) {
         parts = Arrays.copyOf(parts, 3);
     }return parts;

    /*  GIVEN CODE not used
    String parts[] = new String[3];
    
    if (address == null || address.length() == 0){
      parts = new String[0];
      return parts;
    }
    int numParts = 0;
    Scanner sc = new Scanner(address);
    while (sc.hasNext()){
      if (numParts >= 3)
        parts = Arrays.copyOf(parts, parts.length+1);

      parts[numParts] = sc.next();
      numParts++;
    }if (numParts == 1)
      parts = Arrays.copyOf(parts, 1);
    else if (numParts == 2)
      parts = Arrays.copyOf(parts, 2);
    return parts;*/
  }

  //NEW CITY ZONE METHOD //works
 // the city map is now divided into 4 zones: zone 0, 1, 2, 3. 
 // Add a static method to class CityMap called int getCityZone(String address) that
 // returns the zone given a valid address. Return -1 if the address is not valid.

  public static int getCityZone(String address){
    if (validAddress(address)){
      int[] block = getCityBlock(address);
      int avenue = block[0];
      int street = block[1];
      // Zone 0 extends from 1st avenue to 5th avenue and 6th to 9th street. 
      if((avenue >=1 && avenue <=5) && (street >=6 && street <=9)){
        return 0;
      }// Zone 1 extends from 6th avenue to 9th avenue and 6th to 9th street.
      else if ((avenue >=6 && avenue <=9) && (street >=6 && street <=9)){
        return 1;
      }// Zone 2 extends from 6th avenue to 9th avenue and 1st to 5th street.
      else if ((avenue >=6 && avenue <=9) && (street >=1 && street <=5)){
        return 2;
      }// Zone 3 extends from 1st avenue to 5th avenue and 1st to 5th street.
      else if ((avenue >=1 && avenue <=5) && (street >=1 && street <=5)){
        return 3;
      }//Return -1 if the address is not valid
      else{
        return -1;
      }
    }return -1;
  }

  // Checks for a valid address
  public static boolean validAddress(String address)
  {
    // Fill in the code
    // Make use of the helper methods above if you wish
    // There are quite a few error conditions to check for 
    // e.g. number of parts != 3
    String[] parts = getParts(address); //gets string[] of adress
        if (parts.length != 3){ //check if correct length
            return false;
        }if (!allDigits(parts[0])){ //if number not vaild
            return false;
        //check specfic format: (?i) Case-insensitive
        //(?:1st|2nd|3rd|[4-9]\\d*th) checks 1st,2nd,3rd or num[4to9] folled by th
        }if(!parts[1].matches("(?i)(?:1st|2nd|3rd|[4-9]\\d*th)")){ 
            return false;
            //checks third part of address to ensure validity
        }if (!parts[2].equalsIgnoreCase("Street") 
              && !parts[2].equalsIgnoreCase("Avenue")){
            return false;
        }return true;
  }

  // Computes the city block coordinates from an address string
  // returns an int array of size 2. e.g. [3, 4] 
  // where 3 is the avenue and 4 the street
  // See comments at the top for a more detailed explanation
  public static int[] getCityBlock(String address) //works
  { 
    int[] block = {-1, -1};
    // Fill in the code
    int street,avenue;
    String[] parts = getParts(address);
        if (validAddress(address)) {
          int first = Integer.valueOf(parts[0].substring(0, 1));
          int second = Integer.parseInt(parts[1].substring(0, 1));
          if (parts[2].equalsIgnoreCase("Avenue")){
            street = first; // if avenue format street is first
            avenue = second;
          }else{
            street = second; // if avenue format street is second
            avenue = first;
          }
          block[0] = avenue;
          block[1] = street;
        }return block;
  }
  
  // Calculates the distance in city blocks between the 'from' address and 'to' address
  // Hint: be careful not to generate negative distances
  // This skeleton version generates a random distance
  // If you do not want to attempt this method, you may use this default code
  public static int getDistance(String from, String to) //works
  { 
    // Fill in the code or use this default code below. If you use
    // the default code then you are not eligible for any marks for this part
      int[] fromBlock = getCityBlock(from); //use getblocks method to get coordinates 
      int[] toBlock = getCityBlock(to);
      int avenueDiff = Math.abs(toBlock[0] - fromBlock[0]); //calculate distance
      int streetDiff = Math.abs(toBlock[1] - fromBlock[1]);
      int diff = avenueDiff + streetDiff;
      return diff;
  }
/* Given code not used
    // Math.random() generates random number from 0.0 to 0.999
    // Hence, Math.random()*17 will be from 0.0 to 16.999
    double doubleRandomNumber = Math.random() * 17;
    // cast the double to whole number
    int randomNumber = (int)doubleRandomNumber;
    return (randomNumber);
  }*/
}
