package seedu.address.logic.commands.tutorcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.testutil.TypicalModel;

class ListNoteCommandTest {

    @Test
    void execute() throws CommandException {

        Model modelStub = TypicalModel.getTypicalModel();
        ListNoteCommand listNoteCommand = new ListNoteCommand();

        CommandResult commandResult = listNoteCommand.execute(modelStub);

        assertEquals(ListNoteCommand.MESSAGE_SUCCESS,
                commandResult.getFeedbackToUser());
    }


}
