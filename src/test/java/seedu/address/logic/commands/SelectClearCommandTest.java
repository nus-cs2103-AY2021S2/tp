package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SelectClearCommandTest {

    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getAliases());
        // set all to selected initially
        model.updateSelectedPersonList(model.getFilteredPersonList());
    }

    @Test
    public void execute_modelUpdated_success() {
        assertCommandSuccess(new SelectClearCommand(), model, SelectClearCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void equals() {
        SelectClearCommand command = new SelectClearCommand();
        // same object -> equals
        assertEquals(command, command);
        // different object -> equals
        assertEquals(new SelectClearCommand(), new SelectClearCommand());

        // different types -> not equals
        assertNotEquals(command, new SelectShowCommand());

        // null -> not equals
        assertNotEquals(null, command);

        // 1 -> not equals
        assertNotEquals(1, command);
    }
}
