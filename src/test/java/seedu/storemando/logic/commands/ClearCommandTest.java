package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.LocationContainsPredicate;

public class ClearCommandTest {

    @Test
    public void execute_emptyStoreMando_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStoreMando_success() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel.setStoreMando(new StoreMando());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final ClearCommand standardCommand = new ClearCommand();

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new SortAscendingQuantityCommand()));

        // same type but diff predicate -> returns false
        assertFalse(standardCommand.equals(new ClearCommand(new LocationContainsPredicate("Kitchen"))));
    }
}
