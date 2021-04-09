package seedu.address.logic.commands.commandhistory;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;

/**
 * Displays the most recent commands entered to the user.
 */
public class ViewHistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the COUNT most recent commands entered. "
            + "If COUNT is not specified, then it displays all commands entered.\n"
            + "Parameters: COUNT (optional, must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 5";

    public static final String MESSAGE_COUNT_OUT_OF_RANGE = "The provided COUNT is invalid. "
            + "Valid range: %d to %d (inclusive)";

    public static final String MESSAGE_INVALID_COUNT = "The provided COUNT is invalid. "
            + "It should be a positive integer.";

    public static final String MESSAGE_EMPTY_HISTORY = "No successful commands have been entered yet.";

    public static final String MESSAGE_HEADER_SUCCESS = "Last %d command(s):\n";

    public static final String MESSAGE_ENTRY_FORMAT = "%d:\t%s\n";

    private final Optional<Integer> optCount;

    /**
     * Constructs a new {@code ViewHistoryCommand} with the given number of entries to display.
     *
     * @param count The number of entries to display.
     */
    public ViewHistoryCommand(int count) {
        optCount = Optional.of(count);
    }

    /**
     * Constructs a new {@code ViewHistoryCommand} that displays the entire command history.
     */
    public ViewHistoryCommand() {
        optCount = Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewHistoryCommand // instanceof handles nulls
                && optCount.equals(((ViewHistoryCommand) other).optCount)); // state check
    }

    /**
     * Displays the requested number of command history entries.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with the command history to display.
     * @throws CommandException     If the {@code count} this command was constructed with is out of range.
     * @throws NullPointerException If {@code model} is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyCommandHistory history = model.getCommandHistory();
        assert history != null;
        assert history.size() >= 0;
        assert optCount != null;
        int count = optCount.orElse(history.size());

        if (optCount.isPresent() && count <= 0) {
            throw new CommandException(MESSAGE_INVALID_COUNT);
        }

        if (history.size() == 0) {
            return new CommandResult(MESSAGE_EMPTY_HISTORY);
        }

        if (count > history.size()) {
            throw new CommandException(String.format(MESSAGE_COUNT_OUT_OF_RANGE, 1, history.size()));
        }

        StringBuilder msg = new StringBuilder();
        msg.append(String.format(MESSAGE_HEADER_SUCCESS, count));
        for (int i = history.size() - 1; i >= history.size() - count; i--) {
            assert history.get(i) != null && history.get(i).value != null;

            final int entryNum = i + 1;
            final String entryText = history.get(i).toString();
            msg.append(String.format(MESSAGE_ENTRY_FORMAT, entryNum, entryText));
        }

        return new CommandResult(msg.toString());
    }
}
