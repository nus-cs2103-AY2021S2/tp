package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;

/**
 * Deletes a room identified using it's displayed index from SunRez.
 */
public class DeleteRoomCommand extends Command {
    public static final String COMMAND_WORD = "odel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the room identified by the index number used in the displayed room list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ROOM_SUCCESS = "Deleted room: %1$s";

    private final Index targetIndex;

    public DeleteRoomCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownList = model.getFilteredRoomList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Room roomToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRoom(roomToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ROOM_SUCCESS, roomToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRoomCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRoomCommand) other).targetIndex)); // state check
    }
}
