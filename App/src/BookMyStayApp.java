import java.util.HashMap;

/**
 * ------------------------------------------------------------
 * BOOK MY STAY APP
 * Use Case 4: Room Search & Availability Check
 * Version: 4.1
 * ------------------------------------------------------------
 */

abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/* ---------------- INVENTORY (READ ONLY USAGE HERE) ---------------- */

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 0); // example: unavailable
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/* ---------------- SEARCH SERVICE ---------------- */

class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("Available Rooms:\n");

        // Single Room
        if (inventory.getAvailability("Single") > 0) {
            Room room = new SingleRoom();
            System.out.println("Single Room:");
            room.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Single"));
            System.out.println();
        }

        // Double Room
        if (inventory.getAvailability("Double") > 0) {
            Room room = new DoubleRoom();
            System.out.println("Double Room:");
            room.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Double"));
            System.out.println();
        }

        // Suite Room
        if (inventory.getAvailability("Suite") > 0) {
            Room room = new SuiteRoom();
            System.out.println("Suite Room:");
            room.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Suite"));
            System.out.println();
        }
    }
}

/* ---------------- MAIN ---------------- */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform search (READ ONLY)
        searchService.searchAvailableRooms(inventory);
    }
}