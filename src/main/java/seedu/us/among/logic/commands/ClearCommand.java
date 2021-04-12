package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;

/**
 * Clears the API endpoint list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "API endpoint list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setEndpointList(new EndpointList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
