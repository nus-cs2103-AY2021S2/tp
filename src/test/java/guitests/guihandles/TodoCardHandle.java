package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.task.CompletableTodo;
import seedu.address.ui.DeadlineCard;

/**
 * Provides a handle to a {@code TodoCard}.
 */
public class TodoCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String COMPLETED_FIELD_ID = "#completedLabel";

    private final Label idLabel;
    private final Label descriptionLabel;
    private final Label completedLabel;

    /**
     * Constructs a {@code TodoCardHandle} handler object.
     * @param cardNode Node of {@code TodoCard}.
     */
    public TodoCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        completedLabel = getChildNode(COMPLETED_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getCompleted() {
        return completedLabel.getText();
    }

    /**
     * Returns true if this handle contains a {@code CompletableTodo}.
     */
    public boolean equals(CompletableTodo todo) {
        return getDescription().equals(todo.getDescription())
                && getCompleted().equals(DeadlineCard.getTextToDisplay(todo.getIsDone()));
    }
}
