package com.pluralsight.UI;

import com.pluralsight.*;
import com.pluralsight.FileReaders.ContractFileManager;
import com.pluralsight.FileReaders.DealershipFileManager;
import com.pluralsight.Models.*;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    Scanner scanner = new Scanner(System.in);
    Scanner input;

    public UserInterface() {

    }

    public void display() {
        init();
        displayMenu();
    }

    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealership = dealershipFileManager.getDealership();
    }

    private void updateDealership() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);
    }

    private void updateContract(Contract contract) {
        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(contract);
    }

    public void findPriceRange() {
        try {
            System.out.println("Please enter the minimum price: ");
            double min = scanner.nextDouble();

            System.out.println("Please enter the maximum price: ");
            double max = scanner.nextDouble();
            scanner.nextLine();

            List<Vehicle> vehicles = dealership.getVehiclesPrice(min, max);
            displayVehicles(vehicles);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid entry. Please try again.");
        }
    }

    public void findMakeModel() {
        try {
            System.out.println("Please enter the MAKE of the car you're searching for: ");
            String make = scanner.nextLine();

            System.out.println("Please enter the MODEL of the car you're searching for: ");
            String model = scanner.nextLine();

            List<Vehicle> vehicleMakeModel = dealership.getVehiclesMakeModel(make, model);
            displayVehicles(vehicleMakeModel);
        } catch (Exception e) {
            System.out.println("Invalid entry. Please try again.");
        }
    }

    public void findYearRange() {
        try {
            System.out.println("Please enter the minimum year: ");
            int min = scanner.nextInt();

            System.out.println("Please enter the latest year of desired car: ");
            int max = scanner.nextInt();
            scanner.nextLine();

            List<Vehicle> vehicleYear = dealership.getVehiclesYear(min, max);
            displayVehicles(vehicleYear);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid entry. Please try again.");
        }
    }

    public void findVehicleColor() {
        try {
            System.out.println("Please enter the desired color: ");
            String color = scanner.nextLine();

            List<Vehicle> vehicleColor = dealership.getVehiclesColor(color);
            displayVehicles(vehicleColor);
        } catch (Exception e) {
            System.out.println("Invalid entry. Please try again.");
        }

    }

    public void findMileRange() {
        try {
            System.out.println("Please enter the minimum mileage: ");
            int min = scanner.nextInt();

            System.out.println("Please enter the maximum mileage: ");
            int max = scanner.nextInt();
            scanner.nextLine();

            List<Vehicle> vehicleMileage = dealership.getVehiclesMileage(min, max);
            displayVehicles(vehicleMileage);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid entry. Please try again.");

        }
    }

    public void findVehicleType() {
        try {
            System.out.println("Please enter the vehicle type: ");
            String type = scanner.nextLine();

            List<Vehicle> vehicleType = dealership.getVehiclesType(type);
            displayVehicles(vehicleType);
        } catch (Exception e) {
            System.out.println("Invalid entry. Please try again.");
        }

    }


    public void addVehicle() {
        try {
            System.out.println("Please enter the Vehicle VIN: ");
            int vin = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the year of the vehicle: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the make: ");
            String make = scanner.nextLine();

            System.out.println("Please enter the model: ");
            String model = scanner.nextLine();

            System.out.println("Please enter the vehicle type: ");
            String vehicleType = scanner.nextLine();

            System.out.println("Please enter the color: ");
            String color = scanner.nextLine();

            System.out.println("Please enter the number of miles on the odometer: ");
            int odometer = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the price: ");
            double price = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.addVehicle(vehicle);

            System.out.println("New vehicle successfully added!");


        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid entry. Please try again.");
        }


    }

    public void removeVehicle() {
        try {
            System.out.println("Please enter the Vehicle VIN: ");
            int vin = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the year of the vehicle: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the make: ");
            String make = scanner.nextLine();

            System.out.println("Please enter the model: ");
            String model = scanner.nextLine();

            System.out.println("Please enter the vehicle type: ");
            String vehicleType = scanner.nextLine();

            System.out.println("Please enter the color: ");
            String color = scanner.nextLine();

            System.out.println("Please enter the number of miles on the odometer: ");
            int odometer = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the price: ");
            double price = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.removeVehicle(vehicle);

            System.out.println("Vehicle has been successfully removed.");
        } catch (Exception exception) {
            scanner.nextLine();
            System.out.println("Invalid entry. Please try again.");
        }
    }

    public void findAllVehicle() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void displayVehicles(List<Vehicle> listOfVehicles) {
        for (int i = 0; i < listOfVehicles.size(); i++) {
            System.out.println(listOfVehicles.get(i));
        }
    }

    public void buyOrLeaseReq() {
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("[B] - Buy a vehicle");
        System.out.println("[L] - Lease a vehicle");

        String buyOrLeaseOptions = input.nextLine();
        while (!buyOrLeaseOptions.equalsIgnoreCase("B") || !buyOrLeaseOptions.equalsIgnoreCase("l")) {
            if (buyOrLeaseOptions.equalsIgnoreCase("b")) {
                findAllVehicle();
                System.out.println("Please enter the date:");
                String date = input.nextLine();
                System.out.println("Enter your name:");
                String name = input.nextLine();
                System.out.println("Enter your email address:");
                String address = input.nextLine();
                System.out.println("Would you like to Finance the vehicle? ");
                System.out.println("Enter 'Y' for yes OR 'N' for NO: ");
                boolean finance = input.nextLine().equalsIgnoreCase("Y");
                System.out.println("Enter the VIN:");
                int vin = Integer.parseInt(input.nextLine());
                Vehicle vehicle = null;
                for (Vehicle vehicle1 : dealership.getAllVehicles()) {
                    if (vehicle1.getVin() == vin) {
                        vehicle = vehicle1;
                    }
                }
                dealership.removeVehicle(vehicle);
                SalesContract salesContract = new SalesContract(date, name, address, vehicle, finance);
                updateContract(salesContract);
                updateDealership();
                System.out.println("This Vehicle has been successfully bought:" + vehicle + ".");
                break;
            } else if (buyOrLeaseOptions.equalsIgnoreCase("l")) {
                findAllVehicle();
                System.out.println("Enter the date of the contract:");
                String date = input.nextLine();
                System.out.println("Enter your name:");
                String name = input.nextLine();
                System.out.println("Enter your email address:");
                String address = input.nextLine();
                System.out.println("Enter the vin of the vehicle you want to buy:");
                int vin = Integer.parseInt(input.next());
                Vehicle vehicle = null;
                for (Vehicle vehicle1 : dealership.getAllVehicles()) {
                    if (vehicle1.getVin() == vin) {
                        vehicle = vehicle1;

                    }
                }
                dealership.removeVehicle(vehicle);
                LeaseContract salesContract = new LeaseContract(date, name, address, vehicle);
                updateContract(salesContract);
                updateDealership();
                System.out.println("This Vehicle has been successfully leased:" + vehicle + ".");
                break;
            }
        }
    }

    public void displayMenu() {

        boolean running = true;
        while (running) {
            System.out.println(Colors.BRIGHT_BLUE + "\n---------Welcome To The Best Dealership!---------\n" + Colors.TEXT_RESET);
            System.out.println(Colors.BRIGHT_WHITE + """
                    Main Menu:
                                      
                    [0] - Buy / Lease a vehicle
                    [1] - Find Vehicles within a price range
                    [2] - Find vehicles by make / model
                    [3] - Find vehicles by year range
                    [4] - Find vehicles by color
                    [5] - Find vehicles by mileage range
                    [6] - Find vehicles by type (car, truck, SUV, van, etc.)
                    [7] - List ALL vehicles
                    [8] - Add a vehicle
                    [9] - Remove a vehicle                
                    [99] - Quit
                    """ + Colors.TEXT_RESET);
            System.out.print("Please enter the number for your desired action here: ");
            String input = scanner.nextLine();
            System.out.println();

            switch (input) {
                case "0":
                    buyOrLeaseReq();
                    break;
                case "1":
                    findPriceRange();
                    break;
                case "2":
                    findMakeModel();
                    break;
                case "3":
                    findYearRange();
                    break;
                case "4":
                    findVehicleColor();
                    break;
                case "5":
                    findMileRange();
                    break;
                case "6":
                    findVehicleType();
                    break;
                case "7":
                    findAllVehicle();
                    break;
                case "8":
                    addVehicle();
                    break;
                case "9":
                    removeVehicle();
                    break;
                case "99":
                    System.out.println("Thanks for trying the app!");
                    running = false;
                default:
                    break;
            }
        }
        scanner.close();
    }

    @Override
    public String toString()
    {
        return "UserInterface{" +
                "dealership=" + dealership +
                '}';
    }