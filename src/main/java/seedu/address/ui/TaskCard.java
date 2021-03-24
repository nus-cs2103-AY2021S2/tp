package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {
    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label taskDescription;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TaskCode} to display.
     */
    public TaskCard(Task task) {
        super(FXML);
        this.task = task;
        taskDescription.setText(task.getTaskDescription().description);
        date.setText(task.getDate().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy")));
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return taskDescription.getText().equals(card.taskDescription.getText())
                && task.equals(card.task);
    }
}
