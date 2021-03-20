package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Shows the details of an existing API endpoint identified using it's displayed
 * index from the API endpoint list.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the details of an existing API endpoint "
            + "identified using it's displayed index from the API endpoint list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Index index;

    /**
     * @param index of the endpoint in the filtered endpoint list to show
     */
    public ShowCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Endpoint> lastShownList = model.getFilteredEndpointList();
        assert index.getZeroBased() >= 0;
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
        }

        Endpoint endpointToShow = lastShownList.get(index.getZeroBased());
        return new CommandResult(endpointToShow.toString());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCommand)) {
            return false;
        }

        // state check
        ShowCommand command = (ShowCommand) other;
        return this.index.equals(command.index);
    }

}
