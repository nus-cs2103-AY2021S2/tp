package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.dish.Dish;

/**
 * An UI component that displays information of a {@code dish}.
 */
public class MenuCard extends UiPart<Region> {

    private static final String FXML = "MenuListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Dish dish;

    @FXML
    private HBox menuCardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label price;

    /**
     * Creates a {@code MenuCard} with the given {@code dish} and index to display.
     */
    public MenuCard(Dish dish, int displayedIndex) {
        super(FXML);
        this.dish = dish;
        id.setText(displayedIndex + ". ");
        name.setText(dish.getName());
        price.setText(String.valueOf(dish.getPrice()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MenuCard)) {
            return false;
        }

        // state check
        MenuCard card = (MenuCard) other;
        return id.getText().equals(card.id.getText())
                && dish.equals(card.dish);
    }
}
