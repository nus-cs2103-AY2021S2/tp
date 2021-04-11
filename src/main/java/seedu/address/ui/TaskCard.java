package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Task;
import seedu.address.model.tag.State;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskCard.fxml";

    private static final String STYLE_PRIORITY_TAG_LOW = "-fx-background-color: green;";

    private static final String STYLE_PRIORITY_TAG_MEDIUM = "-fx-background-color: yellow; -fx-text-fill: black;";

    private static final String STYLE_PRIORITY_TAG_HIGH = "-fx-background-color: red;";

    private static final String STATUS_PREFIX = "Status: ";

    private static final String FINISHED_EMOJI = "finished ☑";

    private static final String UNFINISHED_EMOJI = "not finished ☒";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TaskTracker level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label taskName;
    @FXML
    private Label priorityTag;
    @FXML
    private Label moduleCode;
    @FXML
    private Label deadlineDate;
    @FXML
    private Label deadlineTime;
    @FXML
    private Label status;
    @FXML
    private Label weightage;
    @FXML
    private Label id;
    @FXML
    private Label notes;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskName.setText(task.getTaskName().fullName);
        if (task.getStatus().hasFinished()) {
            status.setText(STATUS_PREFIX + FINISHED_EMOJI);
        } else {
            status.setText(STATUS_PREFIX + UNFINISHED_EMOJI);
        }
        priorityTag.setText(task.getPriorityTag().getTagName());
        priorityTag.styleProperty().bind(Bindings.createStringBinding(() -> {
            State priorityTagLevel = task.getPriorityTag().getState();
            switch (priorityTagLevel) {
            case HIGH:
                return STYLE_PRIORITY_TAG_HIGH;
            case MEDIUM:
                return STYLE_PRIORITY_TAG_MEDIUM;
            case LOW:
                return STYLE_PRIORITY_TAG_LOW;
            default:
                return "";
            }
        }));
        moduleCode.setText(task.getModuleCode().moduleCode);
        deadlineDate.setText("Submission date : " + task.getDeadlineDate().toString());
        deadlineTime.setText("Submission time : " + task.getDeadlineTime().toString());
        weightage.setText("Weightage : " + task.getWeightage().toString());
        notes.setText("Notes: " + task.getNotes().value);
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
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
