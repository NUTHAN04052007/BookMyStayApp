/**
 * MAIN CLASS - BookMyStayApp
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * @version 11.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        // Shared components
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single Room"));
        bookingQueue.addRequest(new Reservation("Subha", "Single Room"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double Room"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite Room"));

        // Create threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Show remaining inventory
        System.out.println("\nRemaining Inventory:");

        System.out.println("Single Room: " +
                inventory.getRoomAvailability().get("Single Room"));

        System.out.println("Double Room: " +
                inventory.getRoomAvailability().get("Double Room"));

        System.out.println("Suite Room: " +
                inventory.getRoomAvailability().get("Suite Room"));
    }
}