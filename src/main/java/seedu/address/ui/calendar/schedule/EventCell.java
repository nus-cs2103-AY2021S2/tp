package seedu.address.ui.calendar.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ScheduleUiUtil.MARGIN_PER_MINUTE;
import static seedu.address.commons.util.ScheduleUiUtil.checkTimePattern;
import static seedu.address.commons.util.ScheduleUiUtil.getMarginFromDateTime;
import static seedu.address.commons.util.ScheduleUiUtil.toAmPmTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Event;
import seedu.address.ui.UiPart;

/**
 * Represents a cell for an event in the {@Code TimeScale} schedule UI.
 */
public class EventCell extends UiPart<Region> {
    //Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
    private static final String FXML = "schedule/EventCell.fxml";
    private static Logger logger = LogsCenter.getLogger(EventCell.class);

    // The height of title label.
    private static final double MINIMUM_CELL_HEIGHT = 13.0;

    @FXML
    private Label startTime;
    @FXML
    private VBox eventHolder;
    @FXML
    private Label title;

    private Event event;

    /**
     * Constructs an event cell with {@code Event};
     *
     * @param event event for the event cell.
     */
    public EventCell(Event event) {
        super(FXML);
        requireNonNull(event);
        this.event = event;

        // Set title and startTime
        title.setText(event.getDescription().description);
        startTime.setText(getTimeFromEvent(event));

        // Violation of LoD, may need to improve.
        // Calculate the height of the cell;
        double height = getTaskCellHeight();
        eventHolder.setPrefHeight(height);

        //only shows the title when the duration is less than an hour.
        if (!(getTaskDuration() > 60)) {
            eventHolder.getChildren().remove(startTime);
            eventHolder.setAlignment(Pos.CENTER);
        }
        logger.info("successfully initialised event cell");
    }

    /**
     * Method used to update the startTime.
     *
     * @param startTimeStr must be in the form of hh:mm AM/PM
     */
    public void setStartTime(String startTimeStr) {
        assert checkTimePattern(startTimeStr);
        this.startTime.setText(startTimeStr);
    }

    /**
     * Calculates the margin top by the task for the TimeScale.
     */
    public double marginTop() {
        return getMarginFromDateTime(event.getDateTime());
    }

    private void setTitle(String titleStr) {
        this.title.setText(titleStr);
    }

    /**
     * Returns string format of starting time of event.
     *
     * @param event event to have its starting time interpreted.
     * @return String format of starting time of event.
     */
    private String getTimeFromEvent(Event event) {
        LocalDateTime dateTime = event.getDateTime();
        DateTimeFormatter formmater = DateTimeFormatter.ofPattern("HH:mm");
        return toAmPmTime(formmater.format(dateTime));
    }


    private double getTaskCellHeight() {
        double calculatedVal = getTaskDuration() * MARGIN_PER_MINUTE;
        return calculatedVal < MINIMUM_CELL_HEIGHT ? MINIMUM_CELL_HEIGHT : calculatedVal;
    }

    private int getTaskDuration() {
        int duration = event.getDuration();
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof EventCell)) {
            return false;
        }

        return this.event.equals(((EventCell) o).event);
    }

}
