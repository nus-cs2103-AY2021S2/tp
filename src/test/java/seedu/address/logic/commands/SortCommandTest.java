package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SORT_ALPHABETICAL_SUCCESS;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SORT_CHRONOLOGICAL_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());

    @Test
    public void execute_validArgsName_success() {
        SortCommand sortCommand = new SortCommand(OPTION_NAME);
        expectedModel.sortContactList(OPTION_NAME);
        assertCommandSuccess(sortCommand, model, MESSAGE_SORT_ALPHABETICAL_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validArgsDate_success() {
        SortCommand sortCommand = new SortCommand(OPTION_DATE);
        expectedModel.sortContactList(OPTION_DATE);
        assertCommandSuccess(sortCommand, model, MESSAGE_SORT_CHRONOLOGICAL_SUCCESS, expectedModel);
    }
}
