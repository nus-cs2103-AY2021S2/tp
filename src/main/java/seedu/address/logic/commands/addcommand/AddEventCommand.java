package seedu.address.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.GeneralEvent;

public class AddEventCommand extends AddCommand {
    public static final String MESSAGE_USAGE =
            "Missing necessary prefixes: g/ and on/\n"
            + "Event: add g/EVENT DESCRIPTION on/EVENT DATE\n"
            + "Example: add g/EVENT on/03/02/2021 2359";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in your calendar.";

    private final GeneralEvent toAdd;


    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddEventCommand(GeneralEvent event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert toAdd != null;

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
