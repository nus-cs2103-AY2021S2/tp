package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.index.Index.getInterval;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Blacklists or unblacklists all contacts within the specified index range (inclusive).
 */
public class MassBlacklistCommand extends Command {

    public static final String COMMAND_WORD = "massblist";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Blacklists or unblacklists all contacts within the specified index range "
            + "(inclusive).\n"
            + "Parameters: START-END (Both must be positive integers)"
            + " [b/BLACKLIST_OR_UNBLACKLIST]\n"
            + "Example: " + COMMAND_WORD + " 5-21 b/blacklist" ;

    public static final String MESSAGE_MASS_BLACKLIST_PERSON_SUCCESS = "Successfully %1$s "
            + "all contacts within the index range %2$d-%3$d";

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

    /**
     * Returns true if the given string is a valid keyword (Either blacklist or unblacklist).
     */
    public static boolean isValidBlacklistKeyword(String blacklistKeyword) {
        return blacklistKeyword.equals("blacklist") || blacklistKeyword.equals("unblacklist");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (!Index.isValidIndexRange(startIndex, endIndex)) {
            throw new CommandException(Messages.MESSAGE_INVALID_START_INDEX);
        }
        if (endIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_END_INDEX);
        }
        int range = endIndex.getZeroBased() - startIndex.getZeroBased();
        assert range > 0; // Start index must be strictly smaller than end index
        for (int i = 0; i <= range; i++) {
            Person personToToggleBlacklist = lastShownList.get(startIndex.getZeroBased() + i);
            if (toBlacklist) {
                model.blacklistPerson(personToToggleBlacklist);
            } else {
                model.unblacklistPerson(personToToggleBlacklist);
            }
        }
        String outputMessage;
        if (toBlacklist) {
            outputMessage = String.format(MESSAGE_MASS_BLACKLIST_PERSON_SUCCESS,
                    "blacklisted", startIndex.getOneBased(), endIndex.getOneBased());
        } else {
            outputMessage = String.format(MESSAGE_MASS_BLACKLIST_PERSON_SUCCESS,
                    "unblacklisted", startIndex.getOneBased(), endIndex.getOneBased());
        }
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (other instanceof MassBlacklistCommand) {
            return startIndex.equals(((MassBlacklistCommand) other).startIndex)
                    && endIndex.equals(((MassBlacklistCommand) other).endIndex);
        }
        return false;
    }
}
