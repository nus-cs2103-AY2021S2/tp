package seedu.address.logic.commands.residentroom;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;

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
import seedu.address.model.room.Room;

public class DeallocateResidentRoomCommand extends Command {
    public static final String COMMAND_WORD = "dealloc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deallocates a resident from a room in SunRez. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Tan"
            + PREFIX_ROOM_NUMBER + "12-123 ";

    public static final String MESSAGE_SUCCESS = "Deallocation made: %1$s";
    public static final String MESSAGE_NOT_FOUND_RESIDENTROOM = "This allocation does not exist in SunRez";

    private final ResidentRoom toDeallocate;
    private final EditResidentDescriptor editResidentDescriptor;
    private final EditRoomDescriptor editRoomDescriptor;


    /**
     * Creates an AddResidentRoomCommand to add the specified {@code ResidentRoom}
     */
    public DeallocateResidentRoomCommand(ResidentRoom residentRoom, EditResidentDescriptor editResidentDescriptor,
            EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(residentRoom);
        this.toDeallocate = residentRoom;
        this.editResidentDescriptor = new EditResidentDescriptor(editResidentDescriptor);
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasBothResidentRoom(toDeallocate)) {
            throw new CommandException(MESSAGE_NOT_FOUND_RESIDENTROOM);
        }

        // Set Resident room to unallocated
        Resident residentToEdit = model.getResidentWithSameName(toDeallocate.getName());
        Resident editedResident = EditResidentCommand.createEditedResident(residentToEdit, editResidentDescriptor);
        model.setResident(residentToEdit, editedResident);
        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);

        //  Set Room occupancy to N.
        Room roomToEdit = model.getRoomWithSameRoomNumber(toDeallocate.getRoomNumber());
        Room editedRoom = EditRoomCommand.createEditedRoom(roomToEdit, editRoomDescriptor);
        model.setRoom(roomToEdit, editedRoom);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);

        model.deleteResidentRoom(toDeallocate);
        // Set Room occupancy to N.
        // Set Resident room to 'Room unallocated'.
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDeallocate)).setResidentRoomCommand();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeallocateResidentRoomCommand // instanceof handles nulls
                && toDeallocate.equals(((DeallocateResidentRoomCommand) other).toDeallocate));
    }
}
