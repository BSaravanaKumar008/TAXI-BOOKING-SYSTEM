import java.util.ArrayList;

public class Taxi {
    private int taxiId;
    private String stayPoint;
    private int earnings;
    private int bookingCount;
    private ArrayList<Booking> bookingHistory;

    public Taxi(int taxiId) {
        this.taxiId = taxiId;
        this.stayPoint = "A";
        this.earnings = 0;
        this.bookingCount = 0;
        this.bookingHistory = new ArrayList<>();
    }

    public int getTaxiId() {
        return taxiId;
    }

    public String getStayPoint() {
        return stayPoint;
    }

    public void setStayPoint(String stayPoint) {
        this.stayPoint = stayPoint;
    }

    public int getEarnings() {
        return earnings;
    }

    public void addEarnings(int amount) {
        this.earnings += amount;
    }

    public int getBookingCount() {
        return bookingCount;
    }

    public void incrementBookingCount() {
        this.bookingCount++;
    }

    public void addBooking(Booking booking) {
        this.bookingHistory.add(booking);
        this.earnings += booking.getAmount();
    }

    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public boolean isAvailable(int currentTime) {
        if (bookingHistory.isEmpty()) {
            return true;
        }
        Booking lastBooking = bookingHistory.get(bookingHistory.size() - 1);
        return lastBooking.getEndTime() <= currentTime;
    }

    public void displayDetails() {
        System.out.println("\nTaxi-" + taxiId + " Total Earnings: Rs. " + earnings + ", Stay Point: " + stayPoint);
        System.out.println("BookingID CustomerID From To StartTime EndTime Amount");
        for (Booking booking : bookingHistory) {
            System.out.println(booking);
        }
    }
}
