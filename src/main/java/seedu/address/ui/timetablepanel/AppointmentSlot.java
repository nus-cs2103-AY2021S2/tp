package seedu.address.ui.timetablepanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.appointment.Appointment;

/**
 * A region of a calendar view that represents a single slot.
 */
public class AppointmentSlot extends SlotContainer {

    private static final String FXML = "timetablepanel/AppointmentSlot.fxml";

    protected String subjectText;
    protected String dateText;
    protected String timeText;
    protected String descriptionText;

    @javafx.fxml.FXML
    private Label title;

    @FXML
    private Label time;

    @FXML
    private Label description;

    /**
     * Constructor that creates a slot to be added to the schedule with relevant appointment information.
     */
    public AppointmentSlot(Appointment appointment) {
        super(FXML);

        this.subjectText = appointment.getSubject().name;
        this.dateText = appointment.getTimeFrom().toDateString();
        this.timeText = appointment.getTimeFrom().toTimeString() + " - "
                + appointment.getTimeTo().toTimeString();
        this.descriptionText = appointment.getName().fullName + " @ " + appointment.getLocation().value;

        setText();
    }

    private void setText() {
        title.setText(subjectText);
        time.setText(timeText);
        description.setText(descriptionText);
    }
}
