import java.util.HashMap;
import java.util.Map;

/**
 * CLASS RoomInventory
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Acts as the single source of truth for room availability.
 * Room characteristics are obtained from Room objects.
 *
 * @version 3.1
 */
public class RoomInventory {

    // Stores available room count for each room type
    private Map<String, Integer> roomAvailability;

    // Constructor initializes the inventory
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    // Initialize inventory with default counts
    private void initializeInventory() {
        roomAvailability.put("SingleRoom", 5);
        roomAvailability.put("DoubleRoom", 3);
        roomAvailability.put("SuiteRoom", 2);
    }

    // Returns the current availability map
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    // Updates availability for a specific room type
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}