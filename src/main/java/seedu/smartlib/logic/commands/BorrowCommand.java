package seedu.smartlib.logic.commands;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;

import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.record.Record;

/**
 * Adds a record indicating that a reader borrowing a book
 */
public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record of a reader borrowing a book. "
            + "Note that a book cannot be borrowed to multiple readers at the same time.\n"
            + "Parameters: " + PREFIX_BOOK + "<book name> " + PREFIX_READER + "<reader name>\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BOOK + "The Hobbit " + PREFIX_READER + "Alex Yeoh";
    public static final String MESSAGE_SUCCESS = "New borrowing record added.";
    public static final String MESSAGE_DUPLICATE_RECORD = "This record already exists in the registered record base";
    public static final String NO_READER_AND_BOOK_FOUND = "Sorry, we could find "
            + "neither the book nor the reader you specified. Please check if you have spelled correctly.";
    public static final String NO_BOOK_FOUND = "Sorry, we could not find the "
            + "book you specified. Please check if you have spelled correctly.";
    public static final String NO_READER_FOUND = "Sorry, we could not find the "
            + "reader you specified. Please check if you have spelled correctly.";
    public static final String BOOK_ALREADY_BORROWED = "Sorry, the book is already borrowed by someone.";
    public static final String READER_DISABLE_BORROWING = "Sorry, the reader has either borrowed all quota of books"
            + " or has overdue books unreturned.";
    public static final String UNABLE_TO_UPDATE_CODEBASE = "Sorry, an error occured with codebase and we are"
            + "not able to update it.";

    private final Record toAdd;

    /**
     * Creates a BorrowCommand to add a record
     * @param record recordToAdd
     */
    public BorrowCommand(Record record) {
        requireAllNonNull(record);
        toAdd = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        if (model.hasRecord(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }
        if (!model.hasBook(toAdd.getBookName()) && !model.hasReader(toAdd.getReaderName())) {
            throw new CommandException(NO_READER_AND_BOOK_FOUND);
        }
        if (!model.hasBook(toAdd.getBookName())) {
            throw new CommandException(NO_BOOK_FOUND);
        }
        if (!model.hasReader(toAdd.getReaderName())) {
            throw new CommandException(NO_READER_FOUND);
        }

        if (model.isBookBorrowed(toAdd.getBookName())) {
            throw new CommandException(BOOK_ALREADY_BORROWED);
        }
        if (!model.canReaderBorrow(toAdd.getReaderName())) {
            throw new CommandException(READER_DISABLE_BORROWING);
        }

        model.addRecord(toAdd);
        boolean editStatusResult = model.borrowBook(toAdd.getReaderName(), toAdd.getBookName());
        if (!editStatusResult) {
            throw new CommandException(UNABLE_TO_UPDATE_CODEBASE);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BorrowCommand // instanceof handles nulls
                && toAdd.equals(((BorrowCommand) other).toAdd));
    }
}
