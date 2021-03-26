package seedu.address.ui;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ChildTag;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

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
    private ImageView favIcon;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        streamTags(person.getTags()).forEach(tag -> tags.getChildren().add(generateTagLabel(tag)));
        if (person.getFavourite().isFav()) {
            favIcon.setImage(new Image("/images/star_icon_filled.png"));
        } else {
            favIcon.setImage(new Image("/images/star_icon_empty.png"));
        }
    }

    /**
     * Generates a label of different colors based on whether the tag is a ChildTag.
     * @param tag the tag to create a label from.
     * @return Label with the tagName as text and different colors based on the tag type.
     */
    private Label generateTagLabel(Tag tag) {
        Label label = new Label(tag.tagName);
        if (tag instanceof ChildTag) {
            label.setStyle("-fx-background-color: #ff5050");
        }
        return label;
    }

    private Stream<Tag> streamTags(Set<Tag> tagSet) {
        return tagSet.stream()
                .sorted(new TagComparator());
    }

    private class TagComparator implements Comparator<Tag> {

        @Override
        public int compare(Tag tag1, Tag tag2) {
            if (tag1 instanceof ChildTag) {
                if (tag2 instanceof ChildTag) {
                    return String.CASE_INSENSITIVE_ORDER.compare(tag1.tagName, tag2.tagName);
                } else {
                    return -1;
                }
            } else {
                if (tag2 instanceof ChildTag) {
                    return 1;
                } else {
                    return String.CASE_INSENSITIVE_ORDER.compare(tag1.tagName, tag2.tagName);
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
