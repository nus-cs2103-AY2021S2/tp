package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ViewTutorPredicate;

/**
 * View a tutor by its index in tutor list.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view_tutor";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the tutor identified by the index number used in the displayed tutor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_TUTOR_SUCCESS = "View Tutor: %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> updatedTutorList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= updatedTutorList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person tutorToView = updatedTutorList.get(targetIndex.getZeroBased());
        model.updateFilteredPersonList(new ViewTutorPredicate(tutorToView));
        return new CommandResult(String.format(MESSAGE_VIEW_TUTOR_SUCCESS, tutorToView.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
