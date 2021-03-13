package seedu.smartlib.logic.commands;

import static seedu.smartlib.logic.commands.BorrowCommand.MESSAGE_NOT_IMPLEMENTED_YET;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartlib.testutil.TypicalReaders.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.record.Record;

class BorrowCommandTest {
    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void execute() {
        Record record = new Record("Atals", "Mingze");
        assertCommandFailure(new BorrowCommand(record), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }

}
