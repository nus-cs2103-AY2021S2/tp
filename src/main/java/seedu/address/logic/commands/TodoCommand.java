package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo Event to Focuris. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Household Chores"
            + PREFIX_DESCRIPTION + "Clean my room and wash the dishes";

    public static final String MESSAGE_SUCCESS = "New Todo added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This Todo already exists in Focuris";

    private final Event toAdd;

    /**
     * Creates an TodoCommand to add the specified Todo Event {@code Event}
     */
    public TodoCommand(Event event) {
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
                || (other instanceof TodoCommand // instanceof handles nulls
                && toAdd.equals(((TodoCommand) other).toAdd));
    }
}
