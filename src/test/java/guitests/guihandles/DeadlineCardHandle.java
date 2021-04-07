package guitests.guihandles;

import static seedu.address.commons.util.DateUtil.decodeDate;
import static seedu.address.commons.util.DateUtil.decodeDateIntoDay;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.ui.DeadlineCard;

/**
 * Provides a handle to a {@code DeadlineCard}.
 */
public class DeadlineCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String DATE_FIELD_ID = "#date";
    private static final String COMPLETED_FIELD_ID = "#completedLabel";
    private static final String DAY_FIELD_ID = "#day";

    private final Label idLabel;
    private final Label descriptionLabel;
    private final Label dateLabel;
    private final Label completedLabel;
    private final Label dayLabel;

    /**
     * Constructs a {@code DeadlineCardHandle} handler object.
     * @param cardNode Node of {@code DeadlineCard}.
     */
    public DeadlineCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        dateLabel = getChildNode(DATE_FIELD_ID);
        completedLabel = getChildNode(COMPLETED_FIELD_ID);
        dayLabel = getChildNode(DAY_FIELD_ID);
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

    public String getDay() {
        return dayLabel.getText();
    }

    /**
     * Returns true if this handle contains a {@code CompletableDeadline}.
     */
    public boolean equals(CompletableDeadline deadline) {
        return getDescription().equals(deadline.getDescription())
                && getDate().equals(decodeDate(deadline.getBy()))
                && getDay().equals(decodeDateIntoDay(deadline.getBy()))
                && getCompleted().equals(DeadlineCard.getTextToDisplay(deadline.getIsDone()));
    }
}
