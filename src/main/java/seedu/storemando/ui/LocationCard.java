package seedu.storemando.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.storemando.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class LocationCard extends UiPart<Region> {

    private static final String FXML = "LocationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/storeMando-level4/issues/336">The issue on StoreMando level 4</a>
     */

    public final Item item;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label quantity;
    @FXML
    private Label locations;


    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public LocationCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ".");
        locations.setText("Location: " + item.getLocation().value);
        quantity.setText("Quantity: " + item.getQuantity().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LocationCard)) {
            return false;
        }

        // state check
        LocationCard card = (LocationCard) other;
        return id.getText().equals(card.id.getText())
            && locations.getText().equals(card.locations.getText());
    }
}
