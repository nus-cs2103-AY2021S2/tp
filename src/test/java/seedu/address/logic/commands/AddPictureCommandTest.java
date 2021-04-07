package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FILE_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE_EXTENSION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

public class AddPictureCommandTest {

    private static UserPrefs userPrefs;

    private final Path testFilesDir = Path.of("src", "test", "data", "PictureTest");
    private final Path fileNotFoundPath = testFilesDir.resolve("non_existant.jpeg");
    private final Path fileWrongExtPath = testFilesDir.resolve("invalid_format.txt");
    private final Path validPath = testFilesDir.resolve("picture.jpg");
    private final Path validPathWithSpaces = testFilesDir.resolve("picture with space.jpg");
    private final Model model = new ModelManager(getTypicalAddressBook(), userPrefs);

    @BeforeAll
    public static void initPictureDir() {
        userPrefs = new UserPrefs();
        Path tempPictureDir;
        try {
            tempPictureDir = Files.createTempDirectory("");
            userPrefs.setPictureStorageDirPath(tempPictureDir);
            tempPictureDir.toFile().deleteOnExit();
        } catch (IOException ioe) {
            throw new TestAbortedException("Unable to create temp directory for AddPictureCommandTest: " + ioe);
        }
    }

    @AfterAll
    public static void deletePictureDir() {
        File pictureDir = userPrefs.getPictureStorageDirPath().toFile();

        for (File file : pictureDir.listFiles()) {
            file.delete();
        }

        pictureDir.delete();
    }

    @Test
    public void execute_addPictureInvalidFile_failure() {
        AddPictureCommand cmd;

        // file not found
        cmd = new AddPictureCommand(INDEX_FIRST_PERSON, fileNotFoundPath);
        assertCommandFailure(cmd, model, String.format(MESSAGE_FILE_NOT_FOUND, fileNotFoundPath));

        // wrong extension
        cmd = new AddPictureCommand(INDEX_FIRST_PERSON, fileWrongExtPath);
        assertCommandFailure(cmd, model, String.format(MESSAGE_INVALID_FILE_EXTENSION, fileWrongExtPath,
                Picture.ALLOWED_FILE_EXTENSIONS_STRING));
    }

    public void testAddPicture(AddPictureCommand cmd) {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(AddPictureCommand.MESSAGE_ADD_PICTURE_SUCCESS, firstPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage);

        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assertTrue(Files.exists(editedPerson.getPicture().get().getFilePath()));
    }

    @Test
    public void execute_addPictureValidFile_success() {
        AddPictureCommand cmd1 = new AddPictureCommand(INDEX_FIRST_PERSON, validPath);
        AddPictureCommand cmd2 = new AddPictureCommand(INDEX_FIRST_PERSON, validPathWithSpaces);

        testAddPicture(cmd1);
        testAddPicture(cmd2);
    }
}
