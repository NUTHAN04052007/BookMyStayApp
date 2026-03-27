import java.util.*;

public class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // 🔥 THIS IS THE MISSING METHOD
    public List<Reservation> getAllReservations() {
        return history;
    }
}