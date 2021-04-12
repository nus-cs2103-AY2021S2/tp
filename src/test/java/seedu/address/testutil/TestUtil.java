package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

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
     * Returns the middle index of the passenger in the {@code model}'s passenger list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPassengerList().size() / 2);
    }

    /**
     * Returns the last index of the passenger in the {@code model}'s passenger list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPassengerList().size());
    }

    /**
     * Returns the passenger in the {@code model}'s passenger list at {@code index}.
     */
    public static Passenger getPassenger(Model model, Index index) {
        return model.getFilteredPassengerList().get(index.getZeroBased());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    public static PooledPassengerContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new PooledPassengerContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
