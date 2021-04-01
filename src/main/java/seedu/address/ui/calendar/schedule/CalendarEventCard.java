package seedu.address.ui.calendar.schedule;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.Event;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.person.Birthday;
import seedu.address.ui.UiPart;

/**
 * A {@code Event} card to show details for event for {@code DayEventList} in calendar window.
 */
public class CalendarEventCard extends UiPart<Region> {
    private static final String FXML = "schedule/CalendarEventCard.fxml";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label time;

    /**
     * Creates new {@code CalendarEventCard} to be show a event in the {@code DayEventList}.
     * @param event
     */
    public CalendarEventCard(Event event) {
        super(FXML);
        this.event = event;
        init();
    }

    private void init() {
        loadCardColour();
        this.description.setText(event.getTag().tagName + ": " + event.getDescription().description);
        String time = TIME_FORMATTER.format(event.getDateTime());
        this.time.setText(time);
    }

    private void loadCardColour() {
        if (event instanceof Birthday) {
            cardPane.setStyle("-fx-background-color: salmon;");
        }
        if (event instanceof Exam) {
            cardPane.setStyle("-fx-background-color: yellow;");
        }
        if (event instanceof Assignment) {
            cardPane.setStyle("-fx-background-color: lightblue;");
        }
        if (event instanceof GeneralEvent) {
            cardPane.setStyle("-fx-background-color: lime;");
        }
    }

}
