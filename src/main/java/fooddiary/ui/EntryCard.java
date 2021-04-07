package fooddiary.ui;

import java.util.Comparator;

import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Price;
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;
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
    private Label reviews;
    @FXML
    private FlowPane tagCategories;
    @FXML
    private FlowPane tagSchools;

    /**
     * Creates a {@code EntryCode} with the given {@code Entry} and index to display.
     */
    public EntryCard(Entry entry, int displayedIndex) {
        super(FXML);
        this.entry = entry;
        id.setText(displayedIndex + ". ");
        name.setText(entry.getName().fullName);
        rating.setText(String.format("Rating: %s / 5", entry.getRating().value));
        price.setText(String.format("Price: %s" + "%s", Price.PRICE_DOLLAR_SIGN, entry.getPrice().value));
        address.setText(String.format("Address: %s", entry.getAddress().value));
        String reviewStr = "";
        for (int i = 0; i < entry.getReviews().size(); i++) {
            reviewStr += entry.getReviews().get(i).value + "\n";
        }
        reviews.setText(String.format("Reviews:\n%s", reviewStr));

        entry.getTagCategories().stream()
                .sorted(Comparator.comparing(TagCategory::getTag))
                .forEach(tag -> tagCategories.getChildren().add(new Label(tag.getTag())));

        entry.getTagSchools().stream()
                .sorted(Comparator.comparing(TagSchool::getTag))
                .forEach(tag -> tagSchools.getChildren().add(new Label(tag.getTag())));
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
