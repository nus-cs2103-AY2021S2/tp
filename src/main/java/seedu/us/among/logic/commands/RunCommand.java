package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;

import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.request.EndpointCaller;
import seedu.us.among.logic.request.exceptions.AbortRequestException;
import seedu.us.among.logic.request.exceptions.RequestException;
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

    private final Endpoint toRun;

    /**
     * Creates an RunCommand to run the specified {@code Endpoint}
     */
    public RunCommand(Endpoint endpoint) {
        requireNonNull(endpoint);
        toRun = endpoint;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, RequestException, AbortRequestException {
        requireNonNull(model);

        EndpointCaller endpointCaller = new EndpointCaller(toRun);
        Response response = endpointCaller.callEndpoint();

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
