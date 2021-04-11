package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class BackLogCommand extends Command {
    public static final String COMMAND_WORD = "log";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Event with the status of "
            + "BACKLOG to Focuris. "
            + "Parameters: "
            + PREFIX_NAME + "NAME " + " "
            + PREFIX_DESCRIPTION + "DESCRIPTION " + " "
            + "[" + PREFIX_PRIORITY + "PRIORITY]" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Household Chores" + " "
            + PREFIX_DESCRIPTION + "Clean my room and wash the dishes";

    public static final String MESSAGE_SUCCESS = "New Event added with the Status of BACKLOG: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "An Event with the same NAME already exists in Focuris";

    private final Event toAdd;

    /**
     * Creates an BackLogCommand to add the specified BackLog Event {@code Event}
     */
    public BackLogCommand(Event event) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BackLogCommand // instanceof handles nulls
                && toAdd.equals(((BackLogCommand) other).toAdd));
    }
}
