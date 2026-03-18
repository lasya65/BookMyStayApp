import java.util.*;

// Main App
public class UC9BookMyStay {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        try {
            // Valid booking
            service.bookRoom("R101", "Alice", "Deluxe");

            // Invalid room type
            service.bookRoom("R102", "Bob", "Luxury");

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            // Exceeding availability
            service.bookRoom("R103", "Charlie", "Suite");
            service.bookRoom("R104", "David", "Suite");
            service.bookRoom("R105", "Eve", "Suite"); // should fail

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Booking Service (handles validation + booking)
class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingService() {
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 2);
        inventory.put("Standard", 3);
    }

    public void bookRoom(String reservationId, String guestName, String roomType)
            throws InvalidBookingException {

        // Validate room type
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Validate availability
        int available = inventory.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }

        // If valid → proceed
        inventory.put(roomType, available - 1);

        System.out.println("Booking confirmed: " +
                reservationId + " | " + guestName + " | " + roomType);
    }
}

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}