package seedu.address.ui.timetablepanel;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.event.Event;
import seedu.address.model.schedule.Schedule;
import seedu.address.ui.UiPart;

/**
 * Panel containing list of Events.
 */
public class TimeTablePanel extends UiPart<Region> {
    private static final String FXML = "timetablepanel/TimeTablePanel.fxml";

    private static final int SINGLE_COLUMN_WIDTH = 100;
    private static final int ROW_SPAN = 1;
    private static final int GRID_INDEX_BUFFER = 1;
    private static final int NUM_OF_HALF_HOURS = 48;

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final List<LocalDate> dateSlot;
    private final List<Double> hourSlot;
    private final LocalDate queryDate;
    private ObservableList<Event> events;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code ObservableList}.
     */
    public TimeTablePanel(ObservableList<Event> events) {
        super(FXML);

        // Only getting current's week events
        this.events = events.filtered((appointment -> {
            LocalDate now = LocalDate.now();
            LocalDate apptDate = appointment.getTimeFrom().value.toLocalDate();
            LocalDate monday = now.with(DayOfWeek.MONDAY);
            LocalDate sunday = now.with(DayOfWeek.SUNDAY);
            return !apptDate.isBefore(monday) && !apptDate.isAfter(sunday);
        }));

        this.queryDate = LocalDate.now(); // Need to allow command to change the week / view a certain week
        this.dateSlot = new ArrayList<>(7);
        this.hourSlot = new ArrayList<>();
    }

    /**
     * Fills the grid pane with the slots.
     */
    public void construct() {
        logger.info("Constructing Schedule Panel.");
        constructGrid();
        populateDates();
        if (events.size() == 0) {
            logger.info("No appointments in schedule");
            return;
        }
        addAppointmentSlotsToGrid();
    }

    public void reconstruct(ObservableList<Event> events) {
        this.events = events;
        gridPane.getChildren().clear();
        hourSlot.clear();
        construct();
    }

    private int getColSpan(Event event) {
        Duration difference = Duration.between(event.getTimeFrom().value, event.getTimeTo().value);
        int colSpan = (int) (difference.toHours() * 2);
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
                .orElse(LocalTime.of(10, 0));
    }

    private LocalTime getEndTime() {
        return events.stream()
                .map(event -> event.getTimeTo().value.toLocalTime())
                .reduce((time1, time2) -> (time1.isAfter(time2) ? time1 : time2))
                .orElse(LocalTime.of(18, 0));
    }


    private void constructGrid() {
        int earliestTimeIndex = getColIndex(getStartTime());
        int latestTimeIndex = getColIndex(getEndTime());

        int numColumns = NUM_OF_HALF_HOURS - earliestTimeIndex - (NUM_OF_HALF_HOURS - latestTimeIndex);
        for (int i = 0; i < numColumns + GRID_INDEX_BUFFER; i++) {
            ColumnConstraints con = new ColumnConstraints();
            if (i == 0) {
                con.setPrefWidth(80);
            } else {
                con.setPrefWidth(SINGLE_COLUMN_WIDTH);
            }
            gridPane.getColumnConstraints().add(con);
            ColumnConstraints conHalf = new ColumnConstraints();
            conHalf.setPrefWidth(SINGLE_COLUMN_WIDTH);
            gridPane.getColumnConstraints().add(conHalf);
        }

        for (int i = 0; i < (numColumns + GRID_INDEX_BUFFER) * 2; i++) {
            for (int j = 0; j <= 7; j++) {
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

    private void addAppointmentSlotsToGrid() {
        events.forEach(curr -> {
            LocalDate currAppointmentDate = curr.getTimeFrom().value.toLocalDate();
            SlotContainer appointmentSlot = getSlot(curr);
            int colSpan = getColSpan(curr);
            double hour = curr.getTimeFrom().value.toLocalTime().getHour() + (curr.getTimeFrom().value.toLocalTime().getMinute() / 60.0);
            int colIndex = hourSlot.indexOf(hour);

            if (colIndex == 0) {
                colIndex = GRID_INDEX_BUFFER;
            } else {
                colIndex = colIndex + GRID_INDEX_BUFFER;
            }

            if (dateSlot.contains(currAppointmentDate)) {
                int rowIndex = dateSlot.indexOf(currAppointmentDate) + GRID_INDEX_BUFFER;
                gridPane.add(appointmentSlot.getRoot(), colIndex, rowIndex, colSpan, ROW_SPAN);
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

        for (int i = start.getHour(); i <= end.getHour(); i++) {
            hourSlot.add((double) i);
            hourSlot.add(i + 0.5);
        }

        for (int i = 1; i <= 7; i++) { // Add date displays for entire week
            LocalDate currentDate = queryDate.with(DayOfWeek.of(i));
            dateSlot.add(currentDate);

            DisplayDateSlot slot = new DisplayDateSlot(currentDate);
            gridPane.add(slot.getRoot(), 0, i, 1, ROW_SPAN);
        }
    }
}
