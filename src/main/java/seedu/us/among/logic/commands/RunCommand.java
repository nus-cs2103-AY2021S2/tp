package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonParseException;

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

public class RunCommand extends Command {

    public static final String COMMAND_WORD = "run";

    public static final String MESSAGE_API_EXAMPLE_1 = "1. "
            + COMMAND_WORD + " "
            + PREFIX_METHOD + "get "
            + PREFIX_ADDRESS + "http://localhost:3000/ "
            + PREFIX_DATA + "{\"some\": \"data\"} "
            + PREFIX_HEADER + "\"key: value\" "
            + PREFIX_HEADER + "\"key: value\"\n";

    public static final String MESSAGE_API_EXAMPLE_2 = "2. "
            + COMMAND_WORD + " "
            + PREFIX_METHOD + "get "
            + PREFIX_ADDRESS + "https://api.data.gov.sg/v1/environment/air-temperature ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Runs an API request without saving it to the API endpoint list.\n"
            + "Parameters: "
            + PREFIX_METHOD + " METHOD "
            + PREFIX_ADDRESS + " ADDRESS "
            + "[" + PREFIX_DATA + " DATA] "
            + "[" + PREFIX_HEADER + " HEADER]...\n"
            + "Examples:\n"
            + MESSAGE_API_EXAMPLE_1
            + MESSAGE_API_EXAMPLE_2;

    public static final String MESSAGE_INVALID_JSON = "The request was not performed successfully. Check"
            + " that your data is added in the correct JSON format.";
    public static final String MESSAGE_CONNECTION_ERROR = "The request was not performed successfully."
            + " Check your internet connection and endpoint URL.";
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
        } catch (UnknownHostException | ClientProtocolException | SocketTimeoutException e) {
            throw new RequestException(MESSAGE_CONNECTION_ERROR);
        } catch (JsonParseException e) {
            throw new RequestException(MESSAGE_INVALID_JSON);
        } catch (IOException e) {
            throw new RequestException(MESSAGE_GENERAL_ERROR);
        }

        Endpoint endpointWithResponse = createEndpointWithResponse(toRun, response);

        return new CommandResult(endpointWithResponse.getResponse().getResponseEntity(),
                endpointWithResponse,
                false,
                false,
                true);
    }

    /**
     * Creates and returns a {@code Endpoint} with the details of {@code endpointToSend}
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
        if (!(other instanceof RunCommand)) {
            return false;
        }

        // endpoint check
        RunCommand otherRunCommand = (RunCommand) other;
        return this.toRun.equals(otherRunCommand.toRun);
    }

}
