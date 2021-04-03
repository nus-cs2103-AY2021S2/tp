package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.index.Index.getInterval;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Blacklists all contacts within a specified index range(inclusive).
 */
public class MassBlacklistCommand extends Command {

    public static final String COMMAND_WORD = "massblist";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Blacklists all contacts within a specified index range(inclusive).\n"
            + "Parameters: STARTINDEX-ENDINDEX (Both must be positive integers) \n"
            + "Example: " + COMMAND_WORD + " " + "5-21";

    public static final String MESSAGE_MASS_BLACKLIST_PERSON_SUCCESS = "Successfully toggled the "
            + "blacklist status of all contacts within the specified range";
    public static final String MESSAGE_INVALID_END_INDEX = "End index must be larger than start index but "
            + "smaller than the number of people in the contact list.";

    private final Index startIndex;
    private final Index endIndex;
    private final boolean toBlacklist;

    /**
     * Creates a MassBlacklistCommand to delete all contacts within the specified index range.
     */
    public MassBlacklistCommand(Index startIndex, Index endIndex, boolean toBlacklist) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.toBlacklist = toBlacklist;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (endIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_END_INDEX);
        }
        ArrayList<Index> intervalToBlacklist = getInterval(startIndex, endIndex);
        for (Index index : intervalToBlacklist) {
            Person personToBlacklist = lastShownList.get(index.getZeroBased());
            if (toBlacklist) {
                model.blacklistPerson(personToBlacklist);
            } else {
                model.unblacklistPerson(personToBlacklist);
            }
        }
        return new CommandResult(MESSAGE_MASS_BLACKLIST_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (other instanceof MassBlacklistCommand) {
            return startIndex.equals(((MassBlacklistCommand) other).startIndex)
                    && endIndex.equals(((MassBlacklistCommand) other).endIndex);
        } else {
            return false;
        }
    }
}
