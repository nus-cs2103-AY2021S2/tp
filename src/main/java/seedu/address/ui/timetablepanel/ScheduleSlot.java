package seedu.address.ui.timetablepanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.schedule.Schedule;

/**
 * A region of a calendar view that represents a single slot.
 */
public class ScheduleSlot extends SlotContainer {

    private static final String FXML = "timetablepanel/ScheduleSlot.fxml";

    protected String titleText;
    protected String dateText;
    protected String timeText;
    protected String descriptionText;

    @FXML
    private Label title;

    @FXML
    private Label time;

    @FXML
    private Label description;

    /**
     * Constructor that creates a slot to be added to the schedule with relevant appointment information.
     */
    public ScheduleSlot(Schedule schedule) {
        super(FXML);

        this.titleText = schedule.getTitle().value;
        this.dateText = schedule.getTimeFrom().toDateString();
        this.timeText = schedule.getTimeFrom().toTimeString() + " - " + schedule.getTimeTo().toTimeString();
        this.descriptionText = schedule.getDescription().value;

        setText();
    }

    private void setText() {
        title.setText(titleText);
        time.setText(timeText);
        description.setText(descriptionText);
    }
}
