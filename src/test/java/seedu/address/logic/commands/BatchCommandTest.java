package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BatchCommand.
 */
public class BatchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private EditCommandParser editCommandParser = new EditCommandParser();
    private DeleteCommandParser deleteCommandParser = new DeleteCommandParser();
    private List<Index> listOfIndices = Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(6),
            Index.fromOneBased(7));
    private String argsForEdit = "t/enemy i/Pol_#123>www.youtube.com";

    @Test
    public void equalsEdit() {
        final BatchCommand standardBatchEdit = new BatchCommand(editCommandParser, listOfIndices, argsForEdit);

        // same values -> returns true
        BatchCommand commandWithSameValues = new BatchCommand(editCommandParser, listOfIndices, argsForEdit);
        assertTrue(standardBatchEdit.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardBatchEdit.equals(standardBatchEdit));

        // null -> returns false
        assertFalse(standardBatchEdit.equals(null));

        // different types -> returns false
        assertFalse(standardBatchEdit.equals(new HelpCommand()));

        // different parser -> returns false
        assertFalse(standardBatchEdit.equals(new BatchCommand(new DeleteCommandParser(), listOfIndices, argsForEdit)));

        // different list of indices -> returns false
        List<Index> anotherListOfIndices = Arrays.asList(Index.fromOneBased(2), Index.fromOneBased(3),
                Index.fromOneBased(4));
        assertFalse(standardBatchEdit.equals(new BatchCommand(editCommandParser, anotherListOfIndices, argsForEdit)));

        // different arguments -> returns false
        String anotherArgsForEdit = "t/bestfriend i/Pol_#456>www.facebook.com";
        assertFalse(standardBatchEdit.equals(new BatchCommand(editCommandParser, anotherListOfIndices,
                anotherArgsForEdit)));
    }

    @Test
    public void equalsDelete() {
        final BatchCommand standardBatchDelete = new BatchCommand(deleteCommandParser, listOfIndices);

        // same values -> returns true
        BatchCommand commandWithSameValues = new BatchCommand(deleteCommandParser, listOfIndices);
        assertTrue(standardBatchDelete.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardBatchDelete.equals(standardBatchDelete));

        // null -> returns false
        assertFalse(standardBatchDelete.equals(null));

        // different types -> returns false
        assertFalse(standardBatchDelete.equals(new HelpCommand()));

        // different parser -> returns false
        assertFalse(standardBatchDelete.equals(new BatchCommand(new EditCommandParser(), listOfIndices)));

        // different list of indices -> returns false
        List<Index> anotherListOfIndices = Arrays.asList(Index.fromOneBased(2), Index.fromOneBased(3),
                Index.fromOneBased(4));
        assertFalse(standardBatchDelete.equals(new BatchCommand(deleteCommandParser, anotherListOfIndices)));
    }
}
