package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;

import java.time.LocalDate;
import java.util.logging.Logger;

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

    public static CalendarBox create(LocalDate dateTime) {
        return new CalendarBox(dateTime);
    }

    private void initializeCalenderBoxInfo(LocalDate dateTime) {
        date.setText(dateTime.getMonth().toString().substring(0, 3) + " " + dateTime.getDayOfMonth());
        assignment.setText("0 Assignment(s)");
        exam.setText("0 Exam(s)");
        event.setText("0 Event(s)");
    }
}
