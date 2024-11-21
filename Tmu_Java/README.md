# TMUberService System

A Java-based simulation of a ride-sharing and delivery service similar to Uber. This system includes user and driver management, service request handling (Rides and Deliveries), and user interface functionality.

## Project Structure

### Files:

1. **CityMap.java**
   - Handles city zones and manages the mapping of addresses to zones.
   - Used to determine a driver's current zone based on their address.

2. **Driver.java**
   - Represents a driver with attributes like ID, name, car model, license plate, and wallet.
   - Tracks the driver’s current service, status (AVAILABLE or DRIVING), and zone.
   - Includes methods for paying and printing driver info.

3. **TMUberDelivery.java**
   - Extends the `TMUberService` class and adds delivery-specific details (restaurant, food order ID).
   - Provides functionality to check if two deliveries are equal and prints delivery information.

4. **TMUberRegistered.java**
   - Manages the registration of users and drivers.
   - Includes methods for generating unique IDs for users and drivers and loading pre-registered users and drivers from text files (`users.txt` and `drivers.txt`).

5. **TMUberRide.java**
   - Extends the `TMUberService` class and adds ride-specific details (number of passengers, XL ride request).
   - Provides methods to get the service type and set/get ride details.

6. **TMUberService.java**
   - An abstract base class for all service types (Rides and Deliveries).
   - Handles common attributes like from, to, user, distance, and cost.
   - Implements the `Comparable` interface for sorting services based on distance and defines methods for equality checks and printing service details.

7. **TMUberSystemManager.java**
   - The main manager of the system. It handles registering users, drivers, and services.
   - Manages the state of services, assigns drivers to rides or deliveries, and interacts with the user interface.

8. **TMUberUI.java**
   - A user interface class for interacting with the system.
   - Allows users to request rides or deliveries and view available drivers.

9. **User.java**
   - Represents a user with attributes like account ID, name, address, and wallet balance.
   - Includes methods for managing user info and paying for services.

10. **drivers.txt**
    - A sample text file containing pre-registered driver information (name, car model, license plate, address).

11. **users.txt**
    - A sample text file containing pre-registered user information (name, address, wallet balance).

## Features

- **User and Driver Management**: Register users and drivers, assign them unique IDs, and load from text files.
- **Service Requests**: Handle Ride and Delivery requests, including cost and distance calculations.
- **Driver Assignment**: Assign available drivers to service requests based on zones and proximity.
- **Comparison and Sorting**: Sort and compare service requests based on distance.
- **UI Interaction**: A simple command-line interface for users to interact with the system and request services.

## Technologies
- Java (Object-Oriented Programming)
- File handling for user and driver registration
- Basic user interface (CLI-based)
- Implements `Comparable` interface for sorting services

## Setup

1. **Prerequisites**: Ensure you have Java installed on your system.
2. **Compile the Java files**:
   ```bash
   javac *.java
