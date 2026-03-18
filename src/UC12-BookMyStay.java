import java.io.*;
import java.util.*;

// Main App
public class UC12BookMyStay {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Try loading previous state
        system.loadState();

        try {
            system.bookRoom("R101", "Alice", "Deluxe");
            system.bookRoom("R102", "Bob", "Suite");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Save before shutdown
        system.saveState();

        // Simulate restart
        System.out.println("\n--- Restarting System ---\n");

        BookingSystem newSystem = new BookingSystem();
        newSystem.loadState(); // restore

        newSystem.displayState();
    }
}

// Booking System (Serializable)
class BookingSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;
    private Map<String, String> bookings;

    private final String FILE_NAME = "booking_data.ser";

    public BookingSystem() {
        inventory = new HashMap<>();
        bookings = new HashMap<>();

        inventory.put("Deluxe", 2);
        inventory.put("Suite", 2);
        inventory.put("Standard", 3);
    }

    // Booking logic
    public void bookRoom(String reservationId, String guestName, String roomType)
            throws Exception {

        if (!inventory.containsKey(roomType)) {
            throw new Exception("Invalid room type: " + roomType);
        }

        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new Exception("No rooms available for: " + roomType);
        }

        inventory.put(roomType, available - 1);
        bookings.put(reservationId, roomType);

        System.out.println("Booked: " + reservationId + " | " + guestName + " | " + roomType);
    }

    // Save state (Serialization)
    public void saveState() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            oos.writeObject(bookings);

            System.out.println("System state saved.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state (Deserialization)
    public void loadState() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            inventory = (Map<String, Integer>) ois.readObject();
            bookings = (Map<String, String>) ois.readObject();

            System.out.println("System state restored.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading state: " + e.getMessage());
        }
    }

    // Display current state
    public void displayState() {
        System.out.println("Current Inventory: " + inventory);
        System.out.println("Current Bookings: " + bookings);
    }
}