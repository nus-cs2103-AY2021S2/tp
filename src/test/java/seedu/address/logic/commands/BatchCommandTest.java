package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BatchCommand.
 */
public class BatchCommandTest {
    private static final Model MODEL = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new ShortcutLibrary());
    private static final EditCommandParser EDIT_COMMAND_PARSER = new EditCommandParser();
    private static final DeleteCommandParser DELETE_COMMAND_PARSER = new DeleteCommandParser();
    private static final List<Index> LIST_OF_INDICES = Arrays.asList(Index.fromOneBased(7), Index.fromOneBased(6),
            Index.fromOneBased(1));

    @Test
    public void execute_validIndexUnfilteredListForBatchDelete_success() {
        ModelManager expectedModel = new ModelManager(MODEL.getAddressBook(), new UserPrefs(), new ShortcutLibrary());
        for (Index index : LIST_OF_INDICES) {
            Person personToDelete = MODEL.getFilteredPersonList().get(index.getZeroBased());
            expectedModel.deletePerson(personToDelete);
        }

        String expectedMessage = BatchCommand.SUCCESS_MESSAGE;

        List<DeleteCommand> listOfDeleteCommands = new ArrayList<>();
        createDeleteCommands(listOfDeleteCommands);

        BatchCommand<DeleteCommand> batchDeleteCommand = new BatchCommand<>(listOfDeleteCommands);

        assertCommandSuccess(batchDeleteCommand, MODEL, expectedMessage, expectedModel);
    }

    @Test
    public void equalsEdit() {
        List<EditCommand> listOfEditCommands = new ArrayList<>();
        createEditCommands(listOfEditCommands);

        final BatchCommand<EditCommand> standardBatchEdit = new BatchCommand<>(listOfEditCommands);

        // same values -> returns true
        BatchCommand<EditCommand> commandWithSameValues = new BatchCommand<>(listOfEditCommands);
        assertTrue(standardBatchEdit.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardBatchEdit.equals(standardBatchEdit));

        // null -> returns false
        assertFalse(standardBatchEdit.equals(null));

        // different types -> returns false
        assertFalse(standardBatchEdit.equals(new HelpCommand()));

        // different list of edit commands with different indices -> returns false
        List<EditCommand> anotherListOfEditCommandsWithDiffIndices = new ArrayList<>();
        createAnotherListOfEditCommands(anotherListOfEditCommandsWithDiffIndices, CommandTestUtil.ARGS_FOR_EDIT);
        assertFalse(standardBatchEdit.equals(new BatchCommand<>(anotherListOfEditCommandsWithDiffIndices)));

        // different list of edit commands with different arguments -> returns false
        String anotherArgsForEdit = "t/bestfriend i/Pol_#456>www.facebook.com";
        List<EditCommand> anotherListOfEditCommandsWithDiffArgs = new ArrayList<>();
        createAnotherListOfEditCommands(anotherListOfEditCommandsWithDiffArgs, anotherArgsForEdit);
        assertFalse(standardBatchEdit.equals(new BatchCommand<>(anotherListOfEditCommandsWithDiffArgs)));
    }

    @Test
    public void equalsDelete() {
        List<DeleteCommand> listOfDeleteCommands = new ArrayList<>();
        createDeleteCommands(listOfDeleteCommands);

        BatchCommand<DeleteCommand> standardBatchDelete = new BatchCommand<>(listOfDeleteCommands);

        // same values -> returns true
        BatchCommand commandWithSameValues = new BatchCommand<>(listOfDeleteCommands);
        assertTrue(standardBatchDelete.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardBatchDelete.equals(standardBatchDelete));

        // null -> returns false
        assertFalse(standardBatchDelete.equals(null));

        // different types -> returns false
        assertFalse(standardBatchDelete.equals(new HelpCommand()));

        // different list of delete commands with different indices -> returns false
        List<DeleteCommand> anotherListOfDeleteCommands = new ArrayList<>();
        createAnotherListOfDeleteCommands(anotherListOfDeleteCommands);
        assertFalse(standardBatchDelete.equals(new BatchCommand<>(anotherListOfDeleteCommands)));

        // different list of edit commands -> returns false
        List<EditCommand> listOfEditCommands = new ArrayList<>();
        createEditCommands(listOfEditCommands);
        assertFalse(standardBatchDelete.equals(new BatchCommand<>(listOfEditCommands)));
    }

    private void createDeleteCommands(List<DeleteCommand> listOfDeleteCommands) {
        try {
            for (Index index : LIST_OF_INDICES) {
                String newCommandInput = String.valueOf(index.getOneBased());
                DeleteCommand deleteCommand = DELETE_COMMAND_PARSER.parse(newCommandInput);
                listOfDeleteCommands.add(deleteCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createAnotherListOfDeleteCommands(List<DeleteCommand> listOfDeleteCommands) {
        try {
            for (Index index : LIST_OF_INDICES) {
                String newCommandInput = String.valueOf(1);
                DeleteCommand deleteCommand = DELETE_COMMAND_PARSER.parse(newCommandInput);
                listOfDeleteCommands.add(deleteCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createEditCommands(List<EditCommand> listOfEditCommands) {
        try {
            for (Index index : LIST_OF_INDICES) {
                String newCommandInput = index.getOneBased() + " " + CommandTestUtil.ARGS_FOR_EDIT;
                EditCommand editCommand = EDIT_COMMAND_PARSER.parse(newCommandInput);
                listOfEditCommands.add(editCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createAnotherListOfEditCommands(List<EditCommand> listOfEditCommands, String argsForEdit) {
        try {
            for (Index index : LIST_OF_INDICES) {
                String newCommandInput = 1 + " " + argsForEdit;
                EditCommand editCommand = EDIT_COMMAND_PARSER.parse(newCommandInput);
                listOfEditCommands.add(editCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
