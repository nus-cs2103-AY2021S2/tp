package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
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

        Event event;
        if (argMultimap.getValue(PREFIX_DATE).isPresent() && argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            event = new Event(argMultimap.getValue(PREFIX_DATE).get(), argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateCommand.MESSAGE_USAGE));
        }

        return new DateCommand(index, event);
    }
}
