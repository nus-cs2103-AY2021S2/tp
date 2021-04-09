package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residentroom.ResidentRoom;
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
    public static final String MESSAGE_ROOM_ALLOCATED_FAILURE =
            "The room has been allocated to another resident. Please deallocate the room before deletion.";
    public static final String MESSAGE_ROOM_HAS_ISSUES = "This room still has issues assigned to it. Please delete all "
            + "corresponding issues before deleting the room.";

    private final Index targetIndex;

    public DeleteRoomCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownList = model.getFilteredRoomList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_ROOMS);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX, lastShownList.size()));
        }

        Room roomToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (model.hasEitherResidentRoom(new ResidentRoom(null, roomToDelete.getRoomNumber()))) {
            throw new CommandException(MESSAGE_ROOM_ALLOCATED_FAILURE);
        }

        if (model.issuesContainRoom(roomToDelete)) {
            throw new CommandException(MESSAGE_ROOM_HAS_ISSUES);
        }

        model.deleteRoom(roomToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_ROOM_SUCCESS, roomToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRoomCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRoomCommand) other).targetIndex)); // state check
    }
}
