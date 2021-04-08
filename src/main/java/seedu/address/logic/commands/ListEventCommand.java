package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.address.model.Model;

/**
 * Lists all events in Sochedule to the user.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list_event";

    public static final String MESSAGE_SUCCESS = "Listed all event(s)";

    public static final String MESSAGE_EMPTY = "There is no event present!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return model.isEventListEmpty() ? new CommandResult(MESSAGE_EMPTY) : new CommandResult(MESSAGE_SUCCESS);
    }
}
