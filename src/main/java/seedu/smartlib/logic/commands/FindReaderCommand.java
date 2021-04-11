package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.reader.Reader;

/**
 * Finds and lists all readers in SmartLib whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindReaderCommand extends Command {

    public static final String COMMAND_WORD = "findreader";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all readers whose registered names/tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "To find a reader with his/her registered name, use \"findreader " + "NAME [MORE_NAME_INFO]\".\n"
            + "To find reader(s) with matching tags, use \"findreader " + PREFIX_TAG + "TAG [MORE_TAGS]\".\n"
            + "Note that the current version of our app does not support the searching of "
            + "readers with names and tags within the same command.\n"
            + "Example (search by names): " + COMMAND_WORD + " John Doe\n"
            + "Example (search by tags): " + COMMAND_WORD + " " + PREFIX_TAG + "VIP";

    private final Predicate<Reader> predicate;

    /**
     * Creates an FindReaderCommand to find the specified reader(s).
     *
     * @param predicate a Predicate used to find the specified reader(s).
     */
    public FindReaderCommand(Predicate<Reader> predicate) {
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
        model.updateFilteredReaderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_READERS_LISTED_OVERVIEW, model.getFilteredReaderList().size()));
    }

    /**
     * Checks if this FindReaderCommand is equal to another FindReaderCommand.
     *
     * @param other the other FindReaderCommand to be compared.
     * @return true if this FindReaderCommand is equal to the other FindReaderCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindReaderCommand // instanceof handles nulls
                && predicate.equals(((FindReaderCommand) other).predicate)); // state check
    }

}
