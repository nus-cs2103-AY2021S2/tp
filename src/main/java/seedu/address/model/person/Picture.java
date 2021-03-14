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

    public String getAbsoluteFilePath() {
        return filePath.toAbsolutePath().toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("filePath: ")
                .append(filePath);
        return sb.toString();
    }
}
