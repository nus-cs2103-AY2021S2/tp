package seedu.address.logic.commands.favouritecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModel.getTypicalModel;
import static seedu.address.testutil.TypicalTutors.ALICE;
import static seedu.address.testutil.TypicalTutors.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.model.Model;

class UnfavouriteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new UnfavouriteCommand(null, new EditCommand.EditTutorDescriptor()));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new UnfavouriteCommand(Index.fromOneBased(1), null));
    }

    @Test
    void execute_success() throws CommandException {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(1);

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setIsFavourite(false);

        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(index, editTutorDescriptor);

        CommandResult commandResult = unfavouriteCommand.execute(modelStub);

        assertEquals(String.format(UnfavouriteCommand.MESSAGE_SUCCESS, ALICE.getName().toString()),
                commandResult.getFeedbackToUser());

    }

    @Test
    void execute_invalidIndex() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(50);

        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(index, new EditCommand.EditTutorDescriptor());

        assertThrows(CommandException.class, String.format(UnfavouriteCommand.MESSAGE_INVALID_INDEX,
                index.getOneBased()), () -> unfavouriteCommand.execute(modelStub));
    }

    @Test
    void execute_alreadyNotFavourite() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(2);

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setIsFavourite(true);

        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(index, editTutorDescriptor);

        assertThrows(CommandException.class, String.format(UnfavouriteCommand.MESSAGE_ALREADY_UNFAVOURITE,
                BENSON.getName().toString()), () -> unfavouriteCommand.execute(modelStub));
    }


}
