package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GARMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class MatchCommandTest {

    private Model model = new ModelManager(getTypicalWardrobe(), new UserPrefs());

    @Test
    public void equals() {
        final MatchCommand standardCommand = new MatchCommand(INDEX_FIRST_GARMENT);

        // same values -> returns true
        MatchCommand commandWithSameValues = new MatchCommand(INDEX_FIRST_GARMENT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MatchCommand(INDEX_SECOND_GARMENT)));
    }
}
