package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.SortOptions;

/**
 * Sorts the flashcards according to a criterion.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts and lists flashcards according to option given by user.\n"
            + "User is allowed to sort by question in lexicographical order and priority.\n"
            + "Parameters: <question|priority> <-a|-d>\n"
            + "Example: " + COMMAND_WORD + " priority " + "-d";

    public static final String MESSAGE_SORTED_QUESTION_ASCENDING = "Sorted flashcards by question "
            + "in ascending order!";
    public static final String MESSAGE_SORTED_QUESTION_DESCENDING = "Sorted flashcards by question "
            + "in descending order!";
    public static final String MESSAGE_SORTED_PRIORITY_ASCENDING = "Sorted flashcards by priority "
            + "ascending order!";
    public static final String MESSAGE_SORTED_PRIORITY_DESCENDING = "Sorted flashcards by priority "
            + "descending order!";
    public static final String MESSAGE_SORTED_INVALID = "Flashcards could not be sorted!";

    private final SortOptions option;

    public SortCommand(SortOptions option) {
        this.option = option;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredFlashcardList(option);
        switch(option) {
        case PRIORITY_ASCENDING:
            return new CommandResult(MESSAGE_SORTED_PRIORITY_ASCENDING);
        case PRIORITY_DESCENDING:
            return new CommandResult(MESSAGE_SORTED_PRIORITY_DESCENDING);
        case QUESTION_ASCENDING:
            return new CommandResult(MESSAGE_SORTED_QUESTION_ASCENDING);
        case QUESTION_DESCENDING:
            return new CommandResult(MESSAGE_SORTED_QUESTION_DESCENDING);
        default:
            return new CommandResult(MESSAGE_SORTED_INVALID);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortCommand
                && option.equals(((SortCommand) other).option));
    }

}
