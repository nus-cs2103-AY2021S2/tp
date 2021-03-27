package seedu.smartlib.logic.commands;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;

import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.record.IncompleteRecord;
import seedu.smartlib.model.record.Record;

/**
 * Marks the record indicating that a reader returning a book
 */
public class ReturnCommand extends Command {

    public static final String COMMAND_WORD = "return";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns the book borrowed by the reader.\n"
            + "Parameters: " + PREFIX_BOOK + "<book name> " + PREFIX_READER + "<reader name>\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BOOK + "The Hobbit " + PREFIX_READER + "Alex Yeoh";
    public static final String MESSAGE_SUCCESS = "Record marked as returned.";
    public static final String MESSAGE_NO_SUCH_RECORD_FOUND =
            "No such record found. The book has either been returned, or was never borrowed by the reader.";
    public static final String NO_READER_AND_BOOK_FOUND = "Sorry, we were unable to find "
            + "neither the book nor the reader which you have specified. Please check if you have spelled correctly.";
    public static final String NO_BOOK_FOUND = "Sorry, we could not find the "
            + "book which you have specified. Please check if you have spelled correctly.";
    public static final String NO_READER_FOUND = "Sorry, we could not find the "
            + "reader which you have specified. Please check if you have spelled correctly.";
    public static final String UNABLE_TO_UPDATE_CODEBASE = "Sorry, an error has occurred with the codebase and we are"
            + " unable to update it.";

    private final IncompleteRecord incompleteRecord;

    /**
     * Creates a ReturnCommand to add a record.
     *
     * @param incompleteRecord record to be added to the Storage
     */
    public ReturnCommand(IncompleteRecord incompleteRecord) {
        requireAllNonNull(incompleteRecord);
        this.incompleteRecord = incompleteRecord;
    }

    private void verifyNameRegistration(Model model) throws CommandException {
        if (!model.hasBook(incompleteRecord.getBookName()) && !model.hasReader(incompleteRecord.getReaderName())) {
            throw new CommandException(NO_READER_AND_BOOK_FOUND);
        }

        if (!model.hasBook(incompleteRecord.getBookName())) {
            throw new CommandException(NO_BOOK_FOUND);
        }

        if (!model.hasReader(incompleteRecord.getReaderName())) {
            throw new CommandException(NO_READER_FOUND);
        }
    }

    private Record createProperRecord(Model model) {
        Barcode bookBarcode = model.getBookBarcodeForReturn(incompleteRecord.getBookName(),
                incompleteRecord.getReaderName());
        return new Record(bookBarcode, incompleteRecord.getReaderName(), incompleteRecord.getDateBorrowed());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        verifyNameRegistration(model);
        Record properRecord = createProperRecord(model);

        if (!model.hasRecord(properRecord)) {
            return new CommandResult(String.format(MESSAGE_NO_SUCH_RECORD_FOUND, properRecord));
        }

        model.markRecordAsReturned(properRecord);

        boolean editStatusResult = model.returnBook(properRecord.getReaderName(), properRecord.getBookBarcode());
        if (!editStatusResult) {
            throw new CommandException(UNABLE_TO_UPDATE_CODEBASE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, properRecord));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReturnCommand // instanceof handles nulls
                && incompleteRecord.equals(((ReturnCommand) other).incompleteRecord));
    }

}
