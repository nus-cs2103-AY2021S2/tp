package seedu.address.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code Priority} matches any of the keywords given.
 */
public class PriorityContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public PriorityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getPriority().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PriorityContainsKeywordsPredicate) other).keywords)); // state check
    }
}
