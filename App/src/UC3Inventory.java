import java.util.Map;

/**
 * MAIN CLASS UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management
 *
 * @version 3.1
 */
public class UseCase3Inventory {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Create centralized inventory
        RoomInventory inventory = new RoomInventory();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Display Single Room
        System.out.println("Single Room:");
        single.displayDetails();
        System.out.println("Available Rooms: " + availability.get("SingleRoom"));

        // Display Double Room
        System.out.println("Double Room:");
        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + availability.get("DoubleRoom"));

        // Display Suite Room
        System.out.println("Suite Room:");
        suite.displayDetails();
        System.out.println("Available Rooms: " + availability.get("SuiteRoom"));
    }
}