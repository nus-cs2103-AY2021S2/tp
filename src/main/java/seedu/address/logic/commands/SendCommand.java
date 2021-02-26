package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.api.EndpointCaller;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Calls a saved API endpoint using it's displayed index from the API endpoints list.
 */
public class SendCommand extends Command {

    public static final String COMMAND_WORD = "send";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Calls a saved API endpoint using the displayed index from the API endpoints list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CALL_ENDPOINT_SUCCESS = "Called endpoint: %1$s";

    private final Index index;

    /**
     * @param index of the person in the API endpoints list to call
     */
    public SendCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        EndpointCaller epc = new EndpointCaller(personToEdit);
        int responseStatusCode = epc.callEndpoint();
        //to be implemented

        return new CommandResult(String.format(MESSAGE_CALL_ENDPOINT_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SendCommand)) {
            return false;
        }

        // state check
        SendCommand e = (SendCommand) other;
        return index.equals(e.index);
    }
}
