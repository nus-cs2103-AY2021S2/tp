package seedu.address.logic.parser.residentroom;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddResidentCommand object
 */
public class AllocateResidentRoomCommandParser implements Parser<AllocateResidentRoomCommand> {
    public static final String MESSAGE_RESIDENT_INDEX_IS_BELOW_RANGE_PREFIX =
            "The value for Resident Index is invalid: %s";
    public static final String MESSAGE_ROOM_INDEX_IS_BELOW_RANGE_PREFIX =
            "The value for Room Index is invalid: %s";

    /**
     * Parses the given {@code String} of arguments in the context of the AddResidentCommand
     * and returns an AddResidentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AllocateResidentRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESIDENT_INDEX, PREFIX_ROOM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESIDENT_INDEX, PREFIX_ROOM_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AllocateResidentRoomCommand.MESSAGE_USAGE));
        }
        Index targetResidentIndex;
        Index targetRoomIndex;

        // Unique way of dealing with this as these are INDEX types, and not fields that are parsed.
        try {
            targetResidentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RESIDENT_INDEX).get());
        } catch (ParseException | IllegalArgumentException ex) {
            throw new ParseException(
                    String.format(MESSAGE_RESIDENT_INDEX_IS_BELOW_RANGE_PREFIX, ex.getMessage()), ex);
        }

        try {
            targetRoomIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ROOM_INDEX).get());
        } catch (ParseException | IllegalArgumentException ex) {
            throw new ParseException(
                    String.format(MESSAGE_ROOM_INDEX_IS_BELOW_RANGE_PREFIX, ex.getMessage()), ex);
        }

        return new AllocateResidentRoomCommand(targetResidentIndex, targetRoomIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
