package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ENTRY_START_DATE_IN_PAST;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.FreeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.ListOccupyingEntryPredicate;

/**
 * Parses input arguments and creates a new FreeCommand object
 */
public class FreeCommandParser implements Parser<FreeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FreeCommand
     * and returns a FreeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FreeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FreeCommand.MESSAGE_USAGE));
        }

        EntryDate startDateTime = ParserUtil.parseEntryDate(argMultimap.getValue(PREFIX_START_DATE).get());
        EntryDate endDateTime = ParserUtil.parseEntryDate(argMultimap.getValue(PREFIX_END_DATE).get());

        if (startDateTime.isAfter(endDateTime)) {
            throw new ParseException(MESSAGE_INVALID_DATE_RANGE);
        }

        LocalDateTime now = LocalDateTime.now();
        if (startDateTime.getDate().isBefore(now)) {
            throw new ParseException(MESSAGE_ENTRY_START_DATE_IN_PAST);
        }

        return new FreeCommand(new ListOccupyingEntryPredicate(startDateTime, endDateTime));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
