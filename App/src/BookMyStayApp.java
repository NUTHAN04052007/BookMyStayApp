import java.util.LinkedList;
import java.util.Queue;

/**
 * ------------------------------------------------------------
 * BOOK MY STAY APP
 * Use Case 5: Booking Request Queue (FIFO)
 * Version: 5.1
 * ------------------------------------------------------------
 */

/* ---------------- RESERVATION ---------------- */

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

/* ---------------- BOOKING QUEUE ---------------- */

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Added Request → " + reservation.getGuestName());
    }

    // View all requests (FIFO order)
    public void displayQueue() {
        System.out.println("\nBooking Requests in Queue (FIFO Order):\n");

        if (queue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

/* ---------------- MAIN ---------------- */

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulating incoming booking requests
        bookingQueue.addRequest(new Reservation("John", "Single"));
        bookingQueue.addRequest(new Reservation("Alice", "Double"));
        bookingQueue.addRequest(new Reservation("Bob", "Suite"));
        bookingQueue.addRequest(new Reservation("Emma", "Single"));

        // Display queue (FIFO)
        bookingQueue.displayQueue();
    }
}