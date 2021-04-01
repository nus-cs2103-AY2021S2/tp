package seedu.address.ui.calendar.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ScheduleUiUtil.calendarTextToDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.EventList;
import seedu.address.storage.CalendarStorage;
import seedu.address.ui.UiPart;

/**
 * Represents a timeline GUI to show the upcoming events for a day in the calendar.
 */
public class UpcomingSchedule extends UiPart<Region> implements EventHandler<MouseEvent> {
    //Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
    private static final String FXML = "schedule/UpcomingSchedule.fxml";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static Logger logger = LogsCenter.getLogger(UpcomingSchedule.class);

    private Thread thread;
    private CalendarStorage calendarStorage;

    @FXML
    private VBox schedule;

    @FXML
    private Label date;

    @FXML
    private Label year;

    @FXML
    private Label day;

    private CurrentTimePointer currentTimePointer;
    private TimeScale timeScale;
    private DayEventList eventHolder;
    private LocalDate currentDay;

    /**
     * Constructs a schedule for the UpcomingSchedulePanel, which is the left panel of the {@Code CalendarWindow}.
     * @param calendarStorage storage for calendar to access events.
     */
    public UpcomingSchedule(CalendarStorage calendarStorage) {
        super(FXML);
        requireNonNull(calendarStorage);
        this.calendarStorage = calendarStorage;
        currentDay = LocalDate.now();
        timeScale = new TimeScale();
        eventHolder = new DayEventList();
        schedule.getChildren().add(eventHolder.getRoot());
        //schedule.getChildren().add(timeScale.getRoot());
        loadSchedule(currentDay);
        logger.info("upcoming schedule successfully initialised");
    }

    /**
     * Lods the schedule for a certain date.
     * @param date Date for schedule.
     */
    public void loadSchedule(LocalDate date) {
        currentDay = date;
        fillTopLabelForDay();
        logger.info("date details of upcoming schedule loaded successfully");
        fillBase();
        logger.info("timeline nodes loaded successfully");
    }

    private void fillBase() {
        schedule.getChildren().remove(eventHolder.getRoot());
        calendarStorage.refreshStorage();
        EventList events = calendarStorage.getDateEvents(currentDay);
        eventHolder.updateList(events);
        schedule.getChildren().add(eventHolder.getRoot());
    }
    /*
    private void addTimePointer() {
        // Add the currentTimePointer to the TimeScale
        String currentTime = getCurrentTime();
        double marginTop = getMarginFromTime(currentTime) - CURRENT_TIME_POINTER_PADDING;
        currentTimePointer = new CurrentTimePointer(toAmPmTime(currentTime));

        // The sequence matters, tasks must be on top.
        timeScale.placeCurrentTime(currentTimePointer, marginTop);
        timeScale.handleOverlap(currentTime);

        // Open a new thread to handle the position of the currentTimePointer
        thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // a minute
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    String newCurrentTime = getCurrentTime();

                    //update the position of the currentTimePointer
                    currentTimePointer.updateTime(toAmPmTime(newCurrentTime));
                    timeScale.updateCurrentTimePosition(getMarginFromTime(newCurrentTime)
                            - CURRENT_TIME_POINTER_PADDING);
                    timeScale.handleOverlap(newCurrentTime);

                    // update the today label
                    fillTopLabelForDay();
                });
            }
        });
        thread.start();
    }
    */

    private void fillTopLabelForDay() {
        year.setText(String.valueOf(currentDay.getYear()));
        date.setText(getDateString(currentDay));
        day.setText(getDayString(currentDay));
    }

    private String getDateString(LocalDate date) {
        String month = date.getMonth().toString();
        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        String dateString = month + " " + dayOfMonth + ",";
        return dateString;
    }

    private String getDayString(LocalDate date) {
        String dayString = date.getDayOfWeek().toString();
        return dayString.substring(0, 1) + dayString.substring(1).toLowerCase();
    }

    /**
     * Returns time in the format of "HH:mm"
     */
    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return TIME_FORMATTER.format(now);
    }

    public void endThread() {
        //thread.stop();
    }

    /**
     * Handles the mouse click event to load and show the
     * date events of a Calendar Box in the upcoming schedule.
     *
     * @param mouseEvent click on the a {@Code CalendarBox} event.
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        //Get time string from calendar box that was clicked
        String formattedTime = ((Label) ((VBox) mouseEvent.getSource()).getChildren().get(0)).getText();
        LocalDate clickedDate = calendarTextToDate(formattedTime);

        if (clickedDate == currentDay) {
            return;
        }

        currentDay = clickedDate;
        loadSchedule(currentDay);
    }
}
