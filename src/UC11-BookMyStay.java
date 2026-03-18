import java.util.*;

// Main App
public class UC11BookMyStay {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        // Create multiple booking threads
        Thread t1 = new Thread(new BookingTask(service, "R101", "Alice", "Deluxe"));
        Thread t2 = new Thread(new BookingTask(service, "R102", "Bob", "Deluxe"));
        Thread t3 = new Thread(new BookingTask(service, "R103", "Charlie", "Deluxe"));

        // Start threads (simultaneous requests)
        t1.start();
        t2.start();
        t3.start();
    }
}

// Booking Task (Runnable)
class BookingTask implements Runnable {

    private BookingService service;
    private String reservationId;
    private String guestName;
    private String roomType;

    public BookingTask(BookingService service, String reservationId, String guestName, String roomType) {
        this.service = service;
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        service.bookRoom(reservationId, guestName, roomType);
    }
}

// Booking Service (Thread-safe)
class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingService() {
        inventory.put("Deluxe", 2); // Only 2 rooms
    }

    // Critical Section
    public synchronized void bookRoom(String reservationId, String guestName, String roomType) {

        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type: " + roomType);
            return;
        }

        int available = inventory.get(roomType);

        if (available > 0) {
            System.out.println(Thread.currentThread().getName() +
                    " booking for " + reservationId);

            // Simulate delay (to expose race condition if no sync)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            inventory.put(roomType, available - 1);

            System.out.println("Booking confirmed: " +
                    reservationId + " | " + guestName + " | " + roomType);
        } else {
            System.out.println("No rooms available for: " + roomType +
                    " (" + reservationId + ")");
        }
    }
}