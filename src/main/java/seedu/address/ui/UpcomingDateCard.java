package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.PersonEvent;

public class UpcomingDateCard extends UiPart<Region> {

    private static final String FXML = "UpcomingDateCard.fxml";

    public final PersonEvent personEvent;

    @FXML
    private VBox dateCardPane;

    @FXML
    private Label description;

    @FXML
    private Label date;

    /**
     * Creates a {@code UpcomingDateCard} with the given {@code PersonEvent}.
     * @param personEvent Upcoming date event to display.
     */
    public UpcomingDateCard(PersonEvent personEvent) {
        super(FXML);
        this.personEvent = personEvent;
        description.setText(personEvent.getDescription());
        date.setText(personEvent.getFormattedDate());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpcomingDateCard)) {
            return false;
        }

        // state check
        UpcomingDateCard card = (UpcomingDateCard) other;
        return personEvent.equals(card.personEvent);
    }
}
