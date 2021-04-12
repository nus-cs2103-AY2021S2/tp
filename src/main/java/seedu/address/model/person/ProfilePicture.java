package seedu.address.model.person;


import static java.util.Objects.requireNonNull;

import javafx.scene.image.Image;

/**
 * A class representing a person's profile picture in the model.
 */

public class ProfilePicture {
    public final Image picture;
    public ProfilePicture(Image picture) {
        requireNonNull(picture);
        this.picture = picture;
    }
}
