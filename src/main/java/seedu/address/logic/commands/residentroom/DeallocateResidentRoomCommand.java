package seedu.address.logic.commands.residentroom;

import static java.util.Objects.requireNonNull;

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
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.RoomNumber;

public class DeallocateResidentRoomCommand extends Command {
    public static final String COMMAND_WORD = "dealloc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deallocates a resident from a room in SunRez "
            + "identified by the index number used in the displayed resident list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deallocation made: %1$s";
    public static final String UNALLOCATED_FAILURE = "The resident has not been allocated.";

    private final Index targetIndex;

    /**
     * Creates an AddResidentRoomCommand to add the specified {@code ResidentRoom}
     */
    public DeallocateResidentRoomCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Resident> lastShownList = model.getFilteredResidentList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_RESIDENTS);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX, lastShownList.size()));
        }

        Resident residentToDeallocate = lastShownList.get(targetIndex.getZeroBased());
        ResidentRoom residentRoomToDeallocate = getResidentRoomToDeallocate(residentToDeallocate);

        setResidentToUnallocated(residentToDeallocate, model);
        setRoomToUnoccupied(residentToDeallocate, model);

        model.deleteResidentRoom(residentRoomToDeallocate);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, residentRoomToDeallocate));
    }

    private ResidentRoom getResidentRoomToDeallocate(Resident residentToDeallocate) throws CommandException {
        if (residentToDeallocate.getRoom().toString().equals(Room.UNALLOCATED_REGEX)) {
            throw new CommandException(UNALLOCATED_FAILURE);
        }
        Name name = new Name(residentToDeallocate.getName().toString());
        RoomNumber roomNumber = new RoomNumber(residentToDeallocate.getRoom().toString());
        return new ResidentRoom(name, roomNumber);
    }

    private void setResidentToUnallocated(Resident residentToDeallocate, Model model) {
        EditResidentDescriptor editResidentDescriptor = new EditResidentCommand.EditResidentDescriptor();
        editResidentDescriptor.setRoom(new Room(Room.UNALLOCATED_REGEX));
        Resident editedResident = EditResidentCommand
                .createEditedResident(residentToDeallocate, editResidentDescriptor);
        model.setResident(residentToDeallocate, editedResident);
        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
    }

    private void setRoomToUnoccupied(Resident residentToDeallocate, Model model) {
        EditRoomDescriptor editRoomDescriptor = new EditRoomCommand.EditRoomDescriptor();
        editRoomDescriptor.setIsOccupied(new IsOccupied(IsOccupied.UNOCCUPIED));
        RoomNumber roomNumber = new RoomNumber(residentToDeallocate.getRoom().toString());
        seedu.address.model.room.Room roomToEdit = model.getRoomWithSameRoomNumber(roomNumber);
        seedu.address.model.room.Room editedRoom = EditRoomCommand.createEditedRoom(roomToEdit, editRoomDescriptor);
        model.setRoom(roomToEdit, editedRoom);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeallocateResidentRoomCommand // instanceof handles nulls
                && targetIndex.equals(((DeallocateResidentRoomCommand) other).targetIndex));
    }
}
