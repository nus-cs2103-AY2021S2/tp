package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Provides a handle to a {@code TodayEventCard}.
 */
public class TodayEventCardHandle extends NodeHandle<Node> {
    private static final String DESCRIPTION_FIELD_ID = "#eventDescription";
    private static final String DATE_TIME_FIELD_ID = "#dateTime";
    private static final String PROJECT_FIELD_ID = "#projectName";

    private final Label descriptionLabel;
    private final Label dateTimeLabel;
    private final Label projectName;

    /**
     * Constructs an {@code TodayEventCardHandle} handler object.
     * @param cardNode Node of {@code TodayEventCard}.
     */
    public TodayEventCardHandle(Node cardNode) {
        super(cardNode);

        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        dateTimeLabel = getChildNode(DATE_TIME_FIELD_ID);
        projectName = getChildNode(PROJECT_FIELD_ID);
    }


    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getDateTime() {
        return dateTimeLabel.getText();
    }

    public String getProjectName() {
        return projectName.getText();
    }
}
