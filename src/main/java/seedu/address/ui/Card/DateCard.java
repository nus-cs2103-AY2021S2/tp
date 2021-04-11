package seedu.address.ui.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.date.ImportantDate;
import seedu.address.ui.UiPart;


/**
 * An UI component that displays information of an {@code Important Date}.
 */
public class DateCard extends UiPart<Region> {

    private static final String FXML = "DateListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ImportantDate importantDate;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label details;
    @FXML
    private Label id;

    /**
     * Creates a {@code DateCard} with the given {@code Important Date} and index to display.
     */
    public DateCard(ImportantDate importantDate, int displayedIndex) {
        super(FXML);
        this.importantDate = importantDate;
        id.setText(displayedIndex + ". ");
        description.setText(importantDate.getDescription().description);
        details.setText(importantDate.getDetails().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateCard)) {
            return false;
        }

        // state check
        DateCard card = (DateCard) other;
        return id.getText().equals(card.id.getText())
                && importantDate.equals(card.importantDate);
    }

}
