package seedu.address.logic.commands.tutorcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;
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

class AddNoteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddNoteCommand(null, new EditTutorDescriptor()));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddNoteCommand(Index.fromOneBased(1), null));
    }

    @Test
    void execute_success() throws CommandException {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(2);

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptor();
        editTutorDescriptor.setNotes(new Notes("some note"));

        AddNoteCommand addNoteCommand = new AddNoteCommand(index, editTutorDescriptor);

        CommandResult commandResult = addNoteCommand.execute(modelStub);

        assertEquals(String.format(AddNoteCommand.MESSAGE_SUCCESS, BENSON.getName().toString()),
                commandResult.getFeedbackToUser());

    }

    @Test
    void execute_invalidIndex() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(50);

        AddNoteCommand addNoteCommand = new AddNoteCommand(index, new EditTutorDescriptor());

        assertThrows(CommandException.class, String.format(AddNoteCommand.MESSAGE_INVALID_INDEX,
                index.getOneBased()), () -> addNoteCommand.execute(modelStub));
    }

    @Test
    void execute_alreadyHaveNotes() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(1);

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptor();
        editTutorDescriptor.setNotes(new Notes("some note"));

        AddNoteCommand addNoteCommand = new AddNoteCommand(index, editTutorDescriptor);

        assertThrows(CommandException.class, String.format(AddNoteCommand.MESSAGE_ALREADY_HAVE_NOTES,
                ALICE.getName().toString()), () -> addNoteCommand.execute(modelStub));
    }



}
