package guitests.guihandles;

import static seedu.address.commons.util.DateUtil.decodeDate;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.ui.CompletableDeadlineCard;

/**
 * Provides a handle to a {@code CompletableDeadlineCard}.
 */
public class CompletableDeadlineCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String DATE_FIELD_ID = "#date";
    private static final String COMPLETED_FIELD_ID = "#completedLabel";

    private final Label idLabel;
    private final Label descriptionLabel;
    private final Label dateLabel;
    private final Label completedLabel;

    /**
     * Constructs a {@code CompletableDeadlineCardHandle} handler object.
     * @param cardNode Node of {@code CompletableDeadlineCard}.
     */
    public CompletableDeadlineCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        dateLabel = getChildNode(DATE_FIELD_ID);
        completedLabel = getChildNode(COMPLETED_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getDate() {
        return dateLabel.getText();
    }

    public String getCompleted() {
        return completedLabel.getText();
    }

    /**
     * Returns true if this handle contains a {@code CompletableDeadline}.
     */
    public boolean equals(CompletableDeadline deadline) {
        return getDescription().equals(deadline.getDescription())
                && getDate().equals(decodeDate(deadline.getBy()))
                && getCompleted().equals(CompletableDeadlineCard.getTextToDisplay(deadline.getIsDone()));
    }
}
