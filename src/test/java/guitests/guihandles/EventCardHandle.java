package guitests.guihandles;

import static seedu.address.commons.util.DateUtil.decodeDate;
import static seedu.address.commons.util.DateUtil.decodeDateIntoDay;
import static seedu.address.commons.util.TimeUtil.decodeTime;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.task.repeatable.Event;

/**
 * Provides a handle to an {@code EventCard}.
 */
public class EventCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String DESCRIPTION_FIELD_ID = "#eventDescription";
    private static final String DATE_FIELD_ID = "#date";
    private static final String TIME_FIELD_ID = "#time";
    private static final String DAY_FIELD_ID = "#day";

    private final Label idLabel;
    private final Label descriptionLabel;
    private final Label dateLabel;
    private final Label timeLabel;
    private final Label dayLabel;

    /**
     * Constructs an {@code EventCardHandle} handler object.
     * @param cardNode Node of {@code EventCard}.
     */
    public EventCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        dateLabel = getChildNode(DATE_FIELD_ID);
        timeLabel = getChildNode(TIME_FIELD_ID);
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

    public String getTime() {
        return timeLabel.getText();
    }

    public String getDay() {
        return dayLabel.getText();
    }

    /**
     * Returns true if this handle contains an {@code Event}.
     */
    public boolean equals(Event event) {
        return getDescription().equals(event.getDescription())
                && getDate().equals(decodeDate(event.getDate()))
                && getTime().equals(decodeTime(event.getTime()))
                && getDay().equals(decodeDateIntoDay(event.getDate()));
    }
}
