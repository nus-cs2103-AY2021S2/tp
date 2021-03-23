package seedu.smartlib.logic.commands;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;

import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.record.Record;

/**
 * Marks the record indicating that a reader returning a book
 */
public class ReturnCommand extends Command {

    public static final String COMMAND_WORD = "return";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": returns the book borrowed by the reader.\n"
            + "Parameters: " + PREFIX_BOOK + "<book name> " + PREFIX_READER + "<reader name>\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BOOK + "The Hobbit " + PREFIX_READER + "Alex Yeoh";
    public static final String MESSAGE_SUCCESS = "Record marked as returned.";
    public static final String MESSAGE_NO_SUCH_RECORD_FOUND =
            "No such record found. Either already returned or never borrowed";
    public static final String NO_READER_AND_BOOK_FOUND = "Sorry, we could find "
            + "neither the book nor the reader you specified. Please check if you have spelled correctly.";
    public static final String NO_BOOK_FOUND = "Sorry, we could not find the "
            + "book you specified. Please check if you have spelled correctly.";
    public static final String NO_READER_FOUND = "Sorry, we could not find the "
            + "reader you specified. Please check if you have spelled correctly.";
    public static final String UNABLE_TO_UPDATE_CODEBASE = "Sorry, an error occurred with the codebase and we are "
            + "unable to update it.";

    private final Record toReturn;

    /**
     * Creates a ReturnCommand to add a record
     * @param record recordToAdd
     */
    public ReturnCommand(Record record) {
        requireAllNonNull(record);
        toReturn = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        if (!model.hasBook(toReturn.getBookName()) && !model.hasReader(toReturn.getReaderName())) {
            throw new CommandException(NO_READER_AND_BOOK_FOUND);
        }
        if (!model.hasBook(toReturn.getBookName())) {
            throw new CommandException(NO_BOOK_FOUND);
        }
        if (!model.hasReader(toReturn.getReaderName())) {
            throw new CommandException(NO_READER_FOUND);
        }
        if (!model.hasRecord(toReturn)) {
            return new CommandResult(String.format(MESSAGE_NO_SUCH_RECORD_FOUND, toReturn));
        }

        model.markRecordAsReturned(toReturn);
        boolean editStatusResult = model.returnBook(toReturn.getReaderName(), toReturn.getBookName());
        if (!editStatusResult) {
            throw new CommandException(UNABLE_TO_UPDATE_CODEBASE);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toReturn));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReturnCommand // instanceof handles nulls
                && toReturn.equals(((ReturnCommand) other).toReturn));
    }
}
