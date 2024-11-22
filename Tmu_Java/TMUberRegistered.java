
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TMUberRegistered
{
    // These variables are used to generate user account and driver ids
    private static int firstUserAccountID = 900;
    private static int firstDriverId = 700;

    // Generate a new user account id
    public static String generateUserAccountId(ArrayList<User> current)
    {
        return "" + firstUserAccountID + current.size();
    }

    // Generate a new driver id
    public static String generateDriverId(ArrayList<Driver> current)
    {
        return "" + firstDriverId + current.size();
    }


    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    // The test scripts and test outputs included with the skeleton code use these
    // users and drivers below. You may want to work with these to test your code (i.e. check your output with the
    // sample output provided). 
    public static ArrayList<User> loadPreregisteredUsers(String filename) throws FileNotFoundException
    {
        //create an array list of users and each returns a reference to a their list. 
        ArrayList<User> users = new ArrayList<>();
        Scanner scan = new Scanner(new File(filename));
        //User “record” in the users.txt file takes 3 lines:
        //name string, address string and wallet amount(double).
        while (scan.hasNextLine()){
            String name = scan.nextLine();
            String address = scan.nextLine();
            double wallet = Double.parseDouble(scan.nextLine()); 
            users.add(new User(generateUserAccountId(users),name,address,wallet));
        }
        scan.close();
        return users;
    }

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    public static ArrayList<Driver> loadPreregisteredDrivers(String filename) throws FileNotFoundException
    {
        //create an array list of drivers and each returns a reference to a their list. 
        ArrayList<Driver> drivers = new ArrayList<>();
        Scanner scan = new Scanner(new File(filename));
        //Driver “record” has 4 lines: name, car model, car license, and address. 
        while (scan.hasNextLine()){
            String name = scan.nextLine();
            String model = scan.nextLine();
            String license = scan.nextLine();
            String address = scan.nextLine(); // why?
            drivers.add(new Driver(generateDriverId(drivers),name, model,license, address));
        }
        scan.close();
        return drivers;
    }
}

