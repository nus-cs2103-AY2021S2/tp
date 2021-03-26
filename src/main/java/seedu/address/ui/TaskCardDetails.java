package seedu.address.ui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Duration;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

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

    /**
     * Fields of each task that is to be rendered onto the view (not fields that are compulsory in the add command).
     */
    private final List<String> compulsorilyRenderedFields =
            List.of(Title.FIELD_NAME, Status.FIELD_NAME, Duration.FIELD_NAME, Deadline.FIELD_NAME);

    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label status;
    @FXML
    private Label duration;
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
        setId(displayedIndex);
        setFields(task);
        setTagsIfPresent(task);

        setStyleClasses();
    }

    private void setId(int displayedIndex) {
        id.getStyleClass().add("cell_big_label");
        id.setText(displayedIndex + ". ");
    }

    /**
     * Gets optional fields from the task and creates labels dynamically for their string values if present.
     *
     * @param task Task with fields to be rendered.
     */
    private void setFields(Task task) {
        HashMap<String, String> optionalFieldStrings = task.getFields();
        optionalFieldStrings.forEach((fieldName, fieldValue) -> {
            if (fieldValue.isBlank()) {
                return;
            } else if (compulsorilyRenderedFields.contains(fieldName)) {
                setFieldLabel((Label) details.lookup("#" + fieldName.toLowerCase()), fieldValue);
            } else {
                createFieldLabel(fieldName, fieldValue);
            }
        });
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

    /**
     * Sets style class for individual nodes under details. Styles of optional fields are not set here because they
     * are created dynamically.
     */
    private void setStyleClasses() {
        title.getStyleClass().add("cell_big_label");
        id.getStyleClass().add("cell_big_label");
        deadline.getStyleClass().add("cell_small_label");
        duration.getStyleClass().add("cell_small_label");
        boolean isStatusDone = status.getText().equals("done");
        if (isStatusDone) {
            status.getStyleClass().add("status-green");
        } else {
            status.getStyleClass().add("status-red");
        }
    }

    /**
     * Sets the value of the field of the task into an existing label. Asserts that the value is not blank because the
     * label is compulsory.
     *
     * @param label      Static label that is already existing in the view.
     * @param fieldValue Value to be set as the text of the label.
     */
    private void setFieldLabel(Label label, String fieldValue) {
        assert !fieldValue.isBlank() : label.getId() + " label cannot be blank as it is compulsory.";
        label.setText(fieldValue);
    }

    /**
     * Creates a new label for the fieldValue passed in. Sets its style class according to their names. Asserts the
     * value is not blank because a label should not be created with a blank value.
     *
     * @param fieldName  Name of the field of the task.
     * @param fieldValue Value of the field of the task.
     */
    private void createFieldLabel(String fieldName, String fieldValue) {
        assert !fieldValue.isBlank() : fieldName + " label cannot be created with blank value.";
        Label newLabel = new Label(fieldValue);

        if (fieldName.equals(Description.FIELD_NAME)) {
            details.getChildren().add(new Separator());
            newLabel.getStyleClass().add("description");
        } else if (fieldName.equals(RecurringSchedule.FIELD_NAME)) {
            newLabel.getStyleClass().clear();
            newLabel.getStyleClass().add("recurring-schedule");
        } else {
            // Sets style of every other label.
            newLabel.getStyleClass().add("cell_small_label");
        }
        details.getChildren().add(newLabel);
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
}
