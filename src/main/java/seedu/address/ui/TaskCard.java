package seedu.address.ui;

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
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label deadline;
    @FXML
    private Label taskStatus;
    @FXML
    private Label priority;
    @FXML
    private FlowPane assignees;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().taskTitle);
        description.setText("Description: " + task.getDescription().desc);
        deadline.setText("Deadline: " + task.getDeadline().dateString);
        taskStatus.setText("Status: " + task.getTaskStatus().getStatus());
        priority.setText("Priority: " + task.getPriority().getPriority());
        task.getAssignees().stream()
                .sorted(Comparator.comparing(assignee -> assignee.assigneeName))
                .forEach(assignee -> assignees.getChildren().add(new Label(assignee.assigneeName)));
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
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }

}
