package seedu.address.model.budget;

import seedu.address.model.ReadOnlyAppointmentBook;

public class GetTotalCost {

    public static final String APPOINTMENT_BOOK_PATH = "data/appointmentBook.json";

    private int totalCost;

    /**
     * Primary constructor.
     */
    public GetTotalCost() {
        GetAppointmentBook getAppointmentBook = new GetAppointmentBook(APPOINTMENT_BOOK_PATH);
        ReadOnlyAppointmentBook appointmentBook = getAppointmentBook.getAppointmentBook();

        this.totalCost = appointmentBook.getAppointmentList().stream().mapToInt(
            x -> new GetAppointmentCost(x).getAppointmentCost()).sum();
    }

    /**
     * @return Total cost of all booked appointments.
     */
    public int getTotalCost() {
        return totalCost;
    }

}
