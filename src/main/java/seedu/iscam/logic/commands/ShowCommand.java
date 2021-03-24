package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;

/**
 * Shows a client identified using it's displayed index from the iscam book.
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the client identified by the index number used in the displayed client list "
            + "in the details fragment below\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_CLIENT_SUCCESS = "Showing Client: %1$s";

    private final Index targetIndex;

    public ShowCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToShow = lastShownList.get(targetIndex.getZeroBased());
        model.setDetailedClient(clientToShow);
        return new CommandResult(String.format(MESSAGE_SHOW_CLIENT_SUCCESS, clientToShow));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && targetIndex.equals(((ShowCommand) other).targetIndex)); // state check
    }
}
