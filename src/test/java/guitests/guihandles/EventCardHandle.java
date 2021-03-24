package guitests.guihandles;

import static seedu.address.commons.util.DateUtil.decodeDate;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.task.repeatable.Event;

/**
 * Provides a handle to an {@code EventCard}.
 */
public class EventCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String DESCRIPTION_FIELD_ID = "#eventDescription";
    private static final String INTERVAL_FIELD_ID = "#interval";
    private static final String DATE_FIELD_ID = "#date";

    private final Label idLabel;
    private final Label descriptionLabel;
    private final Label intervalLabel;
    private final Label dateLabel;

    /**
     * Constructs an {@code EventCardHandle} handler object.
     * @param cardNode Node of {@code EventCard}.
     */
    public EventCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        intervalLabel = getChildNode(INTERVAL_FIELD_ID);
        dateLabel = getChildNode(DATE_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getInteval() {
        return intervalLabel.getText();
    }

    public String getDate() {
        return dateLabel.getText();
    }

    /**
     * Returns true if this handle contains an {@code Event}.
     */
    public boolean equals(Event event) {
        return getDescription().equals(event.getDescription())
                && getInteval().equals(event.getRecurrence().toString())
                && getDate().equals(decodeDate(event.getAt()));
    }
}
