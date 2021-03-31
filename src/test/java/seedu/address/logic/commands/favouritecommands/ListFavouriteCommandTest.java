package seedu.address.logic.commands.favouritecommands;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.testutil.TypicalModel;

class ListFavouriteCommandTest {

    @Test
    void execute() throws CommandException {

        Model modelStub = TypicalModel.getTypicalModel();
        ListFavouriteCommand listFavouriteCommand = new ListFavouriteCommand();

        CommandResult commandResult = listFavouriteCommand.execute(modelStub);

        assertEquals(ListFavouriteCommand.MESSAGE_SUCCESS,
                commandResult.getFeedbackToUser());
    }

}
