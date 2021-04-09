package seedu.address.logic.commands.residentroom;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand.EditResidentDescriptor;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import seedu.address.model.Model;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.Room;

public class AllocateResidentRoomCommand extends Command {
    public static final String COMMAND_WORD = "alloc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allocates a resident to a room in SunRez"
            + " identified by a resident index and room index displayed in the resident and room lists respectively. \n"
            + "Parameters: "
            + PREFIX_RESIDENT_INDEX + "INDEX "
            + PREFIX_ROOM_INDEX + "INDEX (both indices must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RESIDENT_INDEX + "1 "
            + PREFIX_ROOM_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "New allocation made: %1$s";
    public static final String MESSAGE_DUPLICATE_RESIDENTROOM = "This resident or room has "
            + "already been allocated in SunRez";

    private final Index targetResidentIndex;
    private final Index targetRoomIndex;

    /**
     * Creates an AddResidentRoomCommand to add the specified {@code ResidentRoom}
     */
    public AllocateResidentRoomCommand(Index targetResidentIndex, Index targetRoomIndex) {
        this.targetResidentIndex = targetResidentIndex;
        this.targetRoomIndex = targetRoomIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Resident> lastShownResidentList = model.getFilteredResidentList();
        List<Room> lastShownRoomList = model.getFilteredRoomList();

        if (lastShownResidentList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_RESIDENTS);
        }

        if (lastShownRoomList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_ROOMS);
        }

        if (targetResidentIndex.getZeroBased() >= lastShownResidentList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX, lastShownResidentList.size()));
        }

        if (targetRoomIndex.getZeroBased() >= lastShownRoomList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX, lastShownRoomList.size()));
        }

        Resident residentToAllocate = lastShownResidentList.get(targetResidentIndex.getZeroBased());
        Room roomToBeAllocated = lastShownRoomList.get(targetRoomIndex.getZeroBased());

        ResidentRoom residentRoom = new ResidentRoom(residentToAllocate.getName(), roomToBeAllocated.getRoomNumber());

        // Checks if either resident or room is allocated or occupied
        if (model.hasEitherResidentRoom(residentRoom)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENTROOM);
        }

        setResidentToAllocated(residentToAllocate, roomToBeAllocated, model);
        setRoomToOccupied(roomToBeAllocated, model);

        // Set ResidentRoom
        model.addResidentRoom(residentRoom);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, residentRoom));
    }

    private void setResidentToAllocated(Resident resident, Room room, Model model) {
        EditResidentDescriptor editResidentDescriptor = new EditResidentCommand.EditResidentDescriptor();
        editResidentDescriptor.setRoom(new seedu.address.model.resident.Room(room.getRoomNumber().toString()));
        Resident editedResident = EditResidentCommand
                .createEditedResident(resident, editResidentDescriptor);
        model.setResident(resident, editedResident);
        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
    }

    private void setRoomToOccupied(Room room, Model model) {
        EditRoomDescriptor editRoomDescriptor = new EditRoomCommand.EditRoomDescriptor();
        editRoomDescriptor.setIsOccupied(new IsOccupied(IsOccupied.OCCUPIED));
        seedu.address.model.room.Room editedRoom = EditRoomCommand.createEditedRoom(room, editRoomDescriptor);
        model.setRoom(room, editedRoom);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllocateResidentRoomCommand // instanceof handles nulls
                && targetResidentIndex.equals(((AllocateResidentRoomCommand) other).targetResidentIndex)
                && targetRoomIndex.equals(((AllocateResidentRoomCommand) other).targetRoomIndex));
    }
}
