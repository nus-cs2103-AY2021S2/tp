package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalShortcuts.getTypicalShortcutLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListShortcutCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalShortcutLibrary());
        expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), model.getShortcutLibrary());
    }

    @Test
    public void execute_showsSameShortcutList() {
        String expectedMessage = expectedModel.getShortcutLibrary().getAllShortcutsInString();
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false,
                true, false);
        assertCommandSuccess(new ListShortcutCommand(), model, expectedCommandResult, expectedModel);
    }

}
