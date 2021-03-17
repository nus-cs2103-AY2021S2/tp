package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonParseException;

import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.EndpointCaller;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;


public class RunCommand extends Command {

    public static final String COMMAND_WORD = "run";

    public static final String MESSAGE_API_EXAMPLE_1 = "1. "
            + COMMAND_WORD + " "
            + PREFIX_METHOD + " get "
            + PREFIX_ADDRESS + " http://localhost:3000/ "
            + PREFIX_DATA + " {some: data} "
            + PREFIX_HEADER + " \"key: value\" "
            + PREFIX_HEADER + " \"key: value\"\n";

    public static final String MESSAGE_API_EXAMPLE_2 = "2. "
            + COMMAND_WORD + " "
            + PREFIX_METHOD + " get "
            + PREFIX_ADDRESS + " https://api.data.gov.sg/v1/environment/air-temperature\n";

    public static final String QUICK_RUN_COMMAND_SYNTAX = "Tip (Only for 10x developers):\n"
            + "Run command has a special syntax! Simply specify the API address to be tested"
            + " and a GET request will be performed. This is to cater for the most common test cases"
            + " in API testing.\n"
            + "Parameters: VALID_API_ADDRESS\n"
            + "Examples:\n"
            + "1. run https://reqres.in/api/users\n"
            + "2. run https://api.data.gov.sg/v1/environment/air-temperature";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Runs an API request without saving it to the API endpoint list.\n"
            + "Parameters: "
            + PREFIX_METHOD + " METHOD "
            + PREFIX_ADDRESS + " ADDRESS "
            + "[" + PREFIX_DATA + " DATA] "
            + "[" + PREFIX_HEADER + " HEADER]...\n"
            + "Examples:\n"
            + MESSAGE_API_EXAMPLE_1
            + MESSAGE_API_EXAMPLE_2 + "\n"
            + QUICK_RUN_COMMAND_SYNTAX;



    public static final String MESSAGE_UNKNOWN_HOST = "The host name could not be resolved. Check your"
            + " internet connection and endpoint URL.";
    public static final String MESSAGE_INVALID_JSON = "The request was not performed successfully. Check"
            + " that your data is added in the correct JSON format.";
    public static final String MESSAGE_CONNECTION_ERROR = "The request was not performed successfully."
            + " Check your internet connection and endpoint URL.";
    public static final String MESSAGE_CALL_CANCELLED = "The request has been aborted.";
    public static final String MESSAGE_GENERAL_ERROR = "The request was not performed successfully."
            + " Check that your endpoint fields are correct.";
    private final Endpoint toRun;

    /**
     * Creates an RunCommand to run the specified {@code Endpoint}
     */
    public RunCommand(Endpoint endpoint) {
        requireNonNull(endpoint);
        toRun = endpoint;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, RequestException {
        requireNonNull(model);

        EndpointCaller epc = new EndpointCaller(toRun);
        Response response;
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

        Endpoint endpointWithResponse = new Endpoint(toRun, response);

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
        if (!(other instanceof RunCommand)) {
            return false;
        }

        // endpoint check
        RunCommand otherRunCommand = (RunCommand) other;
        return this.toRun.equals(otherRunCommand.toRun);
    }

}
