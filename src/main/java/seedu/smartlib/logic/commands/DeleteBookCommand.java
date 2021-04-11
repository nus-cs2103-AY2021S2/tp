package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.book.Book;

/**
 * Deletes a book identified using it's displayed index from SmartLib's registered book base.
 */
public class DeleteBookCommand extends Command {

    public static final String COMMAND_WORD = "deletebook";

    public static final String INVALID_COMMAND_FORMAT = "Invalid command format!\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the book identified by the index number used in the displayed book list.\n"
            + "Parameter: INDEX (must be a positive integer smaller than the size of your book list).\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted book: %1$s";
    public static final String MESSAGE_UNABLE_TO_DELETE_UNRETURNED = "The book specified cannot be"
            + " deleted because it is currently on loan.\n"
            + "Please return the book first, and then try to delete it again.";

    private final Index targetIndex;

    /**
     * Creates an DeleteBookCommand to delete the specified book.
     *
     * @param targetIndex index of the book to be deleted from SmartLib's book base.
     */
    public DeleteBookCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
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
        List<Book> lastShownList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(INVALID_COMMAND_FORMAT + MESSAGE_USAGE);
        }
        Book bookToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (bookToDelete.isBorrowed()) {
            throw new CommandException(MESSAGE_UNABLE_TO_DELETE_UNRETURNED);
        }
        model.deleteBook(bookToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete));
    }

    /**
     * Checks if this DeleteBookCommand is equal to another DeleteBookCommand.
     *
     * @param other the other DeleteBookCommand to be compared.
     * @return true if this DeleteBookCommand is equal to the other DeleteBookCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBookCommand) other).targetIndex)); // state check
    }

}

