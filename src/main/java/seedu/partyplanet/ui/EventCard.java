package seedu.partyplanet.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.partyplanet.model.date.Date;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.person.Remark;

/**
 * An UI component that displays information of a {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox details;
    @FXML
    private Label name;
    @FXML
    private Label id;


    /**
     * Creates a {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        name.setText(getTitle(event));
        if (!Date.isEmptyDate(event.getDate())) {
            addDetail(event.getDate().displayValue);
        }
        if (!Remark.isEmptyRemark(event.getDetails())) {
            addDetail(event.getDetails().value);
        }
    }

    private String getTitle(Event event) {
        String name = event.getName().toString();
        return name + " " + event.getStatus();
    }

    /**
     * Adds a new label to the contact with the following detail
     */
    private void addDetail(String detail) {
        Label label = new Label();
        label.setText(detail);
        label.getStyleClass().add("cell_small_label");
        details.getChildren().add(label);
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
