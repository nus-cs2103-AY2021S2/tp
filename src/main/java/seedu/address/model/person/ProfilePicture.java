package seedu.address.model.person;


import javafx.scene.image.Image;

import static java.util.Objects.requireNonNull;

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
