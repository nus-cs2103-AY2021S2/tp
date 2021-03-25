package seedu.storemando.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.storemando.model.expirydate.ItemExpiringPredicate;
import seedu.storemando.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemCard extends UiPart<Region> {

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/storeMando-level4/issues/336">The issue on StoreMando level 4</a>
     */

    private static final String FXML = "ItemListCard.fxml";

    public final Item item;

    private ItemExpiringPredicate expiredItemPredicate = new ItemExpiringPredicate((long) 0);
    private ItemExpiringPredicate itemExpireInThreeDaysPredicate = new ItemExpiringPredicate((long) 3);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label quantity;
    @FXML
    private Label locations;
    @FXML
    private Text expiryDate;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ".");
        name.setText(item.getItemName().fullName);
        locations.setText("Location: " + item.getLocation().value);
        quantity.setText("Quantity: " + item.getQuantity().value);
        if (item.getExpiryDate().getExpiryDate() != null) {
            expiryDate.setText("Expiry Date: " + item.getExpiryDate().toFormattedString());
            expiryDate.setFill(expiryDateColorCode(item));
        }
        item.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Return the color code for each item expiry date
     * @param item   The item that contains expiry date
     * @return       The resulting color of the item expiry date text
     */
    private Color expiryDateColorCode(Item item) {
        if (expiredItemPredicate.test(item)) {
            return new Color(1, 0, 0, 1);
        }
        if (itemExpireInThreeDaysPredicate.test(item)) {
            return Color.DARKORANGE;
        }
        return Color.BLACK;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemCard)) {
            return false;
        }

        // state check
        ItemCard card = (ItemCard) other;
        return id.getText().equals(card.id.getText())
            && item.equals(card.item);
    }
}

