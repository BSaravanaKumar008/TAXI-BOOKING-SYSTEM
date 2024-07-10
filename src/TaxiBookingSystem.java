import java.util.ArrayList;
import java.util.Scanner;

public class TaxiBookingSystem {
    private static ArrayList<Taxi> taxis = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        initializeTaxis();
        initializeCustomers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n...................Taxi_Booking..............................................\n");
            System.out.println("1. Booking  2. Details  3. Exit");
            System.out.print("Enter Choice: ");
            int userChoice = scanner.nextInt();

            if (userChoice == 1) {
                handleBooking(scanner);
            } else if (userChoice == 2) {
                displayAllDetails();
            } else {
                break;
            }
        }

        scanner.close();
    }

    private static void initializeTaxis() {
        for (int i = 1; i <= 4; i++) {
            taxis.add(new Taxi(i));
        }
    }

    private static void initializeCustomers() {
        customers.add(new Customer(1, "Alice"));
        customers.add(new Customer(2, "Bob"));
        customers.add(new Customer(3, "Charlie"));
        customers.add(new Customer(4, "David"));
    }

    private static void handleBooking(Scanner scanner) {
        System.out.print("\nCustomer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Pickup Point: ");
        String pickupPoint = scanner.next();
        System.out.print("Drop Point: ");
        String dropPoint = scanner.next();
        System.out.print("Pickup Time (hours): ");
        int pickupTime = scanner.nextInt();

        Booking newBooking = createBooking(customerId, pickupPoint, dropPoint, pickupTime);

        Taxi allocatedTaxi = allocateTaxi(newBooking);
        if (allocatedTaxi != null) {
            newBooking = new Booking(allocatedTaxi.getTaxiId(), customerId, pickupPoint, dropPoint, newBooking.getStartTime(), newBooking.getEndTime(), newBooking.getAmount());
            allocatedTaxi.addBooking(newBooking);
            allocatedTaxi.incrementBookingCount();
            System.out.println("\nTaxi can be allocated.");
            System.out.println("Taxi-" + allocatedTaxi.getTaxiId() + " is allocated.\n");
        } else {
            System.out.println("All taxis are busy. Please try again later.");
        }
    }

    private static Booking createBooking(int customerId, String from, String to, int startTime) {
        int endTime = startTime + Math.abs(from.charAt(0) - to.charAt(0));
        if (endTime > 24) {
            endTime -= 24;
        }
        int amount = 100 + ((Math.abs(from.charAt(0) - to.charAt(0)) * 15) - 5) * 10;
        return new Booking(0, customerId, from, to, startTime, endTime, amount);
    }

    private static Taxi allocateTaxi(Booking newBooking) {
        Taxi allocatedTaxi = null;
        int minEarnings = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            if (taxi.isAvailable(newBooking.getStartTime()) && taxi.getEarnings() < minEarnings) {
                allocatedTaxi = taxi;
                minEarnings = taxi.getEarnings();
            }
        }

        return allocatedTaxi;
    }

    private static void displayAllDetails() {
        for (Taxi taxi : taxis) {
            taxi.displayDetails();
        }
    }
}
