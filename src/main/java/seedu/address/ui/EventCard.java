package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.repeatable.Event;

/**
 * An UI component that displays information of an {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    public static final String MESSAGE_EVENT_NON_REPEATABLE = "%s, %s, %s";
    public static final String MESSAGE_EVENT_REPEATABLE = "Every %s, starting %s, %s";

    private static final String FXML = "EventCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label eventDescription;
    @FXML
    private Label dateTime;

    /**
     * Creates an {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        requireAllNonNull(event, displayedIndex);

        this.event = event;
        id.setText(displayedIndex + ". ");
        eventDescription.setText(event.getDescription());

        if (event.getIsWeekly()) {
            dateTime.setText(String.format(MESSAGE_EVENT_REPEATABLE, DateUtil.decodeDateIntoDay(event.getDate()),
                    DateUtil.decodeDate(event.getDate()), TimeUtil.decodeTime(event.getTime())));
        } else {
            dateTime.setText(String.format(MESSAGE_EVENT_NON_REPEATABLE, DateUtil.decodeDateIntoDay(event.getDate()),
                    DateUtil.decodeDate(event.getDate()), TimeUtil.decodeTime(event.getTime())));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
