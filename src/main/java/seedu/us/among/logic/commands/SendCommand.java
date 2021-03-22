package seedu.us.among.logic.commands;

import static seedu.us.among.commons.core.Messages.MESSAGE_CALL_CANCELLED;
import static seedu.us.among.commons.core.Messages.MESSAGE_CONNECTION_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_GENERAL_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_JSON;
import static seedu.us.among.commons.core.Messages.MESSAGE_UNKNOWN_HOST;
import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonParseException;

import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.request.EndpointCaller;
import seedu.us.among.logic.request.exceptions.AbortRequestException;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;


/**
 * Calls a saved API endpoint using it's displayed index from the API endpoints list.
 */
public class SendCommand extends Command {

    public static final String COMMAND_WORD = "send";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Calls a saved API endpoint using the displayed index from the API endpoints list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final Logger logger = LogsCenter.getLogger(SendCommand.class);

    private final Index index;

    /**
     * @param index of the endpoint in the API endpoints list to call
     */
    public SendCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, RequestException, AbortRequestException {
        List<Endpoint> lastShownList = model.getFilteredEndpointList();
        assert index.getZeroBased() >= 0;
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.info("Illegal index found, out of bound");
            throw new CommandException(Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
        }

        Endpoint endpointToSend = lastShownList.get(index.getZeroBased());
        EndpointCaller epc = new EndpointCaller(endpointToSend);
        Response response;

        //to-do Tan Jin verify again all errors caught here due to API calls
        try {
            response = epc.callEndpoint();
        } catch (UnknownHostException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_UNKNOWN_HOST);
        } catch (ClientProtocolException | SocketTimeoutException | SocketException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_CONNECTION_ERROR);
        } catch (JsonParseException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_INVALID_JSON);
        } catch (IllegalStateException | SSLException | NoHttpResponseException | InterruptedIOException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new AbortRequestException(MESSAGE_CALL_CANCELLED);
        } catch (IOException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_GENERAL_ERROR);
        }

        Endpoint endpointWithResponse = new Endpoint(endpointToSend, response);
        model.setEndpoint(endpointToSend, endpointWithResponse);

        return new CommandResult(endpointWithResponse.getResponseEntity(),
                endpointWithResponse,
                false,
                false,
                true);
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
