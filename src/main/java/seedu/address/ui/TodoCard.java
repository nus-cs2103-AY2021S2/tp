package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.CompletableTodo;

/**
 * An UI component that displays information of a {@code CompletableTodo}.
 */
public class TodoCard extends UiPart<Region> {

    private static final String FXML = "TodoCard.fxml";

    public final CompletableTodo todo;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label completedLabel;

    /**
     * Creates a {@code TodoCard} with the given {@code CompletableTodo} and index to display.
     */
    public TodoCard(CompletableTodo todo, int displayedIndex) {
        super(FXML);
        this.todo = todo;
        id.setText(displayedIndex + ". ");
        description.setText(todo.getDescription());
        completedLabel.setText(getTextToDisplay(todo.getIsDone()));
    }

    /**
     * Returns the text of the completed label that is to be displayed.
     *
     * @return {@code String} containing the text that is to be displayed.
     */
    public static String getTextToDisplay(boolean isDone) {
        return isDone ? "✔" : "";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TodoCard)) {
            return false;
        }

        // state check
        TodoCard card = (TodoCard) other;
        return id.getText().equals(card.id.getText())
                && todo.equals(card.todo);
    }
}
