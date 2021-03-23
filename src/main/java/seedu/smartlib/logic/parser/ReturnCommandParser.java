package seedu.smartlib.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.ReturnCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.record.DateReturned;
import seedu.smartlib.model.record.Record;

/**
 * Parses input arguments and creates a new {@code ReturnCommand} object
 */
public class ReturnCommandParser implements Parser<ReturnCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code ReturnCommand}
     * and returns a {@code ReturnCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReturnCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOK, PREFIX_READER);

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOK, PREFIX_READER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
        }

        Name bookName = ParserUtil.parseName(argMultimap.getValue(PREFIX_BOOK).get());
        Name readerName = ParserUtil.parseName(argMultimap.getValue(PREFIX_READER).get());
        DateReturned dateReturned = new DateReturned(LocalDate.now());
        Record record = new Record(bookName, readerName, dateReturned);

        return new ReturnCommand(record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
