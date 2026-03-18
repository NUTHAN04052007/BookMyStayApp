import java.util.Scanner;

/**
 * MAIN CLASS - BookMyStayApp
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation\n");

        Scanner scanner = new Scanner(System.in);

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Take input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single Room/Double Room/Suite Room): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // If valid → add to queue
            Reservation reservation = new Reservation(guestName, roomType);
            bookingQueue.addRequest(reservation);

            System.out.println("Booking request added successfully.");

        } catch (InvalidBookingException e) {

            // Handle validation errors
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}