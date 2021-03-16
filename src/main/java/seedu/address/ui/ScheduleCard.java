package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.schedule.Schedule;

/**
 * An UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {
    private static final String FXML = "ScheduleListCard.fxml";

    public final Schedule schedule;

    @FXML
    private HBox cardPane;
    @FXML
    private Label scheduleDescription;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ScheduleCode} to display.
     */
    public ScheduleCard(Schedule schedule) {
        super(FXML);
        this.schedule = schedule;
        scheduleDescription.setText(schedule.getScheduleDescription().description);
        startDate.setText("From: "
                + schedule.getStartDate().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy h:mm a")));
        endDate.setText("To: "
                + schedule.getEndDate().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy h:mm a")));
        schedule.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
