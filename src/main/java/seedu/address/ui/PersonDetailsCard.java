package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

public class PersonDetailsCard extends UiPart<Region> {

    private static final String FXML = "PersonDetailsCard.fxml";

    public final Person person;

    @FXML
    private Label name;
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
    private VBox dates;
    @FXML
    private VBox meetings;
    @FXML
    private ImageView picture;

    public PersonDetailsCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        birthday.setText(person.getBirthday().toUi());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // Temporary UI to test dates and meetings
        person.getDates().forEach(date -> dates.getChildren().add(new Label(date.toString())));
        person.getMeetings().forEach(meeting -> meetings.getChildren().add(new Label(meeting.toUi())));

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
        } else {
            picture.setVisible(false);
            picture.setManaged(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetailsCard)) {
            return false;
        }

        // state check
        PersonDetailsCard card = (PersonDetailsCard) other;
        return person.equals(card.person);
    }
}
