import java.util.*;

// Main App
public class UC7AddOnServiceSelection {

    public static void main(String[] args) {

        // Sample reservation IDs
        String reservation1 = "R101";
        String reservation2 = "R102";

        // Create services
        AddOnService wifi = new AddOnService("WiFi", 200);
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService parking = new AddOnService("Parking", 300);

        // Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(reservation1, wifi);
        manager.addService(reservation1, breakfast);

        manager.addService(reservation2, parking);
        manager.addService(reservation2, wifi);

        // Display services
        System.out.println("Services for " + reservation1 + ":");
        manager.displayServices(reservation1);

        System.out.println("Total Add-On Cost: " + manager.calculateTotalCost(reservation1));

        System.out.println("\nServices for " + reservation2 + ":");
        manager.displayServices(reservation2);

        System.out.println("Total Add-On Cost: " + manager.calculateTotalCost(reservation2));
    }
}

// Add-On Service Class
class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}

// Manager Class
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Display services
    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println("- " + s.getName() + " : " + s.getCost());
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);
        double total = 0;

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}