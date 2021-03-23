package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

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
    private Label birthday;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView picture;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        birthday.setText(person.getBirthday().toUi());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        Optional<Picture> personPicture = person.getPicture();
        if (personPicture.isPresent()) {
            File imgFile = new File(personPicture.get().getAbsoluteFilePath());
            try {
                Image userImage = new Image(new FileInputStream(imgFile));
                picture.setImage(userImage);

                if (userImage.getHeight() > userImage.getWidth()) {
                    picture.setViewport(new Rectangle2D(0, 0, userImage.getWidth(), userImage.getWidth()));
                    picture.setFitWidth(100);
                } else {
                    picture.setViewport(new Rectangle2D(0, 0, userImage.getHeight(), userImage.getHeight()));
                    picture.setFitHeight(100);
                }

                Rectangle clip = new Rectangle();
                clip.setWidth(100);
                clip.setHeight(100);
                clip.setArcHeight(100);
                clip.setArcWidth(100);
                picture.setClip(clip);
            } catch (IOException e) {
                throw new RuntimeException("Unable to read input stream for person");
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
