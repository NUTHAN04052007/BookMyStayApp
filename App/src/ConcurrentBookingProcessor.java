/**
 * CLASS - ConcurrentBookingProcessor
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * @version 11.0
 */
public class ConcurrentBookingProcessor implements Runnable {

    /** Shared booking request queue. */
    private BookingRequestQueue bookingQueue;

    /** Shared room inventory. */
    private RoomInventory inventory;

    /** Shared allocation service. */
    private RoomAllocationService allocationService;

    /**
     * Creates a new booking processor.
     */
    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    /**
     * Executes booking processing logic.
     */
    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            // 🔒 Critical Section 1: Access queue
            synchronized (bookingQueue) {

                if (!bookingQueue.hasPendingRequests()) {
                    break;
                }

                reservation = bookingQueue.getNextRequest();
            }

            // 🔒 Critical Section 2: Allocation + inventory update
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}