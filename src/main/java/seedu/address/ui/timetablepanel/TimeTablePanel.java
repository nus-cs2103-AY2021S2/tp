package seedu.address.ui.timetablepanel;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.event.CurrentWeekPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.schedule.Schedule;
import seedu.address.ui.UiPart;

/**
 * Panel displaying timetable.
 */
public class TimeTablePanel extends UiPart<Region> {
    private static final String FXML = "timetablepanel/TimeTablePanel.fxml";

    private static final int DAY_COLUMN_WIDTH = 80;
    private static final int TYPICAL_COLUMN_WIDTH = 100;
    private static final int FIRST_TIME_COLUMN_WIDTH = 150;
    private static final int SECOND_TIME_COLUMN_WIDTH = 150;
    private static final int TYPICAL_TIME_COLUMN_WIDTH = 200;

    private static final int DATE_DISPLAY_BUFFER = 1;
    private static final int NUM_OF_HALF_HOURS = 48;
    private static final int ROW_SPAN = 1;
    private static final int NUM_OF_DAYS = 7;
    private static final int SCALE_FACTOR = 2;

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final List<LocalDate> dateSlot;
    private final List<Double> hourSlot;
    private final LocalDate queryDate;
    private ObservableList<Event> events;
    private CurrentWeekPredicate weekPredicate;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private GridPane timeGridPane;

    /**
     * Constructs a {@code TimeTablePanel} with Event's {@code ObservableList}.
     */
    public TimeTablePanel(ObservableList<Event> events) {
        super(FXML);
        this.queryDate = LocalDate.now(); // Need to allow command to change the week / view a certain week
        this.dateSlot = new ArrayList<>(NUM_OF_DAYS);
        this.hourSlot = new ArrayList<>();
        this.weekPredicate = new CurrentWeekPredicate(queryDate);
        // Only getting current's week events
        this.events = events.filtered(weekPredicate);
    }

    /**
     * Fills the grid pane with the slots.
     */
    public void construct() {
        logger.info("Constructing Timetable.");
        constructGrid();
        populateDates();
        if (events.size() == 0) {
            logger.info("No events found");
            return;
        }
        addEventSlotsToGrid();
    }

    /**
     * Re-populate the grid pane with new events.
     */
    public void reconstruct(ObservableList<Event> events) {
        this.events = events.filtered(weekPredicate);
        timeGridPane.getChildren().clear();
        timeGridPane.getColumnConstraints().clear();
        hourSlot.clear();
        construct();
    }

    private int getColSpan(Event event) {
        Duration difference = Duration.between(event.getTimeFrom().value, event.getTimeTo().value);
        int colSpan = (int) (difference.toHours() * SCALE_FACTOR);
        long minutes = difference.toMinutes() - (difference.toHours() * 60L);
        if (minutes != 0L) {
            colSpan++;
        }
        return colSpan;
    }

    private int getColIndex(LocalTime startTime) {
        int colIndex = startTime.getHour();
        if (startTime.getMinute() != 0) {
            colIndex += 1;
        }
        return colIndex - 1;
    }

    private LocalTime getStartTime() {
        return events.stream()
                .map(event -> event.getTimeFrom().value.toLocalTime())
                .reduce((time1, time2) -> (time1.isBefore(time2) ? time1 : time2))
                .orElse(LocalTime.of(8, 0));
    }

    private LocalTime getEndTime() {
        return events.stream()
                .map(event -> event.getTimeTo().value.toLocalTime())
                .reduce((time1, time2) -> (time1.isAfter(time2) ? time1 : time2))
                .filter((time) -> time.isAfter(LocalTime.of(11, 0)))
                .orElse(LocalTime.of(12, 0));
    }


