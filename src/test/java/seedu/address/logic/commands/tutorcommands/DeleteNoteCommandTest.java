package seedu.address.logic.commands.tutorcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModel.getTypicalModel;
import static seedu.address.testutil.TypicalTutors.ALICE;
import static seedu.address.testutil.TypicalTutors.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutor.Notes;

class DeleteNoteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteNoteCommand(null, new EditCommand.EditTutorDescriptor()));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteNoteCommand(Index.fromOneBased(1), null));
    }

    @Test
    void execute_success() throws CommandException {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(1);

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setNotes(new Notes(""));

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(index, editTutorDescriptor);

        CommandResult commandResult = deleteNoteCommand.execute(modelStub);

        assertEquals(String.format(DeleteNoteCommand.MESSAGE_SUCCESS, ALICE.getName().toString()),
                commandResult.getFeedbackToUser());

    }

    @Test
    void execute_invalidIndex() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(50);

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(index, new EditCommand.EditTutorDescriptor());

        assertThrows(CommandException.class, String.format(DeleteNoteCommand.MESSAGE_INVALID_INDEX,
                index.getOneBased()), () -> deleteNoteCommand.execute(modelStub));
    }

    @Test
    void execute_alreadyDoesNotHaveNotes() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(2);

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setNotes(new Notes(""));

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(index, editTutorDescriptor);

        assertThrows(CommandException.class, String.format(DeleteNoteCommand.MESSAGE_DOES_NOT_HAVE_NOTES,
                BENSON.getName().toString()), () -> deleteNoteCommand.execute(modelStub));
    }

}
