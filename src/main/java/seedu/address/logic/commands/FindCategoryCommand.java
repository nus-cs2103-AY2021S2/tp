package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.flashcard.CategoryContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards in FlashBack with category containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCategoryCommand extends FindCommand {
    private final CategoryContainsKeywordsPredicate predicate;

    public FindCategoryCommand(CategoryContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindCategoryCommand // instanceof handles nulls
                && predicate.equals(((FindCategoryCommand) other).predicate)); // state check
    }
}
