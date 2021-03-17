package seedu.address.ui.calendar;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.person.Birthday;
import seedu.address.ui.UiPart;

public class CalendarBox extends UiPart<Region> {
    private static final String FXML = "CalendarBox.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarBox.class);

    private LocalDate dateTime;
    private EventList events;

    private VBox dateHolder;
    @FXML
    private Label date;
    @FXML
    private Label birthday;
    @FXML
    private Label assignment;
    @FXML
    private Label exam;
    @FXML
    private Label meeting;

    /**
     * Create a CalendarBox object to display the entries on that day.
     * @param dateTime the date time of the day cell in the calendar view
     */
    public CalendarBox(LocalDate dateTime, EventList events) {
        super(FXML);
        this.dateTime = dateTime;
        this.events = events;
        initializeCalenderBoxInfo(dateTime);
    }

    private void initializeCalenderBoxInfo(LocalDate dateTime) {
        loadDate();
        loadEventsCount();
    }

    private void loadDate() {
        date.setText(dateTime.getMonth().toString().substring(0, 3) + " " + dateTime.getDayOfMonth());
    }

    private void loadEventsCount() {
        int assignmentCount = 0;
        int examCount = 0;
        int birthdayCount = 0;

        for (Event e : events.getEvents()) {
            if (e instanceof Assignment) {
                assignmentCount++;
            }
            if (e instanceof Exam) {
                examCount++;
            }
            if (e instanceof Birthday) {
                birthdayCount++;
            }
        }

        birthday.setText(birthdayCount + " Birthday(s)");
        assignment.setText(assignmentCount + " Assignment(s)");
        exam.setText(examCount + " Exam(s)");
        meeting.setText("0 Event(s)");
    }

}
