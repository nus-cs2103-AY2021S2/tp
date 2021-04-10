package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label priority;
    @FXML
    private FlowPane categories;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane categoryTitle;
    @FXML
    private ImageView tickImageView;
    @FXML
    private ImageView pinImageView;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().fullName);
        deadline.setText("Deadline: " + task.getDeadline().toString());
        priority.setText("Priority: " + task.getPriority().toString());
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        task.getCategories().stream()
                .sorted(Comparator.comparing(category -> category.categoryName))
                .forEach(category -> categories.getChildren().add(new Label(category.categoryName)));

        if (!task.getCategories().isEmpty()) {
            categoryTitle.getChildren().add(new Label("Category:"));
        }

        if (task.isComplete()) {
            tickImageView.setVisible(true);
        } else {
            tickImageView.setVisible(false);
        }
        if (task.isPinned()) {
            pinImageView.setVisible(true);
        } else {
            pinImageView.setVisible(false);
        }
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
