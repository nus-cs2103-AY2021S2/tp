package seedu.address.ui;

import static seedu.address.ui.UiUtil.generateTagLabel;
import static seedu.address.ui.UiUtil.streamTags;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;

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
    private Label address;
    @FXML
    private Label dateTime;
    @FXML
    private Label relevantContactLabel;
    @FXML
    private FlowPane contacts;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        name.setText(appointment.getName().fullName);
        checkForPlaceholder(appointment.getAddress().value, address);
        checkForPlaceholder(appointment.getDateTime().dateTime, dateTime);
        checkForExpiry(appointment.getDateTime().dateTime, cardPane);
        setRelevantContactLabel();
        appointment.getContacts().stream()
                .sorted(Comparator.comparing(contact -> contact.getName().toString()))
                .forEach(contact -> contacts.getChildren().add(new Label(contact.getName().toString())));
        streamTags(appointment.getTags()).forEach(tag -> tags.getChildren().add(generateTagLabel(tag)));
    }

    /**
     * If have contacts then "Relevant Contacts:" will be displayed above contacts, otherwise it will not be displayed.
     */
    private void setRelevantContactLabel() {
        if (appointment.getContacts().isEmpty()) {
            relevantContactLabel.setManaged(false);
        } else {
            relevantContactLabel.setManaged(true);
        }
    }

    private void checkForPlaceholder(String value, Label label) {
        if (value.equals("NIL")) {
            label.setVisible(false);
        } else {
            label.setVisible(true);
            label.setText(value);
        }
    }

    private void checkForExpiry(String date, HBox cardPane) {
        if (!DateTime.isFutureDateTime(date)) {
            cardPane.setStyle("-fx-background-color:salmon");
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
