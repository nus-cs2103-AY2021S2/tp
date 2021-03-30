package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ID_WITH_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BatchCommand.
 */
public class BatchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private EditCommandParser editCommandParser = new EditCommandParser();
    private DeleteCommandParser deleteCommandParser = new DeleteCommandParser();
    private List<Index> listOfIndices = Arrays.asList(Index.fromOneBased(7), Index.fromOneBased(6),
            Index.fromOneBased(1));
    private String argsForEdit = "t/" + VALID_TAG_HUSBAND + " i/" + VALID_POLICY_ID + " i/" + VALID_POLICY_ID_WITH_URL;

    @Test
    public void execute_validIndexUnfilteredListForBatchEdit_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        for (Index index : listOfIndices) {
            Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
            PersonBuilder personBuilder = new PersonBuilder(personToEdit);
            Person editedPerson = personBuilder
                    .withTags(VALID_TAG_HUSBAND)
                    .withPolicies(VALID_POLICY_ID, VALID_POLICY_ID_WITH_URL)
                    .build();
            expectedModel.setPerson(personToEdit, editedPerson);
        }

        String expectedMessage = BatchCommand.SUCCESS_MESSAGE;

        List<EditCommand> listOfEditCommands = new ArrayList<>();
        createEditCommands(listOfEditCommands);

        BatchCommand<EditCommand> batchEditCommand = new BatchCommand<>(listOfEditCommands);

        assertCommandSuccess(batchEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListForBatchDelete_success() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Index index : listOfIndices) {
            Person personToDelete = model.getFilteredPersonList().get(index.getZeroBased());
            expectedModel.deletePerson(personToDelete);
        }

        String expectedMessage = BatchCommand.SUCCESS_MESSAGE;

        List<DeleteCommand> listOfDeleteCommands = new ArrayList<>();
        createDeleteCommands(listOfDeleteCommands);

        BatchCommand<DeleteCommand> batchDeleteCommand = new BatchCommand<>(listOfDeleteCommands);

        assertCommandSuccess(batchDeleteCommand, model, expectedMessage, expectedModel);
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
        createAnotherListOfEditCommands(anotherListOfEditCommandsWithDiffIndices, argsForEdit);
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
            for (Index index : listOfIndices) {
                String newCommandInput = String.valueOf(index.getOneBased());
                DeleteCommand deleteCommand = deleteCommandParser.parse(newCommandInput);
                listOfDeleteCommands.add(deleteCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createAnotherListOfDeleteCommands(List<DeleteCommand> listOfDeleteCommands) {
        try {
            for (Index index : listOfIndices) {
                String newCommandInput = String.valueOf(1);
                DeleteCommand deleteCommand = deleteCommandParser.parse(newCommandInput);
                listOfDeleteCommands.add(deleteCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createEditCommands(List<EditCommand> listOfEditCommands) {
        try {
            for (Index index : listOfIndices) {
                String newCommandInput = String.valueOf(index.getOneBased()) + " " + argsForEdit;
                EditCommand editCommand = editCommandParser.parse(newCommandInput);
                listOfEditCommands.add(editCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createAnotherListOfEditCommands(List<EditCommand> listOfEditCommands, String argsForEdit) {
        try {
            for (Index index : listOfIndices) {
                String newCommandInput = String.valueOf(1) + " " + argsForEdit;
                EditCommand editCommand = editCommandParser.parse(newCommandInput);
                listOfEditCommands.add(editCommand);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
