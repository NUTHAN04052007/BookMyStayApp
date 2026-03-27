public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("John", "Single", "S-101"));
        history.addReservation(new Reservation("Alice", "Double", "D-201"));
        history.addReservation(new Reservation("Bob", "Suite", "SU-301"));
        history.addReservation(new Reservation("Emma", "Single", "S-102"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());
        reportService.generateSummary(history.getAllReservations());
    }
}