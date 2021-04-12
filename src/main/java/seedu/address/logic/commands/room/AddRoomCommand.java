package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;

public class AddRoomCommand extends Command {
    public static final String COMMAND_WORD = "oadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a room to SunRez. "
            + "Parameters: "
            + PREFIX_ROOM_NUMBER + "ROOM_NUMBER "
            + PREFIX_ROOM_TYPE + "TYPE "
            + "[" + PREFIX_ROOM_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "12-123 "
            + PREFIX_ROOM_TYPE + "CORRIDOR_AC "
            + PREFIX_ROOM_TAG + "SHN";

    public static final String MESSAGE_SUCCESS = "New room added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in SunRez";
    private static final Logger logger = LogsCenter.getLogger(AddRoomCommand.class);

    private final Room toAdd;

    /**
     * Creates an AddRoomCommand to add the specified {@code Room}
     *
     * @throws NullPointerException if {@code Room} is null.
     */
    public AddRoomCommand(Room room) {
        requireNonNull(room);
        this.toAdd = room;
    }

    /**
     * Executes the AddRoomCommand with the specified {@code Model}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with the {@code Room} that was added.
     * @throws CommandException If the room being added is a duplicate of one already in the model.
     */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRoom(toAdd)) {
            logger.warning("Duplicate room being added via oadd command");
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.addRoom(toAdd);
        model.commitAddressBook();

        logger.info("AddRoomCommand successfully updated the model");
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoomCommand // instanceof handles nulls
                && toAdd.equals(((AddRoomCommand) other).toAdd));
    }
}
