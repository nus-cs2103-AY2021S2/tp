package seedu.address.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.GeneralEvent;

/**
 * Deletes a person identified using it's displayed index from the remindMe.
 */
public class DeleteGeneralEventCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed general event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_GENERAL_EVENT_SUCCESS = "Deleted General Event: %1$s";

    private final Index targetIndex;

    public DeleteGeneralEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<GeneralEvent> lastShownList = model.getFilteredEventList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GENERAL_EVENT_DISPLAYED_INDEX);
        }

        GeneralEvent eventToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteEvent(eventToDelete);
        return new CommandResult(String.format(MESSAGE_GENERAL_EVENT_SUCCESS, eventToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGeneralEventCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteGeneralEventCommand) other).targetIndex)); // state check
    }
}
