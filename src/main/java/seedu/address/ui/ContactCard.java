package seedu.address.ui;

import static seedu.address.ui.UiUtil.generateTagLabel;
import static seedu.address.ui.UiUtil.streamTags;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.contact.Contact;

/**
 * An UI component that displays information of a {@code Contact}.
 */
public class ContactCard extends UiPart<Region> {

    private static final String FXML = "ContactListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Contact contact;

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
    @FXML
    private Button favIcon;

    /**
     * Creates a {@code ContactCode} with the given {@code Contact} and index to display.
     */
    public ContactCard(Contact contact, int displayedIndex) {
        super(FXML);
        this.contact = contact;
        id.setText(displayedIndex + ". ");
        name.setText(contact.getName().fullName);
        checkForPlaceholder(contact.getPhone().value, phone);
        checkForPlaceholder(contact.getAddress().value, address);
        checkForPlaceholder(contact.getEmail().value, email);
        streamTags(contact.getTags()).forEach(tag -> tags.getChildren().add(generateTagLabel(tag)));
        checkForFavorite();
    }

    private void checkForFavorite() {
        if (!contact.getFavourite().isFav()) {
            favIcon.setStyle("-fx-background-color:transparent");
        }
    }

    private void checkForPlaceholder(String value, Label label) {
        if (value.equals("NIL")) {
            label.setManaged(false);
        } else {
            label.setManaged(true);
            label.setText(value);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactCard)) {
            return false;
        }

        // state check
        ContactCard card = (ContactCard) other;
        return id.getText().equals(card.id.getText())
                && contact.equals(card.contact);
    }
}
