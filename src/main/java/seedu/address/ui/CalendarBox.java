package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;

public class CalendarBox extends UiPart<Region> {
    private static final String FXML = "CalendarBox.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarBox.class);
    private LocalDate dateTime;

    private VBox dateHolder;
    @FXML
    private Label date;
    @FXML
    private Label assignment;
    @FXML
    private Label exam;
    @FXML
    private Label event;

    /**
     * Create a CalendarBox object to display the entries on that day.
     * @param dateTime the date time of the day cell in the calendar view
     */
    public CalendarBox(LocalDate dateTime) {
        super(FXML);
        this.dateTime = dateTime;
        initializeCalenderBoxInfo(dateTime);
    }

    private void initializeCalenderBoxInfo(LocalDate dateTime) {
        date.setText(dateTime.getMonth().toString().substring(0, 3) + " " + dateTime.getDayOfMonth());
        assignment.setText("0  assignment");
        exam.setText("0 Events");
        event.setText("0 event");
    }
}
