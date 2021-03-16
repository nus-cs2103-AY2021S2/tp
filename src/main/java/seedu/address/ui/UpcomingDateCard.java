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

    public UpcomingDateCard(PersonEvent personEvent) {
        super(FXML);
        this.personEvent = personEvent;
        description.setText(personEvent.getDescription());
        date.setText(personEvent.getDate() + "/" + personEvent.getMonth());
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
