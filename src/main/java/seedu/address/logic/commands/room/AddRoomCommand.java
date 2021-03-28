package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_OCCUPANCY_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;

public class AddRoomCommand extends Command {
    public static final String COMMAND_WORD = "oadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a room to SunRez. "
            + "Parameters: "
            + PREFIX_ROOM_NUMBER + "NAME "
            + PREFIX_ROOM_TYPE + "TYPE "
            + PREFIX_ROOM_OCCUPANCY_STATUS + "OCCUPANCY STATUS "
            + "[" + PREFIX_ROOM_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "12-123 "
            + PREFIX_ROOM_TYPE + "CORRIDOR_AC "
            + PREFIX_ROOM_OCCUPANCY_STATUS + "Y "
            + PREFIX_ROOM_TAG + "SHN";

    public static final String MESSAGE_SUCCESS = "New room added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in SunRez";

    private final Room toAdd;

    /**
     * Creates an AddRoomCommand to add the specified {@code Room}
     */
    public AddRoomCommand(Room room) {
        requireNonNull(room);
        this.toAdd = room;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRoom(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.addRoom(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoomCommand // instanceof handles nulls
                && toAdd.equals(((AddRoomCommand) other).toAdd));
    }
}
