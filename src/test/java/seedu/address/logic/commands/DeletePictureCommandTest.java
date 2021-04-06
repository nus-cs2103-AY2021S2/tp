package seedu.address.logic.commands;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opentest4j.TestAbortedException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeletePictureCommandTest {

    private final Path testPicPath = Path.of("src", "test", "data", "PictureTest", "picture.jpg");
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeletePictureCommand cmd = new DeletePictureCommand(outOfBoundIndex);
        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyPicture_throwsCommandException() {
        Person p = model
                .getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())
                .deletePicture();

        DeletePictureCommand cmd = new DeletePictureCommand(INDEX_FIRST_PERSON);
        assertCommandFailure(cmd, model,
                String.format(DeletePictureCommand.MESSAGE_DELETE_PICTURE_NO_PICTURE_FOUND, p.getName()));
    }

    @Test
    public void execute_validIndexAndPicture_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Path tmpFile;

        try {
            tmpFile = Files.createTempFile("picture", ".jpg");
            Files.copy(testPicPath, tmpFile, REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new TestAbortedException("Unable to create test picture for DeletePictureCommand test: " + ioe);
        }

        Picture picture = new Picture(tmpFile);
        Person personWithPicture = person.withPicture(picture);
        model.setPerson(person, personWithPicture);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personWithPicture, person);

        String expectedMessage = String.format(DeletePictureCommand.MESSAGE_DELETE_PICTURE_SUCCESS, person.getName());

        DeletePictureCommand cmd = new DeletePictureCommand(INDEX_FIRST_PERSON);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertFalse(Files.exists(tmpFile));
    }

    @Test
    public void equals() {
        DeletePictureCommand cmd1 = new DeletePictureCommand(INDEX_FIRST_PERSON);
        DeletePictureCommand cmd2 = new DeletePictureCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(cmd1, cmd1);

        // same values -> returns true
        DeletePictureCommand cmd1Copy = new DeletePictureCommand(INDEX_FIRST_PERSON);
        assertEquals(cmd1Copy, cmd1);

        // different indexes -> returns false
        assertNotEquals(cmd2, cmd1);
    }
}
