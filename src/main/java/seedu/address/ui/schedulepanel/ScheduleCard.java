package seedu.address.ui.schedulepanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.schedule.Schedule;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "schedulepanel/ScheduleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Schedule schedule;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label date;
    @FXML
    private Label description;
    @FXML
    private Label time;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     * Temporarily using a Stub due to absence of integration.
     */
    public ScheduleCard(Schedule schedule, int displayedIndex) {
        super(FXML);
        this.schedule = schedule;
        id.setText(displayedIndex + ". ");
        title.setText(schedule.getTitle().value);
        date.setText(schedule.getTimeFrom().toDateString());
        description.setText(schedule.getDescription().value);
        time.setText(schedule.getTimeFrom().toTimeString() + " - " + schedule.getTimeTo().toTimeString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return id.getText().equals(card.id.getText())
                && schedule.equals(card.schedule);
    }
}
