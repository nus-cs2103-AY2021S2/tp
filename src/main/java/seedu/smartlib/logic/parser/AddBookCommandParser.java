package seedu.smartlib.logic.parser;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.AddBookCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;


import java.util.stream.Stream;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CliSyntax.*;

public class AddBookCommandParser implements Parser<AddBookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddBookCommand
     * and returns an AddReaderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOK, PREFIX_AUTHOR, PREFIX_PUBLISHER, PREFIX_ISBN);

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOK, PREFIX_AUTHOR, PREFIX_PUBLISHER, PREFIX_ISBN)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookCommand.MESSAGE_USAGE));
        }

        Name bookName = ParserUtil.parseName(argMultimap.getValue(PREFIX_BOOK).get());
        Author author = ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get());
        Publisher publisher = ParserUtil.parsePublisher(argMultimap.getValue(PREFIX_PUBLISHER).get());
        Isbn isbn = ParserUtil.parseIsbn(argMultimap.getValue(PREFIX_ISBN).get());
        //Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Book book = new Book(bookName, author, publisher, isbn);

        return new AddBookCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
