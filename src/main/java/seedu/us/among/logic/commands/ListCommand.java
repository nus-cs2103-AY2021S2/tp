package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.us.among.model.Model;

/**
 * Lists all API endpoints in the API endpoint list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all API endpoints";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEndpointList(Model.PREDICATE_SHOW_ALL_ENDPOINTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
