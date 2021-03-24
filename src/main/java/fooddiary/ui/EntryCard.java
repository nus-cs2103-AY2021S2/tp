package fooddiary.ui;

import java.util.Comparator;

import fooddiary.model.entry.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Entry}.
 */
public class EntryCard extends UiPart<Region> {

    private static final String FXML = "EntryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Entry entry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label rating;
    @FXML
    private Label price;
    @FXML
    private Label address;
    @FXML
    private Label review;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EntryCode} with the given {@code Entry} and index to display.
     */
    public EntryCard(Entry entry, int displayedIndex) {
        super(FXML);
        this.entry = entry;
        id.setText(displayedIndex + ". ");
        name.setText(entry.getName().fullName);
        rating.setText(String.format("Rating: %s / 5", entry.getRating().value));
        price.setText(String.format("Price: $%s", entry.getPrice().value));
        address.setText(entry.getAddress().value);
        review.setText(entry.getReview().toString());
        entry.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagCategory))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagCategory.titleCase())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntryCard)) {
            return false;
        }

        // state check
        EntryCard card = (EntryCard) other;
        return id.getText().equals(card.id.getText())
                && entry.equals(card.entry);
    }
}
