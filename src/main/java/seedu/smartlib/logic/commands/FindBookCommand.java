package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.book.BookNameContainsKeywordsPredicate;

/**
 * Finds and lists all books in SmartLib whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBookCommand extends Command {

    public static final String COMMAND_WORD = "findbook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all books whose information "
            + "(title, author, publisher, ISBN, or genre) contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "To find a book with the specified keyword(s) in its title , "
            + "use \"findbook KEYWORD(S)_IN_TITLE\".\n"
            + "To find a book with the specified keyword(s) in its labels(author, publisher, isbn, genre), "
            + "use \"findbook KEYWORD(S)_IN_LABEL\".\n"
            + "Note that the current version of our app does not support the searching of "
            + "books with titles and labels, or different types of labels within the same command.\n"
            + "Example (search by titles): " + COMMAND_WORD + " Harry Potter\n"
            + "Example (search by labels): " + COMMAND_WORD + " Fantasy";

    private final BookNameContainsKeywordsPredicate predicate;

    /**
     * Creates an FindBookCommand to find the specified book(s).
     *
     * @param predicate a Predicate used to find the specified book(s).
     */
    public FindBookCommand(BookNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    /**
     * Checks if this FindBookCommand is equal to another FindBookCommand.
     *
     * @param other the other FindBookCommand to be compared.
     * @return true if this FindBookCommand is equal to the other FindBookCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookCommand // instanceof handles nulls
                && predicate.equals(((FindBookCommand) other).predicate)); // state check
    }

}
