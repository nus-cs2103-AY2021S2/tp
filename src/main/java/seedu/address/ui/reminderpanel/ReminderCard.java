package seedu.address.ui.reminderpanel;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.reminder.Reminder;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "reminderpanel/ReminderListCard.fxml";

    public final Reminder reminder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Pane reminderColor;

    /**
     * Creates a {@code ReminderCard} with the given {@code Reminder} and index to display.
     */
    public ReminderCard(Reminder reminder, int displayedIndex) {
        super(FXML);
        this.reminder = reminder;
        id.setText(displayedIndex + ". ");
        description.setText(reminder.getDescription().value);
        date.setText(reminder.getReminderDate().toString());
        setCardColor();
    }

    private void setCardColor() {
        String color = "#42f59e";
        LocalDate today = LocalDate.now();
        LocalDate reminderDateValue = reminder.getReminderDate().value;
        if (reminderDateValue.isEqual(today)) {
            color = "yellow";
        } else if (reminderDateValue.isBefore(today)) {
            color = "red";
        } else if (reminderDateValue.isAfter(today)) {
            long daysDiff = DAYS.between(today, reminderDateValue);
            if (daysDiff <= 3) {
                color = "orange";
            } else {
                color = "#42f59e";
            }
        }
        reminderColor.setStyle("-fx-background-color: " + color + "; -fx-border-color: #3a3a3a;");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return id.getText().equals(card.id.getText())
                && reminder.equals(card.reminder);
    }
}
