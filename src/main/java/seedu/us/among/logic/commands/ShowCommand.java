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
 * Shows the details of an existing API endpoint identified using it's displayed
 * index from the API endpoint list.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_SUCCESS = "Endpoint details: \n%1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the details of an existing endpoint "
            + "using its displayed index from the endpoint list.\n"
            + "Parameters: INDEX\n"
            + MESSAGE_USE_HELP + "\n\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Index index;

    /**
     * @param index of the endpoint in the filtered endpoint list to show
     */
    public ShowCommand(Index index) {
        requireNonNull(index);
        assert index.getZeroBased() >= 0;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Endpoint> lastShownList = model.getFilteredEndpointList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_ERROR,
                    Messages.MESSAGE_INDEX_NOT_WITHIN_LIST,
                    ShowCommand.MESSAGE_USAGE));
        }

        Endpoint endpointToShow = lastShownList.get(index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS, endpointToShow.toString()), endpointToShow, true);
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
