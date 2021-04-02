package seedu.smartlib.logic.commands;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BARCODE;
import static seedu.smartlib.model.SmartLib.HOURS_BORROW_ALLOWED;

import java.time.Duration;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.record.Cost;
import seedu.smartlib.model.record.IncompleteRecord;
import seedu.smartlib.model.record.Record;


/**
 * Updates a record in the record base to indicate that a reader has returned his or her book.
 */
public class ReturnCommand extends Command {

    public static final String COMMAND_WORD = "return";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns the book with the corresponding barcode.\n"
            + "Parameters: " + PREFIX_BARCODE + "<barcode>\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BARCODE + "1016170563";
    public static final String MESSAGE_SUCCESS = "Record marked as returned.\n";
    public static final String MESSAGE_COST = "The total cost is $%.2f.";
    public static final String MESSAGE_TOTAL_HOURS_DUE = "The book is overdue by %d hours.\n";
    public static final String MESSAGE_NO_SUCH_RECORD_FOUND =
            "No such record found. The book has either been returned, or has never been borrowed.";
    public static final String NO_BOOK_FOUND = "Sorry, we can't find the "
            + "book which you have specified. Please check if you have keyed in the correct barcode.";
    public static final String NO_READER_FOUND = "Sorry, the reader who borrowed this book "
            + "is not in our registered reader list";
    public static final String NO_READER_AND_BOOK_FOUND = "Sorry, we are unable to find "
            + "the book via the barcode. Please check if you have keyed in the correct barcode.";
    public static final String UNABLE_TO_UPDATE_CODEBASE = "Sorry, an error has occurred with the codebase and we are"
            + " unable to update it.";

    private final IncompleteRecord incompleteRecord;

    /**
     * Creates a ReturnCommand to add a record.
     *
     * @param incompleteRecord record to be added to the Storage.
     */
    public ReturnCommand(IncompleteRecord incompleteRecord) {
        requireAllNonNull(incompleteRecord);
        this.incompleteRecord = incompleteRecord;
    }

    private void verifyRegistration(Model model) throws CommandException {
        if (!model.hasBookWithBarcode(incompleteRecord.getBookBarcode())) {
            throw new CommandException(NO_BOOK_FOUND);
        }

        if (!model.hasReader(model.getReaderNameForReturn(incompleteRecord.getBookBarcode()))) {
            throw new CommandException(NO_READER_FOUND);
        }

        if (!model.hasBookWithBarcode(incompleteRecord.getBookBarcode())
                && !model.hasReader(model.getReaderNameForReturn(incompleteRecord.getBookBarcode()))) {
            throw new CommandException(NO_READER_AND_BOOK_FOUND);
        }

        if (!model.isBookWithBarcodeBorrowed(incompleteRecord.getBookBarcode())) {
            throw new CommandException(MESSAGE_NO_SUCH_RECORD_FOUND);
        }
    }

    private Record createProperRecord(Model model) {
        Name bookName = model.getBookNameForReturn(incompleteRecord.getBookBarcode());
        Name readerName = model.getReaderNameForReturn(incompleteRecord.getBookBarcode());
        return new Record(bookName, incompleteRecord.getBookBarcode(),
                readerName, incompleteRecord.getDateReturned());
    }

    private boolean isOverdue(Record r) {
        Duration duration = r.getBorrowDuration();

        return ((int) duration.toHours()) > HOURS_BORROW_ALLOWED;
    }

    private String getSuccessMessage(Record r) {
        if (isOverdue(r)) {
            Duration duration = r.getBorrowDuration();
            int overdueHours = ((int) duration.toHours()) - HOURS_BORROW_ALLOWED;
            Cost cost = new Cost(overdueHours);
            return MESSAGE_SUCCESS
                    + String.format(MESSAGE_TOTAL_HOURS_DUE, overdueHours)
                    + String.format(MESSAGE_COST, cost.getCost());
        } else {
            return MESSAGE_SUCCESS;
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        verifyRegistration(model);
        Record properRecord = createProperRecord(model);

        if (!model.hasRecord(properRecord)) {
            return new CommandResult(String.format(MESSAGE_NO_SUCH_RECORD_FOUND, properRecord));
        }

        Record completeRecord = model.markRecordAsReturned(properRecord);

        boolean editStatusResult = model.returnBook(properRecord.getReaderName(), properRecord.getBookBarcode());
        if (!editStatusResult) {
            throw new CommandException(UNABLE_TO_UPDATE_CODEBASE);
        }

        return new CommandResult(String.format(getSuccessMessage(completeRecord), properRecord));
    }

    /**
     * Checks if this ReturnCommand is equal to another ReturnCommand.
     *
     * @param other the other ReturnCommand to be compared.
     * @return true if this ReturnCommand is equal to the other ReturnCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReturnCommand // instanceof handles nulls
                && incompleteRecord.equals(((ReturnCommand) other).incompleteRecord));
    }

}
