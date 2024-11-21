//Name: AMY HU 
//Student Number: 501258348

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class TMUberSystemManager
{
  private Map<String, User> users; // Replace private ArrayList<User>   users;
  private ArrayList<Driver> drivers;
  private Queue<TMUberService>[] serviceQ; //replace private ArrayList<TMUberService> serviceRequests;
  public double totalRevenue; // Total revenues accumulated via rides and deliveries
  
  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;

  public TMUberSystemManager() //given
  {
    users = new HashMap<>();
    drivers = new ArrayList<Driver>();
    // Initialize the array of queues with 4 elements\
    serviceQ = new Queue[4];
    // Initialize each queue in the array
    for (int i = 0; i < 4; i++) {
      serviceQ[i] = new LinkedList<>();
    }    
    //TMUberRegistered.loadPreregisteredUsers(users); TMUberRegistered.loadPreregisteredDrivers(drivers);
    
    totalRevenue = 0;
  }
  //NO LONGER USED
  // General string variable used to store an error message when something is invalid 
  // (e.g. user does not exist, invalid address etc.)  
  // The methods below will set this errMsg string and then return false
  /*String errMsg = null;
  public String getErrorMessage() //given
  {
    return errMsg;
  }
  */
  
    //NEW SET USER METHOD
  //This method uses the given array list to build a Map as described above.
  public void setUsers(ArrayList<User> userList) {
    users.clear();    // Clear the existing users map
    // Populate the users map with the given user list
    for (User user : userList) {
        users.put(user.getAccountId(), user);
    }
  }
    //NEW SET USER METHOD
  //This method uses the given array list to set the drivers instance variable.
  public void setDrivers(ArrayList<Driver> drivers) {
    this.drivers = drivers;
  }

  // Given user account id, find user in list of users
  // Return null if not found
  public User getUser(String accountId)//CHANGED
  {
    return users.get(accountId);
  }

  public Driver getDriver(String driverId)//WORKS
  {
    // Fill in the code
    for (Driver i: drivers){
      if(i.getId().equals(driverId)){
        return i;
      }
    }return null;
  }
  
  // Check for duplicate user 
  private boolean userExists(User user) //CHANGED
  {
    return users.containsValue(user); // Check if the map contains User 

  }
  
 // Check for duplicate driver 
 private boolean driverExists(Driver driver) //works
 {
   // Fill in the code
   for (Driver i: drivers){ //loop through drivers
    if(i.equals(driver)){ //compare drivers, check if equals
      return true;
    }
  }return false;
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests
  private boolean existingRequest(TMUberService req) //CHANGED works
  {
    // Fill in the code
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // ^implemented in the TMUberDelivery equals method 
    //Iterate through queue, then through request in the queue
    for(Queue<TMUberService> q : serviceQ){
      for(TMUberService i: q){
        if(i.equals(req)){
          return true;
      }
    }
  }return false;
}

  // Calculate the cost of a ride or of a delivery based on distance 
  private double getDeliveryCost(int distance) //given
  {
    return distance * DELIVERYRATE;
  }

  private double getRideCost(int distance) //given
  {
    return distance * RIDERATE;
  }
  
  // Go through all drivers and see if one is available
  // Choose the first available driver
  // Return null if no available driver
  // Calculate the cost of a ride or of a delivery based on distance 
  private Object getAvailableDriver () 
  {
    // Fill in the code
      for (Driver i : drivers){
          if(i.getStatus().equals(Driver.Status.AVAILABLE)){
            return i;
          }
      }return null;
  }

  // Print Information (printInfo()) about all registered users in the system
  public void listAllUsers() //Given
  { 
    System.out.println();
    int index = 0;
    for (String i: users.keySet()){
      User temp = users.get(i);
      index++;
      System.out.printf("%-2s. ", index);
      temp.printInfo();
      System.out.println();
    }
  }

  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers() //WORKS
  {
    // Fill in the code
    System.out.println();
    for (int i = 0; i < drivers.size(); i++){
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      drivers.get(i).printInfo();
      System.out.println(); 
      System.out.println(); 
    }
  }

  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests() //CHANGED tested
  {
    // Fill in the code
    System.out.println();
    for (int i = 0; i < serviceQ.length; i++) {
      Queue<TMUberService> q = serviceQ[i];
      System.out.printf("ZONE %d\n", i);
      System.out.println("======");
        if (q.isEmpty()) {
          System.out.println("");
        }else{
          int index = 0;
          for (TMUberService service: q) {
            index++;
            System.out.println(index + ".  ------------------------------------------------------------");
            service.printInfo();
            System.out.println();
          }System.out.println();
      }
    }
  }

  // Add a new user to the system 
  public void registerNewUser(String name, String address, double wallet) //WORKS
  {
    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible erros that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!
    if (name == null || name.trim().isEmpty()){ //checking parameters validity 
      throw new InvalidInputException("Invalid User Name");
    }if (address == null || address.trim().isEmpty() || !CityMap.validAddress(address)){
      throw new InvalidInputException("Invalid User Address");
    }if (wallet < 0){
      throw new InvalidInputException("Invalid Money in Wallet");
    }     
    ArrayList<User> userList = new ArrayList<>(users.values()); // Create an ArrayList using the values() method
    String genId = TMUberRegistered.generateUserAccountId(userList);    
    User test = new User(genId,name, address, wallet); 
      if (userExists(test)){ //check users duplicates
        throw new InvalidReqException("User Already Exists in System");
      }users.put(genId, test); //adding user
  }

  // Add a new driver to the system                               //double check/test
  public void registerNewDriver(String name, String carModel, String Licence, String address)
  { 
    // Fill in the code - see the assignment document for error conditions
    // that might apply. See comments above in registerNewUser
    if (name == null || name.trim().isEmpty()){ //check parameters validity
      throw new InvalidInputException("Invalid Driver Name");
    }if (carModel == null || carModel.trim().isEmpty()){ 
      throw new InvalidInputException("Invalid Car Model");
    }if (Licence == null || Licence.trim().isEmpty()){
      throw new InvalidInputException("Invalid Car Licence Plate");
    }                                                  
    String genId =  TMUberRegistered.generateDriverId(drivers);   
    Driver test = new Driver(genId, name, carModel,Licence, address); 
     if (driverExists(test)){ //check driver for duplicates
      throw new InvalidReqException("Driver Already Exists in System");
    } drivers.add(test); //add driver
  }

  // Request a ride. User wallet will be reduced when drop off happens
  public void requestRide(String accountId, String from, String to) //CHNAGED
  { //given comments
    // Check for valid parameters
  	// Use the account id to find the user object in the list of users
    // Get the distance for this ride
    // Note: distance must be > 1 city block!
    // Find an available driver
    // Create the TMUberRide object
    // Check if existing ride request for this user - only one ride request per user at a time!
    // Change driver status
    // Add the ride request to the list of requests
    // Increment the number of rides for this user
 
    //check parameter enusring validity
    if (accountId.trim().isEmpty() || accountId == null || from.isEmpty() || from == null || to.isEmpty() || to == null){
      throw new InvalidInputException("Input invalid");
    }else if(!CityMap.validAddress(to) || !CityMap.validAddress(from)){
      throw new InvalidReqException("Invalid Address");
    }

    User user = getUser(accountId); //user from accountid
    if(user == null){ //user not found   
      throw new UserNotFoundException("User account not found");
    }
    
    int dist = CityMap.getDistance(from, to);
    if(dist <= 1){ //validitating travel distance 
      throw new InvalidReqException("Insufficient Travel Distance " + dist);
    } 
    
    double cost = getRideCost(dist);
    if (user.getWallet() < cost){
      throw new InvalidReqException("Insufficient Funds");
    }
    
    Driver driver = (Driver) getAvailableDriver();
    if (driver == null){ //no avalible driver
      throw new DriverNotFoundException("No Drivers Available");
    }
    
    TMUberRide ride = new TMUberRide(from, to, user, dist, getRideCost(dist));
    if(existingRequest(ride)){ //checks if user has existing request
      throw new InvalidReqException("User Already Has Ride Request");
    }else{
      //NEW Add the ride request to the appropriate queue
      serviceQ[CityMap.getCityZone(from)].add(ride);
      user.addRide(); //update rides for user
    }
  }

  // Request a food delivery. User wallet will be reduced when drop off happens  //CHANGED
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId)
  {
    // See the comments above and use them as a guide
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // Increment the number of deliveries the user has had
   
    if (accountId.trim().isEmpty() || accountId == null || from.isEmpty() || from == null || to.isEmpty() || to == null
          || restaurant.trim().isEmpty() || restaurant == null || foodOrderId.trim().isEmpty() || foodOrderId == null){
     throw new InvalidInputException("Invalid input");
    }
    else if(!CityMap.validAddress(to) || !CityMap.validAddress(from)){
     throw new InvalidReqException("Invalid Address");
    }
    
    User user = getUser(accountId); //user from accountid
    if(user == null){ //user not found   
      throw new UserNotFoundException("User account not found");
    }
    
    int dist = CityMap.getDistance(from, to);
    if(dist <= 1){ //validitating travel distance 
      throw new InvalidReqException("Insufficient Travel Distance " + dist);
    } 

    double cost = getRideCost(dist);
    if (user.getWallet() < cost){
      throw new InvalidReqException("Insufficient Funds");
    }
    
    Driver driver = (Driver) getAvailableDriver();
    if (driver == null){ //no avalible driver
      throw new DriverNotFoundException("No Drivers Available");
    }
    
    TMUberDelivery delivery = new TMUberDelivery(from, to, user, dist, getDeliveryCost(dist),restaurant,foodOrderId);
    if(existingRequest(delivery)){ //checks if user has existing request
      throw new InvalidReqException("User Already Has Delivery Request at Restaurant with this Food Order");
    }

    //NEW Add the ride request to the appropriate queue
    serviceQ[CityMap.getCityZone(from)].add(delivery);
    user.addDelivery();    
  }

  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list
  public void cancelServiceRequest(int request, int zone) //CHANGED maybe NOT NESSCARILY
  {
    Queue<TMUberService> q = serviceQ[zone];
    // Check if valid request #
    if(q.isEmpty()){ // Check if the queue is empty
      throw new InvalidCancelException("Zone is empty");
    }if(request < 1 || request > q.size() || zone < 0 || zone > 4){
      throw new InvalidInputException( "Invalid Input ");
    }else{
       //NEW Find the service request in the appropriate queue
       TMUberService serve = q.poll();        // Remove the service request from the queue

      // decrement number of rides or number of deliveries for this user 
      if(serve.getServiceType().equalsIgnoreCase("RIDE")){
        if(serve.getUser().getRides() <= 0 ){ 
          throw new InvalidCancelException( "Invalid cancellation");
        }serve.getUser().removeRide();
      }else{
        if(serve.getUser().getDeliveries() <= 0 ){
          throw new InvalidCancelException( "Invalid cancellation");
        }serve.getUser().removeDelivery();
      }
    }
  }
  //NEW pickup method para driver id
  public void pickup(String driverId) {
    // Find the driver by ID
    Driver driver = getDriver(driverId);
    if (driver == null) {
        throw new DriverNotFoundException("Driver not found");
    }
    int zone = driver.getZone(); // Get the zone
    Queue<TMUberService> queue = serviceQ[zone]; // Get the service queue for the driver's zone
    
    // Check if there's a service request in the queue
    if (queue.isEmpty()) {
        throw new ServiceRequestNotFoundException("No Service Request in Zone " + zone);
    }TMUberService service = queue.poll();    // Remove the service request from the queue

    driver.setService(service);     // Set the driver's service 
    driver.setAddress(service.getFrom()); // Set the driver's address
    driver.setStatus(Driver.Status.DRIVING); // Set status to DRIVING
  }

  // Drop off a ride or a delivery. This completes a service.
  // parameter request is the index in the serviceRequests array list
  public void dropOff(String driverId) //CHANGED
  {    // See above method for guidance
    // Get the cost for the service and add to total revenues
    // Pay the driver
    // Deduct driver fee from total revenues
    // Change driver status
    // Deduct cost of service from user
    
    Driver driver = getDriver(driverId); //find driver
    if (driver == null) {
        throw new DriverNotFoundException("Driver not found");
    } 
    //check driver status
    if (!driver.getStatus().equals(Driver.Status.DRIVING)) {
        throw new InvalidReqException("Driver is not currently driving");
    }
    TMUberService service = driver.getService(); // Get the service 
    if (service == null) {
        throw new InvalidReqException("No service assigned to this driver");
    }

    double cost = 0.0;  // Get the cost  
    if (service.getServiceType().equalsIgnoreCase("RIDE")) {
        cost = getRideCost(service.getDistance()); //get ride cost
    } else {
        cost = getDeliveryCost(service.getDistance()); //get delivery cost
    }
    double payment = PAYRATE * cost;     //get payment amount
    driver.setWallet(driver.getWallet() + payment);  //pay driver
    totalRevenue += cost - payment;   //update revenue
    service.getUser().payForService(cost);   //user payment

    driver.setStatus(Driver.Status.AVAILABLE); //set status
    driver.setService(null); //set service
    driver.setAddress(service.getTo()); // set address (driver class should auto update zone)
    driver.setZone(CityMap.getCityZone(service.getTo())); //incase

  }

  public void driveTo(String driverId, String address) {
    // Find the driver by ID
    Driver driver = getDriver(driverId); //find driver
    if (driver == null) {
        throw new DriverNotFoundException("Driver not found");
    } 
    //check driver avilable
    if (!driver.getStatus().equals(Driver.Status.AVAILABLE)) {
        throw new InvalidReqException("Driver is not available");
    }
    // Validate address
    if (!CityMap.validAddress(address)) {
        throw new InvalidInputException("Invalid address");
    }
    driver.setAddress(address); // set address 
    driver.setZone(CityMap.getCityZone(address));
  }

  // Sort users by name
  // Then list all users
  public void sortByUserName()   //CHNAGED sort dosent change orginal
  {
    ArrayList<User> userList = new ArrayList<>(users.values()); // Create an ArrayList 
    Collections.sort(userList, new NameComparator());
    int index = 0;
    for(User i: userList){
      index ++;
      System.out.printf("%-2s. ", index);
      i.printInfo(); 
      System.out.println(); 
    }
  }
  // Helper class for method sortByUserName
  private class NameComparator implements Comparator<User>
  {//using compareto method 
     public int compare(User a, User b)
     {
       return a.getName().compareTo(b.getName());
    }
  }

  // Sort users by number amount in wallet
  // Then list all users
  public void sortByWallet()   //CHANGED
  {
    ArrayList<User> userList = new ArrayList<>(users.values()); // Create an ArrayList 
    Collections.sort(userList, new UserWalletComparator());
    int index = 0;
    for(User i: userList){
      index ++;
      System.out.printf("%-2s. ", index);
      i.printInfo(); 
      System.out.println(); 
    }
  }
  // Helper class for used by sortByWallet
  private class UserWalletComparator implements Comparator<User>
  {
    public int compare(User a, User b){
      return Double.compare(a.getWallet(), b.getWallet());
    }
  }

  // NOTNEEDED Sort trips (rides or deliveries) by distance 
  // Then list all current service requests
  // public void sortByDistance() //Works
  // {
  //   Collections.sort(serviceRequests);
  //   listAllServiceRequests();
  // }
  //old BONUS not requestXL removed
 
}

// Outside of tmubersystemmanager class Custom exceptions
class InvalidInputException extends RuntimeException {
  public InvalidInputException(String message) {
      super(message);
  }
}
class DriverNotFoundException extends RuntimeException {
  public DriverNotFoundException(String message) {
      super(message);
  }
}
class ServiceRequestNotFoundException extends RuntimeException {
  public ServiceRequestNotFoundException(String message) {
      super(message);
  }
}
class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
      super(message);
  }
}
class InvalidReqException extends RuntimeException {
    public InvalidReqException(String message) {
        super(message);
    }
}
class InvalidCancelException extends RuntimeException {
  public InvalidCancelException(String message) {
      super(message);
  }
}
