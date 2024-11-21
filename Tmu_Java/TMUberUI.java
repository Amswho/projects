//Name: AMY HU 
//Student Number: 501258348

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Simulation of a Simple Command-line based Uber App 

// This system supports "ride sharing" service and a delivery service

public class TMUberUI
{
  public static void main(String[] args)
  {
    // Create the System Manager - the main system code is in here 

    TMUberSystemManager tmuber = new TMUberSystemManager();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine())
    {
    try{
      String action = scanner.nextLine();

      if (action == null || action.equals("")) 
      {
        System.out.print("\n>");
        continue;
      }
      // Quit the App
      else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        return;
      // Print all the registered drivers
      else if (action.equalsIgnoreCase("DRIVERS"))  // List all drivers
      {
        tmuber.listAllDrivers(); 
      }
      // Print all the registered users
      else if (action.equalsIgnoreCase("USERS"))  // List all users
      {
        tmuber.listAllUsers(); 
      }
      //Load users from file
      else if (action.equalsIgnoreCase("LOADUSERS")) {
        System.out.print("User File: ");
        String filename = scanner.nextLine();
        try {
            ArrayList<User> loadedUsers = TMUberRegistered.loadPreregisteredUsers(filename);
            tmuber.setUsers(loadedUsers);
            System.out.println("Users Loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } 
        // catch (IOException e) {
        //     System.out.println("Error loading users: " + e.getMessage());
        //     return; // Exit the program
        // }
      }
      //Load drivers from file
      else if (action.equalsIgnoreCase("LOADDRIVERS")) {
        System.out.print("Drivers File: ");
        String filename = scanner.nextLine();
        try {
            ArrayList<Driver> loadedDrivers = TMUberRegistered.loadPreregisteredDrivers(filename);
            tmuber.setDrivers(loadedDrivers);
            System.out.println("Drivers Loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } 
        // catch (IOException e) {
        //     System.out.println("Error loading users: " + e.getMessage());
        //     return; // Exit the program
        // }
      }
      // Print all current ride requests or delivery requests
      else if (action.equalsIgnoreCase("REQUESTS"))  // List all requests
      {
        tmuber.listAllServiceRequests(); 
      }
      // Register a new driver
      else if (action.equalsIgnoreCase("REGDRIVER")) // Regester a new driver //given
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String carModel = "";
        System.out.print("Car Model: ");
        if (scanner.hasNextLine())
        {
          carModel = scanner.nextLine();
        }
        String license = "";
        System.out.print("Car License: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        tmuber.registerNewDriver(name, carModel, license, address);
        System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s", name, carModel, license);
      }
      // Register a new user
      else if (action.equalsIgnoreCase("REGUSER")) // Regester a new user //given
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        double wallet = 0.0;
        System.out.print("Wallet: ");
        if (scanner.hasNextDouble())
        {
          wallet = scanner.nextDouble();
          scanner.nextLine(); // consume nl!! Only needed when mixing strings and int/double
        }
        tmuber.registerNewUser(name, address, wallet);
        System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
      }
      // Request a ride
      else if (action.equalsIgnoreCase("REQRIDE")) // New ride request //WORKS
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestRide() method properly to make a ride request
        // "User Account Id: "      (string)
        String id = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine()){
          id = scanner.nextLine();
        }
        // "From Address: "         (string)
        String from = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        // "To Address: "           (string)
        String to = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        tmuber.requestRide(id, from, to);
        System.out.printf("\nRIDE for: %-15s From Address: %-15s To Address: %-10s", tmuber.getUser(id).getName(), from, to);
      }
      // Request a food delivery
      else if (action.equalsIgnoreCase("REQDLVY")) // New delivery request //WORKS
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestDelivery() method properly to make a ride request
        // "User Account Id: "      (string)
        String id = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine()){
          id = scanner.nextLine();
        }
        // "From Address: "         (string)
        String from = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        // "To Address: "           (string)
        String to = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        // "Restaurant: "           (string)
        String rest = "";
        System.out.print("Restaurant: ");
        if (scanner.hasNextLine())
        {
          rest = scanner.nextLine();
        }
        // "Food Order #: "         (string)
        String order = "";
        System.out.print("Food Order #: ");
        if (scanner.hasNextLine())
        {
          order = scanner.nextLine();
        }
        tmuber.requestDelivery(id, from, to, rest, order);
        System.out.printf("\nRIDE for: %-15s From: %-15s To: %-10s", tmuber.getUser(id).getName(), from, to);
      }
      //removed reqxl
      // Sort users by name
      else if (action.equalsIgnoreCase("SORTBYNAME")) // Sort by name (alpha)//given
      {
        tmuber.sortByUserName();
      }
      // Sort users by number of ride they have had
      else if (action.equalsIgnoreCase("SORTBYWALLET")) // Sort by wallet (low to high) //given
      {
        tmuber.sortByWallet();
      }
      /*  no longer implemented       // Sort current service requests (ride or delivery) by distance
      else if (action.equalsIgnoreCase("SORTBYDIST")) // Sort by distance //given
      {
        tmuber.sortByDistance();
      }
      */
      // Cancel a current service (ride or delivery) request
      else if (action.equalsIgnoreCase("CANCELREQ")) //Cancel request //given
      {
        int request = -1;
        System.out.print("Request #: ");
        if (scanner.hasNextInt())
        {
          request = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        int zone = -1;
        System.out.print("Zone #: ");
        if (scanner.hasNextInt())
        {
          zone = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        tmuber.cancelServiceRequest(request,zone);
        System.out.println("Service request #" + request + " cancelled");
      }
      //NEW Pick-up user or food 
      else if (action.equalsIgnoreCase("PICKUP")){
        String driverid = "";
        System.out.print("Driver Id: ");
        if (scanner.hasNextLine()) {
          driverid = scanner.nextLine();
        }
        tmuber.pickup(driverid);
        System.out.println("Driver "+ driverid + " Picking Up in Zone " + tmuber.getDriver(driverid).getZone());
      }
      // Drop-off the user or the food delivery to the destination address
      else if (action.equalsIgnoreCase("DROPOFF")) //Complete service //given
      {
        String driverid = "";
        System.out.print("Driver Id: ");
        if (scanner.hasNextLine()){
          driverid = scanner.nextLine();
        }
        tmuber.dropOff(driverid);
        System.out.println("Driver "+ driverid + " Dropping Off");
      }
      //New drive-to, driver drives to new address/area
      // Inside the while loop where actions are processed
      else if (action.equalsIgnoreCase("DRIVETO")) 
      {
          String driverid = "";
          System.out.print("Driver Id: ");
          if (scanner.hasNextLine()) {
            driverid = scanner.nextLine();
          }
          String address = "";
          System.out.print("Address: ");
          if (scanner.hasNextLine()) {
            address = scanner.nextLine();
          }
          tmuber.driveTo(driverid, address);
          System.out.println("Driver " + driverid + " Now in Zone " + tmuber.getDriver(driverid).getZone());
      }

      // Get the Current Total Revenues
      else if (action.equalsIgnoreCase("REVENUES")) //given
      {
        System.out.println("Total Revenue: " + tmuber.totalRevenue);
      }
      // Unit Test of Valid City Address 
      else if (action.equalsIgnoreCase("ADDR")) //tester address //given
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        System.out.print(address);
        if (CityMap.validAddress(address))
          System.out.println("\nValid Address"); 
        else
          System.out.println("\nBad Address"); 
      }
      // Unit Test of CityMap Distance Method
      else if (action.equalsIgnoreCase("DIST")) //tester dist //given
      {
        String from = "";
        System.out.print("From: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        String to = "";
        System.out.print("To: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        System.out.print("\nFrom: " + from + " To: " + to);
        System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
      }
      System.out.print("\n>");
      } catch (Exception e){
        System.out.println(e.getMessage());
        System.out.print("\n>");
      }
    }
  }
}


