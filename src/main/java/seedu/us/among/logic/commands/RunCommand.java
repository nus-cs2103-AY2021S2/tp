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
    public static final String MESSAGE_API_EXAMPLE = COMMAND_WORD + " "
            + PREFIX_METHOD + " PUT "
            + PREFIX_ADDRESS + " https://reqres.in/api/users/2 "
            + PREFIX_DATA + " {\"name\": \"steve\", \"job\": \"lawyer\"} "
            + PREFIX_HEADER + " \"key: value\"\n ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Calls an API without saving it to the endpoint list.\n"
            + "Parameters: "
            + PREFIX_METHOD + " METHOD "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_DATA + " DATA "
            + "[" + PREFIX_HEADER + " HEADER]\n"
            + "Example: "
            + MESSAGE_API_EXAMPLE;

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
        return new CommandResult(endpointWithResponse.getResponseEntity(), endpointWithResponse);
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
