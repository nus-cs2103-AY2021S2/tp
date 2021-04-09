package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_USE_HELP;

import java.util.List;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Removes an API endpoint identified using it's displayed index from the API endpoint list.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes an existing endpoint using its displayed index from the endpoint list.\n"
            + "Parameters: INDEX\n"
            + MESSAGE_USE_HELP + "\n\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_ENDPOINT_SUCCESS = "Endpoint removed: \n%1$s";

    private final Index targetIndex;

    public RemoveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Endpoint> lastShownList = model.getFilteredEndpointList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_ERROR,
                    Messages.MESSAGE_INDEX_NOT_WITHIN_LIST, RemoveCommand.MESSAGE_USAGE));
        }

        Endpoint endpointToRemove = lastShownList.get(targetIndex.getZeroBased());
        model.removeEndpoint(endpointToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVE_ENDPOINT_SUCCESS, endpointToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)); // state check
    }
}
