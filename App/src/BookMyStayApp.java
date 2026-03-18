/**
 * MAIN CLASS - BookMyStayApp
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * @version 12.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("System Recovery\n");

        String filePath = "inventory.txt";

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Persistence service
        FilePersistenceService persistenceService = new FilePersistenceService();

        // Load previous state
        persistenceService.loadInventory(inventory, filePath);

        // Display current inventory
        System.out.println("\nCurrent Inventory:");

        System.out.println("Single Room: " +
                inventory.getRoomAvailability().get("Single Room"));

        System.out.println("Double Room: " +
                inventory.getRoomAvailability().get("Double Room"));

        System.out.println("Suite Room: " +
                inventory.getRoomAvailability().get("Suite Room"));

        // Save state before exit
        persistenceService.saveInventory(inventory, filePath);
    }
}