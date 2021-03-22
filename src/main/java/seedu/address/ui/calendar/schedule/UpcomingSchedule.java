package seedu.address.ui.calendar.schedule;

import static seedu.address.commons.util.ScheduleUiUtil.CURRENT_TIME_POINTER_PADDING;
import static seedu.address.commons.util.ScheduleUiUtil.IN_THE_DAY;
import static seedu.address.commons.util.ScheduleUiUtil.getMarginFromTime;
import static seedu.address.commons.util.ScheduleUiUtil.toAmPmTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.Event;
import seedu.address.ui.UiPart;

/*
Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
 */
public class UpcomingSchedule extends UiPart<Region> implements EventHandler<MouseEvent> {
    private static final String FXML = "schedule/UpcomingSchedule.fxml";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private Thread thread;

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
    private ObservableList<Event> calendarList;
    private FilteredList<Event> dayList;
    private LocalDate today;
    private int currentCell;

    /**
     * Constructor for the UpcomingSchedulePanel, which is the left panel of the {@ScheduleUi}.
     * @param calendarList
     */
    public UpcomingSchedule(ObservableList<Event> calendarList) {
        super(FXML);
        this.calendarList = calendarList;
        today = LocalDate.now();
        dayList = calendarList.filtered(task -> IN_THE_DAY.test(task, today.atStartOfDay()));
        fillBase();
        fillInnerToday();
    }

    private void fillBase() {
        timeScale = new TimeScale(dayList);

        schedule.getChildren().add(timeScale.getRoot());
    }

    private void fillOtherDay(int date) {
        timeScale.removeItem(currentTimePointer.getRoot());
        LocalDateTime dateTime = LocalDate.of(today.getYear(), today.getMonth(), date).atStartOfDay();
        dayList.setPredicate(task -> IN_THE_DAY.test(task, dateTime));
        fillTopLabelForOtherDay(dateTime);
    }

    private void fillInnerToday() {
        dayList.setPredicate(task -> IN_THE_DAY.test(task, today.atStartOfDay()));
        currentCell = today.getDayOfMonth();

        // Fill the label for today.
        fillTopLabelForToday();

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
                    fillTopLabelForToday();
                });
            }
        });

        thread.start();
    }

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
        String content = ((Label) ((VBox) mouseEvent.getSource()).getChildren().get(0)).getText();
        int date = Integer.parseInt(content.substring(content.lastIndexOf(" ") + 1));
        if (date == currentCell) {
            return;
        }

        if (date == today.getDayOfMonth()) {
            endThread();
            fillInnerToday();
        }
        if (date != today.getDayOfMonth()) {
            currentCell = date;
            endThread();
            fillOtherDay(date);
        }
    }
}
