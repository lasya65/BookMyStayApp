import java.util.*;

// Main App
public class UC10BookMyStay {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        try {
            // Book rooms
            service.bookRoom("R101", "Alice", "Deluxe");
            service.bookRoom("R102", "Bob", "Suite");

            // Cancel booking
            service.cancelBooking("R101");

            // Try cancelling again (should fail)
            service.cancelBooking("R101");

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Booking Service
class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, String> bookings = new HashMap<>(); // reservationId -> roomType
    private Stack<String> rollbackStack = new Stack<>(); // track released rooms

    public BookingService() {
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 2);
        inventory.put("Standard", 3);
    }

    // Booking logic
    public void bookRoom(String reservationId, String guestName, String roomType)
            throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = inventory.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }

        inventory.put(roomType, available - 1);
        bookings.put(reservationId, roomType);

        System.out.println("Booking confirmed: " +
                reservationId + " | " + guestName + " | " + roomType);
    }

    // Cancellation logic
    public void cancelBooking(String reservationId)
            throws InvalidBookingException {

        if (!bookings.containsKey(reservationId)) {
            throw new InvalidBookingException("Booking not found or already cancelled: " + reservationId);
        }

        String roomType = bookings.get(reservationId);

        // Rollback: increase inventory
        inventory.put(roomType, inventory.get(roomType) + 1);

        // Push to stack (tracking rollback)
        rollbackStack.push(reservationId);

        // Remove booking
        bookings.remove(reservationId);

        System.out.println("Booking cancelled: " + reservationId);
    }
}

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}