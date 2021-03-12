package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.DateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Event;

/**
 * Parses input arguments and creates a new DateCommand object
 */
public class DateCommandParser implements Parser<DateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DateCommand
     * and returns a DateCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public DateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateCommand.MESSAGE_USAGE));
        }

        LocalDate date = DateUtil.fromDateInput(argMultimap.getValue(PREFIX_DATE).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();

        return new DateCommand(index, new Event(date, description));
    }
}
