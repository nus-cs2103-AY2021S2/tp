package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Displays the full details for a person by the index number used in the displayed person list.
 */
public class DetailsCommand extends Command {

    public static final String COMMAND_WORD = "details";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the full details for a person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DETAILS_SUCCESS = "Displaying details for %1$s on the right panel";

    private final Index index;

    public DetailsCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDisplay = lastShownList.get(index.getZeroBased());

        return new CommandResult(String.format(MESSAGE_DETAILS_SUCCESS, personToDisplay.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DetailsCommand)) {
            return false;
        }

        DetailsCommand e = (DetailsCommand) other;
        return index.equals(e.index);
    }
}
