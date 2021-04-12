package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Blacklists or unblacklists all persons within the specified index range (inclusive) in
 * the address book.
 */
public class MassBlacklistCommand extends Command {

    public static final String COMMAND_WORD = "massblist";

    public static final String BLACKLIST_KEYWORD = "blacklist";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Blacklists or unblacklists all persons within the specified index range "
            + "(inclusive).\n"
            + "Parameters: START-END (both must be positive integers)"
            + " b/BLACKLIST_OR_UNBLACKLIST\n"
            + "Example: " + COMMAND_WORD + " 5-21 b/blacklist";

    public static final String MESSAGE_MASS_BLACKLIST_SUCCESS = "Successfully blacklisted "
            + "all persons within the index range %1$d-%2$d";

    public static final String MESSAGE_MASS_UNBLACKLIST_SUCCESS = "Successfully removed "
            + "all contacts within the index range %1$d-%2$d from the blacklist";

    private final Index startIndex;
    private final Index endIndex;
    private final boolean toBlacklist;

    /**
     * Creates a MassBlacklistCommand to blacklist or unblacklist all persons within the
     * specified index range (inclusive).
     */
    public MassBlacklistCommand(Index startIndex, Index endIndex, boolean toBlacklist) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.toBlacklist = toBlacklist;
    }

    /**
     * Returns true if the given string is a valid keyword (either blacklist or unblacklist) and
     * false otherwise.
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
        int start = startIndex.getOneBased();
        int end = endIndex.getOneBased();
        assert start < end : "Start index must be strictly smaller than the end index";
        if (end > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_END_INDEX);
        }
        String outputMessage;
        if (toBlacklist) {
            model.massBlacklist(start, end);
            outputMessage = String.format(MESSAGE_MASS_BLACKLIST_SUCCESS, start, end);
        } else {
            model.massUnblacklist(start, end);
            outputMessage = String.format(MESSAGE_MASS_UNBLACKLIST_SUCCESS, start, end);
        }
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        if (other instanceof MassBlacklistCommand) { // instanceof handles nulls
            MassBlacklistCommand otherBlacklistCommand = (MassBlacklistCommand) other;
            // state check
            boolean isSameStartIndex = startIndex.equals(otherBlacklistCommand.startIndex);
            boolean isSameEndIndex = endIndex.equals(otherBlacklistCommand.endIndex);
            boolean isSameKeyword = (toBlacklist == otherBlacklistCommand.toBlacklist);
            return isSameStartIndex && isSameEndIndex && isSameKeyword;
        }
        return false;
    }
}
