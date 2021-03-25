package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.event.Event;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class EventCardKanbanView extends UiPart<Region> {

    private static final String FXML = "EventCardKanbanView.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    protected final Event event;
    protected final int displayedIndex;

    @FXML
    private VBox cardPane;
    @FXML
    private Label eventName;
    @FXML
    private Label id;
    @FXML
    private Label priority;
    @FXML
    private Label description;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public EventCardKanbanView(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        this.displayedIndex = displayedIndex;

        setInformation();

        /* For v.1.3
        event.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
         */
    }

    private void setInformation() {
        setId();
        setEventName();
        setDescription();
        setPriority();
    }

    private void setId() {
        id.setText("#" + displayedIndex);
        String styleClassName = "eventId";
        id.getStyleClass().add(styleClassName);
    }

    private void setEventName() {
        eventName.setText(event.getName().eventName);
        eventName.setWrapText(true);
    }


    private void setPriority() {
        priority.setText(event.getPriority().name());

        String styleClassName = event.getPriority().name().toLowerCase() + "-priority";
        priority.getStyleClass().add(styleClassName);

    }

    private void setDescription() {
        description.setText(event.getDescription().description);
        description.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCardKanbanView)) {
            return false;
        }

        // state check
        EventCardKanbanView card = (EventCardKanbanView) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
