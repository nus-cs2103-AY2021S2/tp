package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.room.AddRoomCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddRoomCommand object
 */
public class AddRoomCommandParser implements Parser<AddRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoomCommand and returns an AddRoomCommand
     * object for execution.
     *
     * @param userInput The command {@code String} entered by the user.
     * @return The parsed {@code AddRoomCommand}.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddRoomCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ROOM_NUMBER, PREFIX_ROOM_TYPE,
                        PREFIX_ROOM_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOM_NUMBER, PREFIX_ROOM_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.MESSAGE_USAGE));
        }

        RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        RoomType roomType = ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get());

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_ROOM_TAG));

        assert roomNumber != null;
        assert roomType != null;

        Room room = new Room(roomNumber, roomType, tagList);

        return new AddRoomCommand(room);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
