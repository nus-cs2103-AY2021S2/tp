package seedu.address.ui.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.person.Birthday;
import seedu.address.ui.UiPart;
import seedu.address.ui.calendar.schedule.UpcomingSchedule;

/**
 * Represents a box for a date in the calendar, where the calendar is on the right side of the {@code CalendarWindow}.
 */
public class CalendarBox extends UiPart<Region> {
    private static final String FXML = "CalendarBox.fxml";
    private static final Color RED = Color.RED;

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
    private Label event;

    /**
     * Constructs a CalendarBox object to display the entries on that day.
     *
     * @param dateTime Date time of the day cell in the calendar view.
     */
    public CalendarBox(LocalDate dateTime, EventList events) {
        super(FXML);
        requireAllNonNull(dateTime, events);
        this.dateTime = dateTime;
        this.events = events;
        initializeCalenderBoxInfo();
    }

    /**
     * Adds mouse click event handler for {@code CalendarBox}.
     *
     * @param upcomingSchedule To update when the {@code CalendarBox} is clicked.
     */
    public void addClickEventHandler(UpcomingSchedule upcomingSchedule) {
        dateHolder.addEventHandler(MouseEvent.MOUSE_CLICKED, upcomingSchedule);
    }

    public LocalDate getDate() {
        return dateTime;
    }

    private void initializeCalenderBoxInfo() {
        loadDate();
        loadEventsCount();
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

        setBirthdayText(birthdayCount);
        setAssignmentText(assignmentCount);
        setExamCount(examCount);
        setEventText(generalEventCount);
    }

    private void setBirthdayText(int birthdayCount) {
        if (birthdayCount > 0) {
            birthday.setTextFill(RED);
        }
        birthday.setText(birthdayCount + " Birthday(s)");
    }

    private void setAssignmentText(int assignmentCount) {
        if (assignmentCount > 0) {
            assignment.setTextFill(RED);
        }
        assignment.setText(assignmentCount + " Assignment(s)");
    }

    private void setExamCount(int examCount) {
        if (examCount > 0) {
            exam.setTextFill(RED);
        }
        exam.setText(examCount + " Exam(s)");
    }

    private void setEventText(int eventCount) {
        if (eventCount > 0) {
            event.setTextFill(RED);
        }
        event.setText(eventCount + " Event(s)");
    }
}
