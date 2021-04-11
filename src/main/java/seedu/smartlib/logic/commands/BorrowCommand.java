package seedu.smartlib.logic.commands;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BARCODE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.record.IncompleteRecord;
import seedu.smartlib.model.record.Record;

/**
 * Adds a record indicating that a reader borrowing a book.
 */
public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record of a reader borrowing a book.\n"
            + "Note that a book cannot be borrowed to multiple readers at the same time.\n"
            + "Note that readers may only borrow up to " + SmartLib.QUOTA + " books.\n"
            + "Parameters: " + PREFIX_BARCODE + "BARCODE " + PREFIX_READER + "READER_NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BARCODE + "1999999999 " + PREFIX_READER + "Charlotte";
    public static final String MESSAGE_SUCCESS = "New borrowing record added.";
    public static final String MESSAGE_DUPLICATE_RECORD = "This record already exists in the registered record base.";
    public static final String NO_READER_AND_BOOK_FOUND = "Sorry, we were unable to find "
            + "neither the book nor the reader which you have specified. Please check if you have keyed in correctly.";
    public static final String NO_BOOK_FOUND = "Sorry, we could not find the "
            + "book which you have specified. Please check if you have entered the correct barcode.";
    public static final String NO_READER_FOUND = "Sorry, we could not find the "
            + "reader which you have specified. Please check if you have entered the name correctly.";
    public static final String BOOK_ALREADY_BORROWED = "Sorry, the book is already borrowed.";
    public static final String READER_DISABLE_BORROWING = "Sorry, the reader has either reached the quota of books"
            + " that he/she can borrow, or is holding on to an overdue book.";
    public static final String UNABLE_TO_UPDATE_CODEBASE = "Sorry, an error has occurred with the codebase and we are"
            + " unable to update it.";
    public static final String NO_AVAILABLE_BOOKS = "Sorry, all copies of the book are loaned out.\n"
            + "You can perform a search by book name to see the book's borrowing status.";

    private final IncompleteRecord incompleteRecord;

    /**
     * Creates a BorrowCommand to add a record.
     *
     * @param incompleteRecord the record to be added to the Storage.
     */
    public BorrowCommand(IncompleteRecord incompleteRecord) {
        requireAllNonNull(incompleteRecord);
        this.incompleteRecord = incompleteRecord;
    }

    /**
     * Checks the presence of input book barcode and reader name in SmartLib.
     * And throws exception if no such barcode or reader name is found in SmartLib.
     *
     * @param model {@code Model} which checks the presence of input barcode and reader name in SmartLib.
     * @throws CommandException if either the reader name or the barcode, or both are not present in SmartLib.
     */
    private void verifyNameRegistration(Model model) throws CommandException {
        requireAllNonNull(model);

        if (!model.hasBookWithBarcode(incompleteRecord.getBookBarcode())
                && !model.hasReader(incompleteRecord.getReaderName())) {
            throw new CommandException(NO_READER_AND_BOOK_FOUND);
        }

        if (!model.hasBookWithBarcode(incompleteRecord.getBookBarcode())) {
            throw new CommandException(NO_BOOK_FOUND);
        }

        if (!model.hasReader(incompleteRecord.getReaderName())) {
            throw new CommandException(NO_READER_FOUND);
        }
    }

    /**
     * Generates a complete borrow record about the relevant book and reader.
     *
     * @param model {@code Model} which returns the book with the given barcode.
     * @return a complete borrow record about the borrowed book and reader.
     */
    private Record createProperRecord(Model model) {
        Book book = model.getBookByBarcode(incompleteRecord.getBookBarcode());
        Name bookName = book.getName();
        return new Record(bookName, incompleteRecord.getBookBarcode(), incompleteRecord.getReaderName(),
                incompleteRecord.getDateBorrowed());
    }

    /**
     * Verifies the integrity of the complete borrow record.
     *
     * @param model {@code Model} checks for validity or duplicate record.
     * @param properRecord  a complete borrow record about the borrowed book and reader.
     * @throws CommandException if properRecord is duplicated, barcode is invalid, the book is already borrowed,
     * or the reader has reached borrow quota
     */
    private void verifyRecordIntegrity(Model model, Record properRecord) throws CommandException {
        requireAllNonNull(model, properRecord);


        if (model.hasRecord(properRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        if (properRecord.getBookBarcode() == null) {
            throw new CommandException(NO_AVAILABLE_BOOKS);
        }

        if (model.isBookWithBarcodeBorrowed(properRecord.getBookBarcode())) {
            throw new CommandException(BOOK_ALREADY_BORROWED);
        }

        if (!model.canReaderBorrow(properRecord.getReaderName())) {
            throw new CommandException(READER_DISABLE_BORROWING);
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

        verifyNameRegistration(model);
        Record properRecord = createProperRecord(model);
        verifyRecordIntegrity(model, properRecord);
        model.addRecord(properRecord);

        boolean editStatusResult = model.borrowBook(properRecord.getReaderName(), properRecord.getBookBarcode());
        if (!editStatusResult) {
            throw new CommandException(UNABLE_TO_UPDATE_CODEBASE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, properRecord));
    }

    /**
     * Checks if this BorrowCommand is equal to another BorrowCommand.
     *
     * @param other the other BorrowCommand to be compared.
     * @return true if this BorrowCommand is equal to the other BorrowCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BorrowCommand // instanceof handles nulls
                && incompleteRecord.equals(((BorrowCommand) other).incompleteRecord));
    }

}
