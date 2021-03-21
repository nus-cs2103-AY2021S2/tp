package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_FILE_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE_EXTENSION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

public class AddPictureCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    Path testFilesDir = Path.of("src", "test", "data", "PictureTest");
    Path fileNotFoundPath = testFilesDir.resolve("non_existant.jpeg");
    Path fileWrongExtPath = testFilesDir.resolve("invalid_format.txt");
    Path validPath = testFilesDir.resolve("picture.jpg");
    Path validPathWithSpaces = testFilesDir.resolve("picture with space.jpg");

    public void addPictureValidFileHelper(AddPictureCommand cmd) {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(AddPictureCommand.MESSAGE_ADD_PICTURE_SUCCESS, firstPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage);
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

    @Test
    public void execute_addPictureValidFile_success() {
        AddPictureCommand cmd1 = new AddPictureCommand(INDEX_FIRST_PERSON, validPath);
        AddPictureCommand cmd2 = new AddPictureCommand(INDEX_FIRST_PERSON, validPathWithSpaces);

        addPictureValidFileHelper(cmd1);
        addPictureValidFileHelper(cmd2);
    }
}
