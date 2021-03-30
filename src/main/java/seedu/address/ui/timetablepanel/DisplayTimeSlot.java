package seedu.address.ui.timetablepanel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A region to display the time on the schedule.
 */
public class DisplayTimeSlot extends UiPart<Region> {

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("hh:mm a");
    private static final String FXML = "timetablepanel/DisplayTimeSlot.fxml";

    @FXML
    private Label time;

    /**
     * Constructor for time to be displayed on top of timetable.
     */
    public DisplayTimeSlot(LocalTime localTime) {
        super(FXML);
        time.setText(localTime.format(DISPLAY_FORMAT));
    }
}
