package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.event.Event;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class EventCardListView extends UiPart<Region> {

    private static final String FXML = "EventCardListView.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private VBox cardPane;
    @FXML
    private Label eventName;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label description;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public EventCardListView(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText("#" + displayedIndex);
        eventName.setText(event.getName().eventName);
        status.setText(event.getStatus().name());
        description.setText(event.getDescription().description);
        /* For v.1.3
        event.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
         */
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCardListView)) {
            return false;
        }

        // state check
        EventCardListView card = (EventCardListView) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
