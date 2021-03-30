package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.person.Event;

public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventCard.fxml";

    public static final int HEIGHT = 41;

    public final Event event;

    @FXML
    private Label description;

    @FXML
    private Label datetime;

    /**
     * Creates a {@code EventCard} with the given {@code Event}.
     * @param event Event to display
     */
    public EventCard(Event event) {
        super(FXML);
        this.event = event;
        description.setText(event.getDescription());
        String timeString = (event.hasTime() ? String.format(", %s", TimeUtil.toUi(event.getTime())) : "");
        datetime.setText(DateUtil.toUi(event.getDate()) + timeString);
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
        return event.equals(card.event);
    }
}
