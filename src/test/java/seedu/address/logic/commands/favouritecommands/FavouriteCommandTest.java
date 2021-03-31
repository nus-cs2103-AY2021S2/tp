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

class FavouriteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new FavouriteCommand(null, new EditCommand.EditTutorDescriptor()));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new FavouriteCommand(Index.fromOneBased(1), null));
    }

    @Test
    void execute_success() throws CommandException {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(2);

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setIsFavourite(true);

        FavouriteCommand favouriteCommand = new FavouriteCommand(index, editTutorDescriptor);

        CommandResult commandResult = favouriteCommand.execute(modelStub);

        assertEquals(String.format(FavouriteCommand.MESSAGE_SUCCESS, BENSON.getName().toString()),
                commandResult.getFeedbackToUser());

    }

    @Test
    void execute_invalidIndex() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(50);

        FavouriteCommand favouriteCommand = new FavouriteCommand(index, new EditCommand.EditTutorDescriptor());

        assertThrows(CommandException.class, String.format(FavouriteCommand.MESSAGE_INVALID_INDEX,
                index.getOneBased()), () -> favouriteCommand.execute(modelStub));
    }

    @Test
    void execute_alreadyFavourite() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(1);

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setIsFavourite(true);

        FavouriteCommand favouriteCommand = new FavouriteCommand(index, editTutorDescriptor);

        assertThrows(CommandException.class, String.format(FavouriteCommand.MESSAGE_ALREADY_FAVOURITE,
                ALICE.getName().toString()), () -> favouriteCommand.execute(modelStub));
    }

}
