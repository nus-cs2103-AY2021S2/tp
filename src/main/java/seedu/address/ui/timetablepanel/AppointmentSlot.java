package seedu.address.ui.timetablepanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.appointment.Appointment;

/**
 * A region of a calendar view that represents a single slot.
 * Solution below adapted from
 * https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
 * AppointmentSlotGreen.java
 */
public class AppointmentSlot extends SlotContainer {

    private static final String FXML = "timetablepanel/AppointmentSlot.fxml";

    protected String subjectText;
    protected String dateText;
    protected String timeText;
    protected String descriptionText;

    @FXML
    private Label title;

    @FXML
    private Label time;

    @FXML
    private Label description;

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
    // SlotContainer.java
    // with minor modifications (renaming of variables).
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
