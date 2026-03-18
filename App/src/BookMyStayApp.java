/**
 * MAIN CLASS - BookMyStayApp
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection\n");

        // Example reservation ID (from UC6)
        String reservationId = "Single-1";

        // Create service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Create services
        AddOnService food = new AddOnService("Food", 1000.0);
        AddOnService spa = new AddOnService("Spa", 500.0);

        // Attach services
        serviceManager.addService(reservationId, food);
        serviceManager.addService(reservationId, spa);

        // Calculate total cost
        double totalCost =
                serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}