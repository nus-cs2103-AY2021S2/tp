package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;

/**
 * Parses input arguments and creates a new AddTodoCommand object.
 */
public class AddTodoCommandParser implements Parser<AddTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTodoCommand
     * and returns an AddTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddTodoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE), e);
        }

        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Interval interval = ParserUtil.parseInterval(argMultimap.getValue(PREFIX_REPEATABLE_INTERVAL).get());
        LocalDate at = ParserUtil.parseDate(argMultimap.getValue(PREFIX_REPEATABLE_DATE).get());

        Event event = new Event(description, interval, at);

        return new AddEventCommand(index, event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
