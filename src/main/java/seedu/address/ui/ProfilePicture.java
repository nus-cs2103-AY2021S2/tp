package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

public class ProfilePicture extends UiPart<Region> {

    private static final String FXML = "ProfilePicture.fxml";

    public final Person person;

    @FXML
    private ImageView picture;

    /**
     * Creates a {@code ProfilePicture} with the given {@code Person}.
     */
    public ProfilePicture(Person person, Insets margin) {
        super(FXML);
        this.person = person;

        StackPane.setMargin(picture, margin);

        Optional<Picture> personPicture = person.getPicture();

        if (personPicture.isPresent()) {
            File imgFile = new File(personPicture.get().getAbsoluteFilePath());
            try {
                Image userImage = new Image(new FileInputStream(imgFile));
                picture.setImage(userImage);

                // Set viewport to center of image
                if (userImage.getHeight() > userImage.getWidth()) {
                    picture.setViewport(
                            new Rectangle2D(0, (userImage.getHeight() - userImage.getWidth()) / 2,
                                    userImage.getWidth(), userImage.getWidth()));
                    picture.setFitWidth(100);
                } else {
                    picture.setViewport(
                            new Rectangle2D((userImage.getWidth() - userImage.getHeight()) / 2, 0,
                                    userImage.getHeight(), userImage.getHeight()));
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
        if (!(other instanceof ProfilePicture)) {
            return false;
        }

        // state check
        ProfilePicture profilePicture = (ProfilePicture) other;
        return person.equals(profilePicture.person);
    }
}
