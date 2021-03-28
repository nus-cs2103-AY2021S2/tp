package seedu.address.ui.calendar;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.person.Birthday;
import seedu.address.ui.UiPart;
import seedu.address.ui.calendar.schedule.UpcomingSchedule;

/**
 * Represents a box for a date in the calendar.
 */
public class CalendarBox extends UiPart<Region> {
    private static final String FXML = "CalendarBox.fxml";
    private static Logger logger = LogsCenter.getLogger(CalendarBox.class);

    private LocalDate dateTime;
    private EventList events;

    @FXML
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
     * Constructs a CalendarBox object to display the entries on that day.
     *
     * @param dateTime the date time of the day cell in the calendar view.
     */
    public CalendarBox(LocalDate dateTime, EventList events) {
        super(FXML);
        this.dateTime = dateTime;
        this.events = events;
        initializeCalenderBoxInfo(dateTime);
        logger.info("calendar box successfully initialised");
    }

    private void initializeCalenderBoxInfo(LocalDate dateTime) {
        loadDate();
        logger.info("load date into calendar box successful");
        loadEventsCount();
        logger.info("load event counts into calendar box successful");
    }

    public void addClickEventHandler(UpcomingSchedule upcomingSchedule) {
        dateHolder.addEventHandler(MouseEvent.MOUSE_CLICKED, upcomingSchedule);
    }

    public LocalDate getDate() {
        return dateTime;
    }

    private void loadDate() {
        date.setText(dateTime.getMonth().toString().substring(0, 3)
                + " " + dateTime.getDayOfMonth() + " " + dateTime.getYear());
    }

    private void loadEventsCount() {
        int assignmentCount = 0;
        int examCount = 0;
        int birthdayCount = 0;
        int generalEventCount = 0;

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
            if (e instanceof GeneralEvent) {
                generalEventCount++;
            }
        }

        birthday.setText(birthdayCount + " Birthday(s)");
        assignment.setText(assignmentCount + " Assignment(s)");
        exam.setText(examCount + " Exam(s)");
        meeting.setText(generalEventCount + " Event(s)");
    }
}
