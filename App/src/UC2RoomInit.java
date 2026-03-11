/**
 * MAIN CLASS UseCase2RoomInitialization
 * Demonstrates room initialization and static availability.
 */

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("Single Room:");
        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        System.out.println("Double Room:");
        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        System.out.println("Suite Room:");
        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}