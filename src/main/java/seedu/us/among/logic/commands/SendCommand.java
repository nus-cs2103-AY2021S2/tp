package seedu.us.among.logic.commands;

import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.EndpointCaller;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Name;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.tag.Tag;

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
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Endpoint> lastShownList = model.getFilteredEndpointList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
        }

        Endpoint endpointToSend = lastShownList.get(index.getZeroBased());
        EndpointCaller epc = new EndpointCaller(endpointToSend);
        Response response;

        try {
            response = epc.callEndpoint();
        } catch (IOException e) {
            throw new CommandException("Placeholder exception");
        }

        System.out.println(response);

        Endpoint endpointWithResponse = createEndpointWithResponse(endpointToSend, response);

        model.setEndpoint(endpointToSend, endpointWithResponse);

        return new CommandResult(String.format(MESSAGE_CALL_ENDPOINT_SUCCESS, endpointWithResponse));
    }

    /**
     * Creates and returns a {@code Endpoint} with the details of {@code endpointToEdit}
     * edited with {@code editEndpointDescriptor}.
     */
    private static Endpoint createEndpointWithResponse(Endpoint endpointToSend, Response endpointResponse) {
        assert endpointToSend != null;

        Name name = endpointToSend.getName();
        Address address = endpointToSend.getAddress();
        Set<Tag> tags = endpointToSend.getTags();
        Response response = endpointResponse;

        return new Endpoint(name, address, tags, response);
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
