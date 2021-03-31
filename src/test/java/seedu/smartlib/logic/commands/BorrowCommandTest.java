package seedu.smartlib.logic.commands;

import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.record.Record;

class BorrowCommandTest {
    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void execute() {
        Record record = model.getSmartLib().getRecordList().get(0);
        //assertCommandFailure(new BorrowCommand(record), model, AddReaderCommand.MESSAGE_DUPLICATE_READER);
    }

}
