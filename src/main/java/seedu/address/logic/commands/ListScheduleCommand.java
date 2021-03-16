package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.schedule.ListScheduleFormatPredicate;

public class ListScheduleCommand extends Command {
    public static final String COMMAND_WORD = "slist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all schedules by "
            + "day or week and displays them as a list sorted by date.\n"
            + "Parameters: day/week \n"
            + "Example: " + COMMAND_WORD + " week";

    private final ListScheduleFormatPredicate predicate;

    public ListScheduleCommand(ListScheduleFormatPredicate predicate) {
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
                || (other instanceof ListScheduleCommand // instanceof handles nulls
                && predicate.equals(((ListScheduleCommand) other).predicate)); // state check
    }
}
