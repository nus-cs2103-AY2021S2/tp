package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;
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
 * Edits a note of a Tutor in the TutorBook
 */
public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "edit_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + "INDEX" + " " + "NOTE";

    public static final String MESSAGE_SUCCESS = "Successfully edited note to Tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_DOES_NOT_HAVE_NOTES = "Tutor: %s does not have notes, try add_note command";

    private final Index targetIndex;

    private final EditCommand.EditTutorDescriptor editTutorDescriptor;

    /**
     * @param targetIndex of the Tutor
     * @param editTutorDescriptor with a descriptor of the edited note
     */
    public EditNoteCommand (Index targetIndex, EditTutorDescriptor editTutorDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editTutorDescriptor);

        this.targetIndex = targetIndex;
        this.editTutorDescriptor = editTutorDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tutor> tutorList = model.getFilteredTutorList();

        ArrayList<Tutor> tutorPredicateList = new ArrayList<>(tutorList);

        if (targetIndex.getZeroBased() >= tutorList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, targetIndex.getOneBased()));
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditNoteCommand // instanceof handles nulls
                && targetIndex.equals(((EditNoteCommand) other).targetIndex)
                && editTutorDescriptor.equals(((EditNoteCommand) other).editTutorDescriptor));

    }
}
