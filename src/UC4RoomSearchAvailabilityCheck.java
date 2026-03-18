import java.util.HashMap;
import java.util.Map;

/*
 * UC-4: Room Search & Availability Check
 * Demonstrates read-only access to inventory and filtering of available rooms
 */

// --------------------- ABSTRACT ROOM CLASS ---------------------
abstract class Room {

    protected int beds;
    protected int size;
    protected double pricePerNight;

    public Room(int beds, int size, double pricePerNight) {
        this.beds = beds;
        this.size = size;
        this.pricePerNight = pricePerNight;
    }

    public String getDetails(String roomType, int available) {
        return roomType + " Room:\n" +
                "Beds: " + beds + "\n" +
                "Size: " + size + " sqft\n" +
                "Price per night: " + pricePerNight + "\n" +
                "Available: " + available + "\n";
    }
}

// --------------------- ROOM TYPES ---------------------
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 150, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 250, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 350, 5000.0);
    }
}

// --------------------- INVENTORY SERVICE ---------------------
class RoomInventoryService {

    private Map<String, Integer> roomAvailability;

    public RoomInventoryService() {
        roomAvailability = new HashMap<>();

        // Initial inventory values
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    // Read-only access
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

// --------------------- MAIN SEARCH CLASS ---------------------
public class UC4RoomSearchAvailabilityCheck {

    public static void executeAvailabilityCheck() {

        RoomInventoryService inventory = new RoomInventoryService();

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        if (availability.get("Single") > 0) {
            System.out.println(singleRoom.getDetails("Single", availability.get("Single")));
        }

        if (availability.get("Double") > 0) {
            System.out.println(doubleRoom.getDetails("Double", availability.get("Double")));
        }

        if (availability.get("Suite") > 0) {
            System.out.println(suiteRoom.getDetails("Suite", availability.get("Suite")));
        }
    }

    public static void main(String[] args) {
        executeAvailabilityCheck();
    }
}