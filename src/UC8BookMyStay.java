import java.util.*;

// Main App
public class UC8BookMyStay {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        Reservation r1 = new Reservation("R101", "Alice", "Deluxe Room");
        Reservation r2 = new Reservation("R102", "Bob", "Suite");
        Reservation r3 = new Reservation("R103", "Charlie", "Standard Room");

        // Add to history
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin views all bookings
        System.out.println("=== Booking History ===");
        reportService.displayAllBookings(history);

        // Admin views summary
        System.out.println("\n=== Booking Summary ===");
        reportService.generateSummary(history);
    }
}

// Reservation Class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking History Class (stores data)
class BookingHistory {

    private List<Reservation> reservations = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Retrieve all bookings
    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

// Reporting Class (only reads data)
class BookingReportService {

    // Display all bookings
    public void displayAllBookings(BookingHistory history) {
        List<Reservation> list = history.getAllReservations();

        for (Reservation r : list) {
            System.out.println(
                    r.getReservationId() + " | " +
                            r.getGuestName() + " | " +
                            r.getRoomType()
            );
        }
    }

    // Generate summary report
    public void generateSummary(BookingHistory history) {
        List<Reservation> list = history.getAllReservations();

        System.out.println("Total Bookings: " + list.size());

        // Count room types
        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : list) {
            String type = r.getRoomType();
            roomCount.put(type, roomCount.getOrDefault(type, 0) + 1);
        }

        System.out.println("Room Type Distribution:");
        for (String type : roomCount.keySet()) {
            System.out.println(type + " : " + roomCount.get(type));
        }
    }
}