    private void constructGrid() {
        int earliestTimeIndex = getColIndex(getStartTime());
        int latestTimeIndex = getColIndex(getEndTime());
        int numColumns = NUM_OF_HALF_HOURS - earliestTimeIndex - (NUM_OF_HALF_HOURS - latestTimeIndex);

        if (gridPane.getRowCount() != (NUM_OF_DAYS + 1)) {
            gridPane.getRowConstraints().clear();
            for (int i = 0; i < NUM_OF_DAYS; i++) {
                RowConstraints con = new RowConstraints();
                con.setPrefHeight(65);
                gridPane.getRowConstraints().add(con);
            }
        }

        if (gridPane.getColumnCount() != numColumns) {
            gridPane.getColumnConstraints().clear();
            for (int i = 0; i < numColumns + DATE_DISPLAY_BUFFER; i++) {
                ColumnConstraints con = new ColumnConstraints();
                ColumnConstraints timeGridCon = new ColumnConstraints();

                if (i == 0) {
                    con.setPrefWidth(DAY_COLUMN_WIDTH);
                    timeGridCon.setPrefWidth(FIRST_TIME_COLUMN_WIDTH);
                    timeGridCon.setHalignment(HPos.CENTER);
                } else if (i == 1) {
                    con.setPrefWidth(TYPICAL_COLUMN_WIDTH);
                    timeGridCon.setPrefWidth(SECOND_TIME_COLUMN_WIDTH);
                    timeGridCon.setHalignment(HPos.RIGHT);
                } else {
                    con.setPrefWidth(TYPICAL_COLUMN_WIDTH);
                    timeGridCon.setPrefWidth(TYPICAL_TIME_COLUMN_WIDTH);
                    timeGridCon.setHalignment(HPos.RIGHT);
                }

                gridPane.getColumnConstraints().add(con);
                timeGridPane.getColumnConstraints().add(timeGridCon);

                ColumnConstraints conHalfHour = new ColumnConstraints();
                conHalfHour.setPrefWidth(TYPICAL_COLUMN_WIDTH);
                gridPane.getColumnConstraints().add(conHalfHour);
            }
        }

        for (int i = 0; i < (numColumns + DATE_DISPLAY_BUFFER) * SCALE_FACTOR; i++) {
            for (int j = 0; j <= NUM_OF_DAYS; j++) {
                Pane pane = new Pane();
                pane.getStyleClass().add("timetable-grid-cell");
                if (i % 2 == 0) {
                    if (i == 0) {
                        pane.getStyleClass().add("first-column");
                    } else {
                        pane.getStyleClass().add("alternate-column");
                    }
                }
                gridPane.add(pane, i, j);
            }
        }
    }

    private void addEventSlotsToGrid() {
        events.forEach(curr -> {
            LocalDate eventDate = curr.getTimeFrom().value.toLocalDate();
            SlotContainer eventSlot = getSlot(curr);
            int colSpan = getColSpan(curr);

            double hour = curr.getTimeFrom().value.toLocalTime().getHour()
                    + (curr.getTimeFrom().value.toLocalTime().getMinute() / 60.0);
            int colIndex = hourSlot.indexOf(hour);

            if (colIndex == 0) {
                colIndex = DATE_DISPLAY_BUFFER;
            } else {
                colIndex = colIndex + DATE_DISPLAY_BUFFER;
            }

            if (dateSlot.contains(eventDate)) {
                int rowIndex = dateSlot.indexOf(eventDate) + DATE_DISPLAY_BUFFER;
                gridPane.add(eventSlot.getRoot(), colIndex, rowIndex, colSpan, ROW_SPAN);
            }
        });
    }

    private SlotContainer getSlot(Event event) {
        // Only two colours - Green for Appointment, Blue for Schedule
        if (event instanceof Appointment) {
            return new AppointmentSlot((Appointment) event);
        } else {
            return new ScheduleSlot((Schedule) event);
        }
    }

    private void populateDates() {
        LocalTime start = getStartTime();
        LocalTime end = getEndTime();
        int count = 0;
        for (int hour = start.getHour(); hour <= end.getHour(); hour++) {
            hourSlot.add((double) hour);
            DisplayTimeSlot label = new DisplayTimeSlot(LocalTime.of(hour, 0));
            timeGridPane.add(label.getRoot(), count, 0);
            if (hour != end.getHour()) {
                hourSlot.add(hour + 0.5);
            }
            count++;
        }

        // Add date displays for entire week
        for (int rowIndex = 1; rowIndex <= NUM_OF_DAYS; rowIndex++) {
            LocalDate currentDate = queryDate.with(DayOfWeek.of(rowIndex));
            dateSlot.add(currentDate);
            DisplayDateSlot slot = new DisplayDateSlot(currentDate);
            gridPane.add(slot.getRoot(), 0, rowIndex, 1, ROW_SPAN);
        }
    }
}
