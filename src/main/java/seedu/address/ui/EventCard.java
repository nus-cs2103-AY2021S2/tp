package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.event.GeneralEvent;

/**
 * An UI component that displays information of a {@code GeneralEvent}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final GeneralEvent generalEvent;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label date;


    /**
     * Creates a {@code EventCard} with the given {@code GeneralEvent} and index to display.
     */
    public EventCard(GeneralEvent generalEvent, int displayedIndex) {
        super(FXML);
        requireAllNonNull(generalEvent, displayedIndex);
        this.generalEvent = generalEvent;
        id.setText(displayedIndex + ". ");
        description.setText(generalEvent.getDescription().description);
        date.setText("Date: " + generalEvent.getDateTime().format(LocalDateTimeUtil.DATETIME_FORMATTER));
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
            && generalEvent.equals(card.generalEvent);
    }
}
