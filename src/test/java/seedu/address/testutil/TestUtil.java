package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.identifier.Identifier;
import seedu.address.commons.core.identifier.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle identifier of the event in the {@code model}'s event list.
     */
    public static Identifier getMidIdentifier(Model model) {
        return Identifier.fromIdentifier(model.getFilteredEventList().size() / 2);
    }

    /**
     * Returns the last identifier of the event in the {@code model}'s event list.
     */
    public static Identifier getLastIdentifier(Model model) {
        return Identifier.fromIdentifier(model.getFilteredEventList().size());
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
    public static Person getPerson(Model model, Index index) {
        return model.getFilteredPersonList().get(index.getZeroBased());
    }
}
