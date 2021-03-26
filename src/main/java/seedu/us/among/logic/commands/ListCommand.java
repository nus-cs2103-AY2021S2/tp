package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.us.among.model.Model;

/**
 * Lists all API endpoints in the API endpoint list to the user.
 */
public class ListCommand extends Command {

    public static final boolean SHOW_HELP = false;
    public static final boolean IS_EXIT = false;
    public static final boolean IS_LIST = true;

    public static final String COMMAND_WORD = "list";

    private static final String MESSAGE_SUCCESS = "Listed all saved API endpoints on the left-hand-side panel.\n";

    private static final String MESSAGE_FILLED_LIST = "Want to see the details of an API endpoint?\n"
            + "Use the show command with the index of an endpoint!\n"
            + "Example (to show the details of the first endpoint):\n"
            + "show 1";

    private static final String MESSAGE_EMPTY_LIST = "Want to add endpoints to fill your empty list?\n"
            + "Use the add command with the details of an endpoint!\n"
            + "Example (adding a sample endpoint):\n"
            + "add -x GET -u https://api.data.gov.sg/v1/environment/air-temperature";

    public static final String MESSAGE_SUCCESS_WITH_FILLED_LIST = MESSAGE_SUCCESS + MESSAGE_FILLED_LIST;

    public static final String MESSAGE_SUCCESS_WITH_EMPTY_LIST = MESSAGE_SUCCESS + MESSAGE_EMPTY_LIST;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEndpointList(Model.PREDICATE_SHOW_ALL_ENDPOINTS);
        return model.isEndpointListEmpty()
                ? new CommandResult(MESSAGE_SUCCESS_WITH_EMPTY_LIST, SHOW_HELP, IS_EXIT, IS_LIST)
                :
                new CommandResult(MESSAGE_SUCCESS_WITH_FILLED_LIST, SHOW_HELP, IS_EXIT, IS_LIST);
    }
}
