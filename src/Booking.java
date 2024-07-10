public class Booking {
    private static int idCounter = 1;
    private int bookingId;
    private int taxiId;
    private int customerId;
    private String from;
    private String to;
    private int startTime;
    private int endTime;
    private int amount;

    public Booking(int taxiId, int customerId, String from, String to, int startTime, int endTime, int amount) {
        this.bookingId = idCounter++;
        this.taxiId = taxiId;
        this.customerId = customerId;
        this.from = from;
        this.to = to;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return bookingId + " " + customerId + " " + from + " " + to + " " + startTime + " " + endTime + " " + amount;
    }
}
