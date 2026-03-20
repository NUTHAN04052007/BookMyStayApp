import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * ------------------------------------------------------------
 * BOOK MY STAY APP
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Version: 6.1
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
}

/* ---------------- BOOKING REQUEST QUEUE ---------------- */

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean isEmpty() {
        return requestQueue.isEmpty();
    }
}

/* ---------------- INVENTORY SERVICE ---------------- */

class InventoryService {
    private HashMap<String, Integer> availability;

    public InventoryService() {
        availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void decrementAvailability(String roomType) {
        int current = getAvailability(roomType);
        if (current > 0) {
            availability.put(roomType, current - 1);
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String roomType : availability.keySet()) {
            System.out.println(roomType + " Rooms Available: " + availability.get(roomType));
        }
    }
}

/* ---------------- BOOKING SERVICE ---------------- */

class BookingService {
    private InventoryService inventoryService;
    private Set<String> allocatedRoomIds;
    private HashMap<String, Set<String>> allocatedRoomsByType;
    private int singleCounter;
    private int doubleCounter;
    private int suiteCounter;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.allocatedRoomIds = new HashSet<>();
        this.allocatedRoomsByType = new HashMap<>();
        this.allocatedRoomsByType.put("Single", new HashSet<>());
        this.allocatedRoomsByType.put("Double", new HashSet<>());
        this.allocatedRoomsByType.put("Suite", new HashSet<>());

        this.singleCounter = 100;
        this.doubleCounter = 200;
        this.suiteCounter = 300;
    }

    public void processBookings(BookingRequestQueue queue) {
        System.out.println("Processing Booking Requests...\n");

        while (!queue.isEmpty()) {
            Reservation reservation = queue.getNextRequest();
            String roomType = reservation.getRoomType();

            if (inventoryService.getAvailability(roomType) <= 0) {
                System.out.println("Reservation failed for " + reservation.getGuestName()
                        + " | Room Type: " + roomType + " | Reason: No availability");
                continue;
            }

            String roomId = generateUniqueRoomId(roomType);

            if (roomId == null) {
                System.out.println("Reservation failed for " + reservation.getGuestName()
                        + " | Room Type: " + roomType + " | Reason: Could not generate room ID");
                continue;
            }

            allocatedRoomIds.add(roomId);
            allocatedRoomsByType.get(roomType).add(roomId);
            inventoryService.decrementAvailability(roomType);

            System.out.println("Reservation Confirmed:");
            System.out.println("Guest: " + reservation.getGuestName());
            System.out.println("Room Type: " + roomType);
            System.out.println("Allocated Room ID: " + roomId);
            System.out.println();
        }
    }

    private String generateUniqueRoomId(String roomType) {
        String roomId = null;

        if (roomType.equalsIgnoreCase("Single")) {
            do {
                roomId = "S-" + singleCounter++;
            } while (allocatedRoomIds.contains(roomId));
        } else if (roomType.equalsIgnoreCase("Double")) {
            do {
                roomId = "D-" + doubleCounter++;
            } while (allocatedRoomIds.contains(roomId));
        } else if (roomType.equalsIgnoreCase("Suite")) {
            do {
                roomId = "SU-" + suiteCounter++;
            } while (allocatedRoomIds.contains(roomId));
        } else {
            return null;
        }

        return roomId;
    }

    public void displayAllocatedRooms() {
        System.out.println("Allocated Room IDs:");
        for (String roomType : allocatedRoomsByType.keySet()) {
            System.out.println(roomType + ": " + allocatedRoomsByType.get(roomType));
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("John", "Single"));
        requestQueue.addRequest(new Reservation("Alice", "Double"));
        requestQueue.addRequest(new Reservation("Bob", "Suite"));
        requestQueue.addRequest(new Reservation("Emma", "Single"));
        requestQueue.addRequest(new Reservation("David", "Suite"));

        InventoryService inventoryService = new InventoryService();
        BookingService bookingService = new BookingService(inventoryService);

        bookingService.processBookings(requestQueue);
        bookingService.displayAllocatedRooms();
        inventoryService.displayInventory();
    }
}