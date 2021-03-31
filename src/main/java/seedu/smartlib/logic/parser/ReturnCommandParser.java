package seedu.smartlib.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BARCODE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.smartlib.logic.commands.ReturnCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.record.DateReturned;
import seedu.smartlib.model.record.IncompleteRecord;

/**
 * Parses input arguments and creates a new {@code ReturnCommand} object.
 */
public class ReturnCommandParser implements Parser<ReturnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ReturnCommand}
     * and returns a {@code ReturnCommand} object for execution.
     *
     * @param args arguments given in the user input.
     * @return a ReturnCommand object required for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ReturnCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BARCODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_BARCODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
        }

        Barcode bookBarcode = ParserUtil.parseBarcode(argMultimap.getValue(PREFIX_BARCODE).get());

        DateReturned dateReturned = new DateReturned(LocalDateTime.now());
        IncompleteRecord record = new IncompleteRecord(bookBarcode, dateReturned);

        return new ReturnCommand(record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap a map containing the args.
     * @param prefixes prefixes to be checked.
     * @return true if none of the prefixes contains empty values, and false otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
