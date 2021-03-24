package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label remarks;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        name.setText(appointment.getName().name);
        remarks.setText(appointment.getRemarks().remark);
        date.setText(appointment.getDate().toString());
        time.setText(appointment.getTime().toString());
        Date currentDate = new Date(LocalDate.now());
        Time currentTime = new Time(LocalTime.now());

        if (currentDate.compareTo(appointment.getDate()) > 0) {
            cardPane.setStyle("-fx-background-color: #696969");
        } else if (currentDate.compareTo(appointment.getDate()) == 0
                && currentTime.compareTo(appointment.getTime()) > 0) {
            cardPane.setStyle("-fx-background-color: #696969");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}

