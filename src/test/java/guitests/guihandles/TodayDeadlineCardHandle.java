package guitests.guihandles;

import static seedu.address.commons.util.DateUtil.decodeDate;
import static seedu.address.commons.util.DateUtil.decodeDateIntoDay;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.task.deadline.DeadlineWithProject;
import seedu.address.ui.TodayDeadlineCard;

/**
 * Provides a handle to a {@code TodayDeadlineCard}.
 */
public class TodayDeadlineCardHandle extends NodeHandle<Node> {
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String DATE_FIELD_ID = "#date";
    private static final String COMPLETED_FIELD_ID = "#completedLabel";
    private static final String DAY_FIELD_ID = "#day";
    private static final String PROJECT_FIELD_ID = "#projectName";

    private final Label descriptionLabel;
    private final Label dateLabel;
    private final Label completedLabel;
    private final Label dayLabel;
    private final Label projectLabel;

    /**
     * Constructs a {@code TodayDeadlineCardHandle} handler object.
     * @param cardNode Node of {@code TodayDeadlineCard}.
     */
    public TodayDeadlineCardHandle(Node cardNode) {
        super(cardNode);

        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        dateLabel = getChildNode(DATE_FIELD_ID);
        completedLabel = getChildNode(COMPLETED_FIELD_ID);
        dayLabel = getChildNode(DAY_FIELD_ID);
        projectLabel = getChildNode(PROJECT_FIELD_ID);
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

    public String getProjectLabel() {
        return projectLabel.getText();
    }

    /**
     * Returns true if this handle contains a {@code DeadlineWithProject}.
     */
    public boolean equals(DeadlineWithProject deadline) {
        return getDescription().equals(deadline.getDescription())
                && getDate().equals(decodeDate(deadline.getBy()))
                && getDay().equals(decodeDateIntoDay(deadline.getBy()))
                && getCompleted().equals(TodayDeadlineCard.getTextToDisplay(deadline.getIsDone()))
                && getProjectLabel().equals(deadline.getProjectName().toString());
    }
}
