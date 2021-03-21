package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Task;

/**
 * An UI component that displays details of a {@code Task} that belongs in a TaskCard.
 */
public class TaskCardDetails extends UiPart<Region> {

    private static final String FXML = "TaskListCardDetails.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    private Label description = new Label();
    private Label recurringSchedule = new Label();

    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label status;
    @FXML
    private Label starttime;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox details;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCardDetails(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        // compulsory fields that are static in the fxml
        setId(displayedIndex);
        setTitle(task);
        setDeadline(task);
        setStartTime(task);
        setStatus(task);
        // Optional fields that are dynamically added. Order of methods (except tags) determine position.
        setTagsIfPresent(task);
        setDescriptionIfPresent(task);
        setRecurringScheduleIfPresent(task);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCardDetails)) {
            return false;
        }

        // state check
        TaskCardDetails card = (TaskCardDetails) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }

    private void setId(int displayedIndex) {
        id.getStyleClass().add("cell_big_label");
        id.setText(displayedIndex + ". ");
    }

    /**
     * Sets text of the label to be title.
     * Asserts that it is not blank because title cannot be created blank.
     *
     * @param task Task to be displayed.
     */
    private void setTitle(Task task) {
        String titleValue = task.getTitle().fullTitle;
        assert !titleValue.isBlank() : "Title cannot be displayed blank.";
        title.getStyleClass().add("cell_big_label");
        title.setText(titleValue);
    }

    /**
     * Sets text of the label to be deadline.
     * Asserts that it is not blank because deadline cannot be created blank.
     *
     * @param task Task to be displayed.
     */
    private void setDeadline(Task task) {
        String deadlineValue = task.getDeadline().value.toString();
        assert !deadlineValue.isBlank() : "Deadline cannot be displayed blank.";
        deadline.getStyleClass().add("cell_small_label");
        deadline.setText(deadlineValue);
    }

    /**
     * Sets text of the label to be startTime.
     * Asserts that it is not blank because startTime cannot be created blank.
     *
     * @param task Task to be displayed.
     */
    private void setStartTime(Task task) {
        String startTimeValue = task.getStartTime().toString();
        assert !startTimeValue.isBlank() : "StartTime cannot be displayed blank.";
        starttime.getStyleClass().add("cell_small_label");
        starttime.setText(startTimeValue);
    }

    /**
     * Sets text of the label to be status.
     * Asserts that it is not blank because status cannot be created blank.
     * Sets style class according to its value.
     *
     * @param task Task to be displayed.
     */
    private void setStatus(Task task) {
        String statusValue = task.getStatus().value;
        assert !statusValue.isBlank() : "Status cannot be displayed blank.";

        boolean isStatusDone = statusValue.equals("done");
        if (isStatusDone) {
            status.getStyleClass().add("status-green");
        } else {
            status.getStyleClass().add("status-red");
        }
        status.setText(statusValue);
    }

    /**
     * Sets text of the label to be description, only if the description field in the task provided is not blank.
     *
     * @param task Task to be displayed.
     */
    private void setDescriptionIfPresent(Task task) {
        String descriptionValue = task.getDescription().value;
        boolean isDescriptionBlank = descriptionValue.isBlank();

        if (isDescriptionBlank) {
            return;
        }

        details.getChildren().add(new Separator());
        description.setText(descriptionValue);
        description.getStyleClass().add("description");
        details.getChildren().add(description);
    }

    /**
     * Sets text of the label to be recurring schedule, only if
     * the recurring schedule field in the task provided is not blank.
     *
     * @param task Task to be displayed.
     */
    private void setRecurringScheduleIfPresent(Task task) {
        String recurringScheduleValue = task.getRecurringSchedule().value;
        boolean isRecurringScheduleBlank = recurringScheduleValue.isBlank();

        if (isRecurringScheduleBlank) {
            return;
        }

        recurringSchedule.setText(recurringScheduleValue);
        recurringSchedule.getStyleClass().add("cell_small_label");
        details.getChildren().add(recurringSchedule);
    }

    /**
     * Creates new Labels to contain each tag present, and add these tag Labels into the FlowPane.
     * This function will return void if no Labels are created if there are no tags.
     *
     * @param task Task to be displayed.
     */
    private void setTagsIfPresent(Task task) {
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
