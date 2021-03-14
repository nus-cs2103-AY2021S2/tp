package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import seedu.address.model.person.Picture;

public class PictureStorageManager implements PictureStorage {

    private final Path dirPath;

    public PictureStorageManager(Path dirPath) {
        this.dirPath = dirPath;
    }

    public Path getDirPath() {
        return dirPath;
    }

    public Picture copyPicture(Path filePath) throws IOException {
        Path newPath = dirPath.resolve(filePath.getFileName());
        Files.copy(filePath, newPath);

        return new Picture(newPath);
    }
}
