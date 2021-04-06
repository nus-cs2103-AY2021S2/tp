package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validInputs_success() {
        DetailsPanelTab tabToView = DetailsPanelTab.UPCOMING_EVENTS;

        String expectedMessage = String.format(ViewCommand.MESSAGE_DETAILS_SUCCESS, tabToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, tabToView);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        ViewCommand viewCommand = new ViewCommand(DetailsPanelTab.UPCOMING_EVENTS);

        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidInputs_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_TAB, DetailsCommand.MESSAGE_USAGE);
        ViewCommand viewCommand = new ViewCommand(DetailsPanelTab.PERSON_DETAILS);

        assertCommandFailure(viewCommand, model, expectedMessage);
    }
}
