package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

/**
 * Adds an event to PartyPlanet.
 */
public class EAddCommand extends Command {

    public static final String COMMAND_WORD = "eadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to PartyPlanet.\n"
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + "[" + PREFIX_DATE + " DATE] "
            + "[" + PREFIX_REMARK + " REMARK] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " Jan Celebration "
            + PREFIX_DATE + " 2020-01-01 "
            + PREFIX_REMARK + " 1. Check everyone's availability";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD + " -n NAME [-d DATE] [-r REMARK]";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in PartyPlanet";

    private final Event toAdd;

    /**
     * Creates an EAddCommand to add the specified {@code Event}.
     */
    public EAddCommand(Event event) {
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

        String output = String.format(MESSAGE_SUCCESS, toAdd);
        model.addState(output);
        return new CommandResult(output);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EAddCommand // instanceof handles nulls
                && toAdd.equals(((EAddCommand) other).toAdd));
    }
}
