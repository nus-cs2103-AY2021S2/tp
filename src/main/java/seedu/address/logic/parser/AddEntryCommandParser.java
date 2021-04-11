package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddEntryCommand Object
 */
public class AddEntryCommandParser implements Parser<AddEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddEntryCommand
     * and returns an AddEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEntryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEntryCommand.MESSAGE_USAGE));
        }

        EntryName entryName = ParserUtil.parseEntryName(argMultimap.getValue(PREFIX_NAME).get());
        EntryDate startDate = ParserUtil.parseEntryDate(argMultimap.getValue(PREFIX_START_DATE).get());
        EntryDate endDate = ParserUtil.parseEntryDate(argMultimap.getValue(PREFIX_END_DATE).get());

        if (!startDate.isBefore(endDate)) {
            throw new ParseException(MESSAGE_INVALID_DATE_RANGE);
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Entry entry = new Entry(entryName, startDate, endDate, tagList);

        return new AddEntryCommand(entry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
