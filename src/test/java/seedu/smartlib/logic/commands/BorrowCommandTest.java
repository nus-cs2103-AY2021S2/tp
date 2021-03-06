package seedu.smartlib.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.smartlib.logic.commands.BorrowCommand.MESSAGE_NOT_IMPLEMENTED_YET;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartlib.testutil.TypicalPersons.getTypicalAddressBook;

class BorrowCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        assertCommandFailure(new BorrowCommand(2, 2), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }

}