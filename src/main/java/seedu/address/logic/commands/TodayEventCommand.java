package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventCoversTodayPredicate;


public class TodayEventCommand extends Command {
    public static final String COMMAND_WORD = "today_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all events happen today "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_TODAY_EVENT_SUCCESS = "Listed events that happen on today\n";

    private final EventCoversTodayPredicate predicate;

    public TodayEventCommand() {
        this.predicate = new EventCoversTodayPredicate();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(MESSAGE_TODAY_EVENT_SUCCESS
                + String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodayEventCommand // instanceof handles nulls
                && predicate.equals(((TodayEventCommand) other).predicate)); // state check
    }

}
