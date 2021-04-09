package seedu.address.testutil.room;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import java.util.Set;

import seedu.address.logic.commands.room.AddRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.model.room.Room;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Room.
 */
public class RoomUtil {
    /**
     * Returns an add command string for adding the {@code room}.
     */
    public static String getAddCommand(Room room) {
        return AddRoomCommand.COMMAND_WORD + " " + getRoomDetails(room);
    }

    /**
     * Returns the part of command string for the given {@code room}'s details.
     */
    public static String getRoomDetails(Room room) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ROOM_NUMBER + room.getRoomNumber().roomNumber + " ");
        sb.append(PREFIX_ROOM_TYPE + room.getRoomType().value.toString() + " ");
        room.getTags().stream().forEach(
            s -> sb.append(PREFIX_ROOM_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRoomDescriptor}'s details.
     */
    public static String getEditRoomDescriptorDetails(EditRoomCommand.EditRoomDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRoomNumber()
                .ifPresent(roomNumber -> sb.append(PREFIX_ROOM_NUMBER + roomNumber.roomNumber + " "));
        descriptor.getRoomType()
                .ifPresent(roomType -> sb.append(PREFIX_PHONE).append(roomType.value.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_ROOM_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_ROOM_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
