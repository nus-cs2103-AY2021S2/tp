package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Random;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;

/**
 * Adds a book to the registered book base.
 */
public class AddBookCommand extends Command {

    public static final String COMMAND_WORD = "addbook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a book to SmartLib's booklist.\n"
            + "Parameters: "
            + PREFIX_BOOK + "BOOKNAME "
            + PREFIX_AUTHOR + "AUTHOR "
            + PREFIX_PUBLISHER + "PUBLISHER "
            + PREFIX_ISBN + "ISBN "
            + PREFIX_GENRE + "GENRE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOK + "Great Expectations "
            + PREFIX_AUTHOR + "Charles Dickens "
            + PREFIX_PUBLISHER + "Chapman Hall "
            + PREFIX_ISBN + "9780194241748 "
            + PREFIX_GENRE + "Novel ";

    public static final String MESSAGE_SUCCESS = "New book added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the book base.";
    public static final String MESSAGE_DUPLICATE_ISBN = "The ISBN you entered exists in the book base but it has "
            + "a different book name. Please ensure that you have keyed in the correct book name and ISBN.";
    private final Book bookWithTempBarcode;

    /**
     * Creates an AddBookCommand to add the specified book.
     *
     * @param bookWithTempBarcode the book to be added to SmartLib's book base.
     */
    public AddBookCommand(Book bookWithTempBarcode) {
        requireNonNull(bookWithTempBarcode);
        this.bookWithTempBarcode = bookWithTempBarcode;
    }

    /**
     * Verifies information of the new book to add, especially its isbn.
     *
     * @param model {@code Model} checks for matching isbn of books with same title.
     * @throws CommandException if books of the same title have different ISBN.
     */
    private void verifyBookInfo(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasBook(bookWithTempBarcode)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }

        if (model.hasBook(bookWithTempBarcode.getIsbn())) {
            ArrayList<Book> booksWithIsbn = model.getBooksByIsbn(bookWithTempBarcode.getIsbn());
            Name bookName = bookWithTempBarcode.getName();
            for (Book b : booksWithIsbn) {
                if (!b.getName().equals(bookName)) {
                    throw new CommandException(MESSAGE_DUPLICATE_ISBN);
                }
            }
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
        requireNonNull(model);
        verifyBookInfo(model);

        Barcode barcode = generateBarcode(model);
        Book book = new Book(bookWithTempBarcode.getName(), bookWithTempBarcode.getAuthor(),
                bookWithTempBarcode.getPublisher(), bookWithTempBarcode.getIsbn(),
                barcode, bookWithTempBarcode.getGenre());
        model.addBook(book);
        return new CommandResult(String.format(MESSAGE_SUCCESS, book));
    }

    /**
     * Generates an barcode to to distinguish between each book.
     *
     * @param model to check if the newly generated barcode has already be taken up by some book in SmartLib.
     */
    private Barcode generateBarcode(Model model) {
        Random random = new Random();
        int rv = random.nextInt(Barcode.MAX_VALUE - Barcode.MIN_VALUE) + Barcode.MIN_VALUE;
        while (model.hasBookWithBarcode(new Barcode(rv))) {
            rv = random.nextInt(Barcode.MAX_VALUE - Barcode.MIN_VALUE) + Barcode.MIN_VALUE;
        }
        return new Barcode(rv);
    }

    /**
     * Checks if this AddBookCommand is equal to another AddBookCommand.
     *
     * @param other the other AddBookCommand to be compared.
     * @return true if this AddBookCommand is equal to the other AddBookCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookCommand // instanceof handles nulls
                && bookWithTempBarcode.equals(((AddBookCommand) other).bookWithTempBarcode));
    }

}
