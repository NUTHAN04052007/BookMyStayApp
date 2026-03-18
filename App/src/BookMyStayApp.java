/**
 * MAIN CLASS - BookMyStayApp
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * @version 10.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize cancellation service
        CancellationService cancellationService = new CancellationService();

        // Simulate confirmed booking (from UC6)
        String reservationId = "Single Room-1";
        String roomType = "Single Room";

        // Register booking
        cancellationService.registerBooking(reservationId, roomType);

        // Cancel booking
        cancellationService.cancelBooking(reservationId, inventory);

        // Show rollback history
        cancellationService.showRollbackHistory();

        // Show updated inventory
        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getRoomAvailability().get("Single Room"));
    }
}