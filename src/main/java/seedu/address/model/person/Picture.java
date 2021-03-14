package seedu.address.model.person;

import java.nio.file.Path;

public class Picture {
    Path filePath;

    public Picture(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }
}
