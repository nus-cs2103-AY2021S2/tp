package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.model.person.Picture;

public interface PictureStorage {

    /**
     * Returns the path to the directory storing pictures
     */
    Path getDirPath();

    /**
     * Copies the picture from the given path to the picture directory. Assumes that the given file path is correct
     */
    Picture copyPicture(Path filePath) throws IOException;
}
