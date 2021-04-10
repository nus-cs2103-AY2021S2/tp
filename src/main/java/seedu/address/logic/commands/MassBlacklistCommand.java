package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
            + " b/BLACKLIST_OR_UNBLACKLIST\n"
            + "Example: " + COMMAND_WORD + " 5-21 b/blacklist";

    public static final String MESSAGE_MASS_BLACKLIST_SUCCESS = "Successfully blacklisted "
            + "all contacts within the index range %1$d-%2$d";

    public static final String MESSAGE_MASS_UNBLACKLIST_SUCCESS = "Successfully removed "
            + "all contacts within the index range %1$d-%2$d from the blacklist";

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
        String outputMessage;
        if (toBlacklist) {
            model.massBlacklist(startIndex.getOneBased(), endIndex.getOneBased());
            outputMessage = String.format(MESSAGE_MASS_BLACKLIST_SUCCESS,
                    startIndex.getOneBased(), endIndex.getOneBased());
        } else {
            model.massUnblacklist(startIndex.getOneBased(), endIndex.getOneBased());
            outputMessage = String.format(MESSAGE_MASS_UNBLACKLIST_SUCCESS,
                    startIndex.getOneBased(), endIndex.getOneBased());
        }
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (other instanceof MassBlacklistCommand) {
            MassBlacklistCommand otherBlacklistCommand = (MassBlacklistCommand) other;
            boolean sameStartIndex = startIndex.equals(otherBlacklistCommand.startIndex);
            boolean sameEndIndex = endIndex.equals(otherBlacklistCommand.endIndex);
            boolean sameKeyword = toBlacklist == otherBlacklistCommand.toBlacklist;
            return sameStartIndex && sameEndIndex && sameKeyword;
        }
        return false;
    }
}
