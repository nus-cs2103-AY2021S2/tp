package seedu.smartlib.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.smartlib.model.reader.Reader;

/**
 * An UI component that displays information of a {@code Reader}.
 */
public class ReaderCard extends UiPart<Region> {

    private static final String FXML = "ReaderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Reader reader;

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
    private FlowPane borrows;

    /**
     * Creates a {@code ReaderCode} with the given {@code Reader} and index to display.
     */
    public ReaderCard(Reader reader, int displayedIndex) {
        super(FXML);
        this.reader = reader;
        id.setText(displayedIndex + ". ");
        name.setText(reader.getName().fullName);
        phone.setText(reader.getPhone().value);
        address.setText(reader.getAddress().value);
        email.setText(reader.getEmail().value);
        reader.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        reader.getBorrows().forEach((key, value) -> borrows.getChildren()
                .add(new Label(key.fullName + ", borrowed on: " + value.value + "|||")));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReaderCard)) {
            return false;
        }

        // state check
        ReaderCard card = (ReaderCard) other;
        return id.getText().equals(card.id.getText())
                && reader.equals(card.reader);
    }
}
