package seedu.smartlib.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;

import seedu.smartlib.logic.commands.BorrowCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.record.Record;

/**
 * Parses input arguments and creates a new {@code BorrowCommand} object
 */
public class BorrowCommandParser implements Parser<BorrowCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code BorrowCommand}
     * and returns a {@code BorrowCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BorrowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOK, PREFIX_READER);

        String bookName;
        String readerName;
        bookName = argMultimap.getValue(PREFIX_BOOK).orElse("");
        readerName = argMultimap.getValue(PREFIX_READER).orElse("");
        Record record = new Record(bookName, readerName);

        return new BorrowCommand(record);
    }
}
