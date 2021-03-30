package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;
    public final int id;

    private TaskCardDetails taskCardDetails;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox detailsPlaceholder;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        this.id = displayedIndex;
        setDetails(task, displayedIndex);
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
        boolean isSameId = id == card.id;
        boolean isSameTask = task.equals(card.task);
        return isSameId && isSameTask;
    }

    private void setDetails(Task task, int displayedIndex) {
        taskCardDetails = new TaskCardDetails(task, displayedIndex);
        detailsPlaceholder.getChildren().add(taskCardDetails.getRoot());
    }
}
