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
    private static final String DATE_TIME_FIELD_ID = "#dateTime";

    private final Label idLabel;
    private final Label descriptionLabel;
    private final Label dateTimeLabel;

    /**
     * Constructs an {@code EventCardHandle} handler object.
     * @param cardNode Node of {@code EventCard}.
     */
    public EventCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        dateTimeLabel = getChildNode(DATE_TIME_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getDateTime() {
        return dateTimeLabel.getText();
    }


    /**
     * Returns true if this handle contains an {@code Event}.
     */
    public boolean equals(Event event) {
        return getDescription().equals(event.getDescription())
                && getDateTime().equals(decodeDate(event.getDate()));
    }
}
