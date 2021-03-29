package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.room.RoomNumberContainsKeywordsPredicate;

/**
 * Finds and lists all rooms in SunRez whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRoomCommand extends Command {
    public static final String COMMAND_WORD = "ofind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all rooms whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 12-345 01-001";

    private final RoomNumberContainsKeywordsPredicate predicate;

    public FindRoomCommand(RoomNumberContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoomList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ROOMS_LISTED_OVERVIEW, model.getFilteredRoomList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRoomCommand // instanceof handles nulls
                && predicate.equals(((FindRoomCommand) other).predicate)); // state check
    }
}
