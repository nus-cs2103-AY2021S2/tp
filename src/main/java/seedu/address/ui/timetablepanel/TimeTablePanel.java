package seedu.address.ui.timetablepanel;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
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
 * Solution below adapted from
 * https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
 * SchedulePanel.java
 */
public class TimeTablePanel extends UiPart<Region> {
    private static final String FXML = "timetablepanel/TimeTablePanel.fxml";

    private static final int DAY_COLUMN_WIDTH = 80;
    private static final int TYPICAL_COLUMN_WIDTH = 100;
    private static final int FIRST_TIME_COLUMN_WIDTH = 150;
    private static final int SECOND_TIME_COLUMN_WIDTH = 150;
    private static final int TYPICAL_TIME_COLUMN_WIDTH = 200;
    private static final int ROW_HEIGHT = 68;

    private static final int DATE_DISPLAY_BUFFER = 1;
    private static final int NUM_OF_HALF_HOURS = 48;
    private static final int ROW_SPAN = 1;
    private static final int NUM_OF_DAYS = 7;
    private static final int SCALE_FACTOR = 2;

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final List<LocalDate> dateSlot;
    private final List<Double> hourSlot;
    private LocalDate queryDate;
    private final FilteredList<Event> events;
    private CurrentWeekPredicate weekPredicate;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private GridPane timeGridPane;

    @FXML
    private Label title;

    /**
     * Constructs a {@code TimeTablePanel}.
     */
    public TimeTablePanel(ObservableList<Event> events) {
        super(FXML);
        this.events = new FilteredList<>(events);
        this.queryDate = LocalDate.now();
        this.dateSlot = new ArrayList<>(NUM_OF_DAYS);
        this.hourSlot = new ArrayList<>();
        this.weekPredicate = new CurrentWeekPredicate(queryDate);
    }

    // @@author hansebastian-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
    // SchedulePanel.java
    // with minor modifications (renaming of methods).
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
        populateEvents();
    }

    /**
     * Re-populate the grid pane with new events.
     */
    public void reconstruct(LocalDate date) {
        this.queryDate = date;
        this.weekPredicate = new CurrentWeekPredicate(date);
        this.events.setPredicate(weekPredicate);
        if (!dateSlot.contains(date)) {
            dateSlot.clear();
        }
        title.setText("Timetable from " + weekPredicate.toString());
        construct();
    }

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
    // SchedulePanel.java
    // with modifications (retrieving of date time and computation difference).
    private int getColSpan(Event event) {
        Duration difference = Duration.between(event.getTimeFrom().value, event.getTimeTo().value);
        int colSpan = (int) (difference.toHours() * SCALE_FACTOR);
        long minutes = difference.toMinutes() - (difference.toHours() * 60L);
        if (minutes != 0L) {
            colSpan++;
        }
        return colSpan;
    }

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
    // SchedulePanel.java
    // with no modifications.
    private int getColIndex(LocalTime startTime) {
        int colIndex = startTime.getHour();
        if (startTime.getMinute() != 0) {
            colIndex += 1;
        }
        return colIndex - 1;
    }

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/
    // schedulepanel/SchedulePanel.java
    // with minor modification (renaming of method and additional orElse condition to set default start time).
    private LocalTime getStartTime() {
        Optional<LocalTime> earliestTime = events.stream()
                .map(event -> event.getTimeFrom().value.toLocalTime())
                .reduce((time1, time2) -> (time1.isBefore(time2) ? time1 : time2));
        if (earliestTime.isPresent()) {
            LocalTime localTimeVal = earliestTime.get();
            if (localTimeVal.isAfter(LocalTime.of(19, 0))) {
                return localTimeVal.minusHours(earliestTime.get().getHour() - 19);
            }
            return localTimeVal;
        }
        return LocalTime.of(8, 0);
    }

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/
    // schedulepanel/SchedulePanel.java
    // with minor modification (renaming of method and additional orElse condition to set default end time).
    private LocalTime getEndTime() {
        LocalTime endTime = events.stream()
                .map(event -> event.getTimeTo().value.toLocalTime())
                .reduce((time1, time2) -> (time1.isAfter(time2) ? time1 : time2))
                .filter((time) -> time.isBefore(LocalTime.of(23, 1)))
                .orElse(LocalTime.of(12, 0));

        int hourDiff = endTime.getHour() - getStartTime().getHour();
        if (hourDiff < 4) {
            endTime = endTime.plusHours(4 - hourDiff);
        }
        return endTime;
    }

    private void constructGrid() {
        int earliestTimeIndex = getColIndex(getStartTime());
        int latestTimeIndex = getColIndex(getEndTime());
        int numColumns = NUM_OF_HALF_HOURS - earliestTimeIndex - (NUM_OF_HALF_HOURS - latestTimeIndex);
        createRowConstraints();
        createColConstraints(numColumns);
        styleGridCells(numColumns);
    }

    private void createRowConstraints() {
        if ((gridPane.getRowCount() != (NUM_OF_DAYS + DATE_DISPLAY_BUFFER))) {
            gridPane.getRowConstraints().clear();
            for (int i = 0; i < NUM_OF_DAYS; i++) {
                RowConstraints con = new RowConstraints();
                con.setPrefHeight(ROW_HEIGHT);
                gridPane.getRowConstraints().add(con);
            }
        }
    }

    private void createColConstraints(int numColumns) {
        if ((gridPane.getColumnCount() != numColumns)) {
            hourSlot.clear();
            timeGridPane.getChildren().clear();
            timeGridPane.getColumnConstraints().clear();
            gridPane.getChildren().clear();
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

            populateTime();
        }
    }

    private void styleGridCells(int numColumns) {
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

    private void populateEvents() {
        events.forEach(curr -> {
            // @@author RuiFengg-reused
            // Reused from
            // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
            // SchedulePanel.java
            // with no modification.
            LocalDate eventDate = curr.getTimeFrom().toDate();
            SlotContainer eventSlot = getSlot(curr);
            int colSpan = getColSpan(curr);

            double hour = curr.getTimeFrom().toTime().getHour() + (curr.getTimeFrom().toTime().getMinute() / 60.0);
            int colIndex = hourSlot.indexOf(hour);

            if (colIndex == 0) {
                colIndex = DATE_DISPLAY_BUFFER;
            } else {
                colIndex = colIndex + DATE_DISPLAY_BUFFER;
            }

            if (dateSlot.contains(eventDate)) {
                int rowIndex = dateSlot.indexOf(eventDate);
                gridPane.add(eventSlot.getRoot(), colIndex, rowIndex, colSpan, ROW_SPAN);
            }
        });
    }

    private SlotContainer getSlot(Event event) {
        if (event instanceof Appointment) {
            return new AppointmentSlot((Appointment) event);
        } else {
            return new ScheduleSlot((Schedule) event);
        }
    }

    private void populateDates() {
        for (int rowIndex = 0; rowIndex < NUM_OF_DAYS; rowIndex++) {
            LocalDate currentDate = queryDate.with(DayOfWeek.of(rowIndex + 1));
            dateSlot.add(currentDate);
            DisplayDateSlot slot = new DisplayDateSlot(currentDate);
            gridPane.add(slot.getRoot(), 0, rowIndex, 1, ROW_SPAN);
        }
    }

    private void populateTime() {
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
    }
}
