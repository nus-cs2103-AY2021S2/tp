package dog.pawbook.ui;

import java.util.Comparator;

import dog.pawbook.model.owner.Owner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Owner}.
 */
public class OwnerCard extends UiPart<Region> {

    private static final String FXML = "OwnerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Owner owner;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code OwnerCode} with the given {@code Owner} and index to display.
     */
    public OwnerCard(Owner owner, int displayedIndex) {
        super(FXML);
        this.owner = owner;
        id.setText(displayedIndex + ". ");
        name.setText(owner.getName().fullName);
        phone.setText(owner.getPhone().value);
        address.setText(owner.getAddress().value);
        email.setText(owner.getEmail().value);
        owner.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OwnerCard)) {
            return false;
        }

        // state check
        OwnerCard card = (OwnerCard) other;
        return id.getText().equals(card.id.getText())
                && owner.equals(card.owner);
    }
}
