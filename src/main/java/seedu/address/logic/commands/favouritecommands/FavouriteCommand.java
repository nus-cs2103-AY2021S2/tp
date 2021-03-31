package seedu.address.logic.commands.favouritecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;
import static seedu.address.logic.commands.tutorcommands.EditCommand.createEditedTutor;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.model.Model;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.tutor.ViewTutorPredicate;

/**
 * Marks a tutor as a favourite
 */
public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "favourite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " ID";

    public static final String MESSAGE_SUCCESS = "Favourite tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_ALREADY_FAVOURITE = "Tutor: %s is already a favourite";

    private final Index targetIndex;

    private final EditCommand.EditTutorDescriptor editTutorDescriptor;

    /**
     * @param targetIndex of the Tutor to be favourite
     * @param editTutorDescriptor with a favourite descriptor
     */
    public FavouriteCommand(Index targetIndex, EditTutorDescriptor editTutorDescriptor) {
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
        if (tutor.isFavourite()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_FAVOURITE, tutor.getName().toString()));
        }

        Tutor editedTutor = createEditedTutor(tutor, editTutorDescriptor);

        model.setTutor(tutor, editedTutor);
        model.updateFilteredTutorList(new ViewTutorPredicate(tutorPredicateList));

        return new CommandResult(String.format(MESSAGE_SUCCESS, tutor.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavouriteCommand // instanceof handles nulls
                && targetIndex.equals(((FavouriteCommand) other).targetIndex)
                && editTutorDescriptor.equals(((FavouriteCommand) other).editTutorDescriptor));

    }

}
