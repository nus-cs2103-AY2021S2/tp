package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.event.Event;

/**
 * An UI component that displays information of a {@code Event}.
 */
public class EventCardListView extends UiPart<Region> {

    private static final String FXML = "EventCardListView.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
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
    private Label status;
    @FXML
    private Label description;
    @FXML
    private HBox second;


    /**
     * Creates a {@code EventCode} with the given {@code Event} and identifier to display.
     */
    public EventCardListView(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        this.displayedIndex = displayedIndex;

        setInformation();
    }

    private void setInformation() {
        setId();
        setEventName();
        setDescription();
        setPriority();
        setStatus();
        second.setSpacing(5);
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

    private void setStatus() {
        status.setText(event.getStatus().name());
        String styleClassName = event.getPriority().name().toLowerCase() + "-status";
        status.getStyleClass().add(styleClassName);
    }

    private void setDescription() {
        description.setText(event.getDescription().description);
        description.setWrapText(true);
        description.setPadding(new Insets(3, 0, 3, 0));
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
