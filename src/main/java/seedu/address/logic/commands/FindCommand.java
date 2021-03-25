package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.flashcard.FlashcardContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards in FlashBack containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards "
            + "that contains any of the specified keywords (case-insensitive and not full word match) "
            + "in any of the fields (e.g. question, answer, category, priority, tags) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters:  KEYWORD [MORE_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " equation";

    private final FlashcardContainsKeywordsPredicate predicate;

    public FindCommand(FlashcardContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

}
