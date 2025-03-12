import java.util.Scanner; // Import Scanner for user input
import java.util.InputMismatchException; // Add this import for InputMismatchException


// Interface for all vehicles
public interface Vehicle {
    String make(); // Returns the make of the vehicle
    String model(); // Returns the model of the vehicle
    int yearOfManufacture(); // Returns the year of manufacture
}

// Interface specific to Cars
public interface CarVehicle {
    void setDoors(int doors); // Sets the number of doors
    int retrieveNumDoors(); // Retrieves the number of doors
    void setFuelType(String fuelType); // Sets the fuel type
    String getFuelType(); // Retrieves the fuel type
}

// Interface specific to Motorcycles
public interface MotorVehicle {
    void setWheels(int wheels); // Sets the number of wheels
    int retrieveNumWheels(); // Retrieves the number of wheels
    void setMotorcycleType(String type); // Sets the motorcycle type
    String getMotorcycleType(); // Retrieves the motorcycle type
}

// Interface specific to Trucks
public interface TruckVehicle {
    void setCargoCapacity(float capacity); // Sets the cargo capacity
    float retrieveCargoCapacity(); // Retrieves the cargo capacity
    void setTransmissionType(String type); // Sets the transmission type
    String getTransmissionType(); // Retrieves the transmission type
}

// Car Class implementing Vehicle and CarVehicle interfaces
class Car implements Vehicle, CarVehicle {

    // Private attributes for encapsulation
    private String make, model, fuelType;
    private int doors, year;

    // Constructor to initialize Car attributes
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Implementing Vehicle interface methods
    @Override
    public String make() {
        return make;
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public int yearOfManufacture() {
        return year;
    }

    // Implementing CarVehicle interface methods
    @Override
    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public int retrieveNumDoors() {
        return doors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }
}

// Motorcycle Class implementing Vehicle and MotorVehicle interfaces
class Motorcycle implements Vehicle, MotorVehicle {

    // Private attributes for encapsulation
    private String make, model, motorcycleType;
    private int wheels, year;

    // Constructor to initialize Motorcycle attributes
    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Implementing Vehicle interface methods
    @Override
    public String make() {
        return make;
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public int yearOfManufacture() {
        return year;
    }

    // Implementing MotorVehicle interface methods
    @Override
    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    @Override
    public int retrieveNumWheels() {
        return wheels;
    }

    @Override
    public void setMotorcycleType(String type) {
        this.motorcycleType = type;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }
}

// Truck Class implementing Vehicle and TruckVehicle interfaces
class Truck implements Vehicle, TruckVehicle {

    // Private attributes for encapsulation
    private String make, model, transmissionType;
    private float cargoCapacity;
    private int year;

    // Constructor to initialize Truck attributes
    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Implementing Vehicle interface methods
    @Override
    public String make() {
        return make;
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public int yearOfManufacture() {
        return year;
    }

    // Implementing TruckVehicle interface methods
    @Override
    public void setCargoCapacity(float capacity) {
        this.cargoCapacity = capacity;
    }

    @Override
    public float retrieveCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void setTransmissionType(String type) {
        this.transmissionType = type;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }
}

// Main class with improved user interaction and error handling
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter Car details: Make, Model, Year");
            Car car = new Car(scanner.next(), scanner.next(), scanner.nextInt());
            System.out.println("Enter number of doors:");
            car.setDoors(scanner.nextInt());
            System.out.println("Enter fuel type:");
            car.setFuelType(scanner.next());

            System.out.println("Enter Motorcycle details: Make, Model, Year");
            Motorcycle motorcycle = new Motorcycle(scanner.next(), scanner.next(), scanner.nextInt());
            System.out.println("Enter number of wheels:");
            motorcycle.setWheels(scanner.nextInt());
            System.out.println("Enter motorcycle type:");
            motorcycle.setMotorcycleType(scanner.next());

            System.out.println("Enter Truck details: Make, Model, Year");
            Truck truck = new Truck(scanner.next(), scanner.next(), scanner.nextInt());
            System.out.println("Enter cargo capacity in tons:");
            truck.setCargoCapacity(scanner.nextFloat());
            System.out.println("Enter transmission type:");
            truck.setTransmissionType(scanner.next());

            // Display Vehicle Details
            System.out.println("\nCar Details: " + car.make() + " " + car.model() + " (" + car.yearOfManufacture() + ") | Doors: " + car.retrieveNumDoors() + " | Fuel: " + car.getFuelType());
            System.out.println("Motorcycle Details: " + motorcycle.make() + " " + motorcycle.model() + " (" + motorcycle.yearOfManufacture() + ") | Wheels: " + motorcycle.retrieveNumWheels() + " | Type: " + motorcycle.getMotorcycleType());
            System.out.println("Truck Details: " + truck.make() + " " + truck.model() + " (" + truck.yearOfManufacture() + ") | Cargo: " + truck.retrieveCargoCapacity() + " tons | Transmission: " + truck.getTransmissionType());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter the correct data type.");
        } finally {
            scanner.close();
        }
    }
}
