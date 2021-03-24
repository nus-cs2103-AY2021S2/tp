package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.schedule.ScheduleNameContainsKeywordsPredicate;

/**
 * Finds and lists all schedules whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all schedules whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final ScheduleNameContainsKeywordsPredicate predicate;

    public FindScheduleCommand(ScheduleNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, model.getFilteredScheduleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindScheduleCommand // instanceof handles nulls
                && predicate.equals(((FindScheduleCommand) other).predicate)); // state check
    }
}
