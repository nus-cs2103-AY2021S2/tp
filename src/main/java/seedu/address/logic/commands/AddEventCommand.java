package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the SOChedule.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to SOChedule. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_STARTDATE + "YYYY-MM-DD "
            + PREFIX_STARTTIME + "HH:mm "
            + PREFIX_ENDDATE + "YYYY-MM-DD "
            + PREFIX_ENDTIME + "HH:mm "
            + "[" + PREFIX_CATEGORY + "CATEGORY]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103 meeting "
            + PREFIX_STARTDATE + "2021-02-27 "
            + PREFIX_STARTTIME + "15:00 "
            + PREFIX_ENDDATE + "2022-02-27 "
            + PREFIX_ENDTIME + "17:00 "
            + PREFIX_CATEGORY + "School "
            + PREFIX_TAG + "TeamProject";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "New event added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the event list.";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
