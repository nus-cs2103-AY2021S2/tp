package seedu.us.among.logic.commands;

import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.endpoint.EndpointCaller;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.commons.util.CollectionUtil;

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
     * @param index of the endpoint in the API endpoints list to call
     */
    public SendCommand(Index index) {
        CollectionUtil.requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Endpoint> lastShownList = model.getFilteredEndpointList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
        }

        Endpoint endpointToEdit = lastShownList.get(index.getZeroBased());
        EndpointCaller epc = new EndpointCaller(endpointToEdit);
        int responseStatusCode = epc.callEndpoint();
        //to be implemented

        return new CommandResult(String.format(MESSAGE_CALL_ENDPOINT_SUCCESS, endpointToEdit));
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
