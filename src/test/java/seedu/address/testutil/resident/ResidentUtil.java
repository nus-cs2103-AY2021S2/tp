package seedu.address.testutil.resident;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;

/**
 * A utility class for Resident.
 */
public class ResidentUtil {

    /**
     * Returns an add command string for adding the {@code resident}.
     */
    public static String getAddCommand(Resident resident) {
        return AddResidentCommand.COMMAND_WORD + " " + getResidentDetails(resident);
    }

    /**
     * Returns an allocate command string for adding the {@code residentRoom}.
     */
    public static String getAllocateResidentRoomCommand(ResidentRoom residentRoom) {
        return AllocateResidentRoomCommand.COMMAND_WORD + " " + getResidentRoomDetails(residentRoom);
    }

    /**
     * Returns the part of command string for the given {@code residentRoom}'s details.
     */
    public static String getResidentRoomDetails(ResidentRoom residentRoom) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + residentRoom.getName().fullName + " ");
        sb.append(PREFIX_ROOM_NUMBER + residentRoom.getRoomNumber().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code resident}'s details.
     */
    public static String getResidentDetails(Resident resident) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + resident.getName().fullName + " ");
        sb.append(PREFIX_PHONE + resident.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + resident.getEmail().value + " ");
        sb.append(PREFIX_YEAR + resident.getYear().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditResidentDescriptor}'s details.
     */
    public static String getEditResidentDescriptorDetails(EditResidentCommand.EditResidentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getYear().ifPresent(year -> sb.append(PREFIX_YEAR).append(year.value).append(" "));
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room.value).append(" "));

        return sb.toString();
    }
}
