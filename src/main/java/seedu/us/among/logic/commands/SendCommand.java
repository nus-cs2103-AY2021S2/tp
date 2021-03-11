package seedu.us.among.logic.commands;

import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonParseException;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.EndpointCaller;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.endpoint.header.Header;
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

    public static final String MESSAGE_UNKNOWN_HOST = "The host name could not be resolved. Check your"
            + " internet connection and endpoint URL.";
    public static final String MESSAGE_INVALID_JSON = "The request was not performed successfully. Check"
            + " that your data is added in the correct JSON format.";
    public static final String MESSAGE_CONNECTION_ERROR = "Connection was lost or could not be established."
            + " Check your internet connection and endpoint URL. This could also be a problem with the server"
            + " you are attempting to connect to.";
    public static final String MESSAGE_CALL_CANCELLED = "The request has been aborted.";
    public static final String MESSAGE_GENERAL_ERROR = "The request was not performed successfully."
            + " Check that your endpoint fields are correct. This could also be a problem with the server"
            + " you are attempting to connect to.";

    private final Index index;

    /**
     * @param index of the endpoint in the API endpoints list to call
     */
    public SendCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, RequestException {
        List<Endpoint> lastShownList = model.getFilteredEndpointList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
        }

        Endpoint endpointToSend = lastShownList.get(index.getZeroBased());
        EndpointCaller epc = new EndpointCaller(endpointToSend);
        Response response;

        //to-do Tan Jin verify again all errors caught here due to API calls
        try {
            response = epc.callEndpoint();
        } catch (UnknownHostException e) {
            throw new RequestException(MESSAGE_UNKNOWN_HOST);
        } catch (ClientProtocolException | SocketTimeoutException | SocketException | NoHttpResponseException e) {
            throw new RequestException(MESSAGE_CONNECTION_ERROR);
        } catch (JsonParseException e) {
            throw new RequestException(MESSAGE_INVALID_JSON);
        } catch (IllegalStateException | SSLException e) {
            throw new RequestException(MESSAGE_CALL_CANCELLED);
        } catch (IOException e) {
            throw new RequestException(MESSAGE_GENERAL_ERROR);
        }

        Endpoint endpointWithResponse = createEndpointWithResponse(endpointToSend, response);

        model.setEndpoint(endpointToSend, endpointWithResponse);

        return new CommandResult(endpointWithResponse.getResponse().getResponseEntity(),
                endpointWithResponse,
                false,
                false,
                true);
    }

    /**
     * Creates and returns a {@code Endpoint} with the details of {@code endpointToEdit}
     * edited with {@code editEndpointDescriptor}.
     */
    private static Endpoint createEndpointWithResponse(Endpoint endpointToSend, Response endpointResponse) {
        assert endpointToSend != null;

        Method method = endpointToSend.getMethod();
        Address address = endpointToSend.getAddress();
        Data data = endpointToSend.getData();
        Set<Header> headers = endpointToSend.getHeaders();
        Set<Tag> tags = endpointToSend.getTags();

        return new Endpoint(method, address, data, headers, tags, endpointResponse);
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
