import java.util.LinkedList;
import java.util.Queue;
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
    public void displayDetails() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request added for: " + reservation.getGuestName());
    }
    public void displayRequests() {
        System.out.println("\nCurrent Booking Queue (FIFO Order):\n");
        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests available.");
            return;
        }
        for (Reservation r : requestQueue) {
            r.displayDetails();
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("John", "Single"));
        queue.addRequest(new Reservation("Alice", "Double"));
        queue.addRequest(new Reservation("Bob", "Suite"));
        queue.addRequest(new Reservation("Emma", "Single"));
        queue.displayRequests();
    }
}