package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.flashcard.PriorityContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards in FlashBack with priority containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPriorityCommand extends FindCommand {
    private final PriorityContainsKeywordsPredicate predicate;

    public FindPriorityCommand(PriorityContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindPriorityCommand // instanceof handles nulls
                && predicate.equals(((FindPriorityCommand) other).predicate)); // state check
    }
}
