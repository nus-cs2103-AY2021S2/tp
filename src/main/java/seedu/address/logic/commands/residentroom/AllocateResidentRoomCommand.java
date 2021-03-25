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

public class AllocateResidentRoomCommand extends Command {
    public static final String COMMAND_WORD = "alloc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allocates a resident to a room in SunRez. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Tan"
            + PREFIX_ROOM_NUMBER + "12-123 ";

    public static final String MESSAGE_SUCCESS = "New allocation made: %1$s";
    public static final String MESSAGE_NOT_FOUND_RESIDENT = "This resident does not exist";
    public static final String MESSAGE_NOT_FOUND_ROOM = "This room does not exist";
    public static final String MESSAGE_DUPLICATE_RESIDENTROOM = "This resident or room has "
            + "already been allocated in SunRez";

    private final ResidentRoom toAllocate;
    private final EditResidentDescriptor editResidentDescriptor;
    private final EditRoomDescriptor editRoomDescriptor;


    /**
     * Creates an AddResidentRoomCommand to add the specified {@code ResidentRoom}
     */
    public AllocateResidentRoomCommand(ResidentRoom residentRoom, EditResidentDescriptor editResidentDescriptor,
                    EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(residentRoom);
        this.toAllocate = residentRoom;
        this.editResidentDescriptor = new EditResidentDescriptor(editResidentDescriptor);
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Checks if room exists
        if (!model.hasRoom(toAllocate.getRoomNumber())) {
            throw new CommandException(MESSAGE_NOT_FOUND_ROOM);
        }

        // Checks if resident exists
        if (!model.hasResident(toAllocate.getName())) {
            throw new CommandException(MESSAGE_NOT_FOUND_RESIDENT);
        }

        // Checks if either resident or room is allocated or occupied
        if (model.hasEitherResidentRoom(toAllocate)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENTROOM);
        }

        // Set Resident room to Room Number
        Resident residentToEdit = model.getResidentWithSameName(toAllocate.getName());
        Resident editedResident = EditResidentCommand.createEditedResident(residentToEdit, editResidentDescriptor);
        model.setResident(residentToEdit, editedResident);
        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);

        //  Set Room occupancy to Y.
        Room roomToEdit = model.getRoomWithSameRoomNumber(toAllocate.getRoomNumber());
        Room editedRoom = EditRoomCommand.createEditedRoom(roomToEdit, editRoomDescriptor);
        model.setRoom(roomToEdit, editedRoom);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);

        // Set ResidentRoom
        model.addResidentRoom(toAllocate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAllocate)).setResidentRoomCommand();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllocateResidentRoomCommand // instanceof handles nulls
                && toAllocate.equals(((AllocateResidentRoomCommand) other).toAllocate));
    }
}
