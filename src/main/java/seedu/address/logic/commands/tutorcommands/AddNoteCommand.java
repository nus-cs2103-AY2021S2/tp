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
 * Adds a note to a Tutor in the TutorBook
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a note to a tutor specified by the index number. The tutor must not already have a note\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "NOTES \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "This is a note";


    public static final String MESSAGE_SUCCESS = "Successfully added note to Tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_ALREADY_HAVE_NOTES = "Tutor: %s already have notes, try edit_note command";

    private final Index targetIndex;

    private final EditTutorDescriptor editTutorDescriptor;

    /**
     * @param targetIndex of the Tutor
     * @param editTutorDescriptor with descriptor of the note to be added
     */
    public AddNoteCommand (Index targetIndex, EditTutorDescriptor editTutorDescriptor) {
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
        if (tutor.hasNotes()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_HAVE_NOTES, tutor.getName().toString()));
        }

        Tutor editedTutor = createEditedTutor(tutor, editTutorDescriptor);

        model.setTutor(tutor, editedTutor);
        model.updateFilteredTutorList(new ViewTutorPredicate(tutorPredicateList));

        return new CommandResult(String.format(MESSAGE_SUCCESS, tutor.getName().toString()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && targetIndex.equals(((AddNoteCommand) other).targetIndex)
                && editTutorDescriptor.equals(((AddNoteCommand) other).editTutorDescriptor));
    }
}
