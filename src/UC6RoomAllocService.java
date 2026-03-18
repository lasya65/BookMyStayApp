import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UC6RoomAllocService {

    // Queue for booking requests (FIFO)
    private static Queue<BookingRequest> bookingQueue = new LinkedList<>();

    // Inventory of room types
    private static Map<String, Integer> inventory = new HashMap<>();

    // Map room type -> allocated room IDs
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global set to track all allocated room IDs
    private static Set<String> allocatedRoomIds = new HashSet<>();

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("DELUXE", 2);
        inventory.put("STANDARD", 2);
        inventory.put("SUITE", 1);

        // Initialize allocation map
        allocatedRooms.put("DELUXE", new HashSet<>());
        allocatedRooms.put("STANDARD", new HashSet<>());
        allocatedRooms.put("SUITE", new HashSet<>());

        // Add booking requests to queue
        bookingQueue.add(new BookingRequest("Abhi", "DELUXE"));
        bookingQueue.add(new BookingRequest("Subha", "STANDARD"));
        bookingQueue.add(new BookingRequest("Vanmathi", "DELUXE"));
       
        processBookings();
    }

    private static void processBookings() {

        while (!bookingQueue.isEmpty()) {

            BookingRequest request = bookingQueue.poll(); // FIFO

            String roomType = request.roomType;

            System.out.println("\nProcessing booking for " + request.guestName + " (" + roomType + ")");

            // Check availability
            if (!inventory.containsKey(roomType) || inventory.get(roomType) == 0) {
                System.out.println("Booking Failed: No rooms available for " + roomType);
                continue;
            }

            // Generate unique room ID
            String roomId = generateRoomId(roomType);

            // Allocate room
            allocatedRoomIds.add(roomId);
            allocatedRooms.get(roomType).add(roomId);

            // Update inventory immediately
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booking Confirmed!");
            System.out.println("Guest: " + request.guestName);
            System.out.println("Room Type: " + roomType);
            System.out.println("Room ID: " + roomId);
        }

        printSummary();
    }

    private static String generateRoomId(String roomType) {

        String roomId;

        do {
            int number = (int) (Math.random() * 1000);
            roomId = roomType.substring(0, 3).toUpperCase() + number;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }

    private static void printSummary() {

        System.out.println("\n---- Allocation Summary ----");

        for (String roomType : allocatedRooms.keySet()) {
            System.out.println(roomType + " Rooms Allocated: " + allocatedRooms.get(roomType));
        }

        System.out.println("\nRemaining Inventory:");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + ": " + inventory.get(roomType));
        }
    }
}