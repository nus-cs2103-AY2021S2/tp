package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.tutorcommands.EditCommand.createEditedTutor;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.tutor.ViewTutorPredicate;

/**
 * Deletes a note from a Tutor in the TutorBook
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + "INDEX";

    public static final String MESSAGE_SUCCESS = "Successfully deleted note from Tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_DOES_NOT_HAVE_NOTES = "Tutor: %s does not have notes to delete";

    private final Index targetIndex;

    private final EditCommand.EditTutorDescriptor editTutorDescriptor;

    /**
     * @param targetIndex of the Tutor
     * @param editTutorDescriptor with a descriptor of an empty note
     */
    public DeleteNoteCommand (Index targetIndex, EditCommand.EditTutorDescriptor editTutorDescriptor) {
        this.targetIndex = targetIndex;
        this.editTutorDescriptor = editTutorDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tutor> tutorList = model.getFilteredTutorList();

        ArrayList<Tutor> tutorPredicateList = new ArrayList<>(tutorList);

        if (targetIndex.getZeroBased() >= tutorList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, targetIndex.getZeroBased()));
        }

        Tutor tutor = tutorList.get(targetIndex.getZeroBased());
        if (!tutor.hasNotes()) {
            throw new CommandException(String.format(MESSAGE_DOES_NOT_HAVE_NOTES, tutor.getName().toString()));
        }

        Tutor editedTutor = createEditedTutor(tutor, editTutorDescriptor);

        model.setTutor(tutor, editedTutor);
        model.updateFilteredTutorList(new ViewTutorPredicate(tutorPredicateList));

        return new CommandResult(String.format(MESSAGE_SUCCESS, tutor.getName().toString()));
    }
}
