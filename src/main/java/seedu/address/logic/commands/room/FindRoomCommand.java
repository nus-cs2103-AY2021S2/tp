package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.room.RoomNumberOrTagsContainsKeywordsPredicate;

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
    private static final Logger logger = LogsCenter.getLogger(FindRoomCommand.class);

    private final RoomNumberOrTagsContainsKeywordsPredicate predicate;

    /**
     * Creates an FindRoomCommand to return any {@code Room} that matches the provided
     * {@code RoomNumberOrTagsContainsKeywordsPredicate}.
     *
     * @param predicate Predicate indicating what criteria must be matched to return a room.
     */
    public FindRoomCommand(RoomNumberOrTagsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the FindRoomCommand on the provided {@code Model} which will ensure that only rooms matching the
     * provided {@code RoomNumberOrTagsContainsKeywordsPredicate} will be returned in
     * {@link Model#getFilteredRoomList()}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with the number of {@code Room} objects that will be listed.
     * @throws NullPointerException If {@code model} is null.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert predicate != null;

        model.updateFilteredRoomList(predicate);

        logger.info("FindRoomCommand successfully updated the model");
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
