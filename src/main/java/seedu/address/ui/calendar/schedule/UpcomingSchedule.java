package seedu.address.ui.calendar.schedule;

import static seedu.address.commons.util.ScheduleUiUtil.CURRENT_TIME_POINTER_PADDING;
import static seedu.address.commons.util.ScheduleUiUtil.calendarTextToDate;
import static seedu.address.commons.util.ScheduleUiUtil.getMarginFromTime;
import static seedu.address.commons.util.ScheduleUiUtil.toAmPmTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.EventList;
import seedu.address.storage.CalendarStorage;
import seedu.address.ui.UiPart;

/*
Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
 */
public class UpcomingSchedule extends UiPart<Region> implements EventHandler<MouseEvent> {
    private static final String FXML = "schedule/UpcomingSchedule.fxml";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

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
    //private ObservableList<Event> calendarList;
    //private FilteredList<Event> dayList;
    private LocalDate currentDay;
    private int currentCell;

    /**
     * Constructor for the UpcomingSchedulePanel, which is the left panel of the {@ScheduleUi}.
     * @param calendarStorage storage for calendar to access events.
     */
    public UpcomingSchedule(CalendarStorage calendarStorage) {
        super(FXML);
        this.calendarStorage = calendarStorage;
        currentDay = LocalDate.now();
        timeScale = new TimeScale();
        schedule.getChildren().add(timeScale.getRoot());
        loadSchedule(currentDay);
    }

    /**
     * load the schdeule for a certain date.
     * @param date Date for schedule.
     */
    public void loadSchedule(LocalDate date) {
        currentDay = date;
        fillTopLabelForDay();
        fillBase();
    }

    private void fillBase() {
        schedule.getChildren().remove(timeScale.getRoot());
        EventList events = calendarStorage.getDateEvents(currentDay);
        timeScale.updateTimeScale(events);
        schedule.getChildren().add(timeScale.getRoot());

        if (currentDay == LocalDate.now()) {
            timeScale.removeItem(currentTimePointer.getRoot());
        } else {
            addTimePointer();
        }
    }

    /*
    private void fillOtherDay(int date) {
        timeScale.removeItem(currentTimePointer.getRoot());
        //LocalDateTime dateTime = LocalDate.of(today.getYear(), today.getMonth(), date).atStartOfDay();
        //dayList.setPredicate(task -> IN_THE_DAY.test(task, dateTime));
        //fillTopLabelForOtherDay(dateTime);
    }

    private void fillInnerToday() {
        dayList.setPredicate(task -> IN_THE_DAY.test(task, today.atStartOfDay()));
        currentCell = today.getDayOfMonth();

        // Fill the label for today.
        fillTopLabelForDay();
    }*/

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

    private void fillTopLabelForDay() {
        year.setText(String.valueOf(currentDay.getYear()));
        date.setText(getDateString(currentDay));
        day.setText(getDayString(currentDay));
    }

    /*
    private void fillTopLabelForToday() {
        // Fill the label with date of "TODAY"
        today = LocalDate.now();
        year.setText(String.valueOf(today.getYear()));
        date.setText(getDateString(today));
        day.setText(getDayString(today));
    }

    private void fillTopLabelForOtherDay(LocalDateTime dateTime) {
        year.setText(String.valueOf(dateTime.getYear()));
        date.setText(getDateString(dateTime.toLocalDate()));
        day.setText(getDayString(dateTime.toLocalDate()));
    }
    */

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
        thread.stop();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        //Get time string from calendar box that was clicked
        String formattedTime = ((Label) ((VBox) mouseEvent.getSource()).getChildren().get(0)).getText();
        LocalDate clickedDate = calendarTextToDate(formattedTime);

        if (clickedDate == currentDay) {
            return;
        }

        currentDay = clickedDate;
        endThread();
        loadSchedule(currentDay);
    }
}
