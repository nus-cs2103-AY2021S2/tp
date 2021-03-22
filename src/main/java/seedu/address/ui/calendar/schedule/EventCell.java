package seedu.address.ui.calendar.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ScheduleUiUtil.MARGIN_PER_MINUTE;
import static seedu.address.commons.util.ScheduleUiUtil.checkTimePattern;
import static seedu.address.commons.util.ScheduleUiUtil.getMarginFromDateTime;
import static seedu.address.commons.util.ScheduleUiUtil.toAmPmTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.Event;
import seedu.address.ui.UiPart;

/*
Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
 */
public class EventCell extends UiPart<Region> {
    private static final String FXML = "schedule/EventCell.fxml";

    private static final double MINIMUM_CELL_HEIGHT = 13.0; // The height of title label.

    @FXML
    private Label startTime;

    @FXML
    private VBox taskHolder;

    @FXML
    private Label title;

    private Event event;

    /**
     * Construct a TaskCell from a {@Code task}
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
        taskHolder.setPrefHeight(height);

        //only shows the title when the duration is less than an hour.
        if (!(getTaskDuration() > 60)) {
            taskHolder.getChildren().remove(startTime);
            taskHolder.setAlignment(Pos.CENTER);
        }

    }


    /**
     * Method used to update the startTime.
     * @param startTimeStr must be in the form of hh:mm AM/PM
     */
    public void setStartTime(String startTimeStr) {
        assert checkTimePattern(startTimeStr);
        this.startTime.setText(startTimeStr);
    }

    /**
     * Calculate the margin top by the task for the TimeScale.
     */
    public double marginTop() {
        return getMarginFromDateTime(event.getDateTime());
    }

    /**
     * Method used to update the title.
     * @param titleStr
     */
    private void setTitle(String titleStr) {
        this.title.setText(titleStr);
    }

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
        int duration = event.getTimeTaken();
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