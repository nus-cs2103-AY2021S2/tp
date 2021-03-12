package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;

public class ShowCommandTest {

    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void equals() {
        final ShowCommand standardCommand = new ShowCommand(INDEX_FIRST_ENDPOINT);

        // same values -> returns true
        ShowCommand commandWithSameValues = new ShowCommand(INDEX_FIRST_ENDPOINT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ShowCommand(INDEX_SECOND_ENDPOINT)));
    }

}
