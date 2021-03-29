package seedu.address.logic.parser.residentroom;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand.EditResidentDescriptor;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.logic.commands.residentroom.DeallocateResidentRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Room;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.RoomNumber;


/**
 * Parses input arguments and creates a new AddResidentCommand object
 */
public class DeallocateResidentRoomCommandParser implements Parser<DeallocateResidentRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AllocateResidentRoomCommand
     * and returns an AllocateResidentRoomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeallocateResidentRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROOM_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ROOM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AllocateResidentRoomCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());

        ResidentRoom residentRoom = new ResidentRoom(name, roomNumber);

        EditResidentDescriptor editResidentDescriptor = new EditResidentCommand.EditResidentDescriptor();
        EditRoomDescriptor editRoomDescriptor = new EditRoomCommand.EditRoomDescriptor();

        editResidentDescriptor.setRoom(ParserUtil.parseRoom(Room.UNALLOCATED_REGEX));
        editRoomDescriptor.setIsOccupied(ParserUtil.parseRoomOccupancyStatus(IsOccupied.UNOCCUPIED));

        return new DeallocateResidentRoomCommand(residentRoom, editResidentDescriptor, editRoomDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